import org.antlr.v4.gui.TreeViewer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import javax.swing.*;

import java.io.*;
import java.util.Arrays;

import static org.antlr.v4.runtime.CharStreams.fromFileName;

public class Main {

    public static boolean error;
    static CharStream cs;
    static ExprLexer lexer;
    static ExprParser parser;
    static ParseTree parseTree;


    public static void main(String[] args) throws IOException {
        //Error checking
        //Check for parsing errors
        System.out.println("- Parsing:");
        getNewParseTree();

        if (error) {
            System.out.println("Parsing error");
            System.exit(1);
        }
        System.out.println("Parsing successful\n");

        //Check for syntax/semantics errors
        System.out.println("- Checking syntax/semantics");
        SyntaxSemanticsVisitor syntaxSemanticsVisitor = new SyntaxSemanticsVisitor();
        syntaxSemanticsVisitor.visit(parseTree);
        System.out.println("Syntax/semantics correct\n");


        System.out.println("- Input:");
        System.out.println(cs);



        //Translated into python
        getNewParseTree();
        TranslatorVisitor translatorVisitor = new TranslatorVisitor();
        System.out.println("\n\n- Translation into Python:");
        String python = translatorVisitor.visit(parseTree);
        System.out.println(python);



        //Running in python
        System.out.println("\n- Running in python:");
        //Write the script to a file
        FileWriter writer = new FileWriter("./src/script.py");
        writer.write(python);
        writer.close();

        //Execute the script using ProcessBuilder
        ProcessBuilder processBuilder = new ProcessBuilder("python", "./src/script.py");
        Process process = processBuilder.start();

        //Read the output from the script
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
        reader.close();



        //Translated into python
        getNewParseTree();
        ASTVisitor astVisitor = new ASTVisitor();
        System.out.println("\n\n- Translation into Python:");
        astVisitor.visit(parseTree);


        //Visualising the parse tree AST
        //Referenced from - https://stackoverflow.com/questions/23809005/how-to-display-antlr-tree-gui
        //Display the PARSETREE using the TreeViewer - because this seems impossible
        JFrame frame = new JFrame("AST");
        TreeViewer viewer = new TreeViewer(Arrays.asList(parser.getRuleNames()), parseTree);
        frame.add(viewer);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public static void getNewParseTree() {
        try {
            cs = fromFileName("./src/input.txt");
            lexer = new ExprLexer(cs);
            lexer.removeErrorListeners();
            lexer.addErrorListener(new ErrorListener());

            parser = new ExprParser(new CommonTokenStream(lexer));
            parser.removeErrorListeners();
            parser.addErrorListener(new ErrorListener());
            parseTree = parser.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void getNewParseTree(CharStream input) {
        try {
            cs = input;
            lexer = new ExprLexer(cs);
            lexer.removeErrorListeners();
            lexer.addErrorListener(new ErrorListener());

            parser = new ExprParser(new CommonTokenStream(lexer));
            parser.removeErrorListeners();
            parser.addErrorListener(new ErrorListener());
            parseTree = parser.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getRepeatedString(String repeatedString, int number) {
        return new String(new char[number]).replace("\0", repeatedString);
    }
}