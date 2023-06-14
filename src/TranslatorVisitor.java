import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.*;

public class TranslatorVisitor extends ExprBaseVisitor<String> {

    Map<String, String> functionToPythonMethod = new HashMap<>();
    StringBuilder result = new StringBuilder();
    boolean randomImported = false;
    int currentIndent = 0;


    public TranslatorVisitor() {
        functionToPythonMethod.put("print", "print");
        functionToPythonMethod.put("absInteger", "abs");
        functionToPythonMethod.put("absDouble", "abs");
        functionToPythonMethod.put("round", "round");
        functionToPythonMethod.put("random", "random.random");
    }

    @Override
    public String visitStart(ExprParser.StartContext ctx) {
        visit(ctx.block());
        return result.toString();
    }

    @Override
    public String visitStatement(ExprParser.StatementContext ctx) {
        return visit(ctx.getChild(0));
    }

    @Override
    public String visitFunctionDef(ExprParser.FunctionDefContext ctx) {
        result.append("def ").append(getValidID(ctx.identifier.getText())).append("(");

        for (ExprParser.FunctionParameterAssignmentContext parameter : ctx.parameterDeclatration().functionParameterAssignment()) {
            result.append(visit(parameter)).append(" ,");
        }
        if (result.charAt(result.length() - 1) == ',') {
            result.delete(result.length() - 2, result.length());
        }

        currentIndent++;
        result.append("):\n");
        if (ctx.returnValue == null) {
            ifBlockBlank(ctx.block());
        }
        visit(ctx.block());


        if (ctx.returnValue != null) {
            appendIndents(currentIndent);
            result.append("return ").append(visit(ctx.returnValue));
        }
        currentIndent--;

        return "";
    }

    @Override
    public String visitFunctionCall(ExprParser.FunctionCallContext ctx) {
        String mappedFunction = functionToPythonMethod.get(ctx.identifier.getText());
        String functionName;

        if (mappedFunction == null) {
            functionName = getValidID(ctx.identifier.getText());
        } else {
            functionName = mappedFunction;
            if (functionName.contains("random.") && !randomImported) {
                randomImported = true;
                result.insert(0, "import random\n\n");
            }
        }

        StringBuilder functionCall = new StringBuilder(functionName + "(");

        for (ExprParser.ExpressionContext argument : ctx.expression()) {
            functionCall.append(visit(argument));
        }

        return functionCall + ")";
    }

    @Override
    public String visitFunctionParameterAssignment(ExprParser.FunctionParameterAssignmentContext ctx) {
        return ctx.identifier.getText();
    }


    @Override
    public String visitIfStatement(ExprParser.IfStatementContext ctx) {
        currentIndent++;

        //if block
        result.append("if ").append(visit(ctx.expression(0))).append(":\n");

        ifBlockBlank(ctx.block(0));
        visit(ctx.block(0));


        //elseIf block/s
        for (int i = 1; i < ctx.expression().size(); i++) {
            appendIndents(currentIndent - 1);
            result.append("elif ").append(visit(ctx.expression(i))).append(":\n");

            ifBlockBlank(ctx.block(i));
            visit(ctx.block(i));
        }


        //else block
        if (ctx.elseBlock != null) {
            appendIndents(currentIndent - 1);
            result.append("else:\n");

            ifBlockBlank(ctx.elseBlock);
            visit(ctx.elseBlock);
        }

        result.deleteCharAt(result.length() - 1);
        currentIndent--;
        return "";
    }

    private void appendIndents(int indent) {
        result.append(Main.getRepeatedString("\t", indent));
    }

    private void ifBlockBlank(ExprParser.BlockContext ctx) {
        if (ctx.getText().isBlank()) {
            appendIndents(currentIndent);
            result.append("None");
        }
    }

    @Override
    public String visitWhileStatement(ExprParser.WhileStatementContext ctx) {
        currentIndent++;

        result.append("while ");
        result.append(visit(ctx.expression()));
        result.append(":\n");
        ifBlockBlank(ctx.block());
        visit(ctx.block());

        currentIndent--;
        return "";
    }

