import java.io.*;
import java.util.*;

public class ACODE {
    public static void main(String[] args) throws Exception {
        Scanner stdin = new Scanner(System.in);
        String str = stdin.nextLine();
        while (str.charAt(0) != '0') {
            System.out.println(helper(str));
            str = stdin.nextLine();
        }
        stdin.close();
    }

    public static long helper(String currInput) {
        long[] dp = new long[currInput.length() + 1];
        dp[0] = 1;

        for (int idx = 0; idx < currInput.length(); idx++) {
            if (currInput.charAt(idx) > '0' && currInput.charAt(idx) <= '9') {
                dp[idx + 1]+= dp[idx];
            }
            if (idx > 0) {
                int poss = (currInput.charAt(idx - 1) - '0') * 10 + (currInput.charAt(idx) - '0');
                if (poss >= 10 && poss <= 26) {
                    dp[idx + 1]+= dp[idx - 1];
                }
            }
        }
        return dp[currInput.length()];
    }
}

