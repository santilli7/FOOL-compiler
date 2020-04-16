package ast;

import symboltable.SymbolTable;
import throwable.TypeException;
import type.Type;

import java.util.List;

public class SingleExpNode implements Node {
    private Node expression;

    public SingleExpNode(Node exp) {
        expression = exp;
    }

    @Override
    public List<String> checkSemantics(SymbolTable env) {
        return expression.checkSemantics(env);
    }

    @Override
    public Type typeCheck() throws TypeException {
        return expression.typeCheck();
    }

    @Override
    public String codeGeneration() {
        return expression.codeGeneration() + "halt\n";
    }
}