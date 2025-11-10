import java.io.*;
import java.util.*;

public class BEENUMS {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s;
        StringBuilder sb = new StringBuilder();
        while (!(s = br.readLine()).equals("-1")) {
            long n = Long.parseLong(s);
            double k = (3 + Math.sqrt(12.0 * n - 3)) / 6.0;
            long kInt = (long) k;
            if (kInt > 0 && 3 * kInt * (kInt - 1) + 1 == n) {
                sb.append("Y\n");
            } else {
                sb.append("N\n");
            }
        }
        System.out.print(sb);
        br.close();
    }
}

