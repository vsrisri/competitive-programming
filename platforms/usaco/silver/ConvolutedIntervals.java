// USACO 2021 December Contest, Silver
// Problem 3. Convoluted Intervals
import java.util.*;
import java.io.*;

public class ConvolutedIntervals {
    public static void main(String[] args) throws Exception {
        Scanner stdin = new Scanner(System.in);
        int n = stdin.nextInt();
        int m = stdin.nextInt();
        int[] lowerArr = new int[m + 1];
        int[] upperArr = new int[m + 1];
        int[] currInter = new int[2];
        for (int idx = 0; idx < n; idx++) {
            currInter[0] = stdin.nextInt();
            currInter[1] = stdin.nextInt();
            lowerArr[currInter[0]]++;
            upperArr[currInter[1]]++;
        }

        long[] ansArr = new long[2 + (2 * m)];
        for (int idx = 0; idx <= m; idx++) {
            for (int idx2 = 0; idx2 <= m; idx2++) {
                ansArr[idx + idx2]+= (long) lowerArr[idx] * lowerArr[idx2];
                ansArr[idx + idx2 + 1]-= (long) upperArr[idx] * upperArr[idx2];
            }
        }

        long sum = 0;
        for (int idx = 0; idx <= 2 * m; idx++) {
            sum+= ansArr[idx];
            ansArr[idx] = sum;
        }

        for (int idx = 0; idx <= (2 * m); idx++) {
            System.out.println(ansArr[idx]);
        }
        stdin.close();
    }
}
