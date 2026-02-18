import java.util.*;

/**
 * Symbol table for tracking identifiers
 */
public class SymbolTable {
    
    private static class SymbolInfo {
        String name;
        String type;
        int firstLine;
        int frequency;
        
        public SymbolInfo(String name, String type, int firstLine) {
            this.name = name;
            this.type = type;
            this.firstLine = firstLine;
            this.frequency = 1;
        }
        
        public void incrementFrequency() {
            this.frequency++;
        }
        
        @Override
        public String toString() {
            return String.format("%-20s %-15s Line: %-5d Freq: %d", 
                                name, type, firstLine, frequency);
        }
    }
    
    private Map<String, SymbolInfo> symbols;
    
    public SymbolTable() {
        this.symbols = new LinkedHashMap<>();
    }
    
    public void addSymbol(String name, String type, int line) {
        if (symbols.containsKey(name)) {
            symbols.get(name).incrementFrequency();
        } else {
            symbols.put(name, new SymbolInfo(name, type, line));
        }
    }
    
    public boolean contains(String name) {
        return symbols.containsKey(name);
    }
    
    public SymbolInfo getSymbol(String name) {
        return symbols.get(name);
    }
    
    public int getSize() {
        return symbols.size();
    }
    
    public void display() {
        System.out.println("\n========== SYMBOL TABLE ==========");
        System.out.println(String.format("%-20s %-15s %-12s %s", 
                          "Name", "Type", "First Line", "Frequency"));
        System.out.println("=".repeat(60));
        
        if (symbols.isEmpty()) {
            System.out.println("  [No identifiers found]");
        } else {
            for (SymbolInfo info : symbols.values()) {
                System.out.println("  " + info);
            }
        }
        
        System.out.println("=".repeat(60));
        System.out.println("Total unique identifiers: " + symbols.size());
        System.out.println();
    }
    
    public List<SymbolInfo> getAllSymbols() {
        return new ArrayList<>(symbols.values());
    }
    
    public void clear() {
        symbols.clear();
    }
}
