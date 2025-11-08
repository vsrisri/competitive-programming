// USACO 2012 January Contest, Bronze Division
// Problem 2. Haybale Stacking
import java.util.*;
import java.io.*;

public class HaybaleStacking {
    public static void main(String[] args) throws Exception {
        /*
        Scanner sc = new Scanner(new File("stacking.in"));
        PrintWriter pw = new PrintWriter(new File("stacking.out"));
        int n = sc.nextInt();
        int k = sc.nextInt();
        */
        int n = 7;
        int k = 4;

        int[][] test = { { 7 , 4 }, { 5 , 5 }, { 2 , 4 }, { 4 , 6 }, { 3 , 5 } };
        int[] diffs = new int[n + 1];

        for (int i = 1; i < k; i++) {
            /*
            int a = sc.nextInt() - 1;
            int b = sc.nextInt() - 1;
            */
            int a = test[k][0] - 1;
            int b = test[k][1] - 1;
            diffs[a]++;
            diffs[b + 1]--;
        }

        int[] arr = new int[n];
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum+= diffs[i];
            arr[i] = sum;
        }
        Arrays.sort(arr);
        System.out.println(arr[n / 2] + 1);
        /*
        pw.println(arr[n / 2]);
        pw.close();
        sc.close();
        */
    }
}
