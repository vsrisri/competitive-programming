// USACO 2016 February Contest, Bronze Problem 1. Milk Pails
import java.util.*;
import java.io.*;

public class MilkPails {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(new File("pails.in"));
        PrintWriter p = new PrintWriter(new File("pails.out"));
        int sPail = sc.nextInt();
        int mPail = sc.nextInt();
        int total = sc.nextInt();
        int ans = 0;
        for (int s = 0; s <= total; s+= sPail) {
            for (int m = s; m <= total; m+= mPail) {
                ans = Math.max(ans, m);
            }
        }
        p.print(ans);
        sc.close();
        p.close();

    }
}
