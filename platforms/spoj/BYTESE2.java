import java.io.*;
import java.util.*;

public class BYTESE2 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int t = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < t; tc++) {
            int N = Integer.parseInt(br.readLine());
            int[] start = new int[N];
            int[] end = new int[N];
            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                start[i] = Integer.parseInt(st.nextToken());
                end[i] = Integer.parseInt(st.nextToken());
            }

            Arrays.sort(start);
            Arrays.sort(end);
            int i = 0, j = 0;
            int curr = 0;
            int ans = 0;
            while (i < N && j < N) {
                if (start[i] < end[j]) {
                    curr++;
                    ans = Math.max(ans, curr);
                    i++;
                } else {
                    curr--;
                    j++;
                }
            }
            sb.append(ans).append('\n');
        }
        System.out.print(sb);
        br.close();
    }
}

