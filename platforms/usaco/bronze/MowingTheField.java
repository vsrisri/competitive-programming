//USACO 2016 January Contest, Bronze Problem 3. Mowing the Field
import java.util.*;
import java.io.*;

public class MowingTheField {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(new File("mowing.in"));
        PrintWriter p = new PrintWriter(new File("mowing.out"));
        int n = sc.nextInt();
        sc.nextLine();
        String[] inArr = new String[n];
        int i = 0;
        while (sc.hasNext()) {
            inArr[i] = sc.nextLine();
            i++;
        }
        i = 0;

        int[][] graph = new int[2001][2001];
        int x = 1000;
        int y = 1000;
        for (i = 0; i <= 2000; i++) {
            for (int j = 0; j <= 2000; j++) {
                graph[i][j] = -1;
            }
        }
        
        int t = 0;
        int maxT = 100000;
        for (int idx = 0; idx < n; idx++) {
            char dir = inArr[idx].charAt(0);
            int numSteps = Integer.parseInt(inArr[idx].substring(2, inArr[idx].length()));
            for (int idx2 = 1; idx2 <= numSteps; idx2++) {
                if (dir == 'N') {
                    y++;
                } else if (dir == 'S') {
                    y--;
                } else if (dir == 'E') {
                    x++;
                } else if (dir == 'W') {
                    x--;
                }

                if (((graph[x][y]) >= 0) && (t - graph[x][y] < maxT)) {
                    maxT = t - graph[x][y];
                } 
                graph[x][y] = t;
                t++;
            }
        }
        if (maxT == 100000) {
            maxT = -1;
        }
        p.print(maxT);
        sc.close();
        p.close();
    }

}
