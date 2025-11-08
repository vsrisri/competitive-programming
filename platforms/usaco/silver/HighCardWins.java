import java.util.*;
import java.io.*;

public class HighCardWins {
    public static void main(String[] args) throws Exception {
        Scanner stdin = new Scanner(new File("highcard.in"));
        PrintWriter pw = new PrintWriter(new File("highcard.out"));
        int n = stdin.nextInt();
        int[] inArr = new int[n];
        boolean[] cards = new boolean[2 * n];
        for (int idx = 0; idx < n; idx++) {
            inArr[idx] = stdin.nextInt() - 1; 
            cards[inArr[idx]] = true;
        }
        Arrays.sort(inArr);

        int[] bessie = new int[n];
        int b = 0;
        for (int idx = 0; idx < 2 * n; idx++) {
            if (!cards[idx]) {
                bessie[b] = idx;
                b++;
            }
        }
        int ans = 0;
        b = 0;
        for (int idx = 0; idx < n; idx++) {
            while (b < n && bessie[b] < inArr[idx]) {
                b++;
            }
            if (b == n) {
                break;
            }
            ans++;
            b++;
        }
        pw.println(ans);
        stdin.close();
        pw.close();
    }
}
