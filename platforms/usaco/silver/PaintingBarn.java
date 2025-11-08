import java.util.*;
import java.io.*;

public class PaintingBarn {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(new File("paintbarn.in"));
        PrintWriter pw = new PrintWriter(new File("paintbarn.out"));
        int n = sc.nextInt();
        int k = sc.nextInt();
        int max = 1001;
        int[][] input = new int[max][max];
        for (int i=0; i<n; i++) {
            int x1 = sc.nextInt();
            int y1 = sc.nextInt(); 
            int x2 = sc.nextInt();
            int y2 = sc.nextInt();;
            input[x2][y2]++;
            input[x1][y2]--;
            input[x2][y1]--;
            input[x1][y1]++;
        }
        int ans = 0;
        for (int i = max - 1; i >= 0; i--) {
			for (int j = max - 1; j >= 0; j--) {
				if (j < max-1) {
                    input[i][j] += input[i][j + 1];
                }
				if (i == max - 1 && input[i][j] == k) {
                    ans++;
                }
			}
			if (i == max - 1) {
                continue;
            }
			for (int j = max - 1; j >= 0; j--) {
				input[i][j] += input[i + 1][j];
				if (input[i][j] == k) {
                    ans++;
                }
			}
		}

        pw.print(ans);
        sc.close();
        pw.close();
    }
}
