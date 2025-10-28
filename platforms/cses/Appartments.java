//CSES
import java.util.*;
import java.io.*;

public class Appartments {
    public static void main(String[] args) throws Exception {
        Scanner stdin = new Scanner(System.in);
        int n = stdin.nextInt();
        int m = stdin.nextInt();
        int k = stdin.nextInt();
        int[] arr1 = new int[n];
        int[] arr2 = new int[m];
        for (int idx = 0; idx < n; idx++) {
            arr1[idx] = stdin.nextInt();
        }

        for (int idx = 0; idx < m; idx++) {
            arr2[idx] = stdin.nextInt();
        }
        Arrays.sort(arr1);
        Arrays.sort(arr2);
        int idx = 0;
        int idx2 = 0;
        int ans = 0;
        while (idx < n && idx2 < m) {
            if (Math.abs(arr1[idx] - arr2[idx2]) <= k) {
                idx++;
                idx2++;
                ans++;
            } else {
                if (arr1[idx] - arr2[idx2] > k) {
                    idx2++;
                } else {
                    idx++;
                }
            }
        }
        System.out.println(ans);
        stdin.close();

    }
}

