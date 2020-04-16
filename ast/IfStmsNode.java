package ast;

import codegen.Label;
import parser.FOOLParser;
import symboltable.SymbolTable;
import throwable.TypeException;
import type.BoolType;
import type.Type;

import java.util.ArrayList;
import java.util.List;

public class IfStmsNode implements Node {
    private Node conditionNode;
    private Node thenNode;
    private Node elseNode;
    private FOOLParser.IfExpStmsContext ctx;

    public IfStmsNode(Node c, Node t, Node e, FOOLParser.IfExpStmsContext ctx) {
        conditionNode = c;
        thenNode = t;
        elseNode = e;
        this.ctx = ctx;
    }


    @Override
    public List<String> checkSemantics(SymbolTable env) {
        List<String> res = new ArrayList<>();
        res.addAll(conditionNode.checkSemantics(env));
        res.addAll(thenNode.checkSemantics(env));
        if (elseNode != null) res.addAll(elseNode.checkSemantics(env));
        return res;
    }

    @Override
    public Type typeCheck() throws TypeException {
        if (!conditionNode.typeCheck().isSubType(new BoolType())) {
            throw new TypeException("La condizione dell'if non è booleana");
        }
        Type thenType = thenNode.typeCheck();
        if (elseNode == null) return thenType;

        Type elseType = elseNode.typeCheck();
        if (thenType.isSubType(elseType)) {
            return elseType;
        } else if (elseType.isSubType(thenType)) {
            return thenType;
        } else {
            throw new TypeException("Tipi incompatibili nei rami then ed else");
        }
    }

    @Override
    public String codeGeneration() {
        String thenBranch = Label.nuovaLabel();
        String exit = Label.nuovaLabel();

        return conditionNode.codeGeneration() +
                "push 1\n" +
                "beq " + thenBranch + "\n" +
                (elseNode != null ? elseNode.codeGeneration() : "") +
                "b " + exit + "\n" +
                thenBranch + ":\n" +
                thenNode.codeGeneration() +
                exit + ":\n";
    }
}
