//USACO 2019 December Contest, Bronze Problem 1. Cow Gymnastics
import java.util.*;
import java.io.*;

public class CowGymnastics {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(new File("gymnastics.in"));
        PrintWriter p = new PrintWriter(new File("gymnastics.out"));
        int k = sc.nextInt();
        int n = sc.nextInt();
        sc.nextLine();
        int[][] cowScores = new int[k][n];
        for (int i = 0; i < k; i++) {
            for (int j = 0; j < n; j++) {
                int num = sc.nextInt();
                cowScores[i][num - 1] = j;
            }
        }
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int numTimesBeat = 0;
                for (int l = 0; l < k; l++) {
                    if (cowScores[l][i] < cowScores[l][j]) {
                        numTimesBeat++;
                    }
                }
                if ((numTimesBeat == 0) || (numTimesBeat == k)) {
                    ans++;
                }
            }
        }
        p.print(ans);
        sc.close();
        p.close();
    }
}
