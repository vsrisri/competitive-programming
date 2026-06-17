import java.util.*;
import java.io.*;

public class ABCD {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine().trim());
        String row1 = br.readLine().trim();
        char[] ans = new char[2 * n];
        char prev = 0;
        for (int i = 0; i < 2 * n; i += 2) {
            char a = row1.charAt(i);
            char b = row1.charAt(i + 1);
            char[] num = helper(a, b);
            char a1 = num[0];
            char a2 = num[1];
            if (prev == 0 || prev != a1) {
                ans[i] = a1;
                ans[i + 1] = a2;
            } else {
                ans[i] = a2;
                ans[i + 1] = a1;
            }
            prev = ans[i + 1];
        }
        StringBuilder sb = new StringBuilder();
        sb.append(new String(ans));
        System.out.println(sb);
        br.close();
    }

    public static char[] helper(char a, char b) {
        char[] all = {'A', 'B', 'C', 'D'};
        char[] num = new char[2];
        int idx = 0;
        for (char c : all) {
            if (c != a && c != b) {
                num[idx++] = c;
            }
        }
        return num;
    }
}
