// Generated from C:/Users/anton/IdeaProjects/CompProj/src/codegen\SVM.g4 by ANTLR 4.7
package codegen;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link SVMParser}.
 */
public interface SVMListener extends ParseTreeListener {
    /**
     * Enter a parse tree produced by {@link SVMParser#assembly}.
     *
     * @param ctx the parse tree
     */
    void enterAssembly(SVMParser.AssemblyContext ctx);

    /**
     * Exit a parse tree produced by {@link SVMParser#assembly}.
     *
     * @param ctx the parse tree
     */
    void exitAssembly(SVMParser.AssemblyContext ctx);
}