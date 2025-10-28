import java.util.*;
import java.io.*;

public class STPAR {
    public static void main(String[] arsg) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String str = "";
        while (true) {
            int n = Integer.parseInt(reader.readLine());
            if (n == 0) {
                break;
            }
            int idx, num, curr;
            num = curr = 0;
            idx = 1;
            Stack<Integer> stack = new Stack<Integer>();
            StringTokenizer st = new StringTokenizer(reader.readLine(), " ");

            for (curr = 0; curr < n; curr++) {
                num = Integer.parseInt(st.nextToken());
                if (idx == num) {
                    idx++;
                    continue;
                }
                while (stack.size() > 0 && stack.peek() == idx) {
                    stack.pop();
                    idx++;
                }
                if (stack.size() > 0 && stack.peek() < num) {
                    break;
                }
                stack.push(num);
            }

            if (curr == n) {
                System.out.println("yes");
            } else {
                System.out.println("no");
            }
        }
        reader.close();

    }
}
