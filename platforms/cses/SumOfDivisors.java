import java.io.*;

public class SumOfDivisors {
    static final long MOD = (long) 1e9 + 7;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        long n = Long.parseLong(reader.readLine());
        long result = 0;
        long sqrtN = (long) Math.sqrt(n);

        for (long i = 1; i <= sqrtN; i++) {
            long q = (n / i) % MOD; 
            result += (q * i);
            result += ((q - sqrtN) * (sqrtN + 1 + q))/2;
            result %= MOD;
        }

        System.out.println(result);
        reader.close();
    }
}

