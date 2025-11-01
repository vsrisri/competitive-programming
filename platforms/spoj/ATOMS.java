import java.io.*;
import java.util.*;

public class ATOMS {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int P = Integer.parseInt(br.readLine().trim());
        for (int tc = 0; tc < P; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            long N = Long.parseLong(st.nextToken());
            long K = Long.parseLong(st.nextToken());
            long M = Long.parseLong(st.nextToken());
            int t = 0;
            if (N > M) {
                sb.append(0).append("\n");
                continue;
            }

            while (true) {
                if (N > M / K) {
                    break;
                }
                N *= K;
                t++;
            }
            sb.append(t).append("\n");
        }
        System.out.print(sb.toString());
        br.close();
    }
}

