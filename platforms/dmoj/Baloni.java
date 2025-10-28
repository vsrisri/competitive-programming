//Incomplete
import java.util.*;
import java.io.*;

public class Baloni {
    static int n;
    static TreeSet<Integer>[] s = new TreeSet[1000002];
    static int[] hArr;
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(reader.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        hArr = new int[n];
        int ans = 0;
        for (int idx = 0; idx < 1000002; idx++) {
            s[idx] = new TreeSet<Integer>();
        }

        st = new StringTokenizer(reader.readLine(), " ");
        for (int idx = 0; idx < n; idx++) {
            hArr[idx] = Integer.parseInt(st.nextToken());
            s[hArr[idx]].add(idx);
        }

        for (int idx = 0; idx < n; idx++) {
            if (s[hArr[idx]].contains(idx)) {
                int i = idx;
                ans++;
                while (i >= 0) {
                    s[hArr[i]].remove(i);
                    i = helper(i, hArr[i] - 1);
                }
            }
        }

        System.out.println(ans);
    }

    public static int helper(int idx, int h) {
        TreeSet<Integer> set = s[h];
        Integer ans = set.ceiling(idx);
        if (ans == null) {
            return -1;
        }
        return ans;
    }
}
