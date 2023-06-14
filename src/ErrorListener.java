import org.antlr.v4.runtime.*;

public class ErrorListener extends BaseErrorListener {

    @Override
    public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {

        if (msg.contains("token recognition error")) {
            //If not recognised token, offendingSymbol == null causes a crash
            System.err.println("line " + line + ":" + charPositionInLine + ": Invalid character");
            System.err.println(recognizer.getInputStream().toString().split("\n")[line - 1]);
            System.err.print(Main.getRepeatedString(" ", charPositionInLine));
            System.err.println("^");

        } else {
            System.err.println("line " + line + ":" + charPositionInLine + ": " + msg);

            underLineError(recognizer, (Token) offendingSymbol, line, charPositionInLine);
        }

        Main.error = true;
    }

    //Code adapted from 'The Definitive ANTLR 4 Reference' Chapter 9.2 - Altering and Redirecting ANTLR Error Messages
    private void underLineError(Recognizer<?,?> recognizer, Token offendingSymbol, int line, int charPositionInLine) {
        CommonTokenStream tokens = (CommonTokenStream) recognizer.getInputStream();
        String input = tokens.getTokenSource().getInputStream().toString();
        String[] lines = input.split("\n");


        String errorLine;
        while (true) {
            try {
                errorLine = lines[line - 1];

                if (errorLine.isBlank()) {
                    line--;
                    continue;
                }
                break;
            } catch (ArrayIndexOutOfBoundsException e) {
                line--;
            }
        }
        StringBuilder underline = new StringBuilder();

        underline.append(Main.getRepeatedString(" ", charPositionInLine));

        int start = offendingSymbol.getStartIndex();
        int stop = offendingSymbol.getStopIndex();

        if (start >= 0 && stop >= 0) {
            underline.append(Main.getRepeatedString("^", (stop - start + 1)));
        }

        if (!underline.toString().contains("^")) {
            underline.append("^");
        }

        System.err.println("\t" + errorLine);
        System.err.println("\t" + underline + "\n");
    }
}
