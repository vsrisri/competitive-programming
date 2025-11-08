//USACO 2016 February Contest, Bronze Problem 2. Circular Barn
import java.util.*;
import java.io.*;

public class CircularBarn {
    public static void main (String[] args) throws Exception {
        Scanner sc = new Scanner(new File("cbarn.in"));
        PrintWriter p = new PrintWriter(new File("cbarn.out"));
        int n = sc.nextInt();
        sc.nextLine();
        int[] cowNums = new int[n];
        for (int idx = 0; idx < n; idx++) {
            cowNums[idx] = sc.nextInt();
            sc.nextLine();
        }
        int ans = n * n * 100;

        for (int startIdx = 0; startIdx < n; startIdx++) {
            int currAns = 0;
            for (int dist = 0; dist < n; dist++) {
                currAns+= dist * cowNums[(startIdx + dist) % n];
            }
            if (currAns < ans) {
                ans = currAns;
            }
        }
        p.print(ans);
        sc.close();
        p.close();
    }
}
