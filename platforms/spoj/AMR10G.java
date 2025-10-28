import java.util.*;
import java.io.*;

public class AMR10G {
    public static void main(String[] args) throws Exception {
        Scanner stdin = new Scanner(System.in);
        int tc = stdin.nextInt();
        for (int t = 0; t < tc; t++) {
            int n = stdin.nextInt();
            int k = stdin.nextInt();
            int[] arr = new int[n];
            for (int idx = 0; idx < n; idx++) {
                arr[idx] = stdin.nextInt();
            }
            Arrays.sort(arr);
            int ans = 1000000000 ;
            for (int idx = 0; idx <= n - k; idx++) {
                if (arr[idx + k - 1] - arr[idx] < ans) {
                    ans = arr[idx + k - 1] - arr[idx];
                }
            }
            System.out.println(ans);
        }
        stdin.close();
    }
}

