# Překladač

## Návrh jazyka

Jazyk kalkulačky

#### Datové typy
- int 32
- float 32
- boolean 1 (true / false)
#### Deklarace proměnných
- int x
- float y
- boolean z

#### Operátory

- +, -, *, /, =, (, ), [, ]
#### Priorita

1) +, -
2) *, /
3) =
- přednost má vyšší priorita
- při stejné prioritě zleva doprava

#### Funkce

- absolutní hodnota: abs(a)
- mocnina: pow(a, n)
- rozklad na prvočísla: fact(a)

#### Správný výraz

((a+a-b)*a/b) + abs(a-b) - pow(a, 3)


## Gramatika

non_zero_digit ::= "1" | "2" | "3" | "4" | "5" | "6" | "7" | "8" | "9"

digit ::= "0" | non-zero-digit


u_int ::= "0" | non-zero-digit {digit} 

int ::= "0" | \[ "-" \] u_int

float ::= \[ "-" \] u_int "." digit {digit}

boolean ::= "true" | "false"

num ::= int | float

add_op ::= "+" | "-"

mult_op ::= "*" | "/"

abs_fun ::= abs(num)

pow_fun ::= pow(num, int)

fact_fun ::= fact(u_int)

op ::= add_op | mult_op

fun ::= abs_fun | pow_fun | fact_fun

expr ::= num {op (num | fun)}
