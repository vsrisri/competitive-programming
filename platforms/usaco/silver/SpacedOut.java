import java.util.*;
import java.io.*;

public class SpacedOut {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();
        int[][] arr = new int[n][n];
		for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                arr[i][j] = sc.nextInt();
            }
            sc.nextLine();
        }

        int ans1 = SpacedOut.helper(arr, n);
        int[][] arr2 = SpacedOut.swap(arr, n);
        int ans2 = SpacedOut.helper(arr2, n);
        System.out.println(Math.max(ans1, ans2));
        sc.close();
    }

    public static int[][] swap(int[][] arr, int n) {
        int[][] b = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                b[i][j] = arr[j][i];
            }
        }
        return b;
    }

    public static int helper(int[][] arr, int n) {
        int ans = 0;
        for (int i = 0; i < n; i++) {
            int oSum = 0, eSum = 0;
            for (int j = 0; j < n; j++) {
                if (j % 2 == 0) {
                    eSum += arr[i][j];
                } else {
                    oSum += arr[i][j];
                }
            }
            ans += Math.max(eSum, oSum);
        }
        return ans;
    }
    
}
