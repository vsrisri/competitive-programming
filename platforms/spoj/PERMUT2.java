import java.util.*;
import java.io.*;

public class PERMUT2 {
    public static void main(String[] args) throws Exception {
        Scanner stdin = new Scanner(System.in);
        int n = stdin.nextInt();

        while (n > 0) {
            int[] arr = new int[n + 1];
            for (int idx = 1; idx <= n; idx++) {
                arr[idx] = stdin.nextInt();
            }
            boolean isAmb = true;
            for (int idx = 1; idx <= n; idx++) {
                if (arr[arr[idx]] != idx) {
                    isAmb = false;
                    System.out.println("not ambiguous");
                    break;
                }
            }
            if (isAmb) {
                System.out.println("ambiguous");
            }
            n = stdin.nextInt();
        }
        stdin.close();
    }
}
