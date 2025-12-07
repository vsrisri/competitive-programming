import java.io.*;
import java.util.*;

public class WildcatWildcards {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long[] arr = new long[11];
        for (int i = 0; i < 11; i++) {
            arr[i] = Long.parseLong(br.readLine().trim());
        }
        
        long[] voCounts = new long[]{arr[1], arr[5]};
        long[] coCounts = new long[]{arr[0], arr[2], arr[3], arr[4], arr[6], arr[7]};
        long voWilds = arr[8];
        long coWilds = arr[9];
        long unWilds = arr[10];
        long left = 0;
        long ans = 0;
        long right = 100000001;
        while (left <= right) {
            long middle = (left + right) / 2;
            if (helper(middle, voCounts, coCounts, voWilds, coWilds, unWilds)) {
                ans = middle;
                left = middle + 1;
            } else {
                right = middle - 1;
            }
        }
        
        System.out.println(ans);
        br.close();
    }

    public static boolean helper(long signs, long[] voCounts, long[] coCounts, long voWilds, long coWilds, long unWilds) {
        long voDelta = 0;
        for (long count : voCounts) {
            if (count < signs) {
                voDelta += signs - count;
            }
        }
        
        long coDelta = 0;
        for (long count : coCounts) {
            if (count < signs) {
                coDelta += signs - count;
            }
        }
        
        voDelta -= voWilds;
        if (voDelta < 0) {
            voDelta = 0;
        }
        
        coDelta -= coWilds;
        if (coDelta < 0) {
            coDelta = 0;
        }
        
        return voDelta + coDelta <= unWilds;
    }
}
