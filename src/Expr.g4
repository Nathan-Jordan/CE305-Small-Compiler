grammar Expr;

start: block EOF;

statement: ifStatement
         | whileStatement
         | forStatement
         | varAssignment SCOLON
         | functionDef
         | functionCall SCOLON
         ;

ifStatement: IF LBRACKET expression RBRACKET LCBRACKET block RCBRACKET NEWLINES?
    (ELSEIF LBRACKET expression RBRACKET LCBRACKET block RCBRACKET)* NEWLINES?
    (ELSE LCBRACKET elseBlock=block RCBRACKET)? ;


functionDef: FUNCTION identifier=name LBRACKET parameterDeclatration RBRACKET LCBRACKET block
    (RETURN DATATYPE returnValue=expression SCOLON)? NEWLINES* RCBRACKET ;

parameterDeclatration: functionParameterAssignment? (COMMA functionParameterAssignment)* ;

functionParameterAssignment: DATATYPE identifier=name ;

functionCall: identifier=name LBRACKET expression? (COMMA expression)* RBRACKET ;


whileStatement: WHILE LBRACKET condition=expression RBRACKET LCBRACKET block RCBRACKET ;

forStatement: FOR LBRACKET varInitialise=varAssignment SCOLON condition=expression SCOLON
    step=varAssignment RBRACKET LCBRACKET block RCBRACKET ;

block: NEWLINES* | (NEWLINES* statement NEWLINES*)* ;


varAssignment:
      DATATYPE identifier=ID (EQUALS value=expression)? # VarDeclaration
    | identifier=ID EQUALS value=expression # Assignment
    | identifier=ID op=ASSIGNOPERATOR value=expression # AssignmentOperators
    | identifier=ID op=(INCREMENT | DECREMENT) # AssignmentIncDec
    ;

expression:
      op=(ADD | SUB) expression # ExpUnary
    | left=expression op=(MUL | POW) right=expression # ExpMult
    | left=expression op=(DIV | MOD) right=expression # ExpDivision
    | left=expression op=(ADD | SUB) right=expression # ExpAdd
    | left=expression op=(AND | OR) right=expression # ExpAndOr
    | left=expression op=(EQUALTO | NOTEQUAL | GREATERTHAN | GREATEREQUAL | LESSTHAN | LESSEQUAL) right=expression # ExpComparison
    | negate=NOT expression # ExpNegate
    | LBRACKET expression RBRACKET # ExpBrackets
    | functionCall # ExpFunctionCall
    | BOOLEAN # ExpBoolean
    | name # ExpId
    | NUMBER # ExpNumber
    | STRING # ExpString
    | NULL # ExpNull
    ;

name: ID ;  //Something to do with not printing out in an error message, since ID is a terminal

ASSIGNOPERATOR: (ADD | SUB | MUL | DIV | MOD) EQUALS ;
DECREMENT: SUB SUB ;
INCREMENT: ADD ADD ;
NULL: 'null' ;
COLON: ':' ;
SCOLON: ';' ;
IF: 'if' ;
ELSEIF: 'elseIf' ;
ELSE: 'else' ;
WHILE: 'while' ;
FOR: 'for' ;
LBRACKET: '(' ;
RBRACKET: ')' ;
LCBRACKET: '{' ;
RCBRACKET: '}' ;
EQUALS: '=' ;
EQUALTO: '==' ;
NOTEQUAL: '!=' ;
GREATERTHAN: '>' ;
GREATEREQUAL: '>=' ;
LESSTHAN: '<' ;
LESSEQUAL: '<=' ;
NOT: '!';
AND: '&&' ;
OR: '||' ;
MUL: '*' ;
POW: '^' ;
DIV: '/' ;
MOD: '%' ;
ADD: '+' ;
SUB: '-' ;
FUNCTION: 'function';
BOOLEAN: 'true'|'false' ;
COMMA: ',' ;
RETURN: 'return' ;

DATATYPE: ('Integer' | 'Double' | 'String' | 'Boolean') ;
ID: [a-zA-Z][a-zA-Z0-9_]* ; //Identifier names
NUMBER: ('.' DIGITS+ | DIGITS+ '.' | DIGITS+) DIGITS* ([eE][-+]? DIGITS+)? ;
DIGITS: [0-9] ;
STRING: '"' CHARACTER*? '"' ;

COMMENT : ('//' ~[\r\n]*) -> skip;
WS: [ \t]+ -> skip; //Whitespace characters
NEWLINES: ('\n' | '\r' | WS)+ ;
CHARACTER: [a-zA-Z0-9 \\!"£$%^&*()_+={}[\]@~<:?>,;/.-] ;