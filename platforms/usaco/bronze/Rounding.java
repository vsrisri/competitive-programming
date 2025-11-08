import java.util.*;
import java.io.*;

public class Rounding {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        int tc = Integer.parseInt(tokenizer.nextToken());
        for (int t = 0; t < tc; t++) {
            tokenizer = new StringTokenizer(reader.readLine());
            long currAns = 0;
            long n = Long.parseLong(tokenizer.nextToken());
            int digs = numDig(n);
            long lowBound = findLowerBound(digs);
            long upperBound = (5 * (long) Math.pow(10, digs - 1)) - 1;
            if (n > lowBound) {
                currAns += (n >= upperBound) ? (upperBound - lowBound) : (n - lowBound);
            }
            long prevAns = findPrev(digs);
            //System.out.println("digs: " + digs + " lowBound: " + lowBound + " upperBound: " +  upperBound + " currAns: " + currAns + " prevans: " + prevAns);
            System.out.println(currAns + prevAns);
        }

        reader.close();
    }

    public static long findPrevHelper(long num) {
        long ansForLvl = 0;
        for (int idx = 0; idx < num - 1; idx++) { 
            ansForLvl += 5 * (long) Math.pow(10, idx);
        }
        return ansForLvl;
    }

    public static long findPrev(long digs) {
        long ans = 0;
        for (int idx = 2; idx < digs; idx++) {
            ans += findPrevHelper(idx);
        }
        return ans;
    }

    public static int numDig(long n) {
        int ans = 0;
        while (n > 0) {
            n /= 10;
            ans++;
        }
        return ans;
    }

    public static long findLowerBound(int digs) {
        long lowBound = 0;
        for (int idx = 0; idx < digs; idx++) {
            lowBound += 1 * (long) Math.pow(10, idx);
        }
        return lowBound * 4;
    }
}
