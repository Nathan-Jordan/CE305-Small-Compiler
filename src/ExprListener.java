// Generated from java-escape by ANTLR 4.11.1
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link ExprParser}.
 */
public interface ExprListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link ExprParser#start}.
	 * @param ctx the parse tree
	 */
	void enterStart(ExprParser.StartContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprParser#start}.
	 * @param ctx the parse tree
	 */
	void exitStart(ExprParser.StartContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExprParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(ExprParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(ExprParser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExprParser#ifStatement}.
	 * @param ctx the parse tree
	 */
	void enterIfStatement(ExprParser.IfStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprParser#ifStatement}.
	 * @param ctx the parse tree
	 */
	void exitIfStatement(ExprParser.IfStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExprParser#functionDef}.
	 * @param ctx the parse tree
	 */
	void enterFunctionDef(ExprParser.FunctionDefContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprParser#functionDef}.
	 * @param ctx the parse tree
	 */
	void exitFunctionDef(ExprParser.FunctionDefContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExprParser#parameterDeclatration}.
	 * @param ctx the parse tree
	 */
	void enterParameterDeclatration(ExprParser.ParameterDeclatrationContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprParser#parameterDeclatration}.
	 * @param ctx the parse tree
	 */
	void exitParameterDeclatration(ExprParser.ParameterDeclatrationContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExprParser#functionParameterAssignment}.
	 * @param ctx the parse tree
	 */
	void enterFunctionParameterAssignment(ExprParser.FunctionParameterAssignmentContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprParser#functionParameterAssignment}.
	 * @param ctx the parse tree
	 */
	void exitFunctionParameterAssignment(ExprParser.FunctionParameterAssignmentContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExprParser#functionCall}.
	 * @param ctx the parse tree
	 */
	void enterFunctionCall(ExprParser.FunctionCallContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprParser#functionCall}.
	 * @param ctx the parse tree
	 */
	void exitFunctionCall(ExprParser.FunctionCallContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExprParser#whileStatement}.
	 * @param ctx the parse tree
	 */
	void enterWhileStatement(ExprParser.WhileStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprParser#whileStatement}.
	 * @param ctx the parse tree
	 */
	void exitWhileStatement(ExprParser.WhileStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExprParser#forStatement}.
	 * @param ctx the parse tree
	 */
	void enterForStatement(ExprParser.ForStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprParser#forStatement}.
	 * @param ctx the parse tree
	 */
	void exitForStatement(ExprParser.ForStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExprParser#block}.
	 * @param ctx the parse tree
	 */
	void enterBlock(ExprParser.BlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprParser#block}.
	 * @param ctx the parse tree
	 */
	void exitBlock(ExprParser.BlockContext ctx);
	/**
	 * Enter a parse tree produced by the {@code VarDeclaration}
	 * labeled alternative in {@link ExprParser#varAssignment}.
	 * @param ctx the parse tree
	 */
	void enterVarDeclaration(ExprParser.VarDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by the {@code VarDeclaration}
	 * labeled alternative in {@link ExprParser#varAssignment}.
	 * @param ctx the parse tree
	 */
	void exitVarDeclaration(ExprParser.VarDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Assignment}
	 * labeled alternative in {@link ExprParser#varAssignment}.
	 * @param ctx the parse tree
	 */
	void enterAssignment(ExprParser.AssignmentContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Assignment}
	 * labeled alternative in {@link ExprParser#varAssignment}.
	 * @param ctx the parse tree
	 */
	void exitAssignment(ExprParser.AssignmentContext ctx);
	/**
	 * Enter a parse tree produced by the {@code AssignmentOperators}
	 * labeled alternative in {@link ExprParser#varAssignment}.
	 * @param ctx the parse tree
	 */
	void enterAssignmentOperators(ExprParser.AssignmentOperatorsContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AssignmentOperators}
	 * labeled alternative in {@link ExprParser#varAssignment}.
	 * @param ctx the parse tree
	 */
	void exitAssignmentOperators(ExprParser.AssignmentOperatorsContext ctx);
	/**
	 * Enter a parse tree produced by the {@code AssignmentIncDec}
	 * labeled alternative in {@link ExprParser#varAssignment}.
	 * @param ctx the parse tree
	 */
	void enterAssignmentIncDec(ExprParser.AssignmentIncDecContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AssignmentIncDec}
	 * labeled alternative in {@link ExprParser#varAssignment}.
	 * @param ctx the parse tree
	 */
	void exitAssignmentIncDec(ExprParser.AssignmentIncDecContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExpNull}
	 * labeled alternative in {@link ExprParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpNull(ExprParser.ExpNullContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExpNull}
	 * labeled alternative in {@link ExprParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpNull(ExprParser.ExpNullContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExpComparison}
	 * labeled alternative in {@link ExprParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpComparison(ExprParser.ExpComparisonContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExpComparison}
	 * labeled alternative in {@link ExprParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpComparison(ExprParser.ExpComparisonContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExpBrackets}
	 * labeled alternative in {@link ExprParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpBrackets(ExprParser.ExpBracketsContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExpBrackets}
	 * labeled alternative in {@link ExprParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpBrackets(ExprParser.ExpBracketsContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExpFunctionCall}
	 * labeled alternative in {@link ExprParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpFunctionCall(ExprParser.ExpFunctionCallContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExpFunctionCall}
	 * labeled alternative in {@link ExprParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpFunctionCall(ExprParser.ExpFunctionCallContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExpAdd}
	 * labeled alternative in {@link ExprParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpAdd(ExprParser.ExpAddContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExpAdd}
	 * labeled alternative in {@link ExprParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpAdd(ExprParser.ExpAddContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExpNegate}
	 * labeled alternative in {@link ExprParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpNegate(ExprParser.ExpNegateContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExpNegate}
	 * labeled alternative in {@link ExprParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpNegate(ExprParser.ExpNegateContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExpUnary}
	 * labeled alternative in {@link ExprParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpUnary(ExprParser.ExpUnaryContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExpUnary}
	 * labeled alternative in {@link ExprParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpUnary(ExprParser.ExpUnaryContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExpMult}
	 * labeled alternative in {@link ExprParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpMult(ExprParser.ExpMultContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExpMult}
	 * labeled alternative in {@link ExprParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpMult(ExprParser.ExpMultContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExpNumber}
	 * labeled alternative in {@link ExprParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpNumber(ExprParser.ExpNumberContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExpNumber}
	 * labeled alternative in {@link ExprParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpNumber(ExprParser.ExpNumberContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExpDivision}
	 * labeled alternative in {@link ExprParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpDivision(ExprParser.ExpDivisionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExpDivision}
	 * labeled alternative in {@link ExprParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpDivision(ExprParser.ExpDivisionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExpBoolean}
	 * labeled alternative in {@link ExprParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpBoolean(ExprParser.ExpBooleanContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExpBoolean}
	 * labeled alternative in {@link ExprParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpBoolean(ExprParser.ExpBooleanContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExpId}
	 * labeled alternative in {@link ExprParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpId(ExprParser.ExpIdContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExpId}
	 * labeled alternative in {@link ExprParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpId(ExprParser.ExpIdContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExpString}
	 * labeled alternative in {@link ExprParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpString(ExprParser.ExpStringContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExpString}
	 * labeled alternative in {@link ExprParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpString(ExprParser.ExpStringContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExpAndOr}
	 * labeled alternative in {@link ExprParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpAndOr(ExprParser.ExpAndOrContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExpAndOr}
	 * labeled alternative in {@link ExprParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpAndOr(ExprParser.ExpAndOrContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExprParser#name}.
	 * @param ctx the parse tree
	 */
	void enterName(ExprParser.NameContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprParser#name}.
	 * @param ctx the parse tree
	 */
	void exitName(ExprParser.NameContext ctx);
}