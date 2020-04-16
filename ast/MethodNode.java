package ast;

import codegen.FunctionCode;
import codegen.Label;
import org.antlr.v4.runtime.ParserRuleContext;
import symboltable.Entry;
import symboltable.SymbolTable;
import throwable.MultipleIDException;
import throwable.UndecIDException;
import type.*;

import java.util.ArrayList;
import java.util.List;

public class MethodNode extends FunNode {

    private String classID;

    public MethodNode(String ID, Type retType, List<ParameterNode> parametersArrayList, List<Node> declarationsArrayList, List<Node> body, ParserRuleContext parserRuleContext) {
        super(ID, retType, parametersArrayList, declarationsArrayList, body, parserRuleContext);
    }

    public void setClassID(String ID) {
        classID = ID;
    }

    @Override
    public List<String> checkSemantics(SymbolTable env) {
        List<String> res = new ArrayList();

        List<Type> paramsTypeList = new ArrayList<>();
        for (ParameterNode parameterNode : parameterNodeArrayList) {
            paramsTypeList.add(parameterNode.getType());
        }
            // Se restituisco un'istanza di una classe, aggiorno le informazioni
            //ES: [A nomeMetodo(A a) return a;]
            if (returnType instanceof ObjectType) {
                ObjectType objectType = (ObjectType) returnType;
                res.addAll(objectType.updateClassType(env));
            }

        env.newScope();

        //cerco la entry in cui è situata la classe
        try {
            Entry classEntry = env.checkID(classID);
            env.insertDeclaration("this", new ObjectType((ClassType) classEntry.getType()), 0 );
        } catch (MultipleIDException | UndecIDException e) {
            e.printStackTrace();
        }

        //checkSemantic di tutti i parametri
        for (ParameterNode param : parameterNodeArrayList) {
            res.addAll(param.checkSemantics(env));
        }
        //checkSemantic dei paramentri del metodo
        if (declarationsArrayList.size() > 0) {
            env.setOffset(-2);
            for (Node n : declarationsArrayList)
                res.addAll(n.checkSemantics(env));
        }


        //BODY del metodo: un exp oppure uno/molti stms
        for (Node node : body) {
            res.addAll(node.checkSemantics(env));
        }
        env.exitScope();
        return res;
    }

    @Override
    public String codeGeneration() {
        StringBuilder addLocal = new StringBuilder();
        StringBuilder popLocal = new StringBuilder();

        for (Node dec : declarationsArrayList) {
            addLocal.append(dec.codeGeneration());
            popLocal.append("pop\n");
        }

        //i params vengono caricati dal chiamante
        StringBuilder popInputArgs = new StringBuilder();
        for (Node dec : parameterNodeArrayList)
            popInputArgs.append("pop\n");

        StringBuilder addBody = new StringBuilder();
        for (Node b : body) {
            addBody.append(b.codeGeneration());

        }

        String funLabel = Label.nuovaLabelPerFunzione();

        //inserisco il codice della funzione in fondo al main, davanti alla label
        FunctionCode.insertFunctionsCode(funLabel + ":\n" +
                "cfp\n" + //$fp diventa uguale al valore di $sp
                "lra\n" + //push return address
                addLocal + //push dichiarazioni locali
                addBody +
                "srv\n" + //pop del return value
                popLocal +
                "sra\n" + // pop del return address
                "pop\n" + // pop dell'access link, per ritornare al vecchio livello di scope
                popInputArgs +
                "sfp\n" +  // $fp diventa uguale al valore del control link
                "lrv\n" + // push del risultato
                "lra\n" + // push del return address
                "js\n" // jump al return address per continuare dall'istruzione dopo
        );
        return funLabel + "\n";
    }


}