package ast;

import parser.FOOLParser;
import symboltable.SymbolTable;
import throwable.TypeException;
import type.IntType;
import type.Type;

import java.util.ArrayList;
import java.util.List;

public class ExpNode implements Node {
    private Node leftNode;
    private Node rightNode;
    private FOOLParser.ExpContext expContext;
    private String ID; // + oppure -

    public ExpNode(Node l, Node r, FOOLParser.ExpContext context, String i) {
        leftNode = l;
        rightNode = r;
        expContext = context;
        ID = i;
    }

    @Override
    public List<String> checkSemantics(SymbolTable env) {
        List<String> res = new ArrayList<>();
        res.addAll(leftNode.checkSemantics(env));
        res.addAll(rightNode.checkSemantics(env));
        return res;
    }

    @Override
    public Type typeCheck() throws TypeException {
        // Addizione e Sottrazione
        if (!leftNode.typeCheck().isSubType(new IntType()) || !rightNode.typeCheck().isSubType(new IntType())) {
            throw new TypeException("Tipo incompatibile per " + ID + ". È richiesto un intero.");
        }
        return new IntType();
    }

    @Override
    public String codeGeneration() {
        if (ID.equals("'+'")) {
            return leftNode.codeGeneration() +
                    rightNode.codeGeneration() +
                    "add\n";
        } else {
            return leftNode.codeGeneration() +
                    rightNode.codeGeneration() +
                    "sub\n";
        }
    }
}
