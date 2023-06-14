import org.antlr.v4.runtime.ParserRuleContext;

import java.util.*;

public class SyntaxSemanticsVisitor extends ExprBaseVisitor<Variable> {

    public static HashSet<ExprParser.ExpressionContext> stringsToWrap = new HashSet<>();
    Map<String, Class<?>> typeByString = new HashMap<>();
    Map<Class<?>, String> typeNameByClass = new HashMap<>();
    Map<Class<?>, String> typeNameOutputByType = new HashMap<>();

    private final Map<String, SymbolTable>  symbolTable = new HashMap<>();

    int scopeLevel = 0;
    String scope;


    public SyntaxSemanticsVisitor() {
        scope = "main";
        symbolTable.put(scope, new SymbolTable());

        //Built-in functions
        mainSymbolTable().addFunction("print()", void.class, List.of(Object.class));
        mainSymbolTable().addFunction("random()", double.class, List.of());
        mainSymbolTable().addFunction("absInteger()", int.class, List.of(int.class));
        mainSymbolTable().addFunction("absDouble()", double.class, List.of(double.class));
        mainSymbolTable().addFunction("round()", int.class, List.of(double.class));


        //Mapping maps
        typeNameByClass.put(int.class, "Integer");
        typeNameByClass.put(double.class, "Double");
        typeNameByClass.put(String.class, "String");
        typeNameByClass.put(boolean.class, "Boolean");
        typeNameByClass.put(Object.class, "Object");

        typeNameOutputByType.put(int.class, "an Integer");
        typeNameOutputByType.put(double.class, "a Double");
        typeNameOutputByType.put(String.class, "a String");
        typeNameOutputByType.put(boolean.class, "a Boolean");
        typeNameOutputByType.put(null, "Null");
        typeNameOutputByType.put(void.class, "Void");

        //Name>class
        typeByString.put("String", String.class);
        typeByString.put("Integer", int.class);
        typeByString.put("Double", double.class);
        typeByString.put("Boolean", boolean.class);

        //Arithmetic operators
        //Integers
        typeByString.put("int+int", int.class);
        typeByString.put("int-int", int.class);
        typeByString.put("int/int", int.class);
        typeByString.put("int%int", int.class);
        typeByString.put("int*int", int.class);
        typeByString.put("int^int", int.class);

        //Doubles
        typeByString.put("double+double", double.class);
        typeByString.put("int+double", double.class);
        typeByString.put("double+int", double.class);
        typeByString.put("double-double", double.class);
        typeByString.put("int-double", double.class);
        typeByString.put("double-int", double.class);
        typeByString.put("double/double", double.class);
        typeByString.put("int/double", double.class);
        typeByString.put("double/int", double.class);
        typeByString.put("double%double", double.class);
        typeByString.put("int%double", double.class);
        typeByString.put("double%int", double.class);
        typeByString.put("double*double", double.class);
        typeByString.put("int*double", double.class);
        typeByString.put("double*int", double.class);
        typeByString.put("double^double", double.class);
        typeByString.put("int^double", double.class);
        typeByString.put("double^int", double.class);

        //Strings (concatenation)
        typeByString.put("int+class java.lang.String", String. class);
        typeByString.put("double+class java.lang.String", String.class);
        typeByString.put("boolean+class java.lang.String", String.class);
        typeByString.put("class java.lang.String+class java.lang.String", String.class);
        typeByString.put("class java.lang.String+int", String.class);
        typeByString.put("class java.lang.String+double", String.class);
        typeByString.put("class java.lang.String+boolean", String.class);


        //Logical operators
        //Same types
        typeByString.put("null==null", boolean.class);
        typeByString.put("null!=null", boolean.class);

        typeByString.put("boolean&&boolean", boolean.class);
        typeByString.put("boolean||boolean", boolean.class);
        typeByString.put("boolean==boolean", boolean.class);
        typeByString.put("boolean!=boolean", boolean.class);

        typeByString.put("int==int", boolean.class);
        typeByString.put("int!=int", boolean.class);
        typeByString.put("int>int", boolean.class);
        typeByString.put("int>=int", boolean.class);
        typeByString.put("int<int", boolean.class);
        typeByString.put("int<=int", boolean.class);

        typeByString.put("double==double", boolean.class);
        typeByString.put("double!=double", boolean.class);
        typeByString.put("double>double", boolean.class);
        typeByString.put("double>=double", boolean.class);
        typeByString.put("double<double", boolean.class);
        typeByString.put("double<=double", boolean.class);

        typeByString.put("class java.lang.String==class java.lang.String", boolean.class);
        typeByString.put("class java.lang.String!=class java.lang.String", boolean.class);

        //Nulls
        typeByString.put("null==int", boolean.class);
        typeByString.put("null==double", boolean.class);
        typeByString.put("null==class java.lang.String", boolean.class);
        typeByString.put("null==boolean", boolean.class);
        typeByString.put("int==null", boolean.class);
        typeByString.put("double==null", boolean.class);
        typeByString.put("class java.lang.String==null", boolean.class);
        typeByString.put("boolean==null", boolean.class);

        typeByString.put("null!=int", boolean.class);
        typeByString.put("null!=double", boolean.class);
        typeByString.put("null!=class java.lang.String", boolean.class);
        typeByString.put("null!=boolean", boolean.class);
        typeByString.put("int!=null", boolean.class);
        typeByString.put("double!=null", boolean.class);
        typeByString.put("class java.lang.String!=null", boolean.class);
        typeByString.put("boolean!=null", boolean.class);

        //Numbers
        typeByString.put("double==int", boolean.class);
        typeByString.put("double!=int", boolean.class);
        typeByString.put("double>int", boolean.class);
        typeByString.put("double>=int", boolean.class);
        typeByString.put("double<int", boolean.class);
        typeByString.put("double<=int", boolean.class);
        typeByString.put("int==double", boolean.class);
        typeByString.put("int!=double", boolean.class);
        typeByString.put("int>double", boolean.class);
        typeByString.put("int>=double", boolean.class);
        typeByString.put("int<double", boolean.class);
        typeByString.put("int<=double", boolean.class);
    }

