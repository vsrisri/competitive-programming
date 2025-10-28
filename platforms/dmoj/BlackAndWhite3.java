import java.io.*;
import java.util.*;

public class BlackAndWhite3 {
    public static final long MOD = 1_000_000_007;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long k = Long.parseLong(st.nextToken());
        long m = Long.parseLong(st.nextToken());
        long gridSize = (long) Math.pow(2, k);
        long markedCount = countMarked(br, gridSize);
        long pow4 = modExp(4, m, MOD - 1);
        long config = modExp(2, pow4, MOD) - 1;
        config = (config < 0) ? config + MOD : config;
        long ans = modExp(config, markedCount, MOD);
        System.out.println(ans);
    }

    public static long countMarked(BufferedReader br, long gridSize) throws IOException {
        long count = 0;
        for (long r = 0; r < gridSize; r++) {
            String line = br.readLine();
            for (char curr : line.toCharArray()) {
                if (curr == '#') {
                    count++;
                }
            }
        }
        return count;
    }

    public static long modExp(long base, long exp, long mod) {
        long ans = 1;
        base %= mod;
        while (exp > 0) {
            if ((exp & 1) == 1) { 
                ans = (ans * base) % mod;
            }
            base = (base * base) % mod;
            exp >>= 1; 
        }
        return ans;
    }
}

