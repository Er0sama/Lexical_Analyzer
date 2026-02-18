# CustomLang Lexical Analyzer
## Compiler Construction - Assignment 1 (Spring 2026)

---

## 👥 Team Members

| Name | Roll Number |
|------|-------------|
| **Saad Lone** | **22i-0783** |
| **Mobeen Hassan** | **22i-0795** |

**Course:** CS4031 - Compiler Construction  
**Semester:** Spring 2026  
**Submission Date:** February 18, 2026

---

## 📖 Language Overview

**Language Name:** CustomLang  
**File Extension:** `.lang`  
**Description:** A custom programming language designed for lexical analysis, featuring strong typing conventions with uppercase-starting identifiers, signed numeric literals, boolean values, and comprehensive operator support.

---

## 🔑 Complete Keyword List with Meanings

CustomLang uses **boolean literals** as reserved words:

| Keyword | Type | Meaning | Usage Example |
|---------|------|---------|---------------|
| `true` | Boolean Literal | Represents logical truth value | `Flag true` - Set flag to true |
| `false` | Boolean Literal | Represents logical false value | `Is_valid false` - Set validity to false |

**Important Notes:**
- Both keywords are **case-sensitive** and must be lowercase
- These are the **only reserved words** in CustomLang
- They cannot be used as identifier names
- `True`, `FALSE`, etc. are invalid (wrong case)

---

## 🆔 Identifier Rules and Examples

### Rules

Identifiers in CustomLang follow strict naming conventions:

1. **MUST start** with an **uppercase letter** (A-Z)
2. **Can contain** lowercase letters (a-z), digits (0-9), or underscores (_)
3. **Maximum length** of 31 characters total
4. **Case-sensitive** (Variable ≠ variable)
5. **Cannot use** reserved words (`true`, `false`)

### Regular Expression
```regex
[A-Z][a-z0-9_]{0,30}
```

### Valid Examples ✓
```
Variable_name          // 13 characters - Valid
Counter_123           // 11 characters - Valid
A                     // 1 character - Minimum valid length
Z                     // 1 character - Valid
Test_var_2024         // 13 characters - Valid
Num                   // 3 characters - Valid
Result_final          // 12 characters - Valid
Variable_name_with_max_length_3   // Exactly 31 characters (truncated from 32)
```

### Invalid Examples ✗
```
variable              // ✗ Starts with lowercase letter
2Count                // ✗ Starts with digit
myVariable            // ✗ Starts with lowercase letter
_underscore           // ✗ Starts with underscore
Variable-name         // ✗ Contains hyphen (not allowed)
ALLUPPERCASE          // ✗ Second character must be lowercase/digit/underscore
true                  // ✗ Reserved word
Variable_name_that_is_way_too_long_and_exceeds_the_maximum   // ✗ Exceeds 31 characters
```

---

## 📊 Literal Formats with Examples

### 1. Integer Literals

**Pattern:** `[+-]?[0-9]+`

**Description:** Signed or unsigned integer values

**Valid Examples:**
```
42                    // Positive integer
+100                  // Explicitly positive with sign
-567                  // Negative integer
0                     // Zero
+0                    // Positive zero
-0                    // Negative zero
123456789             // Large integer
```

**Invalid Examples:**
```
12.34                 // ✗ Has decimal point (use float)
1,000                 // ✗ No comma separators allowed
++5                   // ✗ Double sign not allowed
```

---

### 2. Floating-Point Literals

**Pattern:** `[+-]?[0-9]+\.[0-9]{1,6}([eE][+-]?[0-9]+)?`

**Description:** Floating-point numbers with optional scientific notation

**Components:**
- Optional sign: `+` or `-`
- Integer part: one or more digits
- Decimal point: `.` (required)
- Fractional part: 1 to 6 digits (required)
- Optional exponent: `e` or `E` followed by signed/unsigned integer

