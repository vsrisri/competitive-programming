import java.util.*;
import java.io.*;

public class ACPC10A {
    public static void main(String[] args) throws Exception {
        Scanner stdin = new Scanner(System.in);
        while (stdin.hasNextLine()) {
            int num1 = stdin.nextInt();
            int num2 = stdin.nextInt();
            int num3 = stdin.nextInt();
            if (a == 0 && b == 0 && c == 0) {
                break;
            }

            int diff = num2 - num1;
            int q = num2;
            if (num1 == 0 || num2 == 0 || num3 == 0) {
                q = 0;
            } else {
                q/= num1;
            }

            if (diff == (num3 - num2)) {
                System.out.println("AP " + (num3 + diff));
            } else {
                System.out.println("GP " + (num3 * q));
            }
        }
        stdin.close();
    }
}
