grammar FOOL;

@header {
    import java.util.ArrayList;
}

@lexer::members {
   public ArrayList<String> lexicalErrors = new ArrayList<>();
}

/*------------------------------------------------------------------
 * PARSER RULES
 *------------------------------------------------------------------*/

prog   : exp SEMIC                                                      #singleExp
       | LET decs IN ((exp SEMIC) | stms)                               #letInExpStms
       | (classdec)+ SEMIC (LET decs)? IN (exp|stms) SEMIC	            #classExp
       ;

classdec  : CLASS ID ( EXTENDS ID )? (LPAR (vardec ( COMMA vardec)*)? RPAR)?  (CLPAR ((met SEMIC)+)? CRPAR)?;

decs       : (dec SEMIC)+ ;

vardec  : type ID ;

varasm     : vardec ASM ( exp | NULL) ;

letVar : LET (varasm SEMIC)+ ;

fun    : (type | VOID ) ID LPAR ( vardec ( COMMA vardec)* )? RPAR (letVar IN)?  (exp | stms) ;

dec   : varasm
      | fun
      ;

met : fun
    ;

type   : INT | BOOL | ID ;

exp    :   left=term (operator=(PLUS | MINUS) right=exp)?
      ;

term   : left=factor (operator=(TIMES | DIV) right=term)?
      ;

factor : left=value (operator=( EQ | LESSEQ | GREATEREQ | GREATER | LESS | AND | OR ) right=value)?
      ;

stm:  IF exp THEN CLPAR thenBranch=stms CRPAR (ELSE CLPAR elseBranch=stms CRPAR)?       #ifExpStms
      | ID ASM (NULL | exp) (SEMIC)?                                                                #varAsmStm
      ;

stms: ( stm )+
      ;


value  : (MINUS)? INTEGER                                                               #intVal
      | (NOT)? ( TRUE | FALSE )                                                         #boolVal
      | LPAR exp RPAR                                                                   #baseExp
      | (NOT | MINUS)? ID                                                               #varID
      | funcall                                                                         #funExp
      | IF cond=exp THEN CLPAR thenBranch=exp CRPAR (ELSE CLPAR elseBranch=exp  CRPAR)?  #ifExp
      | NEW ID ( LPAR (exp (COMMA exp)* )? RPAR)?			                            #newExp
      | ID DOT funcall	                                                                #metExp
      ;


funcall: ID ( LPAR (exp (COMMA exp)* )? RPAR )
       ;

/*------------------------------------------------------------------
 * LEXER RULES
 *------------------------------------------------------------------*/
SEMIC       : ';' ;
COLON       : ':' ;
COMMA       : ',' ;
CLPAR       : '{' ;
CRPAR       : '}' ;
EQ          : '==' ;
ASM         : '=' ;
PLUS        : '+' ;
MINUS       : '-' ;
TIMES       : '*' ;
DIV         : '/' ;
TRUE        : 'true' ;
FALSE       : 'false' ;
LPAR        : '(' ;
RPAR        : ')' ;
IF          : 'if' ;
THEN        : 'then' ;
ELSE        : 'else' ;
LET         : 'let' ;
VOID        : 'void' ;
IN          : 'in' ;
VAR         : 'var' ;
FUN         : 'fun' ;
INT         : 'int' ;
BOOL        : 'bool' ;
CLASS       : 'class' ;
EXTENDS     : 'extends' ;
NEW         : 'new' ;
DOT         : '.' ;
LESSEQ      : '<=' ;
GREATEREQ   : '>=' ;
GREATER     : '>' ;
LESS        : '<' ;
AND         : '&&' ;
OR          : '||' ;
NOT         : 'not' ;
NULL        : 'null';



//Numbers
fragment DIGIT : '0'..'9';
INTEGER       : DIGIT+;

//IDs
fragment CHAR  : 'a'..'z' |'A'..'Z' ;
ID              : CHAR (CHAR | DIGIT)* ;

//ESCAPED SEQUENCES
WS              : (' '|'\t'|'\n'|'\r')-> skip;
LINECOMENTS    : '//' (~('\n'|'\r'))* -> skip;
BLOCKCOMENTS    : '/*'( ~('/'|'*')|'/'~'*'|'*'~'/'|BLOCKCOMENTS)* '*/' -> skip;

ERR_UNKNOWN_CHAR
    :   . { lexicalErrors.add("UNKNOWN_CHAR " + getText()); }
    ;