**Valid Examples:**
```
3.14                  // Pi approximation
+2.5                  // Explicitly positive float
-0.123456             // Maximum 6 decimal places
1.0                   // Simple float
999999.999999         // Large float with max decimals
1.5e10                // Scientific notation: 1.5 × 10^10
2.0E-3                // Negative exponent: 0.002
+1.1e+999             // Large exponent with explicit signs
-1.1e-999             // Small exponent
123.123456            // Exactly 6 decimal digits
```

**Invalid Examples:**
```
3.                    // ✗ No decimal digits after point
.14                   // ✗ No integer part before point
1.2345678             // ✗ More than 6 decimal digits
1e10                  // ✗ No decimal point (use integer)
1.2e                  // ✗ Incomplete exponent
```

---

### 3. Boolean Literals

**Pattern:** `(true|false)`

**Description:** Boolean constants (case-sensitive, exact match)

**Valid Examples:**
```
true                  // Boolean true
false                 // Boolean false
```

**Invalid Examples:**
```
True                  // ✗ Wrong case (uppercase T)
TRUE                  // ✗ All uppercase
False                 // ✗ Wrong case (uppercase F)
FALSE                 // ✗ All uppercase
tru                   // ✗ Incomplete
```

---

## ⚙️ Operator List with Precedence

### Operator Precedence Table

| Level | Operators | Type | Associativity | Description |
|-------|-----------|------|---------------|-------------|
| **1** (Highest) | `+`, `-` | Unary | Right-to-left | Positive/negative sign |
| **2** | `*`, `/`, `%` | Arithmetic | Left-to-right | Multiplication, division, modulus |
| **3** | `+`, `-` | Arithmetic | Left-to-right | Addition, subtraction |
| **4** | `<`, `<=`, `>`, `>=` | Relational | Left-to-right | Comparison operators |
| **5** (Lowest) | `==`, `!=` | Relational | Left-to-right | Equality operators |

---

### 1. Arithmetic Operators

**Purpose:** Mathematical computations

| Operator | Name | Precedence | Example | Description |
|----------|------|------------|---------|-------------|
| `+` | Addition / Unary Plus | 3 / 1 | `A + B` or `+5` | Add two values or positive sign |
| `-` | Subtraction / Unary Minus | 3 / 1 | `A - B` or `-10` | Subtract or negative sign |
| `*` | Multiplication | 2 | `A * B` | Multiply two values |
| `/` | Division | 2 | `A / B` | Divide first by second |
| `%` | Modulus | 2 | `A % B` | Remainder after division |

**Examples:**
```
Result 3 + 5 * 2      // Result = 13 (multiplication before addition)
Value 10 / 3          // Value = 3 (integer division)
Remainder 10 % 3      // Remainder = 1
Sum +5 + -3           // Sum = 2 (unary operators)
```

---

### 2. Relational Operators

**Purpose:** Comparing values and producing boolean results

| Operator | Name | Precedence | Example | Description |
|----------|------|------------|---------|-------------|
| `<` | Less Than | 4 | `A < B` | True if A is less than B |
| `<=` | Less Than or Equal | 4 | `A <= B` | True if A is less than or equal to B |
| `>` | Greater Than | 4 | `A > B` | True if A is greater than B |
| `>=` | Greater Than or Equal | 4 | `A >= B` | True if A is greater than or equal to B |
| `==` | Equal To | 5 | `A == B` | True if A equals B |
| `!=` | Not Equal To | 5 | `A != B` | True if A does not equal B |

**Examples:**
```
X < Y                 // Check if X is less than Y
Counter >= 100        // Check if Counter is at least 100
Result == true        // Check if Result is true
Value != 0            // Check if Value is not zero
```

---

### Precedence Examples

```
Expression: A + B * C
Evaluation: A + (B * C)           // Multiplication has higher precedence

Expression: X < Y == Z > W
Evaluation: (X < Y) == (Z > W)    // Comparison before equality

Expression: +5 * -3
Evaluation: (+5) * (-3) = -15     // Unary operators first

Expression: 10 + 5 * 2 - 3
Evaluation: 10 + (5 * 2) - 3 = 10 + 10 - 3 = 17
```

---

