import java.io.*;
import java.util.*;

public class PRATA {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        for (int tc = 0; tc < t; tc++) {
            int p = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            int l = Integer.parseInt(st.nextToken());
            int[] ranks = new int[l];
            for (int i = 0; i < l; i++) {
                ranks[i] = Integer.parseInt(st.nextToken());
            }

            int low = 0;
            int high = 10000000;
            int ans = 0;
            while (low <= high) {
                int mid = (low + high) / 2;
                if (helper(ranks, p, mid)) {
                    ans = mid;
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }
            sb.append(ans).append('\n');
        }
        System.out.print(sb);
        br.close();
    }

    public static boolean helper(int[] ranks, int p, int time) {
        int total = 0;
        for (int r : ranks) {
            int t = 0;
            int k = 1;
            while (t + k * r <= time) {
                t += k * r;
                total++;
                if (total >= p) return true;
                k++;
            }
        }
        return false;
    }

}

