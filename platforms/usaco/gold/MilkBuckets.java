import java.util.*;
import java.io.*;
public class MilkBuckets {
    public static int[] bit;
    public static int bitSize;
    public static void update(int i, int val) {
        for (i++; i <= bitSize; i += i & -i) {
            bit[i] += val;
        }
    }

    public static int query(int i) {
        int s = 0;
        for (i++; i > 0; i -= i & -i) {
            s += bit[i];
        }
        return s;
    }

    public public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine().trim());
        StringBuilder sb = new StringBuilder();
        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine().trim());
            StringTokenizer st = new StringTokenizer(br.readLine());
            int[] a = new int[n];
            for (int i = 0; i < n; i++) {
                a[i] = Integer.parseInt(st.nextToken());
            }

            int[] sorted = a.clone();
            Arrays.sort(sorted);
            int m = 0;
            for (int i = 0; i < n; i++) {
                if (i == 0 || sorted[i] != sorted[i - 1]) {
                    sorted[m++] = sorted[i];
                }
            }

            int[] arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = Arrays.binarySearch(sorted, 0, m, a[i]);
            }

            long[] smallerLeft = new long[n];
            long[] smallerRight = new long[n];
            bit = new int[m + 2];
            bitSize = m + 1;
            long ans = 0;
            for (int i = 0; i < n; i++) {
                smallerLeft[i] = arr[i] > 0 ? query(arr[i] - 1) : 0;
                update(arr[i], 1);
            }

            bit = new int[m + 2];
            for (int i = n - 1; i >= 0; i--) {
                smallerRight[i] = arr[i] > 0 ? query(arr[i] - 1) : 0;
                update(arr[i], 1);
            }

            for (int i = 0; i < n; i++) {
                ans += Math.min(smallerLeft[i], smallerRight[i]);
            }

            sb.append(ans).append('\n');
        }
        System.out.print(sb);
        br.close();
    }
}
