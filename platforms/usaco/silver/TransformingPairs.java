import java.util.*;
import java.io.*;

public class TransformingPairs {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        for (int tc = 0; tc < t; tc++) {
            long a = sc.nextLong();
            long b = sc.nextLong();
            long c = sc.nextLong();
            long d = sc.nextLong();
            long ans = 0;
            while (true) {
                if (a == c && b == d) {
                    System.out.println(ans);
                    break;
                }

                if (c < d) {
                    long temp = a;
                    a = b;
                    b = temp;
                    temp = c;
                    c = d;
                    d = temp;
                }

                if ((c - a) % d == 0 && b == d) {
                    ans += (c - a) / d;
                    System.out.println(ans);
                    break;
                }

                if (d == 0 || a > c || b > d) {
                    System.out.println(-1);
                    break;
                }

                ans += c / d;
                c %= d;
            }
        }
    }
}