## 💬 Comment Syntax

### Single-Line Comments

**Pattern:** `##[^\n]*`

**Description:** Comments start with double hash `##` and extend to the end of the line

**Syntax Rules:**
- Must begin with exactly two hash symbols: `##`
- Continues until newline character
- Can contain any characters (including operators, special symbols)
- Cannot span multiple lines
- Each line needs its own `##` prefix

**Valid Examples:**
```
## This is a comment

##No space required after hash marks

## You can use any characters: +-*/<>=!@#$%^&*()

Variable_name +5      ## Inline comment explaining code

##====================================
##Program: Calculate factorial
##Author: Saad Lone & Mobeen Hassan
##Date: February 18, 2026
##====================================
```

**Invalid Examples:**
```
# Single hash is invalid        // ✗ Need double hash
// C++ style comment            // ✗ Wrong syntax
/* C style comment */           // ✗ Wrong syntax
## Comment line 1
   Comment line 2               // ✗ Second line not commented (need ## prefix)
```

**Key Features:**
- Comments are completely ignored by the scanner
- Can appear anywhere on a line
- Useful for documentation and code explanation
- Counted in statistics but not included in token output

---

## 💻 Sample Programs

### Sample Program 1: Basic Arithmetic Operations
```customlang
## Program: Basic arithmetic operations
## Description: Variable declarations and mathematical calculations

## Declare numeric variables
Num_1 10
Num_2 20
Sum 0
Difference 0
Product 0
Quotient 0
Remainder 0

## Perform arithmetic operations
Sum Num_1 + Num_2              ## Sum = 30
Difference Num_2 - Num_1       ## Difference = 10
Product Num_1 * Num_2          ## Product = 200
Quotient Num_2 / Num_1         ## Quotient = 2
Remainder Num_2 % Num_1        ## Remainder = 0

## Using signed numbers
Positive_num +100
Negative_num -50
Result Positive_num + Negative_num   ## Result = 50
```

---

### Sample Program 2: Factorial Calculation Simulation
```customlang
## Program: Calculate factorial of a number
## Author: Saad Lone & Mobeen Hassan
## Date: February 18, 2026

## Variable declarations
Num 5
Result 1
Counter 1

## Main computation loop simulation
Counter <= Num
Result Result * Counter
Counter Counter + 1

## Continue loop
Counter <= Num
Result Result * Counter
Counter Counter + 1

## Check conditions
Counter > Num true
Result == 120 false

## Floating point operations for scientific calculations
Pi 3.14159
E 2.71828
Exp_val 1.23e-4
Large_num 9.99e+100

## Boolean flags for program state
Is_done false
Has_error false
Valid_input true

## Additional comparisons
Counter > Num
Result <= 1000
Pi != E
E < Pi
```

---

### Sample Program 3: Edge Cases and Complex Expressions
```customlang
## Program: Comprehensive test of all token types
## Testing edge cases and boundary conditions

## Minimum and maximum identifier lengths
A 1                           ## Single character (minimum)
Z 26
Variable_name_with_max_length_3 100   ## 31 chars (maximum)

## Integer edge cases
Positive_zero +0
Negative_zero -0
Zero 0
Large_int 999999999

## Float edge cases and scientific notation
Small_float 1.0
Precise_float 1.000001        ## 6 decimal places
Large_float 999999.999999
Scientific_positive +1.1e+999
Scientific_negative -1.1e-999
Standard_notation 123.456789e10

## Boolean literals
Flag_true true
Flag_false false

## All relational operators in sequence
A < B <= C > D >= E == F != G

## All arithmetic operators
Complex A + B - C * D / E % F

## Nested expressions with proper precedence
Expression_1 A + B * C        ## B*C first, then add A
Expression_2 A * B + C        ## A*B first, then add C
Expression_3 A < B == C > D   ## Comparisons first, then equality

## Mixed tokens on same line
Variable_1 +123 -456 12.34 true false A < B == true

## Using signed literals
Signed_int_pos +999
Signed_int_neg -999
Signed_float_pos +3.14
Signed_float_neg -2.718

## Comments can contain any characters
## Special chars: !@#$%^&*()_+-=[]{}|;:,.<>?/~`
## Numbers in comments: 123 456 789
## Operators in comments: + - * / % < > == != <= >=
```

---

## 🔧 Compilation and Execution Instructions

### Prerequisites

Before compiling and running the scanners, ensure you have:

**Required Software:**
- **Java JDK 11 or higher** - For compiling and running Java programs
- **JFlex 1.9.1 or higher** - For generating the JFlex scanner (Part 2)
- **Bash shell** - For running build scripts (Linux/Mac/WSL on Windows)

**Verify Installation:**
```bash
java -version          # Should show Java 11 or higher
javac -version         # Should show Java compiler version
jflex --version        # Should show JFlex 1.9.1 or higher
```

**Installing Java (if needed):**
```bash
# Ubuntu/Debian
sudo apt update
sudo apt install default-jdk

