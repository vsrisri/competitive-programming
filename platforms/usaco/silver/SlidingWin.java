import java.io.*;
import java.util.*;

public class SlidingWin {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder out = new StringBuilder();
        int t = Integer.parseInt(br.readLine().trim());
        for (int tc = 0; tc < t; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());
            String r = br.readLine().trim();
            int[] diff = new int[N];
            if (K == 1) {
                int ones = 0;
                for (int i = 0; i < r.length(); i++) {
                    if (r.charAt(i) == '1') ones++;
                }
                out.append(ones).append(" ").append(ones).append("\n");
                continue;
            }

            for (int i = 0; i < N - K; i++) {
                diff[i + K] = (r.charAt(i) - '0') ^ (r.charAt(i + 1) - '0');
            }

            long minAns = 0;
            long maxAns = 0;
            int parityMin = 0;
            int parityMax = 0;
            long minDiff = N;
            for (int start = 0; start < K; start++) {
                int curr = 0;
                int sum0 = 0;
                int len = 0;

                for (int idx = start; idx < N; idx += K) {
                    sum0 += curr;
                    len++;
                    if (idx + K < N) {
                        curr ^= diff[idx + K];
                    }
                }

                int sum1 = len - sum0;
                if (sum0 <= sum1) {
                    minAns += sum0;
                } else {
                    minAns += sum1;
                    parityMin ^= 1;
                }

                if (sum0 >= sum1) {
                    maxAns += sum0;
                } else {
                    maxAns += sum1;
                    parityMax ^= 1;
                }

                minDiff = Math.min(minDiff, Math.abs(sum0 - sum1));
            }

            int target = r.charAt(0) - '0';
            if (parityMin != target) {
                minAns += minDiff;
            }

            if (parityMax != target) {
                maxAns -= minDiff;
            }

            out.append(minAns).append(" ").append(maxAns).append("\n");
        }

        System.out.print(out.toString());
        br.close();
    }
}

