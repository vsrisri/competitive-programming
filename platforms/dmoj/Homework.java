import java.util.*;
import java.io.*;

public class Homework {
    public static int[] fArr = new int[10000001];
    public static void helper() {
        for (int idx = 2; idx <= 10000000; idx++) {
            if (fArr[idx] > 0){
                continue;
            }

            for (int idx2 = idx; idx2 <= 10000000; idx2 += idx) {
                fArr[idx2]++;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(reader.readLine(), " ");
        int t = Integer.parseInt(st.nextToken());
        helper();
        for (int tc = 1; tc <= t; tc++) {
            st = new StringTokenizer(reader.readLine(), " ");
            int ans = 0;
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            for (int idx = a; idx <= b; idx++) {
                if (fArr[idx] == k) {
                    ans++;
                }
            }

            System.out.println("Case #" + tc + ": " + ans);
        }
        reader.close();
    }
}

