import java.util.*;
import java.io.*;

public class AGGRCOW {
    public static void main(String[] args) throws Exception {
        Scanner stdin = new Scanner(System.in);
        int tc = stdin.nextInt();
        for (int t = 0; t < tc; t++) {
            int n = stdin.nextInt();
            int c = stdin.nextInt();
            int[] arr = new int[n];
            for (int idx = 0; idx < n; idx++) {
                arr[idx] = stdin.nextInt();
            }
            Arrays.sort(arr);
            int ans = binSearch(arr, n, c);
            System.out.println(ans);
        }
        stdin.close();
    }

    public static boolean isValid(int num, int[] inArr, int n, int c) {
        int cows = 1;
        int curr = inArr[0];
        for (int idx = 1; idx < n; idx++) {
            if (inArr[idx] - curr >= num) {
                curr = inArr[idx];
                cows++;
                if (cows == c) {
                    return true;
                }
            }
        }
        return false;
    }

    public static int binSearch(int[] arr,  int n, int c) {
        int start = 0;
        int end = arr[n - 1];
        int ans = -1;
        while (end > start) {
            int mid = (start + end) / 2;
            if (isValid(mid, arr, n, c)) {
                if (mid > ans) {
                    ans = mid;
                }
                start = mid + 1;
            } else {
                end = mid;
            }
        }
        return ans;
    }
}
