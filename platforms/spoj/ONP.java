import java.util.*;
import java.io.*;

public class ONP {
    public static void main(String[] args) throws Exception {
        Scanner stdin = new Scanner(System.in);
        int n = stdin.nextInt();
        while (n > 0) {
            String exp = stdin.next();
            int strLength = exp.length();
            int count = -1;

            Stack<Character> stack = new Stack<Character>();
            String out = "";
            while (count++ < exp.length() - 1) {
                if (exp.charAt(count) == '(') {
                    continue;
                }
                char c = exp.charAt(count);

                if (c == '+' || c == '-' || c == '*' || c == '/' || c == '^') {
                    stack.add(c);
                    continue;
                } else if (c == ')') {
                    out+= stack.pop();
                    continue;
                } else {
                    out+= String.valueOf(c);
                    continue;
                }
            }
            n--;
            System.out.println(out);

        }
    }
}