# CentOS/RHEL
sudo yum install java-devel

# Verify installation
java -version
javac -version
```

**Installing JFlex (if needed):**
```bash
# Option 1: Package Manager (Ubuntu/Debian)
sudo apt install jflex

# Option 2: Download manually
wget https://github.com/jflex-de/jflex/releases/download/v1.9.1/jflex-1.9.1.tar.gz
tar xzf jflex-1.9.1.tar.gz
sudo mv jflex-1.9.1 /opt/jflex
echo 'export PATH=$PATH:/opt/jflex/bin' >> ~/.bashrc
source ~/.bashrc

# Verify installation
jflex --version
```

---

### Option 1: Automated Build and Test (Recommended)

#### Part 1: Manual Scanner

The easiest way to compile and test the manual scanner:

```bash
# Navigate to project directory
cd Assignment_Submission

# Make script executable (first time only)
chmod +x build_and_test.sh

# Run automated build and test
./build_and_test.sh
```

**What the script does:**
1. Compiles all Java source files in the `src/` directory
2. Runs the scanner on all 5 test files (`test1.lang` through `test5.lang`)
3. Saves output to `test_results/` directory
4. Reports success or failure

**Expected Output:**
```
=========================================
CustomLang Lexical Analyzer - Build Script
=========================================

Step 1: Compiling Java files...
✓ Compilation successful!

Step 2: Running test files...

Running test1.lang (all valid tokens)...
✓ test1.lang completed
Running test2.lang (complex expressions)...
✓ test2.lang completed
Running test3.lang (edge cases)...
✓ test3.lang completed
Running test4.lang (error detection)...
✓ test4.lang completed
Running test5.lang (full program)...
✓ test5.lang completed

=========================================
All tests completed successfully!
Results saved in: test_results/
=========================================
```

---

#### Part 2: JFlex Scanner

To compile and test the JFlex scanner:

```bash
# Navigate to project directory
cd Assignment_Submission

# Make script executable (first time only)
chmod +x build_jflex.sh

# Run JFlex build and test
./build_jflex.sh
```

**What the script does:**
1. Generates `JFlexScanner.java` from `Scanner.flex` specification
2. Compiles the generated scanner and supporting classes
3. Runs the scanner on all 5 test files
4. Saves output to `jflex_results/` directory

**Expected Output:**
```
JFlex scanner tests completed!
Results in: jflex_results/
```

---

### Option 2: Manual Compilation and Execution

#### Part 1: Manual Scanner - Step by Step

**Step 1: Navigate to source directory**
```bash
cd Assignment_Submission/src
```

**Step 2: Compile all Java files**
```bash
# Compile in dependency order
javac TokenType.java
javac Token.java
javac SymbolTable.java
javac ErrorHandler.java
javac ManualScanner.java

# Or compile all at once
javac *.java
```

**Step 3: Run the scanner on a test file**
```bash
# From src/ directory
java ManualScanner ../tests/test1.lang

# Or from Assignment_Submission/ directory
cd ..
java -cp src ManualScanner tests/test1.lang
```

**Step 4: Run on all test files and save output**
```bash
# From Assignment_Submission/ directory
mkdir -p test_results

