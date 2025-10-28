import java.util.*;
import java.io.*;

public class ABSYS {
    public static void main(String[] args) throws Exception {
        Scanner stdin = new Scanner(System.in);
        int n = stdin.nextInt();
        //System.out.println();
        for (int tcidx = 0; tcidx < n; tcidx++) {
            stdin.nextLine();
            String str1 = stdin.next();
            stdin.next();
            String str2 = stdin.next();
            stdin.next();
            String sumStr = stdin.next();
            if (checkNum(sumStr) == -1) {
                int val1 = checkNum(str1);
                int val2 = checkNum(str2);
                System.out.println(val1 + " + " + val2 + " = " + (val1 + val2));
            } else if (checkNum(str1) == -1) {
                int val2 = checkNum(str2);
                int sum = checkNum(sumStr);
                System.out.println((sum - val2) + " + " + val2 + " = " + sum);
            } else {
                int val1 = checkNum(str1);
                int sum = checkNum(sumStr);
                System.out.println(val1 + " + " + (sum - val1) + " = " + sum);
            }
        }
        stdin.close();
    }

    public static int checkNum(String str) {
        for (int idx = 0; idx < str.length(); idx++) {
            if (str.charAt(idx) == 'm') {
                return -1;
            }
        }
        return Integer.parseInt(str);
    }
}
