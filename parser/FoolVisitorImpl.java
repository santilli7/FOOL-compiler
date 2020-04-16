package parser;

import ast.*;
import org.antlr.v4.runtime.ParserRuleContext;
import parser.FOOLParser.*;
import throwable.TypeException;
import type.Type;
import type.VoidType;

import java.util.ArrayList;
import java.util.List;

public class FoolVisitorImpl extends FOOLBaseVisitor<Node> {

    @Override
    public Node visitSingleExp(SingleExpContext ctx) {
        return new SingleExpNode(visit(ctx.exp()));
    }

    @Override
    public Node visitLetInExpStms(FOOLParser.LetInExpStmsContext ctx) {
        List<Node> declarations = new ArrayList<>();
        for (FOOLParser.DecContext dec : ctx.decs().dec()) {
            declarations.add(visit(dec));
        }
        Node node;
        if (ctx.exp() != null) {
            node = visit(ctx.exp());
            return new LetInExpNode(declarations, node);
        } else {
            List<Node> statements = new ArrayList<>();
            for (StmContext stm : ctx.stms().stm()) {
                statements.add(visit(stm));
            }
            return new LetInStmsNode(declarations, statements);
        }
    }

    @Override
    public Node visitVarasm(VarasmContext ctx) {
        //dichiarazione + assegnamento
        Type type;
        try {
            type = visit(ctx.vardec().type()).typeCheck();
        } catch (TypeException e) {
            //TODO EXCEPTION HERE
            System.err.println(e.getMessage());
            return null;
        }

        //exp da assegnare alla variabile
        Node exp;
        if (ctx.exp() == null) {  //il valore sarà null
            String classID = "";
            if(ctx.vardec().type().ID() != null){
                classID = ctx.vardec().type().ID().getText();
            }
            exp = new NullNode(classID);
        } else {
            exp = visit(ctx.exp());
        }

        return new VarAsgmNode(ctx.vardec().ID().getText(), type, exp, ctx);
    }

    @Override
    public Node visitType(TypeContext ctx) {
        return new TypeNode(ctx.getText());
    }

