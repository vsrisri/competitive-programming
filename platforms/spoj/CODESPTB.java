import java.io.*;
import java.util.*;

public class CODESPTB {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int t = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < t; tc++) {
            int N = Integer.parseInt(br.readLine());
            int[] a = new int[N];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                a[i] = Integer.parseInt(st.nextToken());
            }
            sb.append(helper(a, 0, N - 1)).append('\n');
        }
        System.out.print(sb);
        br.close();
    }

    public static long helper(int[] a, int l, int r) {
        if (l >= r) {
            return 0;
        }

        int m = (l + r) / 2;
        long count = helper(a, l, m);
        count += helper(a, m + 1, r);
        count += merge(a, l, m, r);
        return count;
    }

    public static long merge(int[] a, int l, int m, int r) {
        int n1 = m - l + 1;
        int n2 = r - m;
        int[] L = new int[n1];
        int[] R = new int[n2];
        for (int i = 0; i < n1; i++) {
            L[i] = a[l + i];
        }
        for (int j = 0; j < n2; j++) {
            R[j] = a[m + 1 + j];
        }
        int i = 0, j = 0, k = l;
        long swaps = 0;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                a[k++] = L[i++];
            }
            else {
                a[k++] = R[j++];
                swaps += (n1 - i);
            }
        }
        while (i < n1) {
            a[k++] = L[i++];
        }
        while (j < n2) {
            a[k++] = R[j++];
        }
        return swaps;
    }

}

