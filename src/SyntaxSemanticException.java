import org.antlr.v4.runtime.ParserRuleContext;

public class SyntaxSemanticException extends RuntimeException {

    public SyntaxSemanticException(String errorDescription, ParserRuleContext ctx) {
        super(createMessage(errorDescription, ctx));
        System.out.println("Syntax/semantic error");
    }

    public SyntaxSemanticException(String errorDescription, ParserRuleContext ctx, boolean showCTX) {
        super(createMessage(errorDescription, ctx, showCTX));
        System.out.println("Syntax/semantic error");
    }

    public static String createMessage(String errorDescription, ParserRuleContext ctx) {
        int lineNumber = ctx.start.getLine();
        String[] lines = Main.cs.toString().split("\n");
        String line = lines[lineNumber - 1];
        ;
        return "\n\t" + errorDescription + ": (" + ctx.getText() + ")" +
                "\n\t" + "Error occurred on line " + lineNumber + ": " + line +
                "\n\t" + "at input file.(input.txt:" + ctx.start.getLine() + ")\n";
    }

    public static String createMessage(String errorDescription, ParserRuleContext ctx, boolean showCTX) {
        int lineNumber = ctx.start.getLine();
        String[] lines = Main.cs.toString().split("\n");
        String line = lines[lineNumber - 1];
        ;
        return "\n\t" + errorDescription +
                "\n\t" + "Error occurred on line " + lineNumber + ": " + line +
                "\n\t" + "at input file.(input.txt:" + ctx.start.getLine() + ")\n";
    }
}