lexer grammar LaLexer;

WS  :   ( ' '
        | '\t'
        | '\r'
        | '\n'
        ) {skip();};
        
Reserved_word:
        'algoritmo'
        | 'fim_algoritmo'
        | 'declare'
        | 'constante'
        | 'literal'
        | 'inteiro'
        | 'real'
        | 'leia'
        | 'ate'
        | 'faca'
        | 'escreva'
        | 'tipo'
        | 'registro'
        | 'fim_registro'
        | 'procedimento'
        | 'fim_procedimento'
        | 'var'
        | 'funcao'
        | 'fim_funcao'
        | 'se'
        | 'fim_se'
        | 'senao'
        | 'caso'
        | 'fim_caso'
        | 'seja'
        | 'para'
        | 'fim_para'
        | 'entao'
        | 'logico'
        | 'e'
        | 'nao'
        | 'ou'
        | 'retorne'
        | 'enquanto'
        | 'fim_enquanto'
        | 'falso'
        | 'verdadeiro'
        | '%'
        | ':'
        | ','
        | '('
        | ')'
        | '['
        | ']'
        | '^'
        | '&'
        | '.'
        | '..'
        | '-'
        | '/'
        | '*'
        | '+'
        | '='
        | '<='
        | '>='
        | '<-'
        | '>'
        | '<'
        | '<>';


NUM_REAL    :   [0-9]+ '.' [0-9]+;

NUM_INT     :   [0-9]+;

UNKNOWN_TOKEN : '~' | '}' | '$';

COMMENT_ERROR : '{' ~('\n'|'\r' | '}')* '\n';

COMMENT     :   '{' ~('\n'|'\r' | '}')* '}'{skip();};

IDENT       :   ('a'..'z'|'A'..'Z') ('a'..'z'|'A'..'Z'|'0'..'9' | '_' )*;

CADEIA 	: '"' ( ESC_SEQ | ~('"'|'\\' | '\n') )* '"';

CADEIA_ERROR: '"' ~('"')* '\n';

fragment
ESC_SEQ	: '\\"';