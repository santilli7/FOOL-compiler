package ast;

import codegen.Label;
import parser.FOOLLexer;
import parser.FOOLParser;
import symboltable.SymbolTable;
import throwable.TypeException;
import type.BoolType;
import type.IntType;
import type.Type;

import java.util.ArrayList;
import java.util.List;

//Nodo utilizzato per gestire gli operatori logici
public class FactorNode implements Node {

    private Node leftNode;
    private Node rightNode;
    private FOOLParser.FactorContext factorContext;

    //identificativo per distinguere l'operatore logico
    private String ID;

    public FactorNode(Node l, Node r, FOOLParser.FactorContext context, String i) {
        leftNode = l;
        rightNode = r;
        factorContext = context;
        ID = i;

    }

    @Override
    public List<String> checkSemantics(SymbolTable env) {

        List<String> res = new ArrayList<>();
        res.addAll(leftNode.checkSemantics(env));
        if (rightNode != null) res.addAll(rightNode.checkSemantics(env));

        return res;
    }

    @Override
    public Type typeCheck() throws TypeException {

        Type leftType = leftNode.typeCheck();
        Type rightType = rightNode.typeCheck();
        //Operatori logici AND OR solo tra booleani
        if (ID.equals(FOOLLexer.VOCABULARY.getLiteralName(FOOLLexer.AND)) || ID.equals(FOOLLexer.VOCABULARY.getLiteralName(FOOLLexer.OR))) {
            if (!leftType.isSubType(new BoolType()) || !rightType.isSubType(new BoolType())) {
                throw new TypeException("Tipo incompatibile per " + ID + ". È richiesto un booleano.");
            }
        } else {
            //tutto il  resto tra interi:
            //            EQ | LESSEQ | GREATEREQ | GREATER | LESS
            if (!leftType.isSubType(new IntType()) || !rightType.isSubType(new IntType())) {
                throw new TypeException("Tipo incompatibile per " + ID + ". È richiesto un intero.");
            }
        }

        return new BoolType();
    }

    @Override
    public String codeGeneration() {
        String label = Label.nuovaLabel();
        String exit = Label.nuovaLabel();
        switch (ID) {
            case "'&&'":
                return leftNode.codeGeneration()
                        + "push 0\n"
                        + "beq " + label + "\n"
                        + rightNode.codeGeneration()
                        + "push 0\n"
                        + "beq " + label + "\n"
                        + "push 1\n"
                        + "b " + exit + "\n"
                        + label + ":\n"
                        + "push 0\n"
                        + exit + ":\n";

            case "'||'":
                return leftNode.codeGeneration()
                        + "push 1\n"
                        + "beq " + label + "\n"
                        + rightNode.codeGeneration()
                        + "push 1\n"
                        + "beq " + label + "\n"
                        + "push 0\n"
                        + "b " + exit + "\n"
                        + label + ":\n"
                        + "push 1\n"
                        + exit + ":\n";
            case "'=='":
                return leftNode.codeGeneration() +
                        rightNode.codeGeneration() +
                        "beq " + label + "\n" +
                        "push 0\n" +
                        "b " + exit + "\n" +
                        label + ":\n" +
                        "push 1\n" +
                        exit + ":\n";
            case "'>'":
                return rightNode.codeGeneration() +
                        "push 1\n" +
                        "add\n" +
                        leftNode.codeGeneration() +
                        "bleq " + label + "\n" +
                        "push 0\n" +
                        "b " + exit + "\n" +
                        label + ":\n" +
                        "push 1\n" +
                        exit + ":\n";
            case "'>='":
                return rightNode.codeGeneration() +
                        leftNode.codeGeneration() +
                        "bleq " + label + "\n" +
                        "push 0\n" +
                        "b " + exit + "\n" +
                        label + ":\n" +
                        "push 1\n" +
                        exit + ":\n";
            case "'<'":
                return leftNode.codeGeneration() +
                        "push 1\n" +
                        "add\n" +
                        rightNode.codeGeneration() +
                        "bleq " + label + "\n" +
                        "push 0\n" +
                        "b " + exit + "\n" +
                        label + ":\n" +
                        "push 1\n" +
                        exit + ":\n";
            case "'<='":
                return leftNode.codeGeneration() +
                        rightNode.codeGeneration() +
                        "bleq " + label + "\n" +
                        "push 0\n" +
                        "b " + exit + "\n" +
                        label + ":\n" +
                        "push 1\n" +
                        exit + ":\n";
            default:
                //vuol dire che non esiste il nodo dx
                return "";
        }
    }

}