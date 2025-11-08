//USACO 2017 February Contest, Bronze Problem 2. Why Did the Cow Cross the Road II
import java.util.*;
import java.io.*;

public class CowCrossing {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(new File("circlecross.in"));
        PrintWriter p = new PrintWriter(new File("circlecross.out"));
        String str = sc.nextLine();
        int num = 0;
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < 26; j++) {
                if (i == j) {
                    continue;
                }
                char c1 = alphabet.charAt(i);
                char c2 = alphabet.charAt(j);
                String ans = CowCrossing.checkPair(str, c1, c2);
                if (ans.charAt(0) == ans.charAt(2)) {
                    num++;
                }
            }
        }


        p.print(num/2);
        sc.close();
        p.close();
    }

    public static String checkPair(String str, char c1, char c2) {
        String ans = "";
        for (int idx = 0; idx < str.length() - 1; idx++) {
            if (str.charAt(idx) == c1) {
                ans += Character.toString(c1);
            } else if (str.charAt(idx) == c2) {
                ans += Character.toString(c2);
            }
        }
        return ans;
    }

}
