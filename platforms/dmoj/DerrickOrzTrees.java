import java.util.*;
import java.io.*;

public class DerrickOrzTrees {
    public static final long MOD = Math.round(1e9) + 7;
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        StringTokenizer st = new StringTokenizer(reader.readLine(), " ");
        ArrayList<Long> origArr = new ArrayList<Long>();
        ArrayList<Long> evenArr = new ArrayList<Long>();
        long total = 1;

        for (int idx = 0; idx < n; idx++) {
            long num = Long.parseLong(st.nextToken());
            origArr.add(num);
            if (num % 2 == 0) {
                evenArr.add(num);
            }
        }

        for (Long height : evenArr) {
            total *= helper(height);
            total = total % MOD;
        }

        if (evenArr.size() == 0) {
            System.out.println(1);
        } else {
            System.out.println(total);
        }
        reader.close();
    }

    public static long helper(long height) {
        if (height % 2 == 1) {
            return 1;
        }
        long curr = helper(height / 2) % MOD;
        return (1 + (curr * curr) % MOD) % MOD;
    }
}