    @Override
    public Variable visitStart(ExprParser.StartContext ctx) {
        super.visitStart(ctx);
        return null;
    }

    @Override
    public Variable visitStatement(ExprParser.StatementContext ctx) {
        return super.visitStatement(ctx);
    }

    @Override
    public Variable visitFunctionDef(ExprParser.FunctionDefContext ctx) {
        //Store parent's scope and level
        String parentScope = scope;
        int parentScopeLevel = scopeLevel;

        //Check if already exists
        String functionName = ctx.identifier.getText() + "()";
        if (mainSymbolTable().isFunctionDefined(functionName)) {
            throw new SyntaxSemanticException("Error: Function \"" + functionName + "\" is already defined", ctx.identifier);
        }

        //Reset scope variables
        scope = functionName;
        scopeLevel = 0; //0 because it gets incremented in block()

        //Add new symbol table for the function
        symbolTable.putIfAbsent(scope, new SymbolTable());


        List<Class<?>> parameters = new ArrayList<>();
        //For each parameter
        for (ExprParser.FunctionParameterAssignmentContext parameter : ctx.parameterDeclatration().functionParameterAssignment()) {
            String variableName = parameter.identifier.getText();
            Class<?> parameterType = getTypeByString(parameter.DATATYPE().getText());
            parameters.add(parameterType);


            if (isVariableDefined(variableName, scopeLevel)) {
                throw new SyntaxSemanticException("Error: Variable \"" + variableName + "\" is already defined in the parameter of \"" + functionName  +"\"", ctx.parameterDeclatration());
            }
            currentSymbolTable().addVariable(variableName, parameterType, scopeLevel);
        }


        visit(ctx.block());


        Class<?> returnType = void.class;

        //If the function returns something
        if (ctx.returnValue != null) {
            //Get return type of function
            returnType = getTypeByString(ctx.DATATYPE().getText());

            Variable valueReturned = visit(ctx.returnValue);

            //If the return type does not match the returned value
            if (typeMismatch(valueReturned, returnType)) {
                throw new SyntaxSemanticException("Type mismatch: Type of the returned value (" + getNameOfType(valueReturned.type) +
                        ") must match the return type (" + getNameOfType(returnType) + ")", ctx.returnValue);
            }
        }

        mainSymbolTable().addFunction(functionName, returnType, parameters);

        //Reset scope and level to parent's values
        scope = parentScope;
        scopeLevel = parentScopeLevel;
        return null;
    }

