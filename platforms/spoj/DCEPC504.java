import java.io.*;
import java.util.*;

public class DCEPC504 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder out = new StringBuilder();
        int t = Integer.parseInt(br.readLine().trim());
        for (int tc = 0; tc < t; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            long N = Long.parseLong(st.nextToken());
            long K = Long.parseLong(st.nextToken());
            long x = K - 1;
            if ((Long.bitCount(x) & 1) == 0) {
                out.append("Male\n");
            } else {
                out.append("Female\n");
            }
        }
        System.out.print(out.toString());
        br.close();
    }
}

