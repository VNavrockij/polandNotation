import java.lang.reflect.Array;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Stack;

public class FormulaParse {
    public static void main(String[] args) {
        String formula = "(100-50)/5";
//        System.out.println(Arrays.toString(formula.split("\\b")));
        System.out.println(getPolandNotation(formula));


    }

    private static ArrayDeque<String> getPolandNotation(String text) {

        ArrayDeque<String> box1 = new ArrayDeque<>();
        Stack<String> box2 =  new Stack<>();
        char symbol;
        String temp = "";
        for (int i = 0; i < text.length(); i+=1) {
            symbol = text.charAt(i);
            if (Character.isDigit(symbol)){
                temp += String.valueOf(symbol);
                if (i == text.length() - 1 || !Character.isDigit(text.charAt(i + 1))) {
                    box1.add(temp);
                    temp = "";
                }
            } else if (isMathSymbol(symbol)) {
                if (getPriority(symbol) == 1) {
                    box2.push(String.valueOf(symbol));
                } else if (getPriority(symbol) > 1) {
                    while (box2.size() != 0){
                        if (getPriority(box2.peek().charAt(0)) >= getPriority(symbol)) {
                            box1.add(box2.pop());
                        } else {
                            break;
                        }
                    }
                        box2.push(String.valueOf(symbol));
                } else if (getPriority(symbol) == -1) {
                    while (getPriority(box2.peek().charAt(0)) != 1) {
                        box1.add(box2.pop());
                    }
                    box2.pop();
                }
            }
        }
        while (!box2.isEmpty()) {
            box1.add(box2.pop());
        }
        return box1;
    }

    private static boolean isMathSymbol(char symbol) {
        return symbol == '(' || symbol == ')' || symbol == '-' ||
                symbol == '+' || symbol == '/' || symbol == '*' || symbol == '^';
    }

    private static int getPriority(char sym) {
        if (sym == '^') {
            return 4;
        } else if (sym == '/' || sym == '*') {
            return 3;
        } else if (sym == '-' || sym == '+') {
            return 2;
        } else if (sym == '(') {
            return 1;
        } else if (sym == ')') {
            return -1;
        }
        return 0;
    }
}
