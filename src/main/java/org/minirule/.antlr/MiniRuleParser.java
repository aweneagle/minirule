// Generated from /Users/zhangaiwen/projects/java/minirule/src/main/java/org/minirule/MiniRule.g4 by ANTLR 4.8
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class MiniRuleParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.8", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, T__23=24, 
		T__24=25, T__25=26, T__26=27, T__27=28, True=29, False=30, ID=31, Str=32, 
		Int=33, Float=34, WS=35, ESC=36;
	public static final int
		RULE_actionblock = 0, RULE_block = 1, RULE_action = 2, RULE_fcall = 3, 
		RULE_ifcmp = 4, RULE_mathcal = 5, RULE_param = 6, RULE_variable = 7;
	private static String[] makeRuleNames() {
		return new String[] {
			"actionblock", "block", "action", "fcall", "ifcmp", "mathcal", "param", 
			"variable"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'{'", "'else'", "'}'", "'return'", "':'", "','", "'|'", "'Tran'", 
			"'('", "')'", "'as'", "'>'", "'>='", "'<'", "'<='", "'!='", "'=='", "'!'", 
			"'&&'", "'||'", "'^'", "'*'", "'/'", "'+'", "'-'", "'.'", "'['", "']'", 
			"'true'", "'false'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, "True", "False", "ID", "Str", "Int", "Float", 
			"WS", "ESC"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "MiniRule.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public MiniRuleParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class ActionblockContext extends ParserRuleContext {
		public ActionblockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_actionblock; }
	 
		public ActionblockContext() { }
		public void copyFrom(ActionblockContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class ActConditionContext extends ActionblockContext {
		public ActionContext action() {
			return getRuleContext(ActionContext.class,0);
		}
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public ActConditionContext(ActionblockContext ctx) { copyFrom(ctx); }
	}

	public final ActionblockContext actionblock() throws RecognitionException {
		ActionblockContext _localctx = new ActionblockContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_actionblock);
		try {
			_localctx = new ActConditionContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(16);
			action();
			setState(17);
			block();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BlockContext extends ParserRuleContext {
		public BlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_block; }
	 
		public BlockContext() { }
		public void copyFrom(BlockContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class BlockActionContext extends BlockContext {
		public ActionContext action() {
			return getRuleContext(ActionContext.class,0);
		}
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public BlockActionContext(BlockContext ctx) { copyFrom(ctx); }
	}
	public static class BlockReturnContext extends BlockContext {
		public List<TerminalNode> Str() { return getTokens(MiniRuleParser.Str); }
		public TerminalNode Str(int i) {
			return getToken(MiniRuleParser.Str, i);
		}
		public List<ParamContext> param() {
			return getRuleContexts(ParamContext.class);
		}
		public ParamContext param(int i) {
			return getRuleContext(ParamContext.class,i);
		}
		public BlockReturnContext(BlockContext ctx) { copyFrom(ctx); }
	}
	public static class BlockIfcmpContext extends BlockContext {
		public List<IfcmpContext> ifcmp() {
			return getRuleContexts(IfcmpContext.class);
		}
		public IfcmpContext ifcmp(int i) {
			return getRuleContext(IfcmpContext.class,i);
		}
		public List<BlockContext> block() {
			return getRuleContexts(BlockContext.class);
		}
		public BlockContext block(int i) {
			return getRuleContext(BlockContext.class,i);
		}
		public BlockIfcmpContext(BlockContext ctx) { copyFrom(ctx); }
	}

	public final BlockContext block() throws RecognitionException {
		BlockContext _localctx = new BlockContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_block);
		int _la;
		try {
			setState(59);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				_localctx = new BlockIfcmpContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(19);
				match(T__0);
				setState(23); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(20);
					ifcmp(0);
					setState(21);
					block();
					}
					}
					setState(25); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__8) | (1L << T__17) | (1L << True) | (1L << False) | (1L << ID) | (1L << Str) | (1L << Int) | (1L << Float))) != 0) );
				setState(29);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__1) {
					{
					setState(27);
					match(T__1);
					setState(28);
					block();
					}
				}

				setState(31);
				match(T__2);
				}
				break;
			case 2:
				_localctx = new BlockActionContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(33);
				match(T__0);
				setState(37);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__7 || _la==ID) {
					{
					setState(34);
					action();
					setState(35);
					block();
					}
				}

				setState(39);
				match(T__2);
				}
				break;
			case 3:
				_localctx = new BlockReturnContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(40);
				match(T__0);
				setState(41);
				match(T__3);
				setState(42);
				match(T__0);
				setState(46); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(43);
					match(Str);
					setState(44);
					match(T__4);
					setState(45);
					param();
					}
					}
					setState(48); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==Str );
				setState(54);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__5) {
					{
					setState(50);
					match(T__5);
					setState(51);
					match(Str);
					setState(52);
					match(T__4);
					setState(53);
					param();
					}
				}

				setState(56);
				match(T__2);
				setState(57);
				match(T__2);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ActionContext extends ParserRuleContext {
		public ActionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_action; }
	 
		public ActionContext() { }
		public void copyFrom(ActionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class FcallSerialContext extends ActionContext {
		public List<FcallContext> fcall() {
			return getRuleContexts(FcallContext.class);
		}
		public FcallContext fcall(int i) {
			return getRuleContext(FcallContext.class,i);
		}
		public FcallSerialContext(ActionContext ctx) { copyFrom(ctx); }
	}
	public static class TranSerialContext extends ActionContext {
		public List<FcallContext> fcall() {
			return getRuleContexts(FcallContext.class);
		}
		public FcallContext fcall(int i) {
			return getRuleContext(FcallContext.class,i);
		}
		public TranSerialContext(ActionContext ctx) { copyFrom(ctx); }
	}
	public static class TranParallelContext extends ActionContext {
		public List<FcallContext> fcall() {
			return getRuleContexts(FcallContext.class);
		}
		public FcallContext fcall(int i) {
			return getRuleContext(FcallContext.class,i);
		}
		public TranParallelContext(ActionContext ctx) { copyFrom(ctx); }
	}
	public static class FcallParallelContext extends ActionContext {
		public List<FcallContext> fcall() {
			return getRuleContexts(FcallContext.class);
		}
		public FcallContext fcall(int i) {
			return getRuleContext(FcallContext.class,i);
		}
		public FcallParallelContext(ActionContext ctx) { copyFrom(ctx); }
	}

	public final ActionContext action() throws RecognitionException {
		ActionContext _localctx = new ActionContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_action);
		int _la;
		try {
			setState(89);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
			case 1:
				_localctx = new FcallSerialContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(61);
				fcall();
				setState(64);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__5) {
					{
					setState(62);
					match(T__5);
					setState(63);
					fcall();
					}
				}

				}
				break;
			case 2:
				_localctx = new FcallParallelContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(66);
				fcall();
				setState(69);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__6) {
					{
					setState(67);
					match(T__6);
					setState(68);
					fcall();
					}
				}

				}
				break;
			case 3:
				_localctx = new TranSerialContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(71);
				match(T__7);
				setState(72);
				match(T__4);
				setState(73);
				fcall();
				setState(76); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(74);
					match(T__5);
					setState(75);
					fcall();
					}
					}
					setState(78); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==T__5 );
				}
				break;
			case 4:
				_localctx = new TranParallelContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(80);
				match(T__7);
				setState(81);
				match(T__4);
				setState(82);
				fcall();
				setState(85); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(83);
					match(T__6);
					setState(84);
					fcall();
					}
					}
					setState(87); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==T__6 );
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FcallContext extends ParserRuleContext {
		public List<TerminalNode> ID() { return getTokens(MiniRuleParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(MiniRuleParser.ID, i);
		}
		public List<ParamContext> param() {
			return getRuleContexts(ParamContext.class);
		}
		public ParamContext param(int i) {
			return getRuleContext(ParamContext.class,i);
		}
		public FcallContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fcall; }
	}

	public final FcallContext fcall() throws RecognitionException {
		FcallContext _localctx = new FcallContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_fcall);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(91);
			match(ID);
			setState(92);
			match(T__8);
			setState(101);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__8) | (1L << True) | (1L << False) | (1L << ID) | (1L << Str) | (1L << Int) | (1L << Float))) != 0)) {
				{
				setState(93);
				param();
				setState(98);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__5) {
					{
					{
					setState(94);
					match(T__5);
					setState(95);
					param();
					}
					}
					setState(100);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(103);
			match(T__9);
			setState(106);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__10) {
				{
				setState(104);
				match(T__10);
				setState(105);
				match(ID);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IfcmpContext extends ParserRuleContext {
		public IfcmpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ifcmp; }
	 
		public IfcmpContext() { }
		public void copyFrom(IfcmpContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class IfcmpNotContext extends IfcmpContext {
		public IfcmpContext ifcmp() {
			return getRuleContext(IfcmpContext.class,0);
		}
		public IfcmpNotContext(IfcmpContext ctx) { copyFrom(ctx); }
	}
	public static class IfcmpTrueContext extends IfcmpContext {
		public TerminalNode True() { return getToken(MiniRuleParser.True, 0); }
		public IfcmpTrueContext(IfcmpContext ctx) { copyFrom(ctx); }
	}
	public static class IfcmpAndContext extends IfcmpContext {
		public List<IfcmpContext> ifcmp() {
			return getRuleContexts(IfcmpContext.class);
		}
		public IfcmpContext ifcmp(int i) {
			return getRuleContext(IfcmpContext.class,i);
		}
		public IfcmpAndContext(IfcmpContext ctx) { copyFrom(ctx); }
	}
	public static class IfcmpAgainContext extends IfcmpContext {
		public IfcmpContext ifcmp() {
			return getRuleContext(IfcmpContext.class,0);
		}
		public IfcmpAgainContext(IfcmpContext ctx) { copyFrom(ctx); }
	}
	public static class IfcmpOpContext extends IfcmpContext {
		public Token op;
		public List<ParamContext> param() {
			return getRuleContexts(ParamContext.class);
		}
		public ParamContext param(int i) {
			return getRuleContext(ParamContext.class,i);
		}
		public IfcmpOpContext(IfcmpContext ctx) { copyFrom(ctx); }
	}
	public static class IfcmpFalseContext extends IfcmpContext {
		public TerminalNode False() { return getToken(MiniRuleParser.False, 0); }
		public IfcmpFalseContext(IfcmpContext ctx) { copyFrom(ctx); }
	}
	public static class IfcmpOrContext extends IfcmpContext {
		public List<IfcmpContext> ifcmp() {
			return getRuleContexts(IfcmpContext.class);
		}
		public IfcmpContext ifcmp(int i) {
			return getRuleContext(IfcmpContext.class,i);
		}
		public IfcmpOrContext(IfcmpContext ctx) { copyFrom(ctx); }
	}

	public final IfcmpContext ifcmp() throws RecognitionException {
		return ifcmp(0);
	}

	private IfcmpContext ifcmp(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		IfcmpContext _localctx = new IfcmpContext(_ctx, _parentState);
		IfcmpContext _prevctx = _localctx;
		int _startState = 8;
		enterRecursionRule(_localctx, 8, RULE_ifcmp, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(121);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
			case 1:
				{
				_localctx = new IfcmpTrueContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(109);
				match(True);
				}
				break;
			case 2:
				{
				_localctx = new IfcmpFalseContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(110);
				match(False);
				}
				break;
			case 3:
				{
				_localctx = new IfcmpOpContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(111);
				param();
				setState(112);
				((IfcmpOpContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__11) | (1L << T__12) | (1L << T__13) | (1L << T__14) | (1L << T__15) | (1L << T__16))) != 0)) ) {
					((IfcmpOpContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(113);
				param();
				}
				break;
			case 4:
				{
				_localctx = new IfcmpAgainContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(115);
				match(T__8);
				setState(116);
				ifcmp(0);
				setState(117);
				match(T__9);
				}
				break;
			case 5:
				{
				_localctx = new IfcmpNotContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(119);
				match(T__17);
				setState(120);
				ifcmp(3);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(131);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,16,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(129);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
					case 1:
						{
						_localctx = new IfcmpAndContext(new IfcmpContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_ifcmp);
						setState(123);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(124);
						match(T__18);
						setState(125);
						ifcmp(3);
						}
						break;
					case 2:
						{
						_localctx = new IfcmpOrContext(new IfcmpContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_ifcmp);
						setState(126);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(127);
						match(T__19);
						setState(128);
						ifcmp(2);
						}
						break;
					}
					} 
				}
				setState(133);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,16,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class MathcalContext extends ParserRuleContext {
		public MathcalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_mathcal; }
	 
		public MathcalContext() { }
		public void copyFrom(MathcalContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class MathcalVariableContext extends MathcalContext {
		public VariableContext variable() {
			return getRuleContext(VariableContext.class,0);
		}
		public MathcalVariableContext(MathcalContext ctx) { copyFrom(ctx); }
	}
	public static class MathcalMulDivContext extends MathcalContext {
		public Token op;
		public List<MathcalContext> mathcal() {
			return getRuleContexts(MathcalContext.class);
		}
		public MathcalContext mathcal(int i) {
			return getRuleContext(MathcalContext.class,i);
		}
		public MathcalMulDivContext(MathcalContext ctx) { copyFrom(ctx); }
	}
	public static class MathcalAddDecContext extends MathcalContext {
		public Token op;
		public List<MathcalContext> mathcal() {
			return getRuleContexts(MathcalContext.class);
		}
		public MathcalContext mathcal(int i) {
			return getRuleContext(MathcalContext.class,i);
		}
		public MathcalAddDecContext(MathcalContext ctx) { copyFrom(ctx); }
	}
	public static class MathcalIntContext extends MathcalContext {
		public TerminalNode Int() { return getToken(MiniRuleParser.Int, 0); }
		public MathcalIntContext(MathcalContext ctx) { copyFrom(ctx); }
	}
	public static class MathcalAgainContext extends MathcalContext {
		public MathcalContext mathcal() {
			return getRuleContext(MathcalContext.class,0);
		}
		public MathcalAgainContext(MathcalContext ctx) { copyFrom(ctx); }
	}
	public static class MathcalMMContext extends MathcalContext {
		public List<MathcalContext> mathcal() {
			return getRuleContexts(MathcalContext.class);
		}
		public MathcalContext mathcal(int i) {
			return getRuleContext(MathcalContext.class,i);
		}
		public MathcalMMContext(MathcalContext ctx) { copyFrom(ctx); }
	}
	public static class MathcalFloatContext extends MathcalContext {
		public TerminalNode Float() { return getToken(MiniRuleParser.Float, 0); }
		public MathcalFloatContext(MathcalContext ctx) { copyFrom(ctx); }
	}

	public final MathcalContext mathcal() throws RecognitionException {
		return mathcal(0);
	}

	private MathcalContext mathcal(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		MathcalContext _localctx = new MathcalContext(_ctx, _parentState);
		MathcalContext _prevctx = _localctx;
		int _startState = 10;
		enterRecursionRule(_localctx, 10, RULE_mathcal, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(142);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Int:
				{
				_localctx = new MathcalIntContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(135);
				match(Int);
				}
				break;
			case Float:
				{
				_localctx = new MathcalFloatContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(136);
				match(Float);
				}
				break;
			case ID:
				{
				_localctx = new MathcalVariableContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(137);
				variable(0);
				}
				break;
			case T__8:
				{
				_localctx = new MathcalAgainContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(138);
				match(T__8);
				setState(139);
				mathcal(0);
				setState(140);
				match(T__9);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(155);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,19,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(153);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,18,_ctx) ) {
					case 1:
						{
						_localctx = new MathcalMMContext(new MathcalContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_mathcal);
						setState(144);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						{
						setState(145);
						match(T__20);
						}
						setState(146);
						mathcal(4);
						}
						break;
					case 2:
						{
						_localctx = new MathcalMulDivContext(new MathcalContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_mathcal);
						setState(147);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(148);
						((MathcalMulDivContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==T__21 || _la==T__22) ) {
							((MathcalMulDivContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(149);
						mathcal(3);
						}
						break;
					case 3:
						{
						_localctx = new MathcalAddDecContext(new MathcalContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_mathcal);
						setState(150);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(151);
						((MathcalAddDecContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==T__23 || _la==T__24) ) {
							((MathcalAddDecContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(152);
						mathcal(2);
						}
						break;
					}
					} 
				}
				setState(157);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,19,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class ParamContext extends ParserRuleContext {
		public ParamContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_param; }
	 
		public ParamContext() { }
		public void copyFrom(ParamContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class ParamIntContext extends ParamContext {
		public TerminalNode Int() { return getToken(MiniRuleParser.Int, 0); }
		public ParamIntContext(ParamContext ctx) { copyFrom(ctx); }
	}
	public static class ParamFloatContext extends ParamContext {
		public TerminalNode Float() { return getToken(MiniRuleParser.Float, 0); }
		public ParamFloatContext(ParamContext ctx) { copyFrom(ctx); }
	}
	public static class ParamMathcalContext extends ParamContext {
		public MathcalContext mathcal() {
			return getRuleContext(MathcalContext.class,0);
		}
		public ParamMathcalContext(ParamContext ctx) { copyFrom(ctx); }
	}
	public static class ParamStrContext extends ParamContext {
		public TerminalNode Str() { return getToken(MiniRuleParser.Str, 0); }
		public ParamStrContext(ParamContext ctx) { copyFrom(ctx); }
	}
	public static class ParamVariableContext extends ParamContext {
		public VariableContext variable() {
			return getRuleContext(VariableContext.class,0);
		}
		public ParamVariableContext(ParamContext ctx) { copyFrom(ctx); }
	}
	public static class ParamFalseContext extends ParamContext {
		public TerminalNode False() { return getToken(MiniRuleParser.False, 0); }
		public ParamFalseContext(ParamContext ctx) { copyFrom(ctx); }
	}
	public static class ParamTrueContext extends ParamContext {
		public TerminalNode True() { return getToken(MiniRuleParser.True, 0); }
		public ParamTrueContext(ParamContext ctx) { copyFrom(ctx); }
	}

	public final ParamContext param() throws RecognitionException {
		ParamContext _localctx = new ParamContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_param);
		try {
			setState(165);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,20,_ctx) ) {
			case 1:
				_localctx = new ParamTrueContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(158);
				match(True);
				}
				break;
			case 2:
				_localctx = new ParamFalseContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(159);
				match(False);
				}
				break;
			case 3:
				_localctx = new ParamIntContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(160);
				match(Int);
				}
				break;
			case 4:
				_localctx = new ParamFloatContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(161);
				match(Float);
				}
				break;
			case 5:
				_localctx = new ParamStrContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(162);
				match(Str);
				}
				break;
			case 6:
				_localctx = new ParamVariableContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(163);
				variable(0);
				}
				break;
			case 7:
				_localctx = new ParamMathcalContext(_localctx);
				enterOuterAlt(_localctx, 7);
				{
				setState(164);
				mathcal(0);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VariableContext extends ParserRuleContext {
		public VariableContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variable; }
	 
		public VariableContext() { }
		public void copyFrom(VariableContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class VariableListContext extends VariableContext {
		public VariableContext variable() {
			return getRuleContext(VariableContext.class,0);
		}
		public TerminalNode Int() { return getToken(MiniRuleParser.Int, 0); }
		public VariableListContext(VariableContext ctx) { copyFrom(ctx); }
	}
	public static class VariableMapContext extends VariableContext {
		public VariableContext variable() {
			return getRuleContext(VariableContext.class,0);
		}
		public TerminalNode ID() { return getToken(MiniRuleParser.ID, 0); }
		public VariableMapContext(VariableContext ctx) { copyFrom(ctx); }
	}
	public static class VariableVarListContext extends VariableContext {
		public List<VariableContext> variable() {
			return getRuleContexts(VariableContext.class);
		}
		public VariableContext variable(int i) {
			return getRuleContext(VariableContext.class,i);
		}
		public VariableVarListContext(VariableContext ctx) { copyFrom(ctx); }
	}
	public static class VariableIDContext extends VariableContext {
		public TerminalNode ID() { return getToken(MiniRuleParser.ID, 0); }
		public VariableIDContext(VariableContext ctx) { copyFrom(ctx); }
	}

	public final VariableContext variable() throws RecognitionException {
		return variable(0);
	}

	private VariableContext variable(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		VariableContext _localctx = new VariableContext(_ctx, _parentState);
		VariableContext _prevctx = _localctx;
		int _startState = 14;
		enterRecursionRule(_localctx, 14, RULE_variable, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			_localctx = new VariableIDContext(_localctx);
			_ctx = _localctx;
			_prevctx = _localctx;

			setState(168);
			match(ID);
			}
			_ctx.stop = _input.LT(-1);
			setState(184);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,22,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(182);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,21,_ctx) ) {
					case 1:
						{
						_localctx = new VariableMapContext(new VariableContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_variable);
						setState(170);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(171);
						match(T__25);
						setState(172);
						match(ID);
						}
						break;
					case 2:
						{
						_localctx = new VariableListContext(new VariableContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_variable);
						setState(173);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(174);
						match(T__26);
						setState(175);
						match(Int);
						setState(176);
						match(T__27);
						}
						break;
					case 3:
						{
						_localctx = new VariableVarListContext(new VariableContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_variable);
						setState(177);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(178);
						match(T__26);
						setState(179);
						variable(0);
						setState(180);
						match(T__27);
						}
						break;
					}
					} 
				}
				setState(186);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,22,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 4:
			return ifcmp_sempred((IfcmpContext)_localctx, predIndex);
		case 5:
			return mathcal_sempred((MathcalContext)_localctx, predIndex);
		case 7:
			return variable_sempred((VariableContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean ifcmp_sempred(IfcmpContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 2);
		case 1:
			return precpred(_ctx, 1);
		}
		return true;
	}
	private boolean mathcal_sempred(MathcalContext _localctx, int predIndex) {
		switch (predIndex) {
		case 2:
			return precpred(_ctx, 3);
		case 3:
			return precpred(_ctx, 2);
		case 4:
			return precpred(_ctx, 1);
		}
		return true;
	}
	private boolean variable_sempred(VariableContext _localctx, int predIndex) {
		switch (predIndex) {
		case 5:
			return precpred(_ctx, 3);
		case 6:
			return precpred(_ctx, 2);
		case 7:
			return precpred(_ctx, 1);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3&\u00be\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\3\2\3\2\3\2\3\3"+
		"\3\3\3\3\3\3\6\3\32\n\3\r\3\16\3\33\3\3\3\3\5\3 \n\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\5\3(\n\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\6\3\61\n\3\r\3\16\3\62\3\3"+
		"\3\3\3\3\3\3\5\39\n\3\3\3\3\3\3\3\5\3>\n\3\3\4\3\4\3\4\5\4C\n\4\3\4\3"+
		"\4\3\4\5\4H\n\4\3\4\3\4\3\4\3\4\3\4\6\4O\n\4\r\4\16\4P\3\4\3\4\3\4\3\4"+
		"\3\4\6\4X\n\4\r\4\16\4Y\5\4\\\n\4\3\5\3\5\3\5\3\5\3\5\7\5c\n\5\f\5\16"+
		"\5f\13\5\5\5h\n\5\3\5\3\5\3\5\5\5m\n\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6"+
		"\3\6\3\6\3\6\3\6\3\6\5\6|\n\6\3\6\3\6\3\6\3\6\3\6\3\6\7\6\u0084\n\6\f"+
		"\6\16\6\u0087\13\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\5\7\u0091\n\7\3\7\3"+
		"\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\7\7\u009c\n\7\f\7\16\7\u009f\13\7\3\b\3"+
		"\b\3\b\3\b\3\b\3\b\3\b\5\b\u00a8\n\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3"+
		"\t\3\t\3\t\3\t\3\t\3\t\3\t\7\t\u00b9\n\t\f\t\16\t\u00bc\13\t\3\t\2\5\n"+
		"\f\20\n\2\4\6\b\n\f\16\20\2\5\3\2\16\23\3\2\30\31\3\2\32\33\2\u00db\2"+
		"\22\3\2\2\2\4=\3\2\2\2\6[\3\2\2\2\b]\3\2\2\2\n{\3\2\2\2\f\u0090\3\2\2"+
		"\2\16\u00a7\3\2\2\2\20\u00a9\3\2\2\2\22\23\5\6\4\2\23\24\5\4\3\2\24\3"+
		"\3\2\2\2\25\31\7\3\2\2\26\27\5\n\6\2\27\30\5\4\3\2\30\32\3\2\2\2\31\26"+
		"\3\2\2\2\32\33\3\2\2\2\33\31\3\2\2\2\33\34\3\2\2\2\34\37\3\2\2\2\35\36"+
		"\7\4\2\2\36 \5\4\3\2\37\35\3\2\2\2\37 \3\2\2\2 !\3\2\2\2!\"\7\5\2\2\""+
		">\3\2\2\2#\'\7\3\2\2$%\5\6\4\2%&\5\4\3\2&(\3\2\2\2\'$\3\2\2\2\'(\3\2\2"+
		"\2()\3\2\2\2)>\7\5\2\2*+\7\3\2\2+,\7\6\2\2,\60\7\3\2\2-.\7\"\2\2./\7\7"+
		"\2\2/\61\5\16\b\2\60-\3\2\2\2\61\62\3\2\2\2\62\60\3\2\2\2\62\63\3\2\2"+
		"\2\638\3\2\2\2\64\65\7\b\2\2\65\66\7\"\2\2\66\67\7\7\2\2\679\5\16\b\2"+
		"8\64\3\2\2\289\3\2\2\29:\3\2\2\2:;\7\5\2\2;<\7\5\2\2<>\3\2\2\2=\25\3\2"+
		"\2\2=#\3\2\2\2=*\3\2\2\2>\5\3\2\2\2?B\5\b\5\2@A\7\b\2\2AC\5\b\5\2B@\3"+
		"\2\2\2BC\3\2\2\2C\\\3\2\2\2DG\5\b\5\2EF\7\t\2\2FH\5\b\5\2GE\3\2\2\2GH"+
		"\3\2\2\2H\\\3\2\2\2IJ\7\n\2\2JK\7\7\2\2KN\5\b\5\2LM\7\b\2\2MO\5\b\5\2"+
		"NL\3\2\2\2OP\3\2\2\2PN\3\2\2\2PQ\3\2\2\2Q\\\3\2\2\2RS\7\n\2\2ST\7\7\2"+
		"\2TW\5\b\5\2UV\7\t\2\2VX\5\b\5\2WU\3\2\2\2XY\3\2\2\2YW\3\2\2\2YZ\3\2\2"+
		"\2Z\\\3\2\2\2[?\3\2\2\2[D\3\2\2\2[I\3\2\2\2[R\3\2\2\2\\\7\3\2\2\2]^\7"+
		"!\2\2^g\7\13\2\2_d\5\16\b\2`a\7\b\2\2ac\5\16\b\2b`\3\2\2\2cf\3\2\2\2d"+
		"b\3\2\2\2de\3\2\2\2eh\3\2\2\2fd\3\2\2\2g_\3\2\2\2gh\3\2\2\2hi\3\2\2\2"+
		"il\7\f\2\2jk\7\r\2\2km\7!\2\2lj\3\2\2\2lm\3\2\2\2m\t\3\2\2\2no\b\6\1\2"+
		"o|\7\37\2\2p|\7 \2\2qr\5\16\b\2rs\t\2\2\2st\5\16\b\2t|\3\2\2\2uv\7\13"+
		"\2\2vw\5\n\6\2wx\7\f\2\2x|\3\2\2\2yz\7\24\2\2z|\5\n\6\5{n\3\2\2\2{p\3"+
		"\2\2\2{q\3\2\2\2{u\3\2\2\2{y\3\2\2\2|\u0085\3\2\2\2}~\f\4\2\2~\177\7\25"+
		"\2\2\177\u0084\5\n\6\5\u0080\u0081\f\3\2\2\u0081\u0082\7\26\2\2\u0082"+
		"\u0084\5\n\6\4\u0083}\3\2\2\2\u0083\u0080\3\2\2\2\u0084\u0087\3\2\2\2"+
		"\u0085\u0083\3\2\2\2\u0085\u0086\3\2\2\2\u0086\13\3\2\2\2\u0087\u0085"+
		"\3\2\2\2\u0088\u0089\b\7\1\2\u0089\u0091\7#\2\2\u008a\u0091\7$\2\2\u008b"+
		"\u0091\5\20\t\2\u008c\u008d\7\13\2\2\u008d\u008e\5\f\7\2\u008e\u008f\7"+
		"\f\2\2\u008f\u0091\3\2\2\2\u0090\u0088\3\2\2\2\u0090\u008a\3\2\2\2\u0090"+
		"\u008b\3\2\2\2\u0090\u008c\3\2\2\2\u0091\u009d\3\2\2\2\u0092\u0093\f\5"+
		"\2\2\u0093\u0094\7\27\2\2\u0094\u009c\5\f\7\6\u0095\u0096\f\4\2\2\u0096"+
		"\u0097\t\3\2\2\u0097\u009c\5\f\7\5\u0098\u0099\f\3\2\2\u0099\u009a\t\4"+
		"\2\2\u009a\u009c\5\f\7\4\u009b\u0092\3\2\2\2\u009b\u0095\3\2\2\2\u009b"+
		"\u0098\3\2\2\2\u009c\u009f\3\2\2\2\u009d\u009b\3\2\2\2\u009d\u009e\3\2"+
		"\2\2\u009e\r\3\2\2\2\u009f\u009d\3\2\2\2\u00a0\u00a8\7\37\2\2\u00a1\u00a8"+
		"\7 \2\2\u00a2\u00a8\7#\2\2\u00a3\u00a8\7$\2\2\u00a4\u00a8\7\"\2\2\u00a5"+
		"\u00a8\5\20\t\2\u00a6\u00a8\5\f\7\2\u00a7\u00a0\3\2\2\2\u00a7\u00a1\3"+
		"\2\2\2\u00a7\u00a2\3\2\2\2\u00a7\u00a3\3\2\2\2\u00a7\u00a4\3\2\2\2\u00a7"+
		"\u00a5\3\2\2\2\u00a7\u00a6\3\2\2\2\u00a8\17\3\2\2\2\u00a9\u00aa\b\t\1"+
		"\2\u00aa\u00ab\7!\2\2\u00ab\u00ba\3\2\2\2\u00ac\u00ad\f\5\2\2\u00ad\u00ae"+
		"\7\34\2\2\u00ae\u00b9\7!\2\2\u00af\u00b0\f\4\2\2\u00b0\u00b1\7\35\2\2"+
		"\u00b1\u00b2\7#\2\2\u00b2\u00b9\7\36\2\2\u00b3\u00b4\f\3\2\2\u00b4\u00b5"+
		"\7\35\2\2\u00b5\u00b6\5\20\t\2\u00b6\u00b7\7\36\2\2\u00b7\u00b9\3\2\2"+
		"\2\u00b8\u00ac\3\2\2\2\u00b8\u00af\3\2\2\2\u00b8\u00b3\3\2\2\2\u00b9\u00bc"+
		"\3\2\2\2\u00ba\u00b8\3\2\2\2\u00ba\u00bb\3\2\2\2\u00bb\21\3\2\2\2\u00bc"+
		"\u00ba\3\2\2\2\31\33\37\'\628=BGPY[dgl{\u0083\u0085\u0090\u009b\u009d"+
		"\u00a7\u00b8\u00ba";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}