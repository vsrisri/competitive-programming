import java.util.*;
import java.io.*;

public class PostsCorr {
    public static String[] a;
    public static String[] b;
    public static int[] ans;
    public static int m;
    public static int n;
    public static int k;

    public static void main (String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(reader.readLine(), " ");
        m = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(reader.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        a = new String[n];
        b = new String[n];
        ans = new int[40];
        for (int idx = 0; idx < n; idx++) {
            a[idx] = reader.readLine();
        }

        for (int idx = 0; idx < n; idx++) {
            b[idx] = reader.readLine();
        }

        if (helper2("", "", 0)) {
            System.out.println(k + 1);
            for (int idx = 0; idx <= k; idx++) {
                System.out.println(ans[idx] + 1);
            }
        } else {
            System.out.println("No solution.");
        }
        reader.close();
    }

    public static boolean helper2(String s1, String s2, int numStrs) {
        if (numStrs > m) {
            return false;
        } else if (numStrs > 0 && s1.equals(s2)) {
            return true;
        } else if (!helper(s1, s2)) {
            return false;
        }

        int idx = 0;
        boolean isDone = false;
        while (!isDone && idx < n) {
            k = numStrs;
            ans[k] = idx;
            isDone = helper2(s1 + a[idx], s2 + b[idx], numStrs + 1);
            idx++;
        }
        return isDone;

    }

    public static boolean helper(String s1, String s2) {
        if (s1.equals(s2)) {
            return true;
        }

        if (s1.length() < s2.length()) {
            return s2.startsWith(s1);
        } else if (s2.length() < s1.length()) {
            return s1.startsWith(s2);
        } else {
            return false;
        }
    }
}
