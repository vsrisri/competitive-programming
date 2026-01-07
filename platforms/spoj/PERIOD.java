import java.io.*;
import java.util.*;

public class PERIOD {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder out = new StringBuilder();
        int t = Integer.parseInt(br.readLine().trim());
        for (int tc = 1; tc <= t; tc++) {
            int n = Integer.parseInt(br.readLine().trim());
            String s = br.readLine().trim();
            int[] psArr = new int[n];
            for (int i = 1; i < n; i++) {
                int j = psArr[i - 1];
                while (j > 0 && s.charAt(i) != s.charAt(j)) {
                    j = psArr[j - 1];
                }
                if (s.charAt(i) == s.charAt(j)) {
                    j++;
                }
                psArr[i] = j;
            }

            out.append("Test case #").append(tc).append("\n");
            for (int i = 2; i <= n; i++) {
                int len = i;
                int num = psArr[i - 1];
                int period = len - num;
                if (num > 0 && len % period == 0) {
                    int k = len / period;
                    if (k > 1) {
                        out.append(len).append(" ").append(k).append("\n");
                    }
                }
            }
            out.append("\n");
        }
        System.out.print(out);
        br.close();
    }
}

