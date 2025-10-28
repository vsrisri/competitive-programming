import java.util.*;
import java.io.*;

public class GuessKZero {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int k = in.nextInt();
        int t = in.nextInt();

        int low = 1;
        int high = n;
        int mid = 0;
        int ans = high;
        while (low < high) {
            mid = (low + high) / 2;
            int x = findX(1, mid, t);
            if (x < k) {
                low = mid + 1;
            } else {
                high = mid - 1;
                ans = Math.min(ans, mid);
            }
        }

        System.out.println("! " + ans);
    }

    public static int findX(int l, int r, int t) {
        System.out.println("? " l + " " + r);
        return r - l - t + 1;
    }
}
