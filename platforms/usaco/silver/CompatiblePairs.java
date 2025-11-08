import java.io.*;
import java.util.*;

public class CompatiblePairs {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        Map<Integer, Integer> cows = new TreeMap<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            cows.put(d, n);
        }

        int ans = 0;
        for (Map.Entry<Integer, Integer> entry : cows.entrySet()) {
            int cow = entry.getKey();
            int count = entry.getValue();
            int cow1 = A - cow;
            int cow2 = B - cow;
            if (cows.containsKey(cow2)) {
                int pairings = Math.min(count, cows.get(cow2));
                if (cow == cow2) {
                    pairings = pairings / 2;
                }

                ans += pairings;
                cows.put(cow, cows.get(cow) - pairings);
                cows.put(cow2, cows.get(cow2) - pairings);
            }

            if (cows.containsKey(cow1)) {
                int pairings = Math.min(count, cows.get(cow1));
                if (cow == cow1) {
                    pairings = pairings / 2;
                }

                ans += pairings;
                cows.put(cow, cows.get(cow) - pairings);
                cows.put(cow1, cows.get(cow1) - pairings);
            }
        }

        System.out.println(ans);
        br.close();
    }
}

