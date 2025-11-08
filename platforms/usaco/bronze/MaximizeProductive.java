import java.util.*;
import java.io.*;

public class MaximizeProductive {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(reader.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());
        int[] c = new int[n];
        st = new StringTokenizer(reader.readLine(), " ");
        for (int idx = 0; idx < n; idx++) {
            c[idx] = Integer.parseInt(st.nextToken());
        }
        int[] t = new int[n];
        st = new StringTokenizer(reader.readLine(), " ");
        for (int idx = 0; idx < n; idx++) {
            t[idx] = Integer.parseInt(st.nextToken());
        }

        int[] diff = new int[n];
        for (int idx = 0; idx < n; idx++) {
            diff[idx] = c[idx] - t[idx];
        }

        Arrays.sort(diff);
        for (int idx = 0; idx < q; idx++) {
            st = new StringTokenizer(reader.readLine(), " ");
            int v = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int index = binSearch(diff, s);
            //System.out.println("Index: " + index);
            if (index == n) {
                System.out.println("NO");
            } else if (n - index >= v && index > -1) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
    }

    // Returns the index of the minimum value greater than to num or 'n' if no such value exists
    static int binSearch(int[] a, int key) {
        int low = 0;
        int high = a.length;

        while (low <= high) {
            int mid = low + (high - low + 1) / 2;

            if (a[mid] < key) {
                low = mid + 1;
            } else if (a[mid] > key) {
                high = mid - 1;
            } else if (a[mid] == key) {
                low = mid + 1;
            }
        }
        return low;
    }
}

