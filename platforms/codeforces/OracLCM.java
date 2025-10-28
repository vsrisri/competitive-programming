import java.io.*;
import java.util.*;

public class OracLCM {
    public static long gcd(long a, long b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }

    public static long lcm(long a, long b) {
        return a * (b / gcd(a, b));
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        long[] a = new long[100001];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            a[i] = Long.parseLong(st.nextToken());
        }
        long ans = 0;
        long[] prefGcdArr = new long[100001];
        long[] suffixGcdArr = new long[100001];
        prefGcdArr[1] = a[1];
        suffixGcdArr[n] = a[n];

        for (int i = 2; i <= n; i++) {
            prefGcdArr[i] = gcd(prefGcdArr[i - 1], a[i]);
        }

        for (int i = n - 1; i >= 1; i--) {
            suffixGcdArr[i] = gcd(suffixGcdArr[i + 1], a[i]);
        }

        for (int i = 0; i <= n - 1; i++) {
            if (i == 0) {
                ans = suffixGcdArr[2];
            } else if (i == n - 1) {
                ans = ans * (prefGcdArr[n - 1] / gcd(ans, prefGcdArr[n - 1]));
            } else {
                long val = gcd(prefGcdArr[i], suffixGcdArr[i + 2]);
                ans = ans * (val / gcd(ans, val));
            }
        }

        System.out.println(ans);
    }

}

