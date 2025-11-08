//USACO 2019 February Contest, Bronze Problem 3. Measuring Traffic
import java.util.*;
import java.io.*;

public class MeasuringTraffic {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(new File("traffic.in"));
        PrintWriter p = new PrintWriter(new File("traffic.out"));
        int n = sc.nextInt();
        sc.nextLine();
        String[] word = new String[n];
        int[] l = new int[n];
        int[] u = new int[n];
        for (int idx = 0; idx < n; idx++) {
            String line = sc.nextLine();
            int spaceIdx = line.indexOf(' ');
            word[idx] = line.substring(0, spaceIdx);
            String line2 = line.substring(spaceIdx + 1, line.length());
            int spaceIdx2 = line2.indexOf(' ');
            l[idx] = Integer.parseInt(line2.substring(0, spaceIdx2));
            u[idx] = Integer.parseInt(line2.substring(spaceIdx2 + 1, line2.length()));
        }

        int lower = -999999999;
        int upper = 999999999;
        for (int idx = n - 1; idx >= 0; idx--) {
            if (word[idx].equals("none")) {
                lower = Math.max(lower, l[idx]);
                upper = Math.min(upper, u[idx]);
            } else if (word[idx].equals("off")) {
                lower += l[idx];
                upper += u[idx];
            } else if (word[idx].equals("on")) {
                lower -= u[idx];
                upper -= l[idx];
                lower = Math.max(0, lower);
            }
        }
        p.print(lower + " " + upper);
        p.println();

        lower = -999999999;
        upper = 999999999;
        for (int idx = 0; idx < n; idx++) {
            if (word[idx].equals("none")) {
                lower = Math.max(lower, l[idx]);
                upper = Math.min(upper, u[idx]);
            } else if (word[idx].equals("off")) {
                lower -= u[idx];
                upper -= l[idx];
                lower = Math.max(0, lower);
            } else if (word[idx].equals("on")) {
                lower += l[idx];
                upper += u[idx];
            }
        }
        p.print(lower + " " + upper);
        sc.close();
        p.close();
    }
}