    @Override
    public Node visitFun(FunContext ctx) {
        try {
            List<ParameterNode> params = new ArrayList<>();
            //PARAMETRI
            for (int i = 0; i < ctx.vardec().size(); i++) {
                VardecContext vc = ctx.vardec().get(i);
                //insideClass=false
                params.add(new ParameterNode(vc.ID().getText(), visit(vc.type()).typeCheck(), i + 1, vc));
            }


            //VARIABILI LOCALI ALLA FUNZIONE
            List<Node> declarations = new ArrayList<Node>();
            if (ctx.letVar() != null) {
                for (VarasmContext dc : ctx.letVar().varasm())
                    declarations.add(visit(dc));
            }


            List<Node> body = new ArrayList<>();

            if (ctx.exp() != null)
                body.add(visit(ctx.exp()));
            else {
                for (StmContext stm : ctx.stms().stm()) {
                    body.add(visit(stm));
                }
            }

            Type ret;
            if (ctx.type() == null)
                ret = new VoidType();
            else
                ret = visit(ctx.type()).typeCheck();


            return new FunNode(ctx.ID().getText(), ret, params, declarations, body, ctx);
        } catch (TypeException e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    @Override
    public Node visitExp(ExpContext ctx) {
        if (ctx.right == null) {
            return visit(ctx.left);
        } else {
            return ctx.operator.getType() == FOOLLexer.PLUS ?
                    new ExpNode(visit(ctx.left), visit(ctx.right), ctx,
                            FOOLLexer.VOCABULARY.getLiteralName(FOOLLexer.PLUS)) :
                    new ExpNode(visit(ctx.left), visit(ctx.right), ctx,
                            FOOLLexer.VOCABULARY.getLiteralName(FOOLLexer.MINUS));
        }

    }

    @Override
    public Node visitTerm(TermContext ctx) {
        if (ctx.right == null) {
            return visit(ctx.left);
        } else {
            return ctx.operator.getType() == FOOLLexer.TIMES ?
                    new TermNode(visit(ctx.left), visit(ctx.right), ctx,
                            FOOLLexer.VOCABULARY.getLiteralName(FOOLLexer.TIMES)) :
                    new TermNode(visit(ctx.left), visit(ctx.right), ctx,
                            FOOLLexer.VOCABULARY.getLiteralName(FOOLLexer.DIV));
        }
    }

    @Override
    public Node visitFactor(FactorContext ctx) {
        if (ctx.right == null) {
            return visit(ctx.left);

        } else {
            Node leftNode = visit(ctx.left);
            Node rightNode = visit(ctx.right);
            try {
                switch (ctx.operator.getType()) {
                    case FOOLLexer.EQ:
                        return new FactorNode(leftNode, rightNode, ctx,
                                FOOLLexer.VOCABULARY.getLiteralName(FOOLLexer.EQ));

                    case FOOLLexer.LESSEQ:
                        return new FactorNode(leftNode, rightNode, ctx,
                                FOOLLexer.VOCABULARY.getLiteralName(FOOLLexer.LESSEQ));

                    case FOOLLexer.GREATEREQ:
                        return new FactorNode(leftNode, rightNode, ctx,
                                FOOLLexer.VOCABULARY.getLiteralName(FOOLLexer.GREATEREQ));

                    case FOOLLexer.GREATER:
                        return new FactorNode(leftNode, rightNode, ctx,
                                FOOLLexer.VOCABULARY.getLiteralName(FOOLLexer.GREATER));

                    case FOOLLexer.LESS:
                        return new FactorNode(leftNode, rightNode, ctx,
                                FOOLLexer.VOCABULARY.getLiteralName(FOOLLexer.LESS));

                    case FOOLLexer.AND:
                        return new FactorNode(leftNode, rightNode, ctx,
                                FOOLLexer.VOCABULARY.getLiteralName(FOOLLexer.AND));

                    case FOOLLexer.OR:
                        return new FactorNode(leftNode, rightNode, ctx,
                                FOOLLexer.VOCABULARY.getLiteralName(FOOLLexer.OR));

                    default:
                        throw new Exception("OPERATOR NOT FOUND!!!");
                }

            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }

        }
    }

    @Override
    public Node visitFuncall(FuncallContext ctx) {
        String functionID = ctx.ID().getText();

        boolean isMethod = false;
        ParserRuleContext tmp = ctx;
        while (tmp.getParent() != null) {
            if (tmp.getText().contains("class")) {
                isMethod = true;
                break;
            }
//            else if (tmp.getText().contains("let") && tmp.getParent().getParent().getText().contains("class")) {
//                isMethod = true;
//                break;
//            }
            tmp = tmp.getParent();
        }

        List<Node> args = new ArrayList<Node>();

        for (ExpContext exp : ctx.exp())
            args.add(visit(exp));

        if (isMethod) return new MethodCallNode("this", functionID, args, null);
        else return new FunCallNode(functionID, args, ctx);
    }


    @Override
    public Node visitIntVal(IntValContext ctx) {
        int val = Integer.parseInt(ctx.INTEGER().getText());
        return ctx.MINUS() == null ? new IntNode(val) : new IntNode(-val);
    }

    @Override
    public Node visitBoolVal(BoolValContext ctx) {
        Boolean result;
        if (ctx.TRUE() != null) {  //vuol dire che il valore è TRUE
            result = Boolean.parseBoolean(ctx.TRUE().getText());
        } else {
            result = Boolean.parseBoolean(ctx.FALSE().getText());
        }
        if (ctx.NOT() == null) { //il valore booleano resta intatto
            return new BoolNode(result);
        } else {  //crea un valore opposto
            return new BoolNode(!result);
        }

    }

    @Override
    public Node visitVarID(VarIDContext ctx) {
        //se esiste uno dei due MINUS o NOT allora si chiede l'opposto
        if (ctx.MINUS() == null && ctx.NOT() == null) {
            return new IdNode(ctx.ID().getText(), ctx, false, "");
        } else {
            String op = ctx.MINUS() == null ? "not" : "-";
            return new IdNode(ctx.ID().getText(), ctx, true, op);

        }
    }


    @Override
    public Node visitVarAsmStm(VarAsmStmContext ctx) {
        Node exp;

        if(ctx.exp() == null){
            exp = new NullNode("");
        } else {
            exp = visit(ctx.exp());
        }


        return new AsgmNode(ctx.ID().getText(), exp, ctx);
    }

    @Override
    public Node visitStms(StmsContext ctx) {
        //IfExpStms oppure VarAssgStm
        return super.visitStms(ctx);
    }


    @Override
    public Node visitIfExp(IfExpContext ctx) {
        return new IfNode(
                visit(ctx.cond),
                visit(ctx.thenBranch),
                ctx.elseBranch != null ? visit(ctx.elseBranch) : null,
                ctx
        );

    }

    @Override
    public Node visitIfExpStms(IfExpStmsContext ctx) {
        return new IfStmsNode(
                visit(ctx.exp()),
                visit(ctx.thenBranch),
                ctx.elseBranch != null ? visit(ctx.elseBranch) : null,
                ctx
        );
    }

    @Override
    public Node visitClassExp(ClassExpContext ctx) {
        List<ClassNode> classDecs = new ArrayList<>();
        try {

            //Per ogni dichiarazioni di Classe al TOP-LEVEL
            for (FOOLParser.ClassdecContext classDecCtx : ctx.classdec()) {

                /*CAMPI DELLA CLASSE*/
                List<ParameterNode> fields = new ArrayList<>();
                for (int i = 0; i < classDecCtx.vardec().size(); i++) {
                    VardecContext vardecCtx = classDecCtx.vardec().get(i);
                    fields.add(
                            new ParameterNode(vardecCtx.ID().getText(),
                                    visit(vardecCtx.type()).typeCheck(),
                                    i + 1, //offset
                                    true, //è un campo della classe
                                    vardecCtx
                            )
                    );
                }
                /*METODI DELLA CLASSE*/
                List<MethodNode> methods = new ArrayList<>();
                for (MetContext metContext : classDecCtx.met()) {
                    MethodNode method = (MethodNode) visit(metContext);
                    String idClass = classDecCtx.ID(0).getText();
                    method.setClassID(idClass); //id della classe a cui fa parte il metodo
                    methods.add(method);
                }

                ClassNode classNode;
                //se è null significa che la classe non estende nulla
                if (classDecCtx.ID(1) == null) {
                    classNode = new ClassNode(classDecCtx.ID(0).getText(), "", fields, methods);
                } else { //altrimenti ha una superclasse
                    classNode = new ClassNode(classDecCtx.ID(0).getText(), classDecCtx.ID().get(1).getText(), fields, methods);
                }

                classDecs.add(classNode);
            }

            List<Node> letDec = new ArrayList<>();
            if (ctx.decs() != null) {
                for (FOOLParser.DecContext dec : ctx.decs().dec()) {
                    letDec.add(visit(dec));
                }
            }

            Node letInexpORstms;
            if (ctx.exp() != null) {
                letInexpORstms = new LetInExpNode(letDec, visit(ctx.exp()));
            } else {
                List<Node> statements = new ArrayList<>();
                for (StmContext stm : ctx.stms().stm()) {
                    statements.add(visit(stm));
                }
                letInexpORstms = new LetInStmsNode(letDec, statements);
            }

            return new ClassDecNode(classDecs, letInexpORstms);

        } catch (TypeException e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    @Override
    public Node visitClassdec(ClassdecContext ctx) {
        return super.visitClassdec(ctx);
    }

    @Override
    public Node visitNewExp(NewExpContext ctx) {

        //argumenti per il costruttore della classe
        List<Node> arguments = new ArrayList<>();

        //ID della classe che si vuole istanziare
        String classID = ctx.ID().getText();

        for (ExpContext exp : ctx.exp()) {
            arguments.add(visit(exp));
        }

        return new InstanceNode(classID, arguments, ctx);
    }

    @Override
    public Node visitMetExp(MetExpContext ctx) {
        //gestione parametri del metodo
        List<Node> params = new ArrayList<>();
        for (ExpContext a : ctx.funcall().exp()) {
            params.add(visit(a));
        }
        String idVar = ctx.ID().getText();
        String idMethod = ctx.funcall().ID().getText();

        return new MethodCallNode(idVar, idMethod, params, ctx);
    }

    @Override
    public Node visitMet(MetContext ctx) {
        try {
            FunContext funContext = ctx.fun();
            List<ParameterNode> parameterNodeArrayList = new ArrayList<>();

            //Parametri
            for (int i = 0; i < funContext.vardec().size(); i++) {
                VardecContext vardecContext = funContext.vardec().get(i);
                //System.out.println( visit(vardecContext.type()).typeCheck());
                parameterNodeArrayList.add(
                        new ParameterNode(
                                vardecContext.ID().getText(),
                                visit(vardecContext.type()).typeCheck(),
                                i + 1,
                                vardecContext)
                );
            }
            //Dichiarazioni locali
            List<Node> nestedDeclarations = new ArrayList<>();
            if (funContext.letVar() != null) {
                for (VarasmContext decContext : funContext.letVar().varasm())
                    nestedDeclarations.add(visit(decContext));
            }
            List<Node> body = new ArrayList<>();
            if (ctx.fun().exp() != null) {
                body.add(visit(ctx.fun().exp()));
            } else {
                for (StmContext stm : ctx.fun().stms().stm()) {
                    body.add(visit(stm));
                }
            }

            return new MethodNode(
                    ctx.fun().ID().getText(),
                    funContext.type() == null ? new VoidType() : visit(funContext.type()).typeCheck(),
                    parameterNodeArrayList,
                    nestedDeclarations,
                    body,
                    ctx);


        } catch (TypeException e) {
            return null;
        }
    }

    @Override
    public Node visitBaseExp(BaseExpContext ctx) {
        return visit(ctx.exp());
    }

    @Override
    public Node visitVardec(VardecContext ctx) {
        return super.visitVardec(ctx);
    }

    @Override
    public Node visitFunExp(FunExpContext ctx) {
        return super.visitFunExp(ctx);
    }

    @Override
    public Node visitDec(DecContext ctx) {
        return super.visitDec(ctx);
    }

    @Override
    public Node visitDecs(DecsContext ctx) {
        return super.visitDecs(ctx);
    }

    @Override
    public Node visitLetVar(LetVarContext ctx) {
        return super.visitLetVar(ctx);
    }


}
