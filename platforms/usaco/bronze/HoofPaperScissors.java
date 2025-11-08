import java.util.*;
import java.io.*;

public class HoofPaperScissors {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(new File("hps.in"));
        PrintWriter p = new PrintWriter(new File("hps.out"));
        int n = sc.nextInt();
        int[] freq = new int[6];
        for (int i = 0; i < n; i++) {
            int a = sc.nextInt();
			int b = sc.nextInt();
            if (a == b) {
                continue;
            }
            int ex = 1;
            if ((a + b == 3) || (a == 3 && b == 1)) {
                ex = 0;
            }

            freq[2 * (a - 1) + ex]++;
        }

        p.println(Math.max(freq[0] + freq[3] + freq[4], freq[2] + freq[1] + freq[5]));
        sc.close();
        p.close();
    }
}
