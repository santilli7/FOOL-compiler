// Generated from C:/Users/paolosant/IdeaProjects/CompProj/src/parser\FOOL.g4 by ANTLR 4.7
package parser;

    import java.util.ArrayList;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link FOOLParser}.
 */
public interface FOOLListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by the {@code singleExp}
	 * labeled alternative in {@link FOOLParser#prog}.
	 * @param ctx the parse tree
	 */
	void enterSingleExp(FOOLParser.SingleExpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code singleExp}
	 * labeled alternative in {@link FOOLParser#prog}.
	 * @param ctx the parse tree
	 */
	void exitSingleExp(FOOLParser.SingleExpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code letInExpStms}
	 * labeled alternative in {@link FOOLParser#prog}.
	 * @param ctx the parse tree
	 */
	void enterLetInExpStms(FOOLParser.LetInExpStmsContext ctx);
	/**
	 * Exit a parse tree produced by the {@code letInExpStms}
	 * labeled alternative in {@link FOOLParser#prog}.
	 * @param ctx the parse tree
	 */
	void exitLetInExpStms(FOOLParser.LetInExpStmsContext ctx);
	/**
	 * Enter a parse tree produced by the {@code classExp}
	 * labeled alternative in {@link FOOLParser#prog}.
	 * @param ctx the parse tree
	 */
	void enterClassExp(FOOLParser.ClassExpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code classExp}
	 * labeled alternative in {@link FOOLParser#prog}.
	 * @param ctx the parse tree
	 */
	void exitClassExp(FOOLParser.ClassExpContext ctx);
	/**
	 * Enter a parse tree produced by {@link FOOLParser#classdec}.
	 * @param ctx the parse tree
	 */
	void enterClassdec(FOOLParser.ClassdecContext ctx);
	/**
	 * Exit a parse tree produced by {@link FOOLParser#classdec}.
	 * @param ctx the parse tree
	 */
	void exitClassdec(FOOLParser.ClassdecContext ctx);
	/**
	 * Enter a parse tree produced by {@link FOOLParser#decs}.
	 * @param ctx the parse tree
	 */
	void enterDecs(FOOLParser.DecsContext ctx);
	/**
	 * Exit a parse tree produced by {@link FOOLParser#decs}.
	 * @param ctx the parse tree
	 */
	void exitDecs(FOOLParser.DecsContext ctx);
	/**
	 * Enter a parse tree produced by {@link FOOLParser#vardec}.
	 * @param ctx the parse tree
	 */
	void enterVardec(FOOLParser.VardecContext ctx);
	/**
	 * Exit a parse tree produced by {@link FOOLParser#vardec}.
	 * @param ctx the parse tree
	 */
	void exitVardec(FOOLParser.VardecContext ctx);
	/**
	 * Enter a parse tree produced by {@link FOOLParser#varasm}.
	 * @param ctx the parse tree
	 */
	void enterVarasm(FOOLParser.VarasmContext ctx);
	/**
	 * Exit a parse tree produced by {@link FOOLParser#varasm}.
	 * @param ctx the parse tree
	 */
	void exitVarasm(FOOLParser.VarasmContext ctx);
	/**
	 * Enter a parse tree produced by {@link FOOLParser#letVar}.
	 * @param ctx the parse tree
	 */
	void enterLetVar(FOOLParser.LetVarContext ctx);
	/**
	 * Exit a parse tree produced by {@link FOOLParser#letVar}.
	 * @param ctx the parse tree
	 */
	void exitLetVar(FOOLParser.LetVarContext ctx);
	/**
	 * Enter a parse tree produced by {@link FOOLParser#fun}.
	 * @param ctx the parse tree
	 */
	void enterFun(FOOLParser.FunContext ctx);
	/**
	 * Exit a parse tree produced by {@link FOOLParser#fun}.
	 * @param ctx the parse tree
	 */
	void exitFun(FOOLParser.FunContext ctx);
	/**
	 * Enter a parse tree produced by {@link FOOLParser#dec}.
	 * @param ctx the parse tree
	 */
	void enterDec(FOOLParser.DecContext ctx);
	/**
	 * Exit a parse tree produced by {@link FOOLParser#dec}.
	 * @param ctx the parse tree
	 */
	void exitDec(FOOLParser.DecContext ctx);
	/**
	 * Enter a parse tree produced by {@link FOOLParser#met}.
	 * @param ctx the parse tree
	 */
	void enterMet(FOOLParser.MetContext ctx);
	/**
	 * Exit a parse tree produced by {@link FOOLParser#met}.
	 * @param ctx the parse tree
	 */
	void exitMet(FOOLParser.MetContext ctx);
	/**
	 * Enter a parse tree produced by {@link FOOLParser#type}.
	 * @param ctx the parse tree
	 */
	void enterType(FOOLParser.TypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link FOOLParser#type}.
	 * @param ctx the parse tree
	 */
	void exitType(FOOLParser.TypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link FOOLParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterExp(FOOLParser.ExpContext ctx);
	/**
	 * Exit a parse tree produced by {@link FOOLParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitExp(FOOLParser.ExpContext ctx);
	/**
	 * Enter a parse tree produced by {@link FOOLParser#term}.
	 * @param ctx the parse tree
	 */
	void enterTerm(FOOLParser.TermContext ctx);
	/**
	 * Exit a parse tree produced by {@link FOOLParser#term}.
	 * @param ctx the parse tree
	 */
	void exitTerm(FOOLParser.TermContext ctx);
	/**
	 * Enter a parse tree produced by {@link FOOLParser#factor}.
	 * @param ctx the parse tree
	 */
	void enterFactor(FOOLParser.FactorContext ctx);
	/**
	 * Exit a parse tree produced by {@link FOOLParser#factor}.
	 * @param ctx the parse tree
	 */
	void exitFactor(FOOLParser.FactorContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ifExpStms}
	 * labeled alternative in {@link FOOLParser#stm}.
	 * @param ctx the parse tree
	 */
	void enterIfExpStms(FOOLParser.IfExpStmsContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ifExpStms}
	 * labeled alternative in {@link FOOLParser#stm}.
	 * @param ctx the parse tree
	 */
	void exitIfExpStms(FOOLParser.IfExpStmsContext ctx);
	/**
	 * Enter a parse tree produced by the {@code varAsmStm}
	 * labeled alternative in {@link FOOLParser#stm}.
	 * @param ctx the parse tree
	 */
	void enterVarAsmStm(FOOLParser.VarAsmStmContext ctx);
	/**
	 * Exit a parse tree produced by the {@code varAsmStm}
	 * labeled alternative in {@link FOOLParser#stm}.
	 * @param ctx the parse tree
	 */
	void exitVarAsmStm(FOOLParser.VarAsmStmContext ctx);
	/**
	 * Enter a parse tree produced by {@link FOOLParser#stms}.
	 * @param ctx the parse tree
	 */
	void enterStms(FOOLParser.StmsContext ctx);
	/**
	 * Exit a parse tree produced by {@link FOOLParser#stms}.
	 * @param ctx the parse tree
	 */
	void exitStms(FOOLParser.StmsContext ctx);
	/**
	 * Enter a parse tree produced by the {@code intVal}
	 * labeled alternative in {@link FOOLParser#value}.
	 * @param ctx the parse tree
	 */
	void enterIntVal(FOOLParser.IntValContext ctx);
	/**
	 * Exit a parse tree produced by the {@code intVal}
	 * labeled alternative in {@link FOOLParser#value}.
	 * @param ctx the parse tree
	 */
	void exitIntVal(FOOLParser.IntValContext ctx);
	/**
	 * Enter a parse tree produced by the {@code boolVal}
	 * labeled alternative in {@link FOOLParser#value}.
	 * @param ctx the parse tree
	 */
	void enterBoolVal(FOOLParser.BoolValContext ctx);
	/**
	 * Exit a parse tree produced by the {@code boolVal}
	 * labeled alternative in {@link FOOLParser#value}.
	 * @param ctx the parse tree
	 */
	void exitBoolVal(FOOLParser.BoolValContext ctx);
	/**
	 * Enter a parse tree produced by the {@code baseExp}
	 * labeled alternative in {@link FOOLParser#value}.
	 * @param ctx the parse tree
	 */
	void enterBaseExp(FOOLParser.BaseExpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code baseExp}
	 * labeled alternative in {@link FOOLParser#value}.
	 * @param ctx the parse tree
	 */
	void exitBaseExp(FOOLParser.BaseExpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code varID}
	 * labeled alternative in {@link FOOLParser#value}.
	 * @param ctx the parse tree
	 */
	void enterVarID(FOOLParser.VarIDContext ctx);
	/**
	 * Exit a parse tree produced by the {@code varID}
	 * labeled alternative in {@link FOOLParser#value}.
	 * @param ctx the parse tree
	 */
	void exitVarID(FOOLParser.VarIDContext ctx);
	/**
	 * Enter a parse tree produced by the {@code funExp}
	 * labeled alternative in {@link FOOLParser#value}.
	 * @param ctx the parse tree
	 */
	void enterFunExp(FOOLParser.FunExpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code funExp}
	 * labeled alternative in {@link FOOLParser#value}.
	 * @param ctx the parse tree
	 */
	void exitFunExp(FOOLParser.FunExpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ifExp}
	 * labeled alternative in {@link FOOLParser#value}.
	 * @param ctx the parse tree
	 */
	void enterIfExp(FOOLParser.IfExpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ifExp}
	 * labeled alternative in {@link FOOLParser#value}.
	 * @param ctx the parse tree
	 */
	void exitIfExp(FOOLParser.IfExpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code newExp}
	 * labeled alternative in {@link FOOLParser#value}.
	 * @param ctx the parse tree
	 */
	void enterNewExp(FOOLParser.NewExpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code newExp}
	 * labeled alternative in {@link FOOLParser#value}.
	 * @param ctx the parse tree
	 */
	void exitNewExp(FOOLParser.NewExpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code metExp}
	 * labeled alternative in {@link FOOLParser#value}.
	 * @param ctx the parse tree
	 */
	void enterMetExp(FOOLParser.MetExpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code metExp}
	 * labeled alternative in {@link FOOLParser#value}.
	 * @param ctx the parse tree
	 */
	void exitMetExp(FOOLParser.MetExpContext ctx);
	/**
	 * Enter a parse tree produced by {@link FOOLParser#funcall}.
	 * @param ctx the parse tree
	 */
	void enterFuncall(FOOLParser.FuncallContext ctx);
	/**
	 * Exit a parse tree produced by {@link FOOLParser#funcall}.
	 * @param ctx the parse tree
	 */
	void exitFuncall(FOOLParser.FuncallContext ctx);
}