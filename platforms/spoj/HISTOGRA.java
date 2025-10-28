import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            if (n == 0) {
                break;
            }
            long[] heights = new long[n];
            for (int i = 0; i < n; i++) {
                heights[i] = Long.parseLong(st.nextToken());
            }
            System.out.println(helper(heights, n));
        }
        br.close();
    }

    public static long helper(long[] heights, int n) {
        Stack<Integer> stack = new Stack<>();
        long maxArea = 0;
        int i = 0;
        while (i < n) {
            if (stack.isEmpty() || heights[stack.peek()] <= heights[i]) {
                stack.push(i++);
            } else {
                int top = stack.pop();
                long width = stack.isEmpty() ? i : i - stack.peek() - 1;
                long area = heights[top] * width;
                maxArea = Math.max(maxArea, area);
            }
        }
        while (!stack.isEmpty()) {
            int top = stack.pop();
            long width = stack.isEmpty() ? i : i - stack.peek() - 1;
            long area = heights[top] * width;
            maxArea = Math.max(maxArea, area);
        }
        return maxArea;
    }
}

