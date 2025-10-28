import java.io.*;
import java.util.*;

public class CANTON {
    public static void main(String[] args) throws Exception {
        Scanner stdin = new Scanner(System.in);
        int t = stdin.nextInt();
        for (int idx = 0; idx < t; idx++) {
            int curr = stdin.nextInt();
            int sum = 0;
            int idx2 = 0;
            for (idx2 = 1;; idx2++) {
                sum+= idx2;
                if (sum >= curr) {
                    break;
                }
            }
            int x = curr - (sum - idx2);
            int total = idx2 + 1;
            if (idx2 % 2 == 0) {
                System.out.println("TERM " + curr + " IS " + x + "/" + (total - x));
            } else {
                System.out.println("TERM " + curr + " IS " + (total - x) + "/" + x);
            }
        }
        stdin.close();
    }
}
