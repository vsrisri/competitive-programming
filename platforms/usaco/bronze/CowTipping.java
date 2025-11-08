// USACO 2017 January Contest, Bronze Problem 3. Cow Tipping
import java.util.*;
import java.io.*;

public class CowTipping {
    public static char[][] arr;
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(new File("cowtip.in"));
        PrintWriter pw = new PrintWriter(new File("cowtip.out"));
        int n = sc.nextInt();
        sc.nextLine();
        arr = new char[n][n];

        for (int i = 0; i < n; i++) {
            String s = sc.nextLine();
			for (int j = 0; j < n; j++) {
				arr[i][j] = s.charAt(j);
            }
        }
        int ans = 0;
        for (int i = n - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (arr[i][j] == '0') {
                    ans++;
                }
                for (int l = 0; l <= i; l++) {
                    for (int k = 0; k <= j; k++) {
                        if (arr[l][k] == '0') {
                            arr[l][k] = '1';
                        } else {
                            arr[l][k] = '0';
                        }
                    }
                }
            }

        }

        pw.print(ans);
        sc.close();
        pw.close();
    }

}
