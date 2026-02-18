/* JFlex Scanner for CustomLang */

import java.io.*;
import java.util.*;

%%

%class JFlexScanner
%public
%line
%column
%type Token
%standalone

%init{
    symbolTable = new SymbolTable();
    errorHandler = new ErrorHandler();
    tokens = new ArrayList<>();
%init}

%eof{
    printResults();
%eof}

%{
    private SymbolTable symbolTable;
    private ErrorHandler errorHandler;
    private List<Token> tokens;
    
    private Token createToken(TokenType type) {
        Token token = new Token(type, yytext(), yyline + 1, yycolumn + 1);
        tokens.add(token);
        if (type == TokenType.IDENTIFIER) {
            symbolTable.addSymbol(yytext(), "IDENTIFIER", yyline + 1);
        }
        return token;
    }
    
    private void reportError(String message) {
        errorHandler.reportInvalidCharacter(yyline + 1, yycolumn + 1, yytext().charAt(0));
    }
    
    private void printResults() {
        System.out.println("\n=== TOKENS RECOGNIZED ===\n");
        for (Token token : tokens) {
            if (token.getType() != TokenType.WHITESPACE) {
                System.out.println(token);
            }
        }
        System.out.println("\n=== SYMBOL TABLE ===");
        symbolTable.display();
        System.out.println("\n=== LEXICAL ERRORS ===");
        if (errorHandler.hasErrors()) {
            errorHandler.displaySummary();
        } else {
            System.out.println("No lexical errors found.");
        }
        System.out.println("\n=== SCANNING STATISTICS ===");
        System.out.println("Total Lines: " + (yyline + 1));
        System.out.println("Total Tokens: " + tokens.size());
        System.out.println("Unique Identifiers: " + symbolTable.getSize());
        System.out.println("Lexical Errors: " + errorHandler.getErrorCount());
        System.out.println("\n=== JFLEX SCANNER COMPLETED ===\n");
    }
%}

DIGIT = [0-9]
SIGN = [+-]
WHITESPACE = [ \t\n\r]

%%


{SIGN}?{DIGIT}+"."{DIGIT}{1,6}([eE]{SIGN}?{DIGIT}+)? {
    return createToken(TokenType.FLOAT);
}

{SIGN}?{DIGIT}+ {
    return createToken(TokenType.INTEGER);
}

"true" | "false" {
    return createToken(TokenType.BOOLEAN);
}

[A-Z][a-z0-9_]{0,30} {
    if (yytext().length() > 31) {
        reportError("Identifier too long");
        return null;
    }
    return createToken(TokenType.IDENTIFIER);
}

"<=" | ">=" | "==" | "!=" { return createToken(TokenType.RELATIONAL); }
"<" | ">" { return createToken(TokenType.RELATIONAL); }

"+" | "-" | "*" | "/" | "%" { return createToken(TokenType.OPERATOR); }

"##"[^\n]* {
    return createToken(TokenType.COMMENT);
}

{WHITESPACE} {
    return createToken(TokenType.WHITESPACE);
}

. {
    reportError("Illegal character");
    return null;
}

<<EOF>> {
    return new Token(TokenType.EOF, "", yyline + 1, yycolumn + 1);
}
