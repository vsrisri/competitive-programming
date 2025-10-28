// Incomplete
import java.util.*;
import java.io.*;

public class INVCNT {
    public static void main(String[] args) throws Exception {
        Scanner stdin = new Scanner(System.in);
        int t = stdin.nextInt();
        for (int idx = 0; idx < t; idx++) {
            int n = stdin.nextInt();
            int[] arr = new int[n];
            for (int idx2 = 0; idx2 < n; idx2++) {
                arr[idx2] = stdin.nextInt();
            }
            System.out.println(mergeSort(arr));
        }
        stdin.close();
    }

    public static long mergeSort(int[] arr) throws Exception {
        if (arr.length == 1) {
            return 0;
        }

        int mid = (arr.length - 1) / 2;
        int lLen = mid + 1; int rLen = arr.length - 1 - mid;
        int[] lhs = new int[lLen];
        int[] rhs = new int[rLen];
        for (int idx = 0; idx < lhs.length; idx++) {
            lhs[idx] = arr[idx];
        }

        for (int idx = 0; idx < rhs.length; idx++) {
            rhs[idx] = arr[idx + mid + 1];
        }

        long total = mergeSort(lhs) + mergeSort(rhs);
        int l = 0;
        int r = 0;
        int m = 0;
        while (l < lLen && r < rLen) {
            if (lhs[l] <= rhs[r]) {
                arr[m] = lhs[l];
                m++;
                l++;
            } else {
                arr[m] = rhs[r];
                m++;
                r++;
                total+= lhs.length - l;
            }
        }

        while (l < lLen) {
            arr[m] = lhs[l];
            m++;
            l++;
        }
        while (r < rLen) {
            arr[m] = rhs[r];
            m++;
            r++;
        }

        return total;
    }
}