    @Override
    public Variable visitFunctionCall(ExprParser.FunctionCallContext ctx) {
        String functionName = ctx.identifier.getText() + "()";

        if (!mainSymbolTable().isFunctionDefined(functionName)) {
            throw new SyntaxSemanticException("Error: Function \"" + functionName + "\" is not defined", ctx.identifier);
        }

        checkParameters(ctx, functionName);

        Class<?> returnType = getFunctionReturnType(functionName);

        return new Variable(returnType);
    }

    private void checkParameters(ExprParser.FunctionCallContext ctx, String functionName) {
        String functionNameNoBrackets = ctx.identifier.getText();

        List<ExprParser.ExpressionContext> arguments = ctx.expression();
        List<Class<?>> parameters = mainSymbolTable().getFunctionParameters(functionName);

        String functionDefinition = getFunctionDefinitionWithParams(functionNameNoBrackets, parameters);
        String functionCall = getFunctionCallWithArgs(functionNameNoBrackets, arguments);


        //Check number of args vs number of params
        if (arguments.size() != parameters.size()) {
            throw new SyntaxSemanticException("Error: Incorrect number of arguments provided to the function \"" + functionName + "\" + " +
                                          "\n\tExpected: " + parameters.size() + ":  " + functionDefinition +
                                          "\n\tProvided: " + arguments.size() + ":  " + functionCall, ctx, false);
        }

        //Check arg types vs param types
        for (int i = 0; i < arguments.size(); i++) {
            Class<?> argumentType = visit(arguments.get(i)).type;
            Class<?> parameterType = parameters.get(i);

            if(argumentType != parameterType && parameterType != Object.class) {
                throw new SyntaxSemanticException("Type mismatch: Incorrect types provided to the function \"" + functionName + "\"" +
                                              "\n\tExpected:  "  + functionDefinition +
                                              "\n\tProvided:  " + functionCall, ctx, false);
            }
        }
    }

    private String getFunctionCallWithArgs(String functionName, List<ExprParser.ExpressionContext> arguments) {
        StringBuilder functionCall = new StringBuilder(functionName).append("(");

        for (ExprParser.ExpressionContext argument : arguments) {
            Class<?> type = visit(argument).type;
            functionCall.append(getNameOfType(type)).append(", ");
        }

        functionCall.delete(functionCall.length() - 2, functionCall.length()).append(")");
        return functionCall.toString();
    }

    private String getFunctionDefinitionWithParams(String functionName, List<Class<?>> parametersTypes) {
        StringBuilder functionDefinition = new StringBuilder(functionName).append("(");

        for (Class<?> type : parametersTypes) {
            functionDefinition.append(getNameOfType(type)).append(", ");
        }

        functionDefinition.delete(functionDefinition.length() - 2, functionDefinition.length()).append(")");
        return functionDefinition.toString();
    }


    @Override
    public Variable visitIfStatement(ExprParser.IfStatementContext ctx) {
        for (ExprParser.ExpressionContext expression : ctx.expression()) {
            Variable condition = visit(expression);

            if (condition.type != boolean.class) {
                throw new SyntaxSemanticException("Invalid conditional expression: Conditional expression must result in a Boolean, not " +
                        getOutputNameOfType(condition.type), expression);
            }
        }

        return super.visitIfStatement(ctx);
    }

