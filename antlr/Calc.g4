grammar Calc;

start: (expr  (NL | DELIMITER))* ;

expr:   expr op=(MUL | DIV) expr   #mulDiv
    |   expr op=(ADD | SUB) expr   #addSub
    |   'fact(' expr ')' #fact
    |   'abs(' expr ')' #abs
    |   'pow(' expr',' expr ')' #pow
    |   INT #int
    |   FLOAT #float
    |   '('expr')' #braces
    ;

INT :       ('0') | NON_ZERO_DIGIT DIGIT* ;
FLOAT :     INT ('.' DIGIT+) ;

MUL: '*' ;
DIV: '/' ;
ADD: '+' ;
SUB: '-' ;
WS : [ |\t]+ -> skip ;
NL : [\r\n]+ ;
DELIMITER : ';';

fragment NON_ZERO_DIGIT: ('1' .. '9');
fragment DIGIT: ('0') | NON_ZERO_DIGIT;
