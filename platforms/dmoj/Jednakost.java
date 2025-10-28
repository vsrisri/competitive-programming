import java.io.*;
import java.util.*;

public class Jednakost {
    public static int[][] dp = new int[1001][5001];
    public static int[] fnzIdx = new int[1001];
    public static int[] digits = new int[1001];
    public static int numDigs, target;

    public static int helperAdd(int idx, int rem) {
        if (idx > numDigs) {
            if (rem == 0) {
                return 0;
            } else {
                Integer.MAX_VALUE;
            }
        }

        if (dp[idx][rem] == -1) {
            int num = 0;
            dp[idx][rem] = Integer.MAX_VALUE;
            for (int i = fnzIdx[idx]; i <= numDigs; i++) {
                num = num * 10 + digits[i];
                if (num > rem) {
                    break;
                }
                int nAdd = helperAdd(i + 1, rem - num);
                if (nAdd != Integer.MAX_VALUE) {
                    dp[idx][rem] = Math.min(dp[idx][rem], 1 + nAdd);
                }
            }
        }
        return dp[idx][rem];
    }

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int i = 1;
        char curr = (char) reader.read();
        while (curr != '=') {
            digits[i] = curr - '0';
            curr = (char) reader.read();
            i++;
        }

        numDigs = i - 1;
        target = Integer.parseInt(reader.readLine().trim());
        for (int idx = 1; idx <= numDigs; idx++) {
            Arrays.fill(dp[idx], -1);
        }

        fnzIdx[numDigs] = numDigs;
        for (int idx = numDigs - 1; idx >= 1; idx--) {
            fnzIdx[idx] = digits[idx] == 0 ? fnzIdx[idx + 1] : idx;
        }

        if (helperAdd(1, target) == Integer.MAX_VALUE) {
            System.out.println("No solution");
        } else {
            print(1, target);
        }
    }

    public static void print(int idx, int rem) {
        int num = 0;
        if (idx > numDigs) {
            System.out.println("=" + target);
            return;
        }
        if (idx > 1) {
            System.out.print("+");
        }

        for (int i = idx; i <= numDigs; i++) {
            System.out.print(digits[i]);
            num = num * 10 + digits[i];

            if (helperAdd(idx, rem) == 1 + helperAdd(i + 1, rem - num)) {
                print(i + 1, rem - num);
                break;
            }
        }
    }
}


