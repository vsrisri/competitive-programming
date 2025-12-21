import java.util.*;
import java.io.*;

public class FruitFeast {
    public static void main(String[] args) throws Exception {
        Scanner stdin = new Scanner(new File("feast.in"));
        PrintWriter out = new PrintWriter(new File("feast.out"));
        int t = stdin.nextInt();
        int a = stdin.nextInt();
        int b = stdin.nextInt();
        boolean[] dp = new boolean[t + 1];
        dp[0] = true;
        for (int i = a; i <= t; i++) {
            if (dp[i - a]) {
                dp[i] = true;
            }
        }

        for (int i = b; i <= t; i++) {
            if (dp[i - b]) {
                dp[i] = true;
            }
        }

        TreeSet<Integer> set = new TreeSet<Integer>();
        for (int i = 0; i <= t; i++) {
            if (dp[i]) {
                set.add(i);
            }
        }

        int ans = set.last();
        for (int idx: set) {
            ans = Math.max(ans, idx / 2 + set.lower(t - idx / 2 + 1));
        }

        out.println(ans);
        stdin.close();
        out.close();

    }
}