    @Override
    public Variable visitWhileStatement(ExprParser.WhileStatementContext ctx) {
        Variable condition = visit(ctx.condition);

        if (condition.type != boolean.class) {
            throw new SyntaxSemanticException("Invalid conditional expression: Conditional expression must result in a Boolean, not " +
                    getOutputNameOfType(condition.type), ctx.condition);
        }

        return super.visitWhileStatement(ctx);
    }

    @Override
    public Variable visitForStatement(ExprParser.ForStatementContext ctx) {

        visit(ctx.varInitialise);
        if (ctx.varInitialise instanceof ExprParser.VarDeclarationContext varDeclarationContext) {
            if (varDeclarationContext.value == null) {
                throw new SyntaxSemanticException("Error: Initial variable \"" + varDeclarationContext.identifier.getText() +
                        "\" in for loop must be initialised", ctx.varInitialise);
            }
        }

        Variable condition = visit(ctx.condition);
        if (condition.type != boolean.class) {
            throw new SyntaxSemanticException("Invalid conditional expression: Conditional expression must result in a Boolean, not " +
                    getOutputNameOfType(condition.type), ctx.condition);
        }

        if (ctx.step instanceof ExprParser.VarDeclarationContext varDeclarationContext) {
            throw new SyntaxSemanticException("Error: Step variable \"" + varDeclarationContext.identifier.getText() +
                    "\" in for loop must be \"i++ or i+=1 or i=1\"", ctx.step);
        }
        if (visit(ctx.step).type == null) {
            throw new SyntaxSemanticException("Error: Step variable \"" + ctx.step.getChild(0).getText() +  "\" in cannot be set to null", ctx.step);
        }
        visit(ctx.step);
        visit(ctx.block());

        return null;
    }

    @Override
    public Variable visitBlock(ExprParser.BlockContext ctx) {
        scopeLevel++;
        super.visitBlock(ctx);

        //currentSymbolTable().variableCleanup(1 + scopeLevel);
        currentSymbolTable().variableCleanup(scopeLevel);
        if (Objects.equals(scope, "main")) {
            scopeLevel--;
        }

        return null;
    }

    @Override
    public Variable visitVarDeclaration(ExprParser.VarDeclarationContext ctx) {
        String identifier = ctx.identifier.getText();

        if (isVariableDefined(identifier)) {
            throw new SyntaxSemanticException("Error: Variable \"" + identifier + "\" is already defined in the \"" + scope  +"\" scope", ctx);
        }

        Variable value = new Variable();
        if (ctx.value != null) {

            value = visit(ctx.value);
            //Variable not initialised
            if (value.type != null) {
                if (!Objects.equals(getNameOfType(value.type), ctx.DATATYPE().getText())) {
                    throw new SyntaxSemanticException("Type mismatch: Cannot assign " + getOutputNameOfType(value.type) +
                            " to the " + ctx.DATATYPE().getText() + " variable \"" + identifier + "\"", ctx);
                }
            }
        }

        currentSymbolTable().addVariable(identifier, value.type, scopeLevel);

        return super.visitVarDeclaration(ctx);
    }

    @Override
    public Variable visitAssignment(ExprParser.AssignmentContext ctx) {
        String identifier = ctx.identifier.getText();

        checkVariableDefined(ctx, identifier);
        checkAssignTypeMismatch(ctx, identifier, visit(ctx.value));

        return super.visitAssignment(ctx);
    }

