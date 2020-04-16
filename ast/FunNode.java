package ast;

import codegen.FunctionCode;
import codegen.Label;
import org.antlr.v4.runtime.ParserRuleContext;
import symboltable.SymbolTable;
import throwable.TypeException;
import type.FunType;
import type.ObjectType;
import type.Type;

import java.util.ArrayList;
import java.util.List;

public class FunNode implements Node {
    //protected perché vi possono accedere le sottoclassi, ovvero MethodNode
    protected String ID;
    protected Type returnType;
    protected List<ParameterNode> parameterNodeArrayList = new ArrayList<>();
    protected List<Node> declarationsArrayList;
    protected List<Node> body;
    private ParserRuleContext parserRuleContext;

    public FunNode(String id, Type rT, List<ParameterNode> p, List<Node> d, List<Node> b, ParserRuleContext context) {
        ID = id;
        returnType = rT;
        parameterNodeArrayList = p;
        declarationsArrayList = d;
        body = b;
        parserRuleContext = context;
    }

    public String getID() {
        return ID;
    }

    public List<ParameterNode> getParameterNodeArrayList() {
        return parameterNodeArrayList;
    }

    public Type getReturnType() {
        return returnType;
    }

    @Override
    public List<String> checkSemantics(SymbolTable env) {
        List<String> res = new ArrayList<>();
        List<Type> paramsList = new ArrayList<>();

        for (ParameterNode paramNode : parameterNodeArrayList) {
            paramsList.add(paramNode.getType());
        }

        // Se restituisco un'istanza di una classe, aggiorno le informazioni
        if (returnType instanceof ObjectType) {
            ObjectType objectType = (ObjectType) returnType;
            res.addAll(objectType.updateClassType(env));
        }


        //entro in un nuovo livello di scope
        env.newScope();
        //checkSemantic dei parametri
        for (ParameterNode param : parameterNodeArrayList) {
            res.addAll(param.checkSemantics(env));
        }

        //checkSemantic delle varLocali alla funzione
        if (declarationsArrayList.size() > 0) {
            env.setOffset(-2);
            for (Node n : declarationsArrayList)
                res.addAll(n.checkSemantics(env));
        }

        //check dell'exp della funzione
        for (Node node : body) {
            res.addAll(node.checkSemantics(env));
        }

        env.exitScope();

        return res;
    }

    @Override
    public Type typeCheck() throws TypeException {

        //typecheck di ogni parametro
        ArrayList<Type> paramsType = new ArrayList<>();
        for (ParameterNode param : parameterNodeArrayList) {
            paramsType.add(param.getType());
        }

        //typecheck di ogni dichiarazione
        if (declarationsArrayList.size() > 0) {
            for (Node dec : declarationsArrayList) {
                dec.typeCheck();
            }
        }

        //controllo che il corpo ritorni il tipo dichiarato dalla funzione
        Type bodyType;

        for (Node node : body) {

            bodyType = node.typeCheck();
            if (!bodyType.isSubType(returnType)) {
                throw new TypeException("Tipo incompatibile ritornato dalla funzione '" + ID + "'. ");
            }
        }

        return new FunType(paramsType, returnType);
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
            //gli assegnamenti non inseriscono nulla sullo stack
            addBody.append(b.codeGeneration());
        }

        String funLabel = Label.nuovaLabelPerFunzione();

        //inserisco il codice della funzione in fondo al main, davanti alla label
        FunctionCode.insertFunctionsCode(funLabel + ":\n" +
                "cfp\n" +
                "lra\n" +
                addLocal +
                addBody +
                "srv\n" +
                popLocal +
                "sra\n" +
                popInputArgs +
                "pop\n" +
                "sfp\n" +
                "lrv\n" +
                "lra\n" +
                "js"
        );

        return "push " + funLabel + "\n";

    }
}