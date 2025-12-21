import java.io.*;
import java.util.*;

public class UdderedNotHerd {
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String heard = in.readLine();
        Map<Character, Integer> index = new HashMap<>();
        for (char letter : heard.toCharArray()) {
            index.putIfAbsent(letter, index.size());
        }

        int n = index.size();
        int[][] consecBefArr = new int[n][n];
        for (int i = 1; i < heard.length(); i++) {
            int prev = index.get(heard.charAt(i - 1));
            int curr = index.get(heard.charAt(i));
            consecBefArr[prev][curr]++;
        }

        int[] dp = new int[1 << n];
        dp[0] = 0;
        for (int mask = 1; mask < (1 << n); mask++) {
            dp[mask] = heard.length();
            for (int j = 0; j < n; j++) {
                if ((mask & (1 << j)) != 0) {
                    int previousSubset = mask ^ (1 << j);
                    int sum = dp[previousSubset];
                    for (int k = 0; k < n; k++) {
                        if ((mask & (1 << k)) != 0) {
                            sum += consecBefArr[j][k];
                        }
                    }
                    dp[mask] = Math.min(dp[mask], sum);
                }
            }
        }

        System.out.println(dp[(1 << n) - 1]);
    }
}

