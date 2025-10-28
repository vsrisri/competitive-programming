import java.util.*;
import java.io.*;

public class POUR1 {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(reader.readLine());

        for (int tc = 0; tc < t; tc++) {
            int a = Integer.parseInt(reader.readLine()); int b = Integer.parseInt(reader.readLine()); int c = Integer.parseInt(reader.readLine());
            int gcd = gcd(a, b);
            if (c % gcd > 0 || c > Math.max(a, b)) {
                System.out.println(-1);
                continue;
            }
            System.out.println(Math.min(helper(a, b, c), helper(b, a, c)));
        }
        reader.close();

    }
    public static int helper(int a, int b, int c) {
        int aNum = a; int bNum = 0;
        int temp = 0;
        int ans = 1;
        while (aNum != c && bNum != c) {
            if (aNum <= 0) {
                aNum = a;
                ans++;
            }

            if (bNum >= b) {
                bNum = 0;
                ans++;
            }

            temp = Math.min(aNum, b - bNum);
            ans++;
            aNum-= temp;
            bNum+= temp;
        }

        return ans;
    }

    public static int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }
}
