import java.util.*;
import java.io.*;

public class MoreOddPhotos {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int odd = 0;
        int even = 0;

        for (int idx = 0; idx < n; idx++) {
            int curr = sc.nextInt();
            if (curr % 2 == 0) {
                even++;
            } else {
                odd++;
            }
        }
        while (odd > even) {
            odd = odd - 2;
            even++;
        }

        if (even > odd + 1) {
            even = odd + 1;
        }

        System.out.println(even + odd);
        sc.close();
    }
}
