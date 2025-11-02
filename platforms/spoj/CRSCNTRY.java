import java.io.*;
import java.util.*;

public class CRSCNTRY {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine().trim());
        for (int tc = 0; tc < t; tc++) {
            List<int[]> tArr = new ArrayList<>();
            StringTokenizer st = new StringTokenizer(br.readLine());
            List<Integer> aList = new ArrayList<>();
            int ans = 0;
            while (st.hasMoreTokens()) {
                int x = Integer.parseInt(st.nextToken());
                if (x == 0) {
                    break;
                }
                aList.add(x);
            }

            int[] aArr = aList.stream().mapToInt(i -> i).toArray();
            while (true) {
                st = new StringTokenizer(br.readLine());
                List<Integer> tList = new ArrayList<>();
                int first = Integer.parseInt(st.nextToken());
                if (first == 0) {
                    break;
                }
                tList.add(first);
                while (st.hasMoreTokens()) {
                    int x = Integer.parseInt(st.nextToken());
                    if (x == 0) {
                        break;
                    }
                    tList.add(x);
                }
                tArr.add(tList.stream().mapToInt(i -> i).toArray());
            }
            for (int[] tom : tArr) {
                int[][] dp = new int[aArr.length + 1][tom.length + 1];
                for (int i = 1; i <= aArr.length; i++) {
                    for (int j = 1; j <= tom.length; j++) {
                        if (aArr[i - 1] == tom[j - 1]) {
                            dp[i][j] = dp[i - 1][j - 1] + 1;
                        } else {
                            dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                        }
                    }
                }
                ans = Math.max(ans, dp[aArr.length][tom.length]);
            }
            System.out.println(ans);
        }
        br.close();
    }
}
