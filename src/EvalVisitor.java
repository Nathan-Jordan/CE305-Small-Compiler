import java.util.*;

public class EvalVisitor extends ExprBaseVisitor<Number> {

    private final Map<String, Number> variables = new HashMap<>();
    private final Stack<Boolean> stackBool = new Stack<>();
/*
    @Override
    public Number visitStart(ExprParser.StartContext ctx) {
        visitStatements(ctx.statement());

        return null;
    }

    @Override
    public Number visitStatement(ExprParser.StatementContext ctx) {
        return visit(ctx.getChild(0));
    }

    @Override
    public Number visitIfStatement(ExprParser.IfStatementContext ctx) {
        //Evaluate each boolean expression in turn and add it to the stack
        boolean ifTrue = false;

        for (int i = 0; i < ctx.boolExpression().size(); i++) {
            visit(ctx.boolExpression(i));

            if (stackBool.pop()) {
                visitStatements(ctx.block(i).statement());
                ifTrue = true;
                break;
            }
        }

        //if there is an 'else' statement and if an 'if/elseIf' has not been reached
        if (ctx.elseBlock != null && !ifTrue) {
            visitStatements(ctx.elseBlock.statement());
        }

        return null;
    }

    @Override
    public Number visitWhileStatement(ExprParser.WhileStatementContext ctx) {
        //Evaluate the boolean expression and add it to the stack
        visit(ctx.boolExpression());

        while (stackBool.pop()) {
            visitStatements(ctx.block().statement());
            visit(ctx.boolExpression());
        }

        return null;
    }

    @Override
    public Number visitForStatement(ExprParser.ForStatementContext ctx) {
        visit(ctx.varInitialise);

        //Evaluate the boolean expression and add it to the stack
        visit(ctx.boolExpression());

        while (stackBool.pop()) {
            visitStatements(ctx.block().statement());
            visit(ctx.step);
            visit(ctx.boolExpression());
        }

        return null;
    }

    @Override
    public Number visitAssignment(ExprParser.AssignmentContext ctx) {
        return super.visitAssignment(ctx);
    }

    @Override
    public Number visitAssignmentOperators(ExprParser.AssignmentOperatorsContext ctx) {
        return super.visitAssignmentOperators(ctx);
    }

    @Override
    public Number visitVarAssignment(ExprParser.VarAssignmentContext ctx) {
        String identifier = ctx.identifier.getText();
        Number value = visit(ctx.value);

        variables.put(identifier, value);

        return null;
    }

    private void visitStatements(List<ExprParser.StatementContext> statements) {
        for (ExprParser.StatementContext statement : statements) {
            if (statement.expression() != null) {
                System.out.println(visit(statement));
            } else {
                visit(statement);
            }
        }
    }

    @Override
    public Number visitComparison(ExprParser.ComparisonContext ctx) {
        double left = visit(ctx.left).doubleValue();
        double right = visit(ctx.right).doubleValue();

        switch (ctx.op.getText()) {
            case "==" -> stackBool.push(left == right);
            case "!=" -> stackBool.push(left != right);
            case ">"  -> stackBool.push(left > right);
            case ">=" -> stackBool.push(left >= right);
            case "<"  -> stackBool.push(left < right);
            case "<=" -> stackBool.push(left <= right);
        }

        return null;
    }

    @Override
    public Number visitAndOr(ExprParser.AndOrContext ctx) {
        //Evaluate both the left and right-side boolean expressions (adds it to the stack)
        visit(ctx.left);
        visit(ctx.right);

        boolean result = false;

        switch (ctx.op.getText()) {
            case "&&"  -> result = stackBool.pop() && stackBool.pop();
            case "||" -> result = stackBool.pop() || stackBool.pop();
        }

        stackBool.push(result);

        return null;
    }

    @Override
    public Number visitNegate(ExprParser.NegateContext ctx) {
        visit(ctx.boolExpression());

        if (ctx.negate != null) {
            stackBool.push(!stackBool.pop());
        }

        return null;
    }

    @Override
    public Number visitBoolBrackets(ExprParser.BoolBracketsContext ctx) {
        visit(ctx.boolExpression());

        return null;
    }

    @Override
    public Number visitMult(ExprParser.MultContext ctx) {
        Number left = visit(ctx.left);
        Number right = visit(ctx.right);

        double result = 0;

        switch (ctx.op.getText()) {
            case "*" -> result = left.doubleValue() * right.doubleValue();
            case "^" -> result = Math.pow(left.doubleValue(), right.doubleValue());
        }

        //If both inputs and the result are not integers
        if (left instanceof Integer && right instanceof Integer && result % 1 == 0) {
            return (int) result;
        } else {
            return result;
        }
    }

    @Override
    public Number visitDivision(ExprParser.DivisionContext ctx) {
        Number left = visit(ctx.left);
        Number right = visit(ctx.right);

        double result = 0;

        if (right.doubleValue() == 0) {
            throw new SyntaxException("Division by zero", ctx);
        }

        switch (ctx.op.getText()) {
            case "/" -> result = left.doubleValue() / right.doubleValue();
            case "%" -> result = left.doubleValue() % right.doubleValue();
        }

        //If both inputs and the result are not integers
        if (left instanceof Integer && right instanceof Integer && result % 1 == 0) {
            return (int) result;
        } else {
            return result;
        }
    }

    @Override
    public Number visitAdd(ExprParser.AddContext ctx) {
        Number left = visit(ctx.left);
        Number right = visit(ctx.right);

        double result;

        if (Objects.equals(ctx.op.getText(), "+")) {
            result = left.doubleValue() + right.doubleValue();
        } else {
            result = left.doubleValue() - right.doubleValue();
        }

        //If both inputs and the result are not integers
        if (left instanceof Integer && right instanceof Integer) {
            return (int) result;
        } else {
            return result;
        }
    }

    @Override
    public Number visitNumber(ExprParser.NumberContext ctx) {
        String number = ctx.getText();

        if (number.contains(".")) {
            return Double.parseDouble(number);
        } else {
            return Integer.parseInt(number);
        }
    }

    @Override
    public Number visitId(ExprParser.IdContext ctx) {
        if (!variables.containsKey(ctx.getText())) {
            throw new SyntaxException("Variable is not defined", ctx);
        }

        return variables.get(ctx.getText());
    }

    @Override
    public Number visitExpressionBrackets(ExprParser.ExpressionBracketsContext ctx) {
        return visit(ctx.expression());
    }*/
}
