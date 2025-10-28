import java.util.*;
import java.io.*;

public class Po {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(reader.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(reader.readLine(), " ");
        Stack<Integer> stack = new Stack<Integer>();
        int ans = 0;

        for (int idx = 0; idx < n; idx++) {
            int curr = Integer.parseInt(st.nextToken());

            while (stack.size() > 0 && stack.peek() > curr) {
                stack.pop();
            }

            if (stack.size() > 0 && stack.peek() == curr) {
                continue;
            }

            if (curr > 0) {
                stack.push(curr);
                ans++;
            }
        }

        System.out.println(ans);
        reader.close();
    }
}
