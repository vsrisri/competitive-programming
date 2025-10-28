import java.util.*;
import java.io.*;

public class Garland {
    public static void main(String[]args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        String s = sc.next();
        int q = sc.nextInt();

        for (int i = 0; i < q; i++) {
            int currSum = sc.nextInt();
            char c = sc.next().charAt(0);
            int low = 0;
            int high = 0;

            int max = currSum;
            if (s.charAt(high) == c) {
                currSum++;
            }

            while (high < n) {
                while (high - low + 1 <= currSum) {
                    high++;
                    if (high == n) {
                        break;
                    }
                    if (s.charAt(high) == c) {
                        currSum++;
                    }
                }
                max = Math.max(max, currSum);
                low++;
                if (s.charAt(low - 1) == c) {
                    currSum--;
                }
            }
            if (max > n) {
                System.out.println(n);
            } else {
                System.out.println(max);
            }
        }
        sc.close();
    }
}

