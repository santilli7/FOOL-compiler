import ast.Node;
import codegen.DispatchTable;
import codegen.ExecuteVM;
import codegen.SVMLexer;
import codegen.SVMParser;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import parser.FOOLLexer;
import parser.FOOLParser;
import parser.FoolVisitorImpl;
import symboltable.SymbolTable;
import throwable.*;
import type.Type;

import java.io.*;
import java.lang.StackOverflowError;
import java.util.ArrayList;
import java.util.List;

public class Start {

    public static void main(String[] args) {
        try {
            String fileName = "src/main.fool";
            CharStream input = CharStreams.fromFileName(fileName);

            //LEXER
            System.out.println("[ LEXICAL ANALYSIS ]");
            FOOLLexer lexer = new FOOLLexer(input);
            if (lexer.lexicalErrors.size() > 0) throw new LexerException(lexer.lexicalErrors);
            CommonTokenStream tokens = new CommonTokenStream(lexer);

            //PARSER
            System.out.println("[ SYNTAX ANALYSIS ]");
            FOOLParser parser = new FOOLParser(tokens);
            FOOLParser.ProgContext progContext = parser.prog(); //in caso di errore solleva l'eccezione ed il programma termina
            if (parser.getNumberOfSyntaxErrors() > 0)
                throw new ParserException("Errori rilevati: " + parser.getNumberOfSyntaxErrors() + "\n");

            //SEMANTIC
            System.out.println("[ SEMANTIC ANALYSIS ]");
            FoolVisitorImpl visitor = new FoolVisitorImpl();
            Node ast = visitor.visit(progContext);
            SymbolTable env = new SymbolTable();
            List<String> err = ast.checkSemantics(env);
            if (err.size() > 0) throw new SemanticException(err);

            //TYPE CHECKING
            System.out.println("[ TYPE CHECKING ]");
            Type type = ast.typeCheck(); //type-checking bottom-up
            System.out.println("Type checking: " + type.toPrint().toUpperCase());

            //CODE GENERATION
            System.out.println("[ CODE GEN ]");
            String code = ast.codeGeneration();
            code += "\n" + DispatchTable.generaCodiceDispatchTable();

            //scrittura su file
            File svmFile = new File("./src/codice.svm");
            BufferedWriter svmWriter = new BufferedWriter(new FileWriter(svmFile.getAbsoluteFile()));
            svmWriter.write(code);
            svmWriter.close();
            if (!type.toPrint().toUpperCase().equals("VOID")) addPrintCodeGen();
            CharStream inputASM = CharStreams.fromFileName("./src/codice.svm");
            SVMLexer lexerASM = new SVMLexer(inputASM);
            CommonTokenStream tokensASM = new CommonTokenStream(lexerASM);
            SVMParser parserASM = new SVMParser(tokensASM);
            parserASM.assembly();

            if (lexerASM.errors.size() > 0) {
                throw new LexerException(lexerASM.errors);
            }
            if (parserASM.getNumberOfSyntaxErrors() > 0) {
                throw new ParserException("Errore di parsing in SVM");
            }
            int[] bytecode = parserASM.getBytecode();
            ExecuteVM vm = new ExecuteVM(bytecode);

            ArrayList<String> out = vm.cpu();

            //per non stampare la stack commenta l'istruzione
            vm.print();

            String risultato = "No output";
            if (out.size() > 0)
                risultato = out.get(out.size() - 1);
            System.out.println("RESULT: " + risultato);

        } catch (HeapOverflowError | SegFaultError | StackOverflowError | IOException | LexerException | ParserException | SemanticException | TypeException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void addPrintCodeGen() {
        try {
            File f1 = new File("./src/codice.svm");
            FileReader fr = new FileReader(f1);
            BufferedReader br = new BufferedReader(fr);
            String line;
            List<String> lines = new ArrayList<>();
            while ((line = br.readLine()) != null) {
                if (line.contains("halt"))
                    line = line.replace("halt", "print\nhalt");
                lines.add(line + "\n");
            }
            fr.close();
            br.close();

            FileWriter fw = new FileWriter(f1);
            BufferedWriter out = new BufferedWriter(fw);
            for (String s : lines)
                out.write(s);
            out.flush();
            out.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