java -cp src ManualScanner tests/test1.lang > test_results/test1_output.txt
java -cp src ManualScanner tests/test2.lang > test_results/test2_output.txt
java -cp src ManualScanner tests/test3.lang > test_results/test3_output.txt
java -cp src ManualScanner tests/test4.lang > test_results/test4_output.txt
java -cp src ManualScanner tests/test5.lang > test_results/test5_output.txt

echo "All tests completed! Results in test_results/"
```

---

#### Part 2: JFlex Scanner - Step by Step

**Step 1: Navigate to source directory**
```bash
cd Assignment_Submission/src
```

**Step 2: Generate scanner from specification**
```bash
# Generate JFlexScanner.java from Scanner.flex
jflex Scanner.flex

# This creates JFlexScanner.java in the current directory
```

**Step 3: Compile generated scanner and dependencies**
```bash
javac TokenType.java
javac Token.java
javac SymbolTable.java
javac ErrorHandler.java
javac JFlexScanner.java

# Or compile all at once
javac *.java
```

**Step 4: Run the JFlex scanner**
```bash
# From src/ directory
java JFlexScanner ../tests/test1.lang

# Or from Assignment_Submission/ directory
cd ..
java -cp src JFlexScanner tests/test1.lang
```

**Step 5: Run on all test files and save output**
```bash
# From Assignment_Submission/ directory
mkdir -p jflex_results

java -cp src JFlexScanner tests/test1.lang > jflex_results/test1_output.txt
java -cp src JFlexScanner tests/test2.lang > jflex_results/test2_output.txt
java -cp src JFlexScanner tests/test3.lang > jflex_results/test3_output.txt
java -cp src JFlexScanner tests/test4.lang > jflex_results/test4_output.txt
java -cp src JFlexScanner tests/test5.lang > jflex_results/test5_output.txt

echo "All JFlex tests completed! Results in jflex_results/"
```

---

### Running on Your Own Custom Files

#### Create a Custom Test File

```bash
# Create a new .lang file
cd Assignment_Submission

cat > tests/mytest.lang << 'EOF'
## My custom test program
Counter 42
Value +3.14159
Is_valid true

## Test arithmetic
Sum 10 + 20
Product 5 * 6

## Test comparisons
X < Y
Result == true
EOF
```

#### Run Manual Scanner on Custom File

```bash
java -cp src ManualScanner tests/mytest.lang
```

#### Run JFlex Scanner on Custom File

```bash
java -cp src JFlexScanner tests/mytest.lang
```

#### Save Custom File Output

```bash
java -cp src ManualScanner tests/mytest.lang > test_results/mytest_output.txt
java -cp src JFlexScanner tests/mytest.lang > jflex_results/mytest_output.txt
```

---

### Understanding the Output

Both scanners produce output in the following format:

#### Output Sections

**1. Token List**
```
========== TOKENS ==========
<INTEGER, "42", Line: 1, Col: 1>
<IDENTIFIER, "Variable_name", Line: 2, Col: 1>
<FLOAT, "3.14", Line: 3, Col: 1>
============================
```

**2. Statistics**
```
========== STATISTICS ==========
Total tokens: 36
Lines processed: 29
Comments removed: 10

Token counts by type:
  COMMENT: 10
  INTEGER: 4
  OPERATOR: 5
  FLOAT: 5
  RELATIONAL: 6
  IDENTIFIER: 4
  BOOLEAN: 2
================================
```

**3. Symbol Table**
```
========== SYMBOL TABLE ==========
Name                 Type            First Line   Frequency
============================================================
  Variable_name        IDENTIFIER      Line: 1     Freq: 2
  Counter_123          IDENTIFIER      Line: 5     Freq: 3
============================================================
Total unique identifiers: 2
```

**4. Error Summary**
```
========== ERROR SUMMARY ==========
  No lexical errors found. ✓
