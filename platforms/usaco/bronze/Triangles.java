// USACO 2020 February Contest, Bronze Problem 1. Triangles
import java.util.*;
import java.io.*;

public class Triangles {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(new File("triangles.in"));
        PrintWriter p = new PrintWriter(new File("triangles.out"));
        int n = sc.nextInt();
        sc.nextLine();
        int[][] points = new int[n][2];

        for (int idx = 0; idx < n; idx++) {
            String line = sc.nextLine();
            int spaceIdx = line.indexOf(' ');
            int start = Integer.parseInt(line.substring(0, spaceIdx));
            int end = Integer.parseInt(line.substring(spaceIdx + 1, line.length()));
            points[idx][0] = start;
            points[idx][1] = end;
        }
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    continue;
                }
                for (int k = 0; k < n; k++) {
                    if ((k == i) || (k == j)) {
                        continue;
                    }
                    if ((points[i][1] == points[j][1]) && (points[i][0] == points[k][0])) {
                        int currArea = Math.abs(points[j][0] - points[i][0]) * Math.abs(points[k][1] - points[i][1]);
                        ans = Math.max(ans, currArea);
                    }
                }
            }
        }
        p.print(ans);
        sc.close();
        p.close();
    }

    
}
