import java.io.*;
import java.util.*;

public class CCC13S5 {
    public static void main(String[] args) throws Exception {
        Scanner stdin = new Scanner(System.in);
        int n = stdin.nextInt();
        int ans = 0;
        while (n > 1) {
            int low = 2;
            int high = (int) Math.sqrt(n);
            while (low <= high) {
                if (n % low == 0) {
                    break;
                } else {
                    low++;
                }
            }
            if (low < n && n % low == 0) {
                int cost = n / low;
                n-= cost;
                ans+= n / cost;
            } else {
                n--;
                ans+= n;
            }
        }
        System.out.println(ans);
    }
}
