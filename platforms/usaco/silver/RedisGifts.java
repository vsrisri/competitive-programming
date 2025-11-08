// USACO 2022 Feb Silver 
import java.util.*;
import java.io.*;

public class RedisGifts {
    public static void main(String[] args) throws Exception {
        Scanner stdin = new Scanner(System.in);
        int n = stdin.nextInt();
        ArrayList<ArrayList<Integer>> inArr = new ArrayList<ArrayList<Integer>>();
        for (int idx = 0; idx < n; idx++) {
            inArr.add(new ArrayList<Integer>());
            boolean b = false;
            for (int idx2 = 0; idx2 < n; idx2++) {
                int num = stdin.nextInt() - 1;
                if (idx == num) {
                    b = true;
                }
                if (b == false) {
                    inArr.get(idx).add(num);
                }
            }
        }

        boolean[][] isPoss = new boolean[n][n];
        for (int idx = 0; idx < n; idx++) {
            for (int num : inArr.get(idx)) {
                dfs(idx, num, isPoss, inArr, n);
            }
        }

        for (int idx = 0; idx < n; idx++) {
            int ans = idx + 1;
            for (int num : inArr.get(idx)) {
                if (isPoss[num][idx]) {
                    ans = num + 1;
                    break;
                }
            }
            System.out.println(ans);
        }
        stdin.close();

    }

    public static void dfs(int source, int curr, boolean[][] poss, ArrayList<ArrayList<Integer>> inArr, int n) {
        if (poss[source][curr]) {
            return;
        }
        poss[source][curr] = true;
        for (int num : inArr.get(curr)) {
            dfs(source, num, poss, inArr, n);
        }
    }
}