    @Override
    public Variable visitAssignmentOperators(ExprParser.AssignmentOperatorsContext ctx) {
        String variableName = ctx.identifier.getText();

        Variable value = visit(ctx.value);
        Class<?> variableType = getVariableType(variableName);

        if (value.type == null || getVariableType(variableName) == null) {
            throw new SyntaxSemanticException("Unsupported operation: The operator \"" + ctx.op.getText() + "\" cannot be applied to "
                    + getOutputNameOfType(variableType) + " and " + getOutputNameOfType(value.type), ctx);
        }


        if (variableType == String.class && value.type != String.class) {
            stringsToWrap.add(ctx.value);
        }

        checkVariableDefined(ctx, variableName);
        checkAssignTypeMismatch(ctx, variableName, visit(ctx.value));

        return super.visitAssignmentOperators(ctx);
    }

    @Override
    public Variable visitAssignmentIncDec(ExprParser.AssignmentIncDecContext ctx) {
        String variableName = ctx.identifier.getText();

        checkVariableDefined(ctx, variableName);

        Class<?> variableType = getVariableType(variableName);

        if (variableType != int.class) {
            throw new SyntaxSemanticException("Unsupported operation: The operator \"" + ctx.op.getText() + "\" cannot be applied to the " +
                    getNameOfType(variableType) + " variable \"" + variableName + "\"", ctx);
        }

        return new Variable(variableType);
    }

    @Override
    public Variable visitExpUnary(ExprParser.ExpUnaryContext ctx) {
        Variable variable = visit(ctx.expression());

        if (variable.type != double.class && variable.type != int.class) {
            throw new SyntaxSemanticException("Unsupported operation: The unary operator \"" + ctx.op.getText() + "\" cannot be applied to " +
                    getOutputNameOfType(visit(ctx.expression()).type), ctx);
        }

        return variable;
    }

    @Override
    public Variable visitExpMult(ExprParser.ExpMultContext ctx) {
        return getExpressionResultType(ctx);
    }

    @Override
    public Variable visitExpDivision(ExprParser.ExpDivisionContext ctx) {

        double right = 1;
        try {
            right = Double.parseDouble(ctx.right.getText());
        } catch (Exception ignored) {}

        if (right == 0.0) {
            throw new SyntaxSemanticException("Error: Cannot divide by zero", ctx);
        }

        return getExpressionResultType(ctx);
    }

    @Override
    public Variable visitExpAdd(ExprParser.ExpAddContext ctx) {
        Variable left = visit(ctx.left);
        Variable right = visit(ctx.right);
        Variable resultType = getExpressionResultType(ctx);

        if (resultType.type == String.class) {
            if (left.type != String.class) {
                stringsToWrap.add(ctx.left);
            }
            if (right.type != String.class) {
                stringsToWrap.add(ctx.right);
            }
        }

        return resultType;
    }

    @Override
    public Variable visitExpAndOr(ExprParser.ExpAndOrContext ctx) {
        return getExpressionResultType(ctx);
    }

    @Override
    public Variable visitExpComparison(ExprParser.ExpComparisonContext ctx) {
        return getExpressionResultType(ctx);
    }

    @Override
    public Variable visitExpNegate(ExprParser.ExpNegateContext ctx) {
        Variable variable = visit(ctx.expression());

        if (variable.type != boolean.class) {
            throw new SyntaxSemanticException("Unsupported operation: The \"!\" operator can only be applied to Booleans, not "
                    + getNameOfType(variable.type) + "s", ctx);
        }

        return variable;
    }

    @Override
    public Variable visitExpBrackets(ExprParser.ExpBracketsContext ctx) {
        return visit(ctx.expression());
    }

    @Override
    public Variable visitExpFunctionCall(ExprParser.ExpFunctionCallContext ctx) {
        return visit(ctx.functionCall());
    }

    @Override
    public Variable visitExpBoolean(ExprParser.ExpBooleanContext ctx) {
        Variable bool;

        try {
            if (ctx.getText().equals("true")) {
                bool = new Variable(true);
            } else if (ctx.getText().equals("false")) {
                bool = new Variable(false);
            } else {
                bool = new Variable(Integer.parseInt(ctx.getText()));
            }
        } catch (Exception e) {
            throw new SyntaxSemanticException("Not a boolean", ctx);
        }

        return bool;
    }

