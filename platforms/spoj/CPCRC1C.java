import java.io.*;
import java.util.*;

public class CPCRC1C {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        while (true) {
            st = new StringTokenizer(br.readLine());
            long a = Long.parseLong(st.nextToken());
            long b = Long.parseLong(st.nextToken());
            if (a == -1 && b == -1) {
                break;
            }
            System.out.println(helper(b) - helper(a - 1));
        }
        br.close();
    }

    public static long helper(long n) {
        if (n < 0) {
            return 0;
        }
        long sum = 0;
        long f = 1;
        while (n / f > 0) {
            long lo = n - (n / f) * f;
            long d = (n / f) % 10;
            long hi = n / (f * 10);
            sum += hi * f * 45 + d * (lo + 1);
            sum += f * d * (d - 1) / 2;
            f *= 10;
        }
        return sum;
    }
}

