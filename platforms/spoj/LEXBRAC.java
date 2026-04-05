import java.io.*;
import java.util.*;
import java.math.BigInteger;

public class LEXBRAC {
    public static BigInteger[][] dp;
    public static BigInteger INF = BigInteger.TEN.pow(130);
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine().trim());
        BigInteger k = new BigInteger(br.readLine().trim());
        dp = new BigInteger[n + 1][n + 1];
        StringBuilder sb = new StringBuilder();
        int depth = 0;
        Deque<Character> stack = new ArrayDeque<>();
        for (int pos = 0; pos < n; pos++) {
            int rem = n - pos - 1;
            char[] choices = new char[]{'(', ')', '[', ']'};
            boolean placed = false;
            for (char c : choices) {
                if (c == ')') {
                    if (stack.isEmpty() || stack.peek() != '(') {
                        continue;
                    }
                } else if (c == ']') {
                    if (stack.isEmpty() || stack.peek() != '[') {
                        continue;
                    }
                } else {
                    if (rem == 0) {
                        continue;
                    }
                }
                int ndepth = (c == '(' || c == '[') ? depth + 1 : depth - 1;
                BigInteger cNum = count(rem, ndepth);
                if (cNum.compareTo(k) >= 0) {
                    sb.append(c);
                    if (c == '(' || c == '[') {
                        stack.push(c);
                    } else {
                        stack.pop();
                    }
                    depth = ndepth;
                    placed = true;
                    break;
                } else {
                    k = k.subtract(cNum);
                }
            }
            if (!placed) {
                break;
            }
        }
        System.out.println(sb.toString());
        br.close();
    }

    public static BigInteger count(int rem, int depth) {
        if (depth > rem) {
            return BigInteger.ZERO;
        }
        if (rem == 0) {
            return depth == 0 ? BigInteger.ONE : BigInteger.ZERO;
        }
        if (dp[rem][depth] != null) {
            return dp[rem][depth];
        }
        BigInteger ans = BigInteger.ZERO;
        if (depth > 0) {
            ans = ans.add(count(rem - 1, depth - 1));
        }
        if (depth < rem) {
            ans = ans.add(count(rem - 1, depth + 1));
            ans = ans.add(count(rem - 1, depth + 1));
        }
        if (ans.compareTo(INF) > 0) {
            ans = INF;
        }
        dp[rem][depth] = ans;
        return ans;
    }
}
