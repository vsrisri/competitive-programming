// USACO 2017 US Open Contest, Bronze Problem 2. Bovine Genomics
import java.util.*;
import java.io.*;

public class BovineGenomics {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(new File("cownomics.in"));
        PrintWriter p = new PrintWriter(new File("cownomics.out"));
        int n = sc.nextInt();
		int m = sc.nextInt();
        sc.nextLine();
        String[] spotty = new String[n];
        for (int i = 0; i < n; i++) {
            spotty[i] = sc.next();
        }
        String[] plain = new String[n];
        for (int i = 0; i < n; i++) {
            plain[i] = sc.next();
        }
        int ans = 0;
        for (int idx = 0; idx < m; idx++) {
            if (BovineGenomics.helper(spotty, plain, idx)) {
                ans++;
            }
        }
        p.print(ans);
        sc.close();
        p.close();
    }

    public static boolean helper(String[] spotty,String[] plain, int idx) {
        boolean[] first = new boolean[26];
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        for (int i = 0; i < spotty.length; i++) {
            first[alphabet.indexOf(spotty[i].charAt(idx))] = true; 
        }
        for (int i = 0; i < plain.length; i++) {
            if (first[alphabet.indexOf(plain[i].charAt(idx))]) {
                return false;
            }
        }
        return true;
    }
}
