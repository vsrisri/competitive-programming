// Incomplete
import java.util.*;
import java.io.*;

public class ArrayDivision {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int[] arr = new int[n];
        sc.nextLine();
        int maxSum = 0;

        for (int idx = 0; idx < n; idx++) {
            arr[idx] = sc.nextInt();
            maxSum+= arr[idx];
        }

        int ans = 10000;
        int low = 0;
        int high = maxSum;
        int mid = 

        for (int idx = max; idx < sum; idx++) {
            if (check(arr, n, k, idx)){
                ans = idx;
                break;
            }
        }

        System.out.println(ans);
    }
}
