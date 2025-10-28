import java.io.*;
import java.util.*;

public class PRIME1 {
    public static List<Integer> primes = new ArrayList<>();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder output = new StringBuilder();
        int t = Integer.parseInt(br.readLine());
        List<int[]> ranges = new ArrayList<>();
        int max = 0;
        for (int i = 0; i < t; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken());
            int n = Integer.parseInt(st.nextToken());
            ranges.add(new int[]{m, n});
            max = Math.max(max, (int)Math.sqrt(n));
        }

        sieve(max);
        for (int i = 0; i < t; i++) {
            int m = ranges.get(i)[0];
            int n = ranges.get(i)[1];
            boolean[] isPrime = new boolean[n - m + 1];
            Arrays.fill(isPrime, true);

            for (int p : primes) {
                if ((long) p * p > n) {
                    break;
                }
                long start = Math.max((long) p * p, ((m + p - 1) / p) * (long) p);
                for (long j = start; j <= n; j += p) {
                    isPrime[(int) (j - m)] = false;
                }
            }

            if (m == 1) {
                isPrime[0] = false;
            }

            for (int j = 0; j < isPrime.length; j++) {
                if (isPrime[j]) {
                    output.append(j + m).append("\n");
                }
            }

            if (i != t - 1) {
                output.append("\n");
            }
        }

        System.out.print(output);
        br.close();
    }

    public static void sieve(int limit) {
        boolean[] isPrime = new boolean[limit + 1];
        Arrays.fill(isPrime, true);
        isPrime[0] = isPrime[1] = false;
        for (int i = 2; i * i <= limit; i++) {
            if (isPrime[i]) {
                for (int j = i * i; j <= limit; j += i) {
                    isPrime[j] = false;
                }
            }
        }

        for (int i = 2; i <= limit; i++) {
            if (isPrime[i]) {
                primes.add(i);
            }
        }
    }
}

