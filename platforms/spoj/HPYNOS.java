import java.io.*;
import java.util.*;

public class HPYNOS {
    public static void main(String[] args) throws Exception {
        Scanner stdin = new Scanner(System.in);
        int n = stdin.nextInt();
        ArrayList<Integer> arr = new ArrayList<Integer>();
        for (int i = 0; i <= 1000000; i++) {
            arr.add(0);
        }
        int count = 0;
        int res = 0;

        while (true) {
            int temp = 0;
            temp = HPYNOS.helper(n);

            n = temp;
            count++;
            if (n == 1) {
                res = 0;
                break;
            }

            if (arr.get(temp) == 0) {
                arr.add(temp, 1);
            } else {
                res = 1;
                break;
            }
        }

        if (res == 1) {
            System.out.println("-1");
        } else {
            System.out.println(count);
        }
    }

    public static int helper(int num) {
        int temp, out = 0;
        while (num > 0) {
            temp = num % 10;
            num = num / 10;
            out+= temp * temp;
        }
        return out;
    }
}
