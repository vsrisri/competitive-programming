import java.io.*;
import java.util.*;

public class SleepyCowSort {
    static final long MOD = (long) 1e9 + 7;
    static final long INF = (long) 1e16;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("sleepy.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("sleepy.out")));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int[] arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int idx = 0; idx < n; idx++) {
            arr[idx] = Integer.parseInt(st.nextToken());
        }

        int sortedTail = 1;
        for (int i = n - 2; i >= 0; i--) {
            if (arr[i] > arr[i + 1]) {
                break;
            }
            sortedTail++;
        }

        out.println(n - sortedTail);
        BIT bit = new BIT(n + 1);
        for (int i = n - sortedTail; i < n; i++) {
            bit.update(arr[i], 1);
        }

        for (int i = 0; i < n - sortedTail; i++) {
            int greaterOnRight = n - sortedTail - i - 1;
            int smallerOrEqual = (int) bit.query(1, arr[i]);
            bit.update(arr[i], 1);
            out.print(greaterOnRight + smallerOrEqual);
            if (i < n - sortedTail - 1) out.print(" ");
        }

        out.println();
        out.flush();
        out.close();
    }

    public static class BIT {
        int size;
        long[] tree;

        public BIT(int size) {
            this.size = size;
            this.tree = new long[size + 1];
        }

        public void update(int idx, long delta) {
            while (idx <= size) {
                tree[idx] += delta;
                idx += idx & -idx;
            }
        }

        public long prefixSum(int idx) {
            long res = 0;
            while (idx > 0) {
                res += tree[idx];
                idx -= idx & -idx;
            }
            return res;
        }

        public long query(int left, int right) {
            if (left > right) {
                return 0;
            }
            return prefixSum(right) - prefixSum(left - 1);
        }
    }
}

