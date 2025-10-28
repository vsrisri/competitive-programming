import java.util.*;
import java.io.*;

public class Perica {
    public static final int MOD = 1000000007;
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(reader.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] inArr = new int[n];
        st = new StringTokenizer(reader.readLine());
        for (int i = 0; i < n; i++) {
            inArr[i] = Integer.parseInt(st.nextToken());
        }

        int[][] binom = new int[n + 1][k + 1];
        findBinoms(binom, n, k);
        System.out.println(helper(inArr, binom, k));
        reader.close();
    }
    
    public static void findBinoms(int[][] binom, int n, int k) {
        for (int i = 0; i <= n; i++) {
            binom[i][0] = 1; 
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= Math.min(i, k); j++) {
                binom[i][j] = (binom[i - 1][j - 1] + binom[i - 1][j]) % MOD;
            }
        }
    }

    public static int helper(int[] inArr, int[][] binom, int k) {
        int len = inArr.length;
        Arrays.sort(inArr);
        long out = 0;
        for (int i = 0; i < len; i++) {
            int count = binom[i][k - 1];
            out = (out + (long) count * inArr[i]) % MOD;
        }
        return (int) out;
    }
}

