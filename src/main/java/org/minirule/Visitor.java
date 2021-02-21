package org.minirule;

import java.util.HashMap;

import org.minirule.MiniRuleParser.FcallContext;
import org.minirule.grammar.Calculater;
import org.minirule.grammar.Compare;
import org.minirule.grammar.FailException;
import org.minirule.grammar.FuncCallException;
import org.minirule.grammar.IVar;
import org.minirule.grammar.Method;
import org.minirule.grammar.RuleError;
import org.minirule.grammar.RuleException;
import org.minirule.grammar.Tran;
import org.minirule.grammar.TranRunner;
import org.minirule.grammar.TryAgainException;
import org.minirule.grammar.Var;
import org.minirule.grammar.VarError;

public class Visitor extends MiniRuleBaseVisitor<IVar>{
	public HashMap<String, Object> context;
	private HashMap<String, IVar> locals;
	private MiniRule engine;
	
	public Visitor(MiniRule engine) {
		this.locals = new HashMap<String, IVar>();
		this.context = new HashMap<String, Object>();
		this.engine = engine;
	}

	/**
	 * {@inheritDoc}
	 *
	 *  action block		#actCondition
	 */
	@Override public IVar visitActCondition(MiniRuleParser.ActConditionContext ctx) { 
		IVar res = ctx.action().accept(this);
		if (res.getType() == VarError.class) {
			return res;
		}
        return ctx.block().accept(this); 
    }
	/**
	 * {@inheritDoc}
	 *
	 *  '{' (ifcmp block) + ('else' block)? '}' #blockIfcmp //条件
	 */
	@Override public IVar visitBlockIfcmp(MiniRuleParser.BlockIfcmpContext ctx) { 
		// handle 'if' condition
		int i = 0;
		for (; i < ctx.ifcmp().size(); i ++) {
			IVar ifcmp = ctx.ifcmp(i).accept(this);
			// when some error accurs, return error
			if (ifcmp.getType() == VarError.class) {
				return ifcmp;
			}
			// pick the right block
			if (ifcmp.getBoolean() == true) {
				return ctx.block(i).accept(this);
			}
		}
		// handle 'else' condition
		if (ctx.block().size() > ctx.ifcmp().size()) {
			return ctx.block(i).accept(this);
		} else {
			return Var.New(null);
		}
	}
	/**
	 * {@inheritDoc}
	 *
	 * '{' (action block) ? '}' 	 		   #blockAction    //动作
	 */
	@Override public IVar visitBlockAction(MiniRuleParser.BlockActionContext ctx) { 
		if (ctx.action() == null) {
			return Var.New(null);
		}
		IVar res = ctx.action().accept(this);
		if (res.getType() == VarError.class) {
			return res;
		}
		return ctx.block().accept(this);
	}
	/**
	 * {@inheritDoc}
	 *
	 * '{' 'return' '{' (Str ':' param)+(',' Str ':' param)? '}' '}' 	 		   #blockReturn    //返回
	 */
	@Override public IVar visitBlockReturn(MiniRuleParser.BlockReturnContext ctx) {
		HashMap<String, Object> m = new HashMap<String, Object>();
		for (int i = 0; i < ctx.Str().size(); i ++) {
			IVar field = ctx.param(i).accept(this);
			if (field.getType() == VarError.class) {
				return field;
			}
			//trim "\"" and "'"
			String f = ctx.Str(i).getText();
			m.put(f.substring(1, f.length()-1), field.getValue());
		}
		return Var.New(m);
	}
	/**
	 * {@inheritDoc}
	 *
	 * fcall (',' fcall) ?  #fcallSerial    //串行执行
	 */
	@Override public IVar visitFcallSerial(MiniRuleParser.FcallSerialContext ctx) {
		for (int i = 0; i < ctx.fcall().size(); i ++) {
			IVar res = ctx.fcall(i).accept(this);
			if (res.getType() == VarError.class) {
				return res;
			}
		}
		return Var.New(null); 
	}
	/**
	 * {@inheritDoc}
	 *
	 * fcall ('|' fcall) ?  #fcallParallel  //并行执行
	 * @todo
	 */
	@Override public IVar visitFcallParallel(MiniRuleParser.FcallParallelContext ctx) { 
		return visitChildren(ctx); 
	}
	/**
	 * {@inheritDoc}
	 *
	 * 'Tran' ':' fcall (',' fcall) + #tranSerial    //串行事务(至少有两个fcall)
	 */
	@Override public IVar visitTranSerial(MiniRuleParser.TranSerialContext ctx) {
		//获取所有事务
		int tranNum = ctx.fcall().size();
		Tran[] trans = new Tran[tranNum];
		for (int i = 0; i < trans.length; i ++) {
			Tran t = engine.tran(ctx.fcall(i).ID(0).getText());
			if (t == null) {
				return Var.New(new RuleException(RuleError.FuncNotFound));
			}
			t.setContext(context);
			trans[i] = t;
		}
		//进行第一阶段提交
		TranRunner tr = new TranRunner(trans);
		for (int i = 0; i < trans.length; i ++) {
			FcallContext fc = ctx.fcall(i);
			IVar[] args = new IVar[fc.param().size()];
			// 先计算所有参数
			for (int j = 0; j < fc.param().size(); j ++) {
				IVar arg = fc.param(j).accept(this);
				if (arg.getType() == VarError.class) {
					return arg;
				}
				args[j] = arg;
			}
			// 调用prepare
			try {
				this.setLocal(getVarName(ctx.fcall(i)), tr.prepare(i, args));
			} catch (TryAgainException e) {
				return Var.New(new RuleException(RuleError.TryAgain));
			} catch (FailException e) {
				// TODO 错误码整理
				return Var.New(new RuleException(RuleError.TranFailed));
			} catch (RuleException e) {
				return Var.New(e);
			}
		}
		//进行第二阶段提交
		try {
			tr.commit();
			return Var.New(true);
		} catch (TryAgainException e) {
			return Var.New(new RuleException(RuleError.TryAgain));
		} catch (RuleException e) {
			return Var.New(e);
		}
	}
	private String getVarName(FcallContext ctx) {
		String varName = ctx.ID(0).getText();
		if (ctx.ID().size() > 1) {
			varName = ctx.ID(1).getText();
		}
		return varName;
	}
	/**
	 * {@inheritDoc}
	 *
	 * 'Tran' ':' fcall ('|' fcall) + #tranParallel  //并行事务(至少有两个fcall)
	 */
	@Override public IVar visitTranParallel(MiniRuleParser.TranParallelContext ctx) { return visitChildren(ctx); }
	/**
	 * {@inheritDoc}
	 *
	 * ID '(' ( param (',' param) * )? ')' ('as' ID)?
	 */
	@Override public IVar visitFcall(MiniRuleParser.FcallContext ctx) { 
		String varName = this.getVarName(ctx);
		String funcName = ctx.ID(0).getText();
		Method f = engine.method(funcName);
		if (f == null) {
			return Var.New(new RuleException(RuleError.FuncNotFound));
		}
		f.setContext(context);
		IVar[] args = new IVar[ctx.param().size()];
		for (int i = 0; i < ctx.param().size(); i ++) {
			IVar arg = ctx.param(i).accept(this);
			if (arg.getType() == VarError.class) {
				return arg;
			}
			args[i] = arg;
		}
		try {
			IVar res = f.call(args);
			if (res.getType() == VarError.class) {
				return res;
			}
			this.setLocal(varName, res);
			return res;
		} catch (FuncCallException e) {
			return Var.New(e);
		} catch (RuleException e) {
			return Var.New(e);
		}
	}

