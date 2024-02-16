import java.util.Stack;

public class Functions {
    static int evaluateInfix(String infix) {
        Stack<Character> operators = new Stack<>();
        Stack<Integer> operands = new Stack<>();

        for (char c : infix.toCharArray()) {
            if (Character.isDigit(c)) {
                operands.push(c - '0'); // Convert char to int
            } else if (c == '(') {
                operators.push(c);
            } else if (c == ')') {
                while (!operators.isEmpty() && operators.peek() != '(') {
                    processOperation(operators, operands);
                }
                operators.pop(); // Pop the '('
            } else if (isOperator(c)) {
                while (!operators.isEmpty() && precedence(c) <= precedence(operators.peek())) {
                    processOperation(operators, operands);
                }
                operators.push(c);
            }
        }

        while (!operators.isEmpty()) {
            processOperation(operators, operands);
        }

        return operands.pop();
    }
    static void processOperation(Stack<Character> operators, Stack<Integer> operands) {
        char operator = operators.pop();
        int operand2 = operands.pop();
        int operand1 = operands.pop();
        int result = performOperation(operator, operand1, operand2);
        operands.push(result);
    }
    static int performOperation(char operator, int operand1, int operand2) {
        switch (operator) {
            case '+':
                return operand1 + operand2;
            case '-':
                return operand1 - operand2;
            case '*':
                return operand1 * operand2;
            case '/':
                return operand1 / operand2;
            default:
                throw new IllegalArgumentException("Invalid operator: " + operator);
        }
    }
    static boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }

    static int precedence(char operator) {
        switch (operator) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            default:
                return -1;
        }
    }
    public static void getResult(String infixExpression)
    {
        int result = evaluateInfix(infixExpression);

        System.out.println("Infix Expression: " + infixExpression);
        System.out.println("Result after evaluation: " + result);
    }

}
