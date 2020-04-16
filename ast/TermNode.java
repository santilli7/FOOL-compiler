package ast;

import parser.FOOLParser;
import symboltable.SymbolTable;
import throwable.TypeException;
import type.IntType;
import type.Type;

import java.util.ArrayList;
import java.util.List;

//Nodo utilizzato per il prodotto e la divisione
public class TermNode implements Node {
    private Node leftNode;
    private Node rightNode;
    private FOOLParser.TermContext termContext;
    private String ID;

    public TermNode(Node l, Node r, FOOLParser.TermContext context, String i) {
        leftNode = l;
        rightNode = r;
        termContext = context;
        ID = i;
    }

    @Override
    public List<String> checkSemantics(SymbolTable env) {

        ArrayList<String> res = new ArrayList<>();

        res.addAll(leftNode.checkSemantics(env));
        res.addAll(rightNode.checkSemantics(env));

        return res;
    }

    @Override
    public Type typeCheck() throws TypeException {
        // TIMES (*) e DIV
        if (!(leftNode.typeCheck().isSubType(new IntType()) && rightNode.typeCheck().isSubType(new IntType()))) {
            throw new TypeException(ID + " permette solo tipi interi!");
        }
        return new IntType();
    }

    @Override
    public String codeGeneration() {
        if (ID.equals("'*'")) {
            return leftNode.codeGeneration() +
                    rightNode.codeGeneration() +
                    "mult\n";
        } else {
            return leftNode.codeGeneration() +
                    rightNode.codeGeneration() +
                    "div\n";
        }
    }
}