	private void setLocal(String varName, IVar var) {
		this.locals.put(varName, var);
	}

	private IVar getLocal(String varName) {
		IVar v = this.locals.get(varName);
		if (v == null) {
			return Var.New(null);
		} else {
			return v;
		}
	}

	/**
	 * {@inheritDoc}
	 *
	 * '!' ifcmp             #ifcmpNot
	 */
	@Override public IVar visitIfcmpNot(MiniRuleParser.IfcmpNotContext ctx) { 
		IVar ifcmp = ctx.ifcmp().accept(this);
		if (ifcmp.getType() == VarError.class) {
			return ifcmp;
		} 
		return Var.New(!ifcmp.getBoolean());
	}
	/**
	 * {@inheritDoc}
	 *
	 * True  				#ifcmpTrue
	 */
	@Override public IVar visitIfcmpTrue(MiniRuleParser.IfcmpTrueContext ctx) { return Var.New(true); }
	/**
	 * {@inheritDoc}
	 *
	 * ifcmp '&&' ifcmp      #ifcmpAnd
	 */
	@Override public IVar visitIfcmpAnd(MiniRuleParser.IfcmpAndContext ctx) { 
		IVar ifcmp = ctx.ifcmp(0).accept(this);
		if (ifcmp.getType() == VarError.class) {
			return ifcmp;
		}
		if (ifcmp.getBoolean() == false) {
			return ifcmp;
		}
		return ctx.ifcmp(1).accept(this);
	}
	/**
	 * {@inheritDoc}
	 *
	 * '(' ifcmp ')'         #ifcmpAgain
	 */
	@Override public IVar visitIfcmpAgain(MiniRuleParser.IfcmpAgainContext ctx) { return ctx.ifcmp().accept(this); }
	/**
	 * {@inheritDoc}
	 *
	 * param op=('>'|'>='|'<'|'<='|'!='|'==') param   #ifcmpOp
	 */
	@Override public IVar visitIfcmpOp(MiniRuleParser.IfcmpOpContext ctx) { 
		IVar left = ctx.param(0).accept(this);
		if (left.getType() == VarError.class) {
			return left;
		}
		IVar right = ctx.param(1).accept(this);
		if (right.getType() == VarError.class) {
			return right;
		}
		Compare c = new Compare();
		try {
			switch (ctx.op.getText()) {
				case ">":
					return c.gt(left, right);
				case ">=":
					return c.gte(left, right);
				case "<":
					return c.st(left, right);
				case "<=":
					return c.ste(left, right);
				case "!=":
					return c.neq(left, right);
				case "==":
					return c.eq(left, right);
				default:
					return Var.New(new RuleException(RuleError.TypeUnknown, "unknown op:" + ctx.op.getText()));
			}
		} catch (RuleException e) {
			return Var.New(e);
		}
	}
	/**
	 * {@inheritDoc}
	 *
	 * False 				#ifcmpFalse
	 */
	@Override public IVar visitIfcmpFalse(MiniRuleParser.IfcmpFalseContext ctx) { return Var.New(false); }
	/**
	 * {@inheritDoc}
	 *
	 * ifcmp '||' ifcmp      #ifcmpOr
	 */
	@Override public IVar visitIfcmpOr(MiniRuleParser.IfcmpOrContext ctx) { 
		IVar ifcmp = ctx.ifcmp(0).accept(this);
		if (ifcmp.getType() == VarError.class) {
			return ifcmp;
		}
		if (ifcmp.getBoolean() == true) {
			return ifcmp;
		}
		return ctx.ifcmp(1).accept(this);
	 }
	/**
	 * {@inheritDoc}
	 *
	 * variable		       			#mathcalVariable
	 */
	@Override public IVar visitMathcalVariable(MiniRuleParser.MathcalVariableContext ctx) { return ctx.variable().accept(this); }
	/**
	 * {@inheritDoc}
	 *
	 * mathcal op=('*'|'/') mathcal     #mathcalMulDiv
	 */
	@Override public IVar visitMathcalMulDiv(MiniRuleParser.MathcalMulDivContext ctx) { 
		IVar left = ctx.mathcal(0).accept(this);
		if (left.getType() == VarError.class) {
			return left;
		}
		IVar right = ctx.mathcal(1).accept(this);
		if (right.getType() == VarError.class) {
			return right;
		}
		Calculater c = new Calculater();
		try {
			switch (ctx.op.getText()) {
				case "*":
					return c.mul(left, right);
				case "/":
					return c.div(left, right);
				default:
					return Var.New(new RuleException(RuleError.TypeUnknown, "unknown op:" + ctx.op.getText()));
			}
		} catch (RuleException e) {
			return Var.New(e);
		}
	}
	/**
	 * {@inheritDoc}
	 *
	 * mathcal op=('+'|'-') mathcal     #mathcalAddDec
	 */
	@Override public IVar visitMathcalAddDec(MiniRuleParser.MathcalAddDecContext ctx) {
		IVar left = ctx.mathcal(0).accept(this);
		if (left.getType() == VarError.class) {
			return left;
		}
		IVar right = ctx.mathcal(1).accept(this);
		if (right.getType() == VarError.class) {
			return right;
		}
		Calculater c = new Calculater();
		try {
			switch (ctx.op.getText()) {
				case "+":
					return c.add(left, right);
				case "-":
					return c.dec(left, right);
				default:
					return Var.New(new RuleException(RuleError.TypeUnknown, "unknown op:" + ctx.op.getText()));
			}
		} catch (RuleException e) {
			return Var.New(e);
		}
	}
	/**
	 * {@inheritDoc}
	 *
	 * Int			       			#mathcalInt
	 */
	@Override public IVar visitMathcalInt(MiniRuleParser.MathcalIntContext ctx) { 
		return Var.New(Integer.valueOf(ctx.Int().getText()));
	}
	/**
	 * {@inheritDoc}
	 *
	 * '(' mathcal ')'		    	#mathcalAgain
	 */
	@Override public IVar visitMathcalAgain(MiniRuleParser.MathcalAgainContext ctx) { return ctx.mathcal().accept(this); }
	/**
	 * {@inheritDoc}
	 *
	 * mathcal ('^') mathcal     	#mathcalMM
	 */
	@Override public IVar visitMathcalMM(MiniRuleParser.MathcalMMContext ctx) { 
		IVar left = ctx.mathcal(0).accept(this);
		if (left.getType() == VarError.class) {
			return left;
		}
		IVar right = ctx.mathcal(1).accept(this);
		if (right.getType() == VarError.class) {
			return right;
		}
		Calculater c = new Calculater();
		try {
			return c.pow(left, right);
		} catch (RuleException e) {
			return Var.New(e);
		}
	 }
	/**
	 * {@inheritDoc}
	 *
	 * Float		       				#mathcalFloat
	 */
	@Override public IVar visitMathcalFloat(MiniRuleParser.MathcalFloatContext ctx) { 
		return Var.New(Float.valueOf(ctx.Float().getText()));
	}
	/**
	 * {@inheritDoc}
	 *
	 * True     		#paramTrue
	 */
	@Override public IVar visitParamTrue(MiniRuleParser.ParamTrueContext ctx) { return Var.New(true); }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public IVar visitParamFalse(MiniRuleParser.ParamFalseContext ctx) { return Var.New(false); }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public IVar visitParamInt(MiniRuleParser.ParamIntContext ctx) { 
		return Var.New(Integer.valueOf(ctx.Int().getText()));
	}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public IVar visitParamFloat(MiniRuleParser.ParamFloatContext ctx) { 
		return Var.New(Float.valueOf(ctx.Float().getText()));
	}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public IVar visitParamStr(MiniRuleParser.ParamStrContext ctx) { 
		String f = ctx.Str().getText();
		return Var.New(f.substring(1, f.length()-1));
	 }
	/**
	 * {@inheritDoc}
	 *
	 * variable   	#paramVariable
	 */
	@Override public IVar visitParamVariable(MiniRuleParser.ParamVariableContext ctx) { return ctx.variable().accept(this); }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public IVar visitParamMathcal(MiniRuleParser.ParamMathcalContext ctx) { return ctx.mathcal().accept(this); }
	/**
	 * {@inheritDoc}
	 *
	 * variable '[' Int ']' 		#variableList
	 */
	@Override public IVar visitVariableList(MiniRuleParser.VariableListContext ctx) { 
		try {
			IVar var = ctx.variable().accept(this);
			if (var.getType() == VarError.class) {
				return var;
			}
			return var.getElement(Integer.valueOf(ctx.Int().getText()));
		} catch(RuleException e) {
			return Var.New(e);
		}
	}
	/**
	 * {@inheritDoc}
	 *
	 * variable '.' ID      		#variableMap
	 */
	@Override public IVar visitVariableMap(MiniRuleParser.VariableMapContext ctx) { 
		try {
			IVar v = ctx.variable().accept(this);
			return v.getProperty(ctx.ID().getText());
		} catch(RuleException e) {
			return Var.New(e);
		}
	}
	/**
	 * {@inheritDoc}
	 *
	 * variable '[' variable ']' #variableVarList
	 */
	@Override public IVar visitVariableVarList(MiniRuleParser.VariableVarListContext ctx) { 
		IVar var = ctx.variable(0).accept(this);
		IVar field = ctx.variable(1).accept(this);
		try {
			return var.property(field);
		} catch(RuleException e) {
			return Var.New(e);
		}
	}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public IVar visitVariableID(MiniRuleParser.VariableIDContext ctx) { return this.getLocal(ctx.ID().getText()); }
}
