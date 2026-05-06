import java.util.Stack;

public class ReversePolishNotation {

    public int calculate(int a, int b, char operand) {
        if (operand == '+') {
            return a + b;
        } else if (operand == '-') {
            return a - b;
        } else if (operand == '*') {
            return a * b;
        } else if (operand == '/') {
            return a / b;
        }
        return 0;
    }

    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        for (String token : tokens) {
            if (token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/")) {
                int b = stack.pop();
                int a = stack.pop();
                stack.push(calculate(a, b, token.charAt(0)));
            } else {
                stack.push(Integer.parseInt(token));
            }
        }
        return stack.pop();
    }

    public StringBuilder convertToRPN(String expr) {
        StringBuilder output = new StringBuilder();
        Stack<Operator> ops = new Stack<>();
        for (char c : expr.toCharArray()) {
            if (Character.isDigit(c)) {
                output.append(c);
            } else if (c == '(') {
                ops.push(c);
            } else if (c == ')') {
                while (!ops.isEmpty() && ops.peek() != '(') {
                    output.append(ops.pop().getSymbol());
                }
                ops.pop();
            } else if (c == '+' || c == '-' || c == '*' || c == '/') {
                while (!ops.isEmpty() && Operator.fromChar(c).comparePrecedence(ops.peek())) {
                    output.append(ops.pop());
                }
                Operator op = Operator.fromChar(c);
                ops.push(op);
            }
        }
        return output;
    }

    public enum Operator {
        ADDITION('+', 1),
        SUBTRACTION('-', 1),
        MULTIPLICATION('*', 2),
        DIVISION('/', 2);

        private final char symbol;
        private final int precedence;

        Operator(char c, int i) {
            this.symbol = c;
            this.precedence = i;
        }

        public int getPrecedence() {
            return precedence;
        }

        public char getSymbol() {
            return symbol;
        }

        public boolean comparePrecedence(Operator other) {
            return this.precedence <= other.precedence;
        }

        public static Operator fromChar(char c) {
            for (Operator op : Operator.values()) {
                if (op.getSymbol() == c) {
                    return op;
                }
            }
            throw new IllegalArgumentException("Invalid operator: " + c);
        }
    }

    public static void main(String[] args) {
        ReversePolishNotation rpn = new ReversePolishNotation();
        String[] tokens = {"2", "1", "+", "3", "*"};
        System.out.println(rpn.evalRPN(tokens)); // 9
        System.out.println(rpn.convertToRPN("3+(4*5)-6")); // Output: 3 4 5 * + 6 -
    }
}
