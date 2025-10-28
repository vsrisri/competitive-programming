import java.io.*;
import java.util.*;

public class SantasBot {
    static final int MOD = 998244353;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        Map<Integer, Integer> itemCount = new HashMap<>();
        List<List<Integer>> kidsItems = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());
            List<Integer> items = new ArrayList<>();
            for (int j = 0; j < k; j++) {
                int item = Integer.parseInt(st.nextToken());
                items.add(item);
                itemCount.put(item, itemCount.getOrDefault(item, 0) + 1);
            }
            kidsItems.add(items);
        }

        long validD = 0;
        long invN = modInverse(n, MOD);
        for (List<Integer> items : kidsItems) {
            long invK = modInverse(items.size(), MOD);
            for (int item : items) {
                validD = (validD + itemCount.get(item) * invK % MOD * invN % MOD) % MOD;
            }
        }

        long totalD = invN * invN % MOD;
        long prob = validD * totalD % MOD;
        System.out.println(prob);
    }

    public static long modInverse(long a, int mod) {
        return modExp(a, mod - 2, mod);
    }

    public static long modExp(long base, long exp, int mod) {
        long ans = 1;
        while (exp > 0) {
            if ((exp & 1) != 0) {
                ans = ans * base % mod;
            }
            base = base * base % mod;
            exp >>= 1;
        }
        return ans;
    }
}

