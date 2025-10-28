import java.util.*;
import java.io.*;

public class MultTable {
    public static void main(String[] args) throws Exception {
        Scanner stdin = new Scanner(System.in);
        long n = stdin.nextInt();
        long low = 1;
        long high = n * n;
        long mid;
        long possNums;
        while (low < high) {
            mid = (low + high) / 2;
            possNums = 0;
            for (int idx = 1; idx <= n; idx++) {
                possNums+= Math.min(n, mid / idx);
            }
            if (possNums >= (n * n + 1) / 2) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }

        System.out.println(high);
        stdin.close();
    }
}
