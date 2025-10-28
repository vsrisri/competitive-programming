import java.io.*;
import java.util.*;

public class MAJOR {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int t = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < t; tc++) {
            int n = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            HashMap<Integer, Integer> map = new HashMap<>();
            int ans = 0;
            int max = 0;
            for (int idx = 0; idx < n; idx++) {
                int num = Integer.parseInt(st.nextToken());
                int freq = map.getOrDefault(num, 0) + 1;
                map.put(num, freq);
                if (freq > max) {
                    max = freq;
                    ans = num;
                }
            }
            if (max > n / 2) {
                sb.append("YES ").append(ans).append('\n');
            } else {
                sb.append("NO\n");
            }
        }
        System.out.print(sb.toString());
        br.close();
    }
}