    @Override
    public String visitForStatement(ExprParser.ForStatementContext ctx) {
        result.append(visit(ctx.varInitialise)).append("\n");
        appendIndents(currentIndent);
        result.append("while ");
        result.append(visit(ctx.condition));
        result.append(":\n");
        currentIndent++;

        visit(ctx.block());
        appendIndents(currentIndent);
        result.append(visit(ctx.step));

        currentIndent--;
        return "";
    }

    @Override
    public String visitBlock(ExprParser.BlockContext ctx) {
        if (ctx.children == null) {
            return "";
        }

        for (ParseTree child : ctx.children) {

            if (child instanceof ExprParser.StatementContext) {
                appendIndents(currentIndent);
                result.append(visit(child));
                result.append("\n");

            } else {
                //If the terminal node is newlines
                TerminalNode terminal = (TerminalNode) child;
                if (terminal.getSymbol().getType() == Main.parser.getTokenType("NEWLINES")) {

                    //If the last entry is \n remove it and add the newlines
                    if (result.toString().endsWith("\n")) {
                        result.deleteCharAt(result.length() - 1);
                    }
                    result.append(child.getText().replace(" ", ""));
                }
            }
        }
        return "";
    }

    @Override
    public String visitVarDeclaration(ExprParser.VarDeclarationContext ctx) {
        String variableName = getValidID(ctx.identifier.getText());
        String value;
        if (ctx.value != null) {
            value = visit(ctx.value);
        } else {
            value = "None";
        }

        return variableName + " = " + value;
    }

    @Override
    public String visitAssignment(ExprParser.AssignmentContext ctx) {
        String variableName = getValidID(ctx.identifier.getText());
        String value = visit(ctx.value);

        return variableName + " = " + value;
    }

    @Override
    public String visitAssignmentOperators(ExprParser.AssignmentOperatorsContext ctx) {
        String variableName = getValidID(ctx.identifier.getText());
        String value = visit(ctx.value);


        String result = findStringConcatenationAddEquals(ctx);

        if (result != null) {
            return variableName + result;
        }

        return variableName + " " + ctx.op.getText() + " " + value;
    }

    @Override
    public String visitAssignmentIncDec(ExprParser.AssignmentIncDecContext ctx) {
        String variableName = getValidID(ctx.identifier.getText());

        return variableName + " = " + variableName + " " + ctx.op.getText().charAt(0) + " 1";
    }

    @Override
    public String visitExpUnary(ExprParser.ExpUnaryContext ctx) {
        return super.visitExpUnary(ctx);
    }

    @Override
    public String visitExpMult(ExprParser.ExpMultContext ctx) {
        return getExpression(ctx);
    }

    @Override
    public String visitExpDivision(ExprParser.ExpDivisionContext ctx) {
        return getExpression(ctx);
    }

    @Override
    public String visitExpAdd(ExprParser.ExpAddContext ctx) {
        String result = findStringConcatenationAdd(ctx);

        if (result != null) {
            return result;
        }

        return getExpression(ctx);
    }

    private String findStringConcatenationAdd(ExprParser.ExpAddContext ctx) {
        StringBuilder result = new StringBuilder();

        if (ctx.op.getText().equals("+")) {
            for (ExprParser.ExpressionContext expression : SyntaxSemanticsVisitor.stringsToWrap) {
                if (expressionsEqual(expression, ctx.right)) {
                    result.append(visit(ctx.left)).append(" + str(").append(visit(ctx.right)).append(")");
                    return result.toString();
                } else if (expressionsEqual(expression, ctx.left)) {
                    result.append("str(").append(visit(ctx.left)).append(") + ").append(visit(ctx.right));
                    return result.toString();
                }
            }
        }

        return null;
    }
    private String findStringConcatenationAddEquals(ExprParser.AssignmentOperatorsContext ctx) {
        StringBuilder result = new StringBuilder();

        if (ctx.op.getText().contains("+")) {
            for (ExprParser.ExpressionContext expression : SyntaxSemanticsVisitor.stringsToWrap) {
                if (expressionsEqual(expression, ctx.value)) {
                    result.append("=+ str(").append(visit(ctx.value)).append(")");
                    return result.toString();
                }
            }
        }

        return null;
    }

