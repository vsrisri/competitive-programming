import java.util.*;
import java.io.*;

public class CitiesStates {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(new File("citystate.in"));
        PrintWriter pw = new PrintWriter(new File("citystate.out"));
        int[][] freq = new int[676][676];
        int n = sc.nextInt();
        sc.nextLine();

        for (int i=0; i<n; i++) {
            String city = sc.next();
            String state = sc.next();
            freq[getIdx(city)][getIdx(state)]++;
        }

        long ans = 0;
        for (int i = 0; i < 676; i++) {
            for (int j = i + 1; j < 676; j++) {
                ans += ((long)freq[i][j]*freq[j][i]);
            }
        }
        pw.print(ans);
        sc.close();
        pw.close();
    }

    public static int getIdx(String s) {
        return 26 * (s.charAt(0) - 'A') + s.charAt(1) - 'A';
    }
}

