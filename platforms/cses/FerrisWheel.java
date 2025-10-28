//CSES
import java.util.*;
import java.io.*;

public class FerrisWheel {
    public static void main(String[] args) throws Exception {
        Scanner stdin = new Scanner(System.in);
        int n = stdin.nextInt();
        int x = stdin.nextInt();
        int[] weights = new int[n];
        for (int idx = 0; idx < n; idx++) {
            weights[idx] = stdin.nextInt();
        }
        Arrays.sort(weights);
        boolean[] hasGond = new boolean[n];
        int ans = 0;
        int idx = 0;
        int idx2 = n - 1;

        while (idx < idx2) {
            if (weights[idx] + weights[idx2] > x) {
                idx2--;
            } else {
                ans++;
                hasGond[idx] = true;
                hasGond[idx2] = true;
                idx++;
                idx2--;
            }
        }
        for (int i = 0; i < n; i++) {
            if (hasGond[i] == false) {
                ans++;
            }
        }
        System.out.println(ans);
        stdin.close();
    }
}

