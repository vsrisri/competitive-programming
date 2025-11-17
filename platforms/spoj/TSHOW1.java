import java.io.*;
import java.util.*;

public class TSHOW1 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine().trim());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < t; i++) {
            long k = Long.parseLong(br.readLine().trim());
            sb.append(helper(k)).append('\n');
        }
        System.out.print(sb);
        br.close();
    }

    public static String helper(long k) {
        long count = 2;
        long cumul = 2;
        int len = 1;
        while (k > cumul) {
            len++;
            count <<= 1;
            cumul += count;
        }

        long prev = cumul - count;
        long idx = k - prev - 1;
        StringBuilder s = new StringBuilder(len);
        for (int i = len - 1; i >= 0; i--) {
            s.append(((idx >> i) & 1L) == 0 ? '5' : '6');
        }
        return s.toString();
    }
}

