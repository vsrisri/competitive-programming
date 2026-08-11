import java.io.*;
import java.util.*;

public class JLEAGUE {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            line = line.trim();
            if (line.isEmpty()) {
                continue;
            }
            StringTokenizer st = new StringTokenizer(line);
            int H = Integer.parseInt(st.nextToken());
            int R = Integer.parseInt(st.nextToken());
            if (H == 0 && R == 0) {
                break;
            }
            int[] deg = new int[H + 1];
            long[] prefix = new long[H + 1];
            int[] d = new int[H];
            for (int i = 0; i < R; i++) {
                line = br.readLine();
                while (line != null && line.trim().isEmpty()) {
                    line = br.readLine();
                }
                st = new StringTokenizer(line);
                int A = Integer.parseInt(st.nextToken());
                int B = Integer.parseInt(st.nextToken());
                deg[A]++;
                deg[B]++;
            }
            for (int i = 1; i <= H; i++) {
                d[i - 1] = deg[i];
            }

            Arrays.sort(d);
            for (int i = 0, j = d.length - 1; i < j; i++, j--) {
                int temp = d[i];
                d[i] = d[j];
                d[j] = temp;
            }

            for (int i = 0; i < H; i++) {
                prefix[i + 1] = prefix[i] + d[i];
            }

            int m = 0;
            for (int i = 1; i <= H; i++) {
                if (d[i - 1] >= i - 1) {
                    m = i;
                } else {
                    break;
                }
            }
            long sumfM = prefix[m];
            long total = prefix[H];
            long sumRest = total - sumfM;
            long rhs = (long) m * (m - 1) + sumRest;
            if (sumfM == rhs) {
                sb.append('Y').append('\n');
            } else {
                sb.append('N').append('\n');
            }
        }
        System.out.print(sb);
        br.close();
    }
}
