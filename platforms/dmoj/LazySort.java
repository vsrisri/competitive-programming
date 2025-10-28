import java.util.*;
import java.io.*;

public class LazySort {
    public static int[] arr = new int[50000];
    public static int n;

    public static boolean helper(int min, int max, int idx, int idx2) {
        while (min != 1 || max != n) {
            if (arr[idx] == min - 1) { 
                idx++; 
                min--;
            } else if (arr[idx] == max + 1) { 
                idx++;
                max++;
            } else if (arr[idx2] == min - 1) { 
                idx2--; 
                min--;
            } else if (arr[idx2] == max + 1) { 
                idx2--; 
                max++;
            } else {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        for (int tc = 1; tc <= t; tc++) {
            n = sc.nextInt();
            for (int idx = 0; idx < n; idx++)
                arr[idx] = sc.nextInt();

            boolean ansBool = helper(arr[0], arr[0], 1, n - 1) || helper(arr[n - 1], arr[n - 1], 0, n - 2);
            String ans = "yes";
            if (!ansBool) {
                ans = "no";
            }
            System.out.println("Case #" + tc + ": " + ans);
        }
        sc.close();
    }
}
