import java.io.*;
import java.util.*;

public class BEENUMS {
    public static void main(String[] args) throws Exception {
        Set<Long> vals = new HashSet<>();
        long curr = 1;
        long step = 1;
        vals.add(curr);
        int limit = (int) 1e9;
        while (curr <= limit) {
            curr = step * (step + 1) * 3 + 1;
            vals.add(curr);
            step++;
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s;
        StringBuilder sb = new StringBuilder();
        while (!(s = br.readLine()).equals("-1")) {
            long n = Long.parseLong(s);
            sb.append(vals.contains(n) ? "Y\n" : "N\n");
        }
        System.out.print(sb);
        br.close();
    }
}

