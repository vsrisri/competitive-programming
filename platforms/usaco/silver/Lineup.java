import java.io.*;
import java.util.*;

public class Lineup {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int q = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < q; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int type = Integer.parseInt(st.nextToken());
            long a = Long.parseLong(st.nextToken());
            long t = Long.parseLong(st.nextToken());
            sb.append(helper(type, a, t)).append('\n');
        }
        System.out.print(sb.toString());
    }

    public static long helper(int type, long x, long time) {
        long idx = x;
        long total = time;
        long num = time;

        if (type == 1) {
            if (time < x * 2) {
                return x;
            }
            total = 2 * x - 1;
            num = time - total;
            idx = x;
        }

        while (num > 0) {
            long mid = total / 2;
            if (type == 2 && idx > mid) {
                return idx;
            }

            if (type == 2 && idx == mid) {
                idx = 0;
                num--;
                total--;
                continue;
            }

            if (type == 2 && idx < mid) {
                long move = (total - 2 * idx) / 3;
                if (move < 1) {
                    move = 1;
                }
                idx += move;
                num -= move;
                total -= move;
                continue;
            }

            if (type == 1) {
                if (num <= idx) {
                    return idx - num;
                }
                num -= idx;
                total += idx;
                idx = 0;
                num--;
                total++;
                idx = total / 2;
            }
        }

        return idx;
    }
}

