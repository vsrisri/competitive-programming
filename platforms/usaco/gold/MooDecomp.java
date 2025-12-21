import java.util.*;

public class MooDecomp {
    public static final int MOD = 1000000007;
    public static final int MAXN = 1000001;
    public static int[] factorial = new int[MAXN];
    public static int h2(int a, int b) {
        return (int) (((long) a * b) % MOD);
    }

    public static int modExp(int base, long exp) {
        if (exp == 0) {
            return 1;
        }
        int result = modExp(base, exp / 2);
        result = h2(result, result);
        if (exp % 2 != 0) {
            result = h2(result, base);
        }
        return result;
    }

    public static int modInverse(int a) {
        return modExp(a, MOD - 2);
    }

    public static int binomial(int n, int k) {
        int result = factorial[n];
        result = h2(result, modInverse(factorial[k]));
        result = h2(result, modInverse(factorial[n - k]));
        return result;
    }

    public static void helper() {
        factorial[0] = 1;
        for (int i = 1; i <= MAXN; i++) {
            factorial[i] = h2(factorial[i - 1], i);
        }
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int K = sc.nextInt();
        int N = sc.nextInt();
        long L = sc.nextLong();
        sc.nextLine(); 
        String inputString = sc.nextLine();
        helper();
        int nec = 0;
        int result = 1;
        for (int i = 0; i < N; i++) {
            if (inputString.charAt(i) == 'M') {
                result = h2(result, binomial(nec + K, K));
                nec += K;
            } else {
                nec--;
            }
        }

        System.out.println(modExp(result, L));
    }
}

