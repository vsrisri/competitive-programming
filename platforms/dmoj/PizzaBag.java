import java.util.*;
import java.io.*;

public class PizzaBag {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(reader.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        long ans = 0;
        long[] prefixSum = new long[2 * n + 1];

        st = new StringTokenizer(reader.readLine(), " ");
        for (int idx = 1; idx <= n; idx++) {
            prefixSum[idx] = Long.parseLong(st.nextToken());
            prefixSum[idx + n] = prefixSum[idx];
        }

        for (int idx = 1; idx <= 2 * n; idx++) {
            prefixSum[idx] += prefixSum[idx - 1];
        }

        Deque<Curr> deque = new ArrayDeque<Curr>();
        deque.addFirst(new Curr(0, 0));

        for (int idx = 1; idx < 2 * n; idx++) {
            Curr add = new Curr(idx, prefixSum[idx]);
            while (!deque.isEmpty() && deque.getLast().sum >= prefixSum[idx]) {
                deque.removeLast();
            }

            deque.addLast(add);
            while (deque.getFirst().pIdx < idx - k) {
                deque.removeFirst();
            }

            ans = Math.max(ans, prefixSum[idx] - deque.getFirst().sum);
        }

        System.out.println(ans);
        reader.close();
    }

    public static class Curr {
        int pIdx;
        long sum;
        Curr (int pIdx, long sum) {
            this.pIdx = pIdx;
            this.sum = sum;
        }
    }
}
