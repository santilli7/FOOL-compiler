// Generated from C:/Users/paolosant/IdeaProjects/CompProj/src/parser\FOOL.g4 by ANTLR 4.7
package parser;

    import java.util.ArrayList;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link FOOLParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface FOOLVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by the {@code singleExp}
	 * labeled alternative in {@link FOOLParser#prog}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSingleExp(FOOLParser.SingleExpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code letInExpStms}
	 * labeled alternative in {@link FOOLParser#prog}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLetInExpStms(FOOLParser.LetInExpStmsContext ctx);
	/**
	 * Visit a parse tree produced by the {@code classExp}
	 * labeled alternative in {@link FOOLParser#prog}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassExp(FOOLParser.ClassExpContext ctx);
	/**
	 * Visit a parse tree produced by {@link FOOLParser#classdec}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassdec(FOOLParser.ClassdecContext ctx);
	/**
	 * Visit a parse tree produced by {@link FOOLParser#decs}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDecs(FOOLParser.DecsContext ctx);
	/**
	 * Visit a parse tree produced by {@link FOOLParser#vardec}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVardec(FOOLParser.VardecContext ctx);
	/**
	 * Visit a parse tree produced by {@link FOOLParser#varasm}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarasm(FOOLParser.VarasmContext ctx);
	/**
	 * Visit a parse tree produced by {@link FOOLParser#letVar}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLetVar(FOOLParser.LetVarContext ctx);
	/**
	 * Visit a parse tree produced by {@link FOOLParser#fun}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFun(FOOLParser.FunContext ctx);
	/**
	 * Visit a parse tree produced by {@link FOOLParser#dec}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDec(FOOLParser.DecContext ctx);
	/**
	 * Visit a parse tree produced by {@link FOOLParser#met}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMet(FOOLParser.MetContext ctx);
	/**
	 * Visit a parse tree produced by {@link FOOLParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType(FOOLParser.TypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link FOOLParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExp(FOOLParser.ExpContext ctx);
	/**
	 * Visit a parse tree produced by {@link FOOLParser#term}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTerm(FOOLParser.TermContext ctx);
	/**
	 * Visit a parse tree produced by {@link FOOLParser#factor}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFactor(FOOLParser.FactorContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ifExpStms}
	 * labeled alternative in {@link FOOLParser#stm}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfExpStms(FOOLParser.IfExpStmsContext ctx);
	/**
	 * Visit a parse tree produced by the {@code varAsmStm}
	 * labeled alternative in {@link FOOLParser#stm}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarAsmStm(FOOLParser.VarAsmStmContext ctx);
	/**
	 * Visit a parse tree produced by {@link FOOLParser#stms}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStms(FOOLParser.StmsContext ctx);
	/**
	 * Visit a parse tree produced by the {@code intVal}
	 * labeled alternative in {@link FOOLParser#value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIntVal(FOOLParser.IntValContext ctx);
	/**
	 * Visit a parse tree produced by the {@code boolVal}
	 * labeled alternative in {@link FOOLParser#value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBoolVal(FOOLParser.BoolValContext ctx);
	/**
	 * Visit a parse tree produced by the {@code baseExp}
	 * labeled alternative in {@link FOOLParser#value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBaseExp(FOOLParser.BaseExpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code varID}
	 * labeled alternative in {@link FOOLParser#value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarID(FOOLParser.VarIDContext ctx);
	/**
	 * Visit a parse tree produced by the {@code funExp}
	 * labeled alternative in {@link FOOLParser#value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunExp(FOOLParser.FunExpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ifExp}
	 * labeled alternative in {@link FOOLParser#value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfExp(FOOLParser.IfExpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code newExp}
	 * labeled alternative in {@link FOOLParser#value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNewExp(FOOLParser.NewExpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code metExp}
	 * labeled alternative in {@link FOOLParser#value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMetExp(FOOLParser.MetExpContext ctx);
	/**
	 * Visit a parse tree produced by {@link FOOLParser#funcall}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFuncall(FOOLParser.FuncallContext ctx);
}