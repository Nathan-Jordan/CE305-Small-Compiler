import org.antlr.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.*;

public class ASTVisitor extends ExprBaseVisitor<String> {

    int indent = 0;
    int tmp = 0;


    @Override
    public String visitStart(ExprParser.StartContext ctx) {
        return visit(ctx.block());
    }

    @Override
    public String visitFunctionCall(ExprParser.FunctionCallContext ctx) {
        indent+=2;
        getRepeatedString(indent);
        visit(ctx.identifier);
        indent-=2;
        return null;
    }

    @Override
    public String visitIfStatement(ExprParser.IfStatementContext ctx) {
        indent+=2;
        getRepeatedString(indent);
        visit(ctx.IF());
        visit(ctx.block(0));
        indent-=2;
        return null;
    }

    @Override
    public String visitExpComparison(ExprParser.ExpComparisonContext ctx) {
        expression(ctx);
        return null;
    }

    @Override
    public String visitExpDivision(ExprParser.ExpDivisionContext ctx) {
        expression(ctx);
        return null;
    }

    @Override
    public String visitExpAndOr(ExprParser.ExpAndOrContext ctx) {
        expression(ctx);
        return null;
    }

    @Override
    public String visitExpMult(ExprParser.ExpMultContext ctx) {
        expression(ctx);
        return null;
    }

    @Override
    public String visitExpAdd(ExprParser.ExpAddContext ctx) {
        expression(ctx);
        return null;
    }

    private void expression(ExprParser.ExpressionContext ctx) {
        tmp = indent;
        visit(ctx.getChild(0));
        indent+=2;
        getRepeatedString(indent);
        System.out.println(ctx.getChild(1).getText());
        indent+=2;
        getRepeatedString(indent);
        visit(ctx.getChild(2));

        indent = tmp;
    }


    @Override
    public String visitVarDeclaration(ExprParser.VarDeclarationContext ctx) {
        tmp = indent;
        indent+=2;
        getRepeatedString(indent);
        visit(ctx.DATATYPE());
        indent+=2;
        getRepeatedString(indent);
        visit(ctx.ID());
        indent+=2;

        if (ctx.value != null) {
            getRepeatedString(indent);
            System.out.println("=");
            indent += 2;
            getRepeatedString(indent);
            visit(ctx.expression());
        }

        indent = tmp;
        return null;
    }

    @Override
    public String visitTerminal(TerminalNode node) {

        if (!(node.getText().isBlank() || node.getText().equals(";"))) {
            System.out.println(node.getText());
        }
        return null;
    }

    public static void getRepeatedString(int number) {
        System.out.print(new String(new char[number-2]).replace("\0", " "));
        System.out.print("└─");
    }
}
