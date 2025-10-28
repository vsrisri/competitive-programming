import java.io.*;
import java.util.*;

public class Exponentiation2 {
    static final int MOD = 1000000007;
    static long modExp(long base, long exp, long mod) {
        long result = 1;
        base %= mod;
        while (exp > 0) {
            if (exp % 2 == 1) {
                result = (result * base) % mod;
            }
            base = (base * base) % mod;
            exp /= 2;
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        int cases = Integer.parseInt(input.readLine());
        StringBuilder output = new StringBuilder();

        while (cases-- > 0) {
            StringTokenizer st = new StringTokenizer(input.readLine());
            long a = Long.parseLong(st.nextToken());
            long b = Long.parseLong(st.nextToken());
            long c = Long.parseLong(st.nextToken());
            long expMod = modExp(b, c, MOD - 1);
            long result = modExp(a, expMod, MOD);

            output.append(result).append("\n");
        }

        System.out.print(output);
    }
}
