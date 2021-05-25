grammar Calc;

start: (expr  (NL | DELIMITER))* ;

expr:   expr (MUL | DIV) expr
    |   expr (ADD | SUB) expr
    |   'abs(' expr ')'
    |   'pow(' (INT | FLOAT)',' INT ')'
    |   'fact(' INT ')'
    |   INT
    |   FLOAT
    |   BOOLEAN
    |   '('expr')'
    ;

INT :       ('0') | NON_ZERO_DIGIT DIGIT* ;
FLOAT :     INT ('.' DIGIT+) ;
BOOLEAN :   '0' | '1' ;

MUL: '*' ;
DIV: '/' ;
ADD: '+' ;
SUB: '-' ;
WS : [ |\t]+ -> skip ;
NL : [\r\n]+ ;
DELIMITER : ';';

fragment NON_ZERO_DIGIT: ('1' .. '9');
fragment DIGIT: ('0') | NON_ZERO_DIGIT;
