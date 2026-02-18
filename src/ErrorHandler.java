import java.util.*;

/**
 * Handles lexical error detection and reporting
 */
public class ErrorHandler {
    
    public static class LexicalError {
        String errorType;
        int line;
        int column;
        String lexeme;
        String reason;
        
        public LexicalError(String errorType, int line, int column, 
                           String lexeme, String reason) {
            this.errorType = errorType;
            this.line = line;
            this.column = column;
            this.lexeme = lexeme;
            this.reason = reason;
        }
        
        @Override
        public String toString() {
            return String.format("ERROR [%s] at Line %d, Col %d: '%s' - %s",
                                errorType, line, column, lexeme, reason);
        }
    }
    
    private List<LexicalError> errors;
    private Map<String, Integer> errorCounts;
    
    public ErrorHandler() {
        this.errors = new ArrayList<>();
        this.errorCounts = new HashMap<>();
    }
    
    public void reportError(String errorType, int line, int column, 
                           String lexeme, String reason) {
        LexicalError error = new LexicalError(errorType, line, column, lexeme, reason);
        errors.add(error);
        
        errorCounts.put(errorType, errorCounts.getOrDefault(errorType, 0) + 1);
        
        System.err.println(error);
    }
    
    public void reportInvalidCharacter(int line, int column, char c) {
        reportError("INVALID_CHARACTER", line, column, 
                   String.valueOf(c), 
                   "Character '" + c + "' is not recognized in this language");
    }
    
    public void reportMalformedLiteral(int line, int column, String lexeme, String detail) {
        reportError("MALFORMED_LITERAL", line, column, lexeme, detail);
    }
    
    public void reportInvalidIdentifier(int line, int column, String lexeme, String detail) {
        reportError("INVALID_IDENTIFIER", line, column, lexeme, detail);
    }
    
    public void reportIncompleteToken(int line, int column, String lexeme, String expected) {
        reportError("INCOMPLETE_TOKEN", line, column, lexeme, 
                   "Expected " + expected + " but reached end or invalid character");
    }
    
    public int getErrorCount() {
        return errors.size();
    }
    
    public boolean hasErrors() {
        return !errors.isEmpty();
    }
    
    public List<LexicalError> getErrors() {
        return new ArrayList<>(errors);
    }
    
    public void displaySummary() {
        System.err.println("\n========== ERROR SUMMARY ==========");
        
        if (errors.isEmpty()) {
            System.err.println("  No lexical errors found. ✓");
        } else {
            System.err.println("  Total errors: " + errors.size());
            System.err.println("\n  Errors by type:");
            for (Map.Entry<String, Integer> entry : errorCounts.entrySet()) {
                System.err.println("    " + entry.getKey() + ": " + entry.getValue());
            }
            
            System.err.println("\n  First 10 errors (if any):");
            int limit = Math.min(10, errors.size());
            for (int i = 0; i < limit; i++) {
                System.err.println("    " + (i + 1) + ". " + errors.get(i));
            }
            
            if (errors.size() > 10) {
                System.err.println("    ... and " + (errors.size() - 10) + " more");
            }
        }
        
        System.err.println("===================================\n");
    }
    
    /**
     * Clear all errors
     */
    public void clear() {
        errors.clear();
        errorCounts.clear();
    }
    
    /**
     * Error recovery strategy: skip to next valid starting character
     * Returns the next character to try
     */
    public static char recoverFromError(String input, int currentPos) {
        // Skip to next whitespace or known operator/punctuator
        int pos = currentPos + 1;
        while (pos < input.length()) {
            char c = input.charAt(pos);
            if (Character.isWhitespace(c) || 
                isValidStartChar(c)) {
                return c;
            }
            pos++;
        }
        return '\0'; // End of input
    }
    
    /**
     * Check if character is a valid start of a token
     */
    private static boolean isValidStartChar(char c) {
        return Character.isUpperCase(c) ||  // Identifier
               Character.isDigit(c) ||       // Number
               c == '+' || c == '-' ||       // Signed number or operator
               c == '*' || c == '/' ||       // Operators
               c == '%' ||
               c == '<' || c == '>' ||       // Relational
               c == '=' || c == '!' ||
               c == '#' ||                   // Comment
               c == 't' || c == 'f';         // Boolean
    }
}
