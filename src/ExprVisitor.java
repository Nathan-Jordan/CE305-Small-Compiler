// Generated from java-escape by ANTLR 4.11.1
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link ExprParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface ExprVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link ExprParser#start}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStart(ExprParser.StartContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExprParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatement(ExprParser.StatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExprParser#ifStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfStatement(ExprParser.IfStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExprParser#functionDef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionDef(ExprParser.FunctionDefContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExprParser#parameterDeclatration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParameterDeclatration(ExprParser.ParameterDeclatrationContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExprParser#functionParameterAssignment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionParameterAssignment(ExprParser.FunctionParameterAssignmentContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExprParser#functionCall}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionCall(ExprParser.FunctionCallContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExprParser#whileStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhileStatement(ExprParser.WhileStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExprParser#forStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForStatement(ExprParser.ForStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExprParser#block}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlock(ExprParser.BlockContext ctx);
	/**
	 * Visit a parse tree produced by the {@code VarDeclaration}
	 * labeled alternative in {@link ExprParser#varAssignment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarDeclaration(ExprParser.VarDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Assignment}
	 * labeled alternative in {@link ExprParser#varAssignment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignment(ExprParser.AssignmentContext ctx);
	/**
	 * Visit a parse tree produced by the {@code AssignmentOperators}
	 * labeled alternative in {@link ExprParser#varAssignment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignmentOperators(ExprParser.AssignmentOperatorsContext ctx);
	/**
	 * Visit a parse tree produced by the {@code AssignmentIncDec}
	 * labeled alternative in {@link ExprParser#varAssignment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignmentIncDec(ExprParser.AssignmentIncDecContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExpNull}
	 * labeled alternative in {@link ExprParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpNull(ExprParser.ExpNullContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExpComparison}
	 * labeled alternative in {@link ExprParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpComparison(ExprParser.ExpComparisonContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExpBrackets}
	 * labeled alternative in {@link ExprParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpBrackets(ExprParser.ExpBracketsContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExpFunctionCall}
	 * labeled alternative in {@link ExprParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpFunctionCall(ExprParser.ExpFunctionCallContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExpAdd}
	 * labeled alternative in {@link ExprParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpAdd(ExprParser.ExpAddContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExpNegate}
	 * labeled alternative in {@link ExprParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpNegate(ExprParser.ExpNegateContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExpUnary}
	 * labeled alternative in {@link ExprParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpUnary(ExprParser.ExpUnaryContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExpMult}
	 * labeled alternative in {@link ExprParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpMult(ExprParser.ExpMultContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExpNumber}
	 * labeled alternative in {@link ExprParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpNumber(ExprParser.ExpNumberContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExpDivision}
	 * labeled alternative in {@link ExprParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpDivision(ExprParser.ExpDivisionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExpBoolean}
	 * labeled alternative in {@link ExprParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpBoolean(ExprParser.ExpBooleanContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExpId}
	 * labeled alternative in {@link ExprParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpId(ExprParser.ExpIdContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExpString}
	 * labeled alternative in {@link ExprParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpString(ExprParser.ExpStringContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExpAndOr}
	 * labeled alternative in {@link ExprParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpAndOr(ExprParser.ExpAndOrContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExprParser#name}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitName(ExprParser.NameContext ctx);
}