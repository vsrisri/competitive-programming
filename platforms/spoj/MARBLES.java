import java.util.*;
import java.io.*;
import java.math.*;

public class MARBLES {
    public static void main(String[] args) throws Exception {
        Scanner stdin = new Scanner(System.in);
        int t = stdin.nextInt();
        for (int idx = 0; idx < t; idx++) {
            int n = stdin.nextInt();
            int k = stdin.nextInt();
            System.out.println(MARBLES.choose(n - 1, k - 1));
        }
        stdin.close();
    }

    public static BigInteger choose(int n, int k) {
        if (k < 0 || k > n) {
            return BigInteger.ZERO;
        }
        if (n - k < k) {
            k = n - k;
        }

        BigInteger a = BigInteger.ONE;

        for (int i = 1; i <= k; i++) {
            int num = n - i + 1;
            a = a.multiply(new BigInteger(Integer.toString(num)));
            a = a.divide(new BigInteger(Integer.toString(i)));
        }

        return a;
    }

}

