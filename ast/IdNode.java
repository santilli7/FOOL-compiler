package ast;

import org.antlr.v4.runtime.ParserRuleContext;
import symboltable.Entry;
import symboltable.SymbolTable;
import throwable.TypeException;
import throwable.UndecIDException;
import type.FunType;
import type.ObjectType;
import type.Type;

import java.util.ArrayList;
import java.util.List;

public class IdNode implements Node {

    private String ID;
    private Entry entry;
    private int nestingLevel;
    private ParserRuleContext parserRuleContext;

    //variabile utilizzata per gestire il meno davanti agli ID
    private boolean isNegative;
    private boolean isBool = false;
    private String operator;

    public IdNode(String i, ParserRuleContext context, boolean isNeg, String op) {
        parserRuleContext = context;
        ID = i;
        isNegative = isNeg;
        operator = op;
    }

    public String getID() {
        return ID;
    }

    @Override
    public List<String> checkSemantics(SymbolTable env) {
        //cercare ID nella symbol table, con casi particolari per le classi
        List<String> res = new ArrayList<>();

        try {

            //verifica della presenza dell'id nella symbolTable
            //l'eccezione viene generata dalla symtable
            entry = env.checkIDnotFunction(ID);

            if (entry.getType() instanceof ObjectType) {
                ObjectType decType = (ObjectType) entry.getType();
                res.addAll(decType.updateClassType(env));
            }
            nestingLevel = env.getNestingLevel();

        } catch (UndecIDException e) {
            res.add(e.getMessage());

        }

        return res;
    }

    @Override
    public Type typeCheck() throws TypeException {

        if (entry.getType() instanceof FunType) {
            throw new TypeException("Utilizzo errato di un identificativo di funzione");
        }

        if (isNegative) {
            if (operator.equals("not") && entry.getType().toPrint().equals("int")) {
                throw new TypeException("Utilizzo errato del 'not'!");
            } else if (operator.equals("-") && entry.getType().toPrint().equals("bool")) {
                throw new TypeException("Utilizzo errato del '-'!");

            }
        }


        if (entry.getType().toPrint().equals("bool")) {
            isBool = true;
        }
        return entry.getType();
    }

    public String codeGeneration() {
        String retCode = "";
        StringBuilder getActivationRecord = new StringBuilder();
        if (entry.isInsideClass()) {
            retCode = "push " + entry.getOffset() + "\n" + // pusho offset dell'ID
                    "lfp\n" +
                    "lw\n" +
                    "heapoffset\n" +  // converto l'offset logico nell'offset fisico a cui l'identificatore
                    // si riferisce, poi lo carica sullo stack
                    // utilizzato solo per i parametri dei metodi all'interno
                    // delle classi
                    "add\n" +
                    "lw\n";//carico sullo stack il valore all'indirizzo ottenuto
        } else {
            //for e getActivationRecord per gestire le funzioni annidate
            for (int i = 0; i < nestingLevel - entry.getNestingLevel(); i++)
                getActivationRecord.append("lw\n");
            retCode = "push " + entry.getOffset() + "\n" + //metto offset sullo stack
                    "lfp\n" + getActivationRecord + //risalgo la catena statica
                    "add\n" +
                    "lw\n";
        }
        if (isNegative) {
            if (isBool) {
                retCode = "push 1\n" + retCode + "sub\n";
            } else retCode += "push -1\nmult\n";
        }
        return retCode;
    }


    public Entry getEntry() {
        return entry;
    }

    public void setEntry(Entry entry) {
        this.entry = entry;
    }
}