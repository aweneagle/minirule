// Generated from MiniRule.g4 by ANTLR 4.8
package org.minirule;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link MiniRuleParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface MiniRuleVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by the {@code actCondition}
	 * labeled alternative in {@link MiniRuleParser#actionblock}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitActCondition(MiniRuleParser.ActConditionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code blockIfcmp}
	 * labeled alternative in {@link MiniRuleParser#block}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlockIfcmp(MiniRuleParser.BlockIfcmpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code blockAction}
	 * labeled alternative in {@link MiniRuleParser#block}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlockAction(MiniRuleParser.BlockActionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code blockReturn}
	 * labeled alternative in {@link MiniRuleParser#block}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlockReturn(MiniRuleParser.BlockReturnContext ctx);
	/**
	 * Visit a parse tree produced by the {@code fcallSerial}
	 * labeled alternative in {@link MiniRuleParser#action}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFcallSerial(MiniRuleParser.FcallSerialContext ctx);
	/**
	 * Visit a parse tree produced by the {@code fcallParallel}
	 * labeled alternative in {@link MiniRuleParser#action}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFcallParallel(MiniRuleParser.FcallParallelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code tranSerial}
	 * labeled alternative in {@link MiniRuleParser#action}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTranSerial(MiniRuleParser.TranSerialContext ctx);
	/**
	 * Visit a parse tree produced by the {@code tranParallel}
	 * labeled alternative in {@link MiniRuleParser#action}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTranParallel(MiniRuleParser.TranParallelContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniRuleParser#fcall}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFcall(MiniRuleParser.FcallContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ifcmpNot}
	 * labeled alternative in {@link MiniRuleParser#ifcmp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfcmpNot(MiniRuleParser.IfcmpNotContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ifcmpTrue}
	 * labeled alternative in {@link MiniRuleParser#ifcmp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfcmpTrue(MiniRuleParser.IfcmpTrueContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ifcmpAnd}
	 * labeled alternative in {@link MiniRuleParser#ifcmp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfcmpAnd(MiniRuleParser.IfcmpAndContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ifcmpAgain}
	 * labeled alternative in {@link MiniRuleParser#ifcmp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfcmpAgain(MiniRuleParser.IfcmpAgainContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ifcmpOp}
	 * labeled alternative in {@link MiniRuleParser#ifcmp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfcmpOp(MiniRuleParser.IfcmpOpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ifcmpFalse}
	 * labeled alternative in {@link MiniRuleParser#ifcmp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfcmpFalse(MiniRuleParser.IfcmpFalseContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ifcmpOr}
	 * labeled alternative in {@link MiniRuleParser#ifcmp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfcmpOr(MiniRuleParser.IfcmpOrContext ctx);
	/**
	 * Visit a parse tree produced by the {@code mathcalVariable}
	 * labeled alternative in {@link MiniRuleParser#mathcal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMathcalVariable(MiniRuleParser.MathcalVariableContext ctx);
	/**
	 * Visit a parse tree produced by the {@code mathcalMulDiv}
	 * labeled alternative in {@link MiniRuleParser#mathcal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMathcalMulDiv(MiniRuleParser.MathcalMulDivContext ctx);
	/**
	 * Visit a parse tree produced by the {@code mathcalAddDec}
	 * labeled alternative in {@link MiniRuleParser#mathcal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMathcalAddDec(MiniRuleParser.MathcalAddDecContext ctx);
	/**
	 * Visit a parse tree produced by the {@code mathcalInt}
	 * labeled alternative in {@link MiniRuleParser#mathcal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMathcalInt(MiniRuleParser.MathcalIntContext ctx);
	/**
	 * Visit a parse tree produced by the {@code mathcalAgain}
	 * labeled alternative in {@link MiniRuleParser#mathcal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMathcalAgain(MiniRuleParser.MathcalAgainContext ctx);
	/**
	 * Visit a parse tree produced by the {@code mathcalMM}
	 * labeled alternative in {@link MiniRuleParser#mathcal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMathcalMM(MiniRuleParser.MathcalMMContext ctx);
	/**
	 * Visit a parse tree produced by the {@code mathcalFloat}
	 * labeled alternative in {@link MiniRuleParser#mathcal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMathcalFloat(MiniRuleParser.MathcalFloatContext ctx);
	/**
	 * Visit a parse tree produced by the {@code paramTrue}
	 * labeled alternative in {@link MiniRuleParser#param}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParamTrue(MiniRuleParser.ParamTrueContext ctx);
	/**
	 * Visit a parse tree produced by the {@code paramFalse}
	 * labeled alternative in {@link MiniRuleParser#param}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParamFalse(MiniRuleParser.ParamFalseContext ctx);
	/**
	 * Visit a parse tree produced by the {@code paramInt}
	 * labeled alternative in {@link MiniRuleParser#param}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParamInt(MiniRuleParser.ParamIntContext ctx);
	/**
	 * Visit a parse tree produced by the {@code paramFloat}
	 * labeled alternative in {@link MiniRuleParser#param}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParamFloat(MiniRuleParser.ParamFloatContext ctx);
	/**
	 * Visit a parse tree produced by the {@code paramStr}
	 * labeled alternative in {@link MiniRuleParser#param}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParamStr(MiniRuleParser.ParamStrContext ctx);
	/**
	 * Visit a parse tree produced by the {@code paramVariable}
	 * labeled alternative in {@link MiniRuleParser#param}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParamVariable(MiniRuleParser.ParamVariableContext ctx);
	/**
	 * Visit a parse tree produced by the {@code paramMathcal}
	 * labeled alternative in {@link MiniRuleParser#param}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParamMathcal(MiniRuleParser.ParamMathcalContext ctx);
	/**
	 * Visit a parse tree produced by the {@code variableList}
	 * labeled alternative in {@link MiniRuleParser#variable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariableList(MiniRuleParser.VariableListContext ctx);
	/**
	 * Visit a parse tree produced by the {@code variableMap}
	 * labeled alternative in {@link MiniRuleParser#variable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariableMap(MiniRuleParser.VariableMapContext ctx);
	/**
	 * Visit a parse tree produced by the {@code variableVarList}
	 * labeled alternative in {@link MiniRuleParser#variable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariableVarList(MiniRuleParser.VariableVarListContext ctx);
	/**
	 * Visit a parse tree produced by the {@code variableID}
	 * labeled alternative in {@link MiniRuleParser#variable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariableID(MiniRuleParser.VariableIDContext ctx);
}