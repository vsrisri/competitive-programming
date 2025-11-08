import java.util.*;
import java.io.*;

public class PhotoShoot2 {
    public static void main(String[] args) throws Exception {
        Scanner stdin = new Scanner(System.in);
        int n = stdin.nextInt();
        int[] a = new int[n];
        int[] b = new int[n];
        for (int idx = 0; idx < n; idx++) {
            int x = stdin.nextInt();
            a[idx] = x;
        }
        for (int idx = 0; idx < n; idx++) {
            int x = stdin.nextInt();
            b[idx] = x;
        }

        int currIdx = 0;
        int ans = 0;
        boolean[] isVisited = new boolean[n];
        for (int bIdx = 0; bIdx < n; bIdx++) {
            for (int i = currIdx; i < n; i++) {
                if (isVisited[a[currIdx] - 1]) {
                    currIdx++;
                } else {
                    break;
                }
            }
            if (b[bIdx] == a[currIdx]) {
                currIdx++;
            } else {
                isVisited[b[bIdx] - 1] = true;
                ans++;
            }
        }
        System.out.println(ans);
        stdin.close();
    }
/*
    static class Position {
        public int x;
        public boolean isVisited;
        public Position(int x, boolean isVisited) {
            this.x = x;
            this.isVisited = isVisited;
        }
    }*/
}