===================================
```

---

### Viewing Test Results

**View Manual Scanner Results:**
```bash
cat test_results/test1_output.txt
cat test_results/test2_output.txt
cat test_results/test3_output.txt
cat test_results/test4_output.txt
cat test_results/test5_output.txt
```

**View JFlex Scanner Results:**
```bash
cat jflex_results/test1_output.txt
cat jflex_results/test2_output.txt
cat jflex_results/test3_output.txt
cat jflex_results/test4_output.txt
cat jflex_results/test5_output.txt
```

**Compare Manual vs JFlex Output:**
```bash
# Both scanners should produce identical token recognition
diff test_results/test1_output.txt jflex_results/test1_output.txt

# Note: Minor differences in formatting are expected,
# but token recognition should be identical
```

---



## 📁 Project Structure

```
Assignment_Submission/
│
├── src/                              # Source code directory
│   ├── ManualScanner.java           # Part 1: Hand-coded 32-state DFA scanner
│   ├── Token.java                   # Token class (type, lexeme, line, column)
│   ├── TokenType.java               # Enum for 10 token types
│   ├── SymbolTable.java             # Identifier tracking with frequency
│   ├── ErrorHandler.java            # Error detection and reporting
│   ├── Scanner.flex                 # Part 2: JFlex specification (125 lines)
│   └── JFlexScanner.java            # Generated scanner (auto-created by JFlex)
│
├── tests/                            # Test files directory
│   ├── test1.lang                   # All valid tokens showcase
│   ├── test2.lang                   # Complex arithmetic expressions
│   ├── test3.lang                   # Edge cases and boundaries
│   ├── test4.lang                   # Error detection tests
│   └── test5.lang                   # Full program simulation
│
├── test_results/                     # Manual scanner output directory
│   ├── test1_output.txt
│   ├── test2_output.txt
│   ├── test3_output.txt
│   ├── test4_output.txt
│   └── test5_output.txt
│
├── jflex_results/                    # JFlex scanner output directory
│   ├── test1_output.txt
│   ├── test2_output.txt
│   ├── test3_output.txt
│   ├── test4_output.txt
│   └── test5_output.txt
│
├── docs/                             # Documentation directory
│   ├── 
│   ├── LanguageGrammar.txt          # Formal grammar specification
│   ├── Automata_Design.pdf          # NFA/DFA diagrams (to be created)
│   └── Comparison.pdf               # Scanner comparison (to be created)
│
├── jflap_files/                      # JFLAP automata source files
│   ├── 1_Integer_Literal/
│   │   ├── integer_NFA.jff
│   │   └── integer_DFA.jff
│   ├── 2_Float_Literal/
│   ├── 3_Identifier/
│   ├── 4_Single_Line_Comment/
│   ├── 5_Boolean_Literals/
│   ├── 6_Relational_Operators/
│   └── 7_Single_Char_Operators/
│
├── build_and_test.sh                 # Automated build script (Manual)
├── build_jflex.sh                    # Automated build script (JFlex)
├── README.md                         # This file
```

---



## 🔍 Testing Information

### Test File Descriptions

| Test File | Purpose | Tokens | Errors | Description |
|-----------|---------|--------|--------|-------------|
| `test1.lang` | Valid tokens showcase | 36 | 0 | Demonstrates all valid token types |
| `test2.lang` | Complex expressions | 78 | 0 | Arithmetic and relational operations |
| `test3.lang` | Edge cases | 60 | 0 | Boundary conditions, min/max lengths |
| `test4.lang` | Error detection | 95 | 61 | Invalid characters and malformed tokens |
| `test5.lang` | Full program | 108 | 0 | Simulates complete factorial program |

### Testing the Scanner

**Run All Tests:**
```bash
./build_and_test.sh      # Manual scanner
./build_jflex.sh         # JFlex scanner
```

**Run Individual Test:**
```bash
java -cp src ManualScanner tests/test1.lang
java -cp src JFlexScanner tests/test1.lang
```

**Compare Results:**
```bash
diff test_results/test1_output.txt jflex_results/test1_output.txt
```

---


---

*End of README.md*