    @Override
    public Variable visitExpId(ExprParser.ExpIdContext ctx) {
        String variableName = ctx.getText();

        checkVariableDefined(ctx, variableName);

        Class<?> variableType = getVariableType(variableName);

        return new Variable(variableType);
    }

    @Override
    public Variable visitExpNumber(ExprParser.ExpNumberContext ctx) {
        Variable number;

        try {
            if (ctx.getText().contains(".")) {
                number = new Variable(Double.parseDouble(ctx.getText()));
            } else {
                number = new Variable(Integer.parseInt(ctx.getText()));
            }
        } catch (Exception e) {
            throw new SyntaxSemanticException("Not a number", ctx);
        }

        return number;
    }

    @Override
    public Variable visitExpString(ExprParser.ExpStringContext ctx) {
        return new Variable(ctx.getText());
    }

    @Override
    public Variable visitExpNull(ExprParser.ExpNullContext ctx) {
        return new Variable(null);
    }

    private Variable getExpressionResultType(ExprParser.ExpressionContext ctx) {
        Variable left = visit(ctx.getChild(0));
        Variable right = visit(ctx.getChild(2));
        String operator = ctx.getChild(1).getText();

        Class<?> expressionResultType = checkExpression(ctx, left, right, operator);

        return new Variable(expressionResultType);
    }

    private Class<?> checkExpression(ExprParser.ExpressionContext ctx, Variable left, Variable right, String operator) {
        String key = left.type + operator + right.type;
        Class<?> expressionResultType = getTypeByString(key);

        if (expressionResultType == null) {
            throw new SyntaxSemanticException("Unsupported operation: The operator \"" + operator + "\" cannot be applied to "
                    + getOutputNameOfType(left.type) + " and " + getOutputNameOfType(right.type), ctx);
        }
        return expressionResultType;
    }

    private void checkAssignTypeMismatch(ExprParser.VarAssignmentContext ctx, String variableName, Variable value) {
        Class<?> variableType = getVariableType(variableName);

        //If either the variable or the value are null - update the symbol table
        if (variableType == null || value.type == null) {
            currentSymbolTable().updateVariableType(variableName, value.type);
        }

        if (typeMismatch(value, variableType)) {
            throw new SyntaxSemanticException("Type mismatch: Cannot assign " + getOutputNameOfType(value.type) +
                    " to the " + getNameOfType(variableType) + " variable \"" + variableName + "\"", ctx);
        }
    }

    private static boolean typeMismatch(Variable value, Class<?> variableType) {
        return !value.validType(variableType);
    }

    private void checkVariableDefined(ParserRuleContext ctx, String variableName) {
        if (!isVariableDefined(variableName)) {
            throw new SyntaxSemanticException("Error: Variable \"" + variableName + "\" is not defined in the \"" + scope  +"\" scope", ctx);
        }
    }

    public String getNameOfType(Class<?> type) {
        return typeNameByClass.get(type);
    }

    public String getOutputNameOfType(Class<?> type) {
        return typeNameOutputByType.get(type);
    }

    public Class<?> getTypeByString(String string) {
        return typeByString.get(string);
    }

    private SymbolTable mainSymbolTable() {
        return symbolTable.get("main");
    }

    private Class<?> getFunctionReturnType(String functionName) {
        return mainSymbolTable().getType(functionName, "function", 1);
    }

    private SymbolTable currentSymbolTable() {
        return symbolTable.get(scope);
    }

    private Class<?> getVariableType(String identifier) {
        return currentSymbolTable().getType(identifier, "variable", scopeLevel);
    }

    private boolean isVariableDefined(String identifier) {
        return isVariableDefined(identifier, scopeLevel);
    }

    private boolean isVariableDefined(String identifier, int scopeLevel) {
        return currentSymbolTable().isVariableDefined(identifier, scopeLevel);
    }
}
