import java.util.Stack;

public class TestRPN {
    public static void main(String[] args) {
//        String[] rpn = {"100", "50", "5", "/", "-"};
        String[] rpn = {"100", "50", "-", "5", "/"};
//        String[] rpn = {"2", "2", "^"};
        System.out.println(evalAnswer(rpn));

    }
    public static double evalAnswer(String[] token) {
        Stack<Double> stack = new Stack<>();
        double value;
        for (int i = 0; i < token.length; i+=1) {
            switch (token[i]) {
                case "+":
                    stack.push(stack.pop() + stack.pop());
                    break;
                case "-":
                    value = stack.pop();
                    stack.push(stack.pop() - value);
                    break;
                case "*":
                    stack.push(stack.pop() * stack.pop());
                    break;
                case "/":
                    value = stack.pop();
                    stack.push(stack.pop() / value);
                    break;
                case "^":
                    Double value1 = stack.pop();
                    Double value2 = stack.pop();
                    Double result = pow(value2, value1);
                    stack.push(result);
                    break;
                default:
                    stack.push(Double.parseDouble(token[i]));
                    break;
            }
        }

        return stack.pop();
    }

    public static double pow(double value, double powValue) {
        double result = 1;
        for (int i = 1; i <= powValue; i++) {
            result = result * value;
        }
        return result;
    }
}
