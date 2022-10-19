import java.util.ArrayDeque;
import java.util.Deque;

public class InfixPostfix {
    public static void main(String[] args){
        String str = "a+b*(c^d-e)^(f+g*h)-i";
        String results = convertExpression(str);
        System.out.println(results);
    }
    static String convertExpression(String str){
        StringBuilder final_result = new StringBuilder();
        Deque<Character> stack = new ArrayDeque<>();
        for(int i = 0; i < str.length(); ++i){
            char c = str.charAt(i);
            if(Character.isLetterOrDigit(c)){
                final_result.append(c);
            }else if (c == '('){
                stack.push(c);
            }else if (c == ')'){
                while (!stack.isEmpty() && stack.peek() != '('){
                    final_result.append(stack.peek());
                    stack.pop();
                }
                stack.pop();
            }else {
                while (!stack.isEmpty() && Proc(c) <= Proc(stack.peek())){
                    final_result.append(stack.peek());
                    stack.pop();
                }
                stack.push(c);
            }
        }
        while (!stack.isEmpty()){
            if(stack.peek() == '('){
                return "Invalid Expression";
            }
            final_result.append(stack.peek());
            stack.pop();
        }
        return final_result.toString();
    }
    static int Proc(char c){
        return switch (c) {
            case '+', '-' -> 1;
            case '*', '/' -> 2;
            case '^' -> 3;
            default -> -1;
        };
    }
}