    private boolean expressionsEqual(ExprParser.ExpressionContext i, ExprParser.ExpressionContext ctx) {
        return ctx.start.getStartIndex() == i.start.getStartIndex()
        && ctx.stop.getStartIndex()  == i.stop.getStartIndex()
        && Objects.equals(ctx.getText(), i.getText())
        && i.children.size() == ctx.children.size()
        && i.invokingState == ctx.invokingState
        && Objects.equals(i.parent.getText(), ctx.parent.getText())
        && i.getRuleIndex() == ctx.getRuleIndex();
    }

    @Override
    public String visitExpAndOr(ExprParser.ExpAndOrContext ctx) {
        String left = visit(ctx.getChild(0));
        String right = visit(ctx.getChild(2));
        String operator = "";

        switch (ctx.op.getText()) {
            case "||" -> operator = "or";
            case "&&" -> operator = "and";
        }

        return left + " " + operator + " " + right;
    }

    @Override
    public String visitExpComparison(ExprParser.ExpComparisonContext ctx) {
        return getExpression(ctx);
    }

    @Override
    public String visitExpNegate(ExprParser.ExpNegateContext ctx) {
        return "not " + visit(ctx.expression());
    }

    @Override
    public String visitExpBrackets(ExprParser.ExpBracketsContext ctx) {
        return "(" + visit(ctx.expression()) + ")";
    }

    @Override
    public String visitExpFunctionCall(ExprParser.ExpFunctionCallContext ctx) {
        return visit(ctx.functionCall());
    }

    @Override
    public String visitExpBoolean(ExprParser.ExpBooleanContext ctx) {
        return ctx.getText().substring(0,1).toUpperCase() + ctx.getText().substring(1);
    }

    @Override
    public String visitExpId(ExprParser.ExpIdContext ctx) {
        return getValidID(ctx.getText());
    }

    @Override
    public String visitExpNumber(ExprParser.ExpNumberContext ctx) {
        return ctx.getText();
    }

    @Override
    public String visitExpString(ExprParser.ExpStringContext ctx) {
        return ctx.getText();
    }

    @Override
    public String visitExpNull(ExprParser.ExpNullContext ctx) {
        return "None";
    }

    private String getExpression(ExprParser.ExpressionContext ctx) {
        String left = visit(ctx.getChild(0));
        String right = visit(ctx.getChild(2));

        return left + " " + ctx.getChild(1) + " " + right;
    }

    private String getValidID(String identifier) {
        Set<String> reservedKeywords = new HashSet<>(Arrays.asList(
                "False", "None", "True", "and", "as", "assert", "async", "await", "break", "class", "continue", "def",
                "del", "elif", "else", "except", "finally", "for", "from", "global", "if", "import", "in", "is", "lambda",
                "nonlocal", "not", "or", "pass", "raise", "return", "try", "while", "with", "yield", "abs", "all", "any",
                "ascii", "bin", "bool", "bytearray", "bytes", "callable", "chr", "classmethod", "compile", "complex",
                "delattr", "dict", "dir", "divmod", "enumerate", "eval", "exec", "filter", "float", "format", "frozenset",
                "getattr", "globals", "hasattr", "hash", "help", "hex", "id", "input", "int", "isinstance", "issubclass",
                "iter", "len", "list", "locals", "map", "max", "memoryview", "min", "next", "object", "oct", "open", "ord",
                "pow", "print", "property", "range", "repr", "reversed", "round", "set", "setattr", "slice", "sorted",
                "staticmethod", "str", "sum", "super", "tuple", "type", "vars", "zip", "__import__"));

        if (reservedKeywords.contains(identifier)) {
            return identifier + "1";
        }

        return identifier;
    }
}
