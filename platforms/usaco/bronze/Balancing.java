//USACO 2016 February Contest, Bronze Problem 3. Load Balancing
import java.io.*;
import java.util.*;
public class Balancing {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(new File("balancing.in"));
        PrintWriter p = new PrintWriter(new File("balancing.out"));
        int n = sc.nextInt();
        sc.nextLine();
        int[] x = new int[n];
        int[] y = new int[n];
        for (int idx = 0; idx < n; idx++) {
            String line = sc.nextLine();
            int spaceIdx = line.indexOf(' ');
            x[idx] = Integer.parseInt(line.substring(0, spaceIdx));
            y[idx] = Integer.parseInt(line.substring(spaceIdx + 1, line.length()));
        }

        int maxCow = n;
        for (int xi = 0; xi < n; xi++) {
            for (int yi = 0; yi < n; yi++) {
                int x2 = x[xi] + 1;
                int y2 = y[yi] + 1;
                int upperLeft = 0;
				int upperRight = 0;
				int lowerLeft = 0;
				int lowerRight = 0;
                for (int idx = 0; idx < n; idx++) {
                    if ((x[idx] < x2) && (y[idx] < y2)) {
                        lowerLeft++;
                    }
                    if ((x[idx] < x2) && (y[idx] > y2)) {
                        upperLeft++;
                    }
                    if ((x[idx] > x2) && (y[idx] < y2)) {
                        lowerRight++;
                    }
                    if ((x[idx] > x2) && (y[idx] > y2)) {
                        upperRight++;
                    }
                }
                int worst = 0;
                if (upperLeft > worst) {
					worst = upperLeft;
				}
				if (upperRight > worst) {
					worst = upperRight;
				}
				if (lowerLeft > worst) {
					worst = lowerLeft;
				}
				if (lowerRight > worst) {
					worst = lowerRight;
				}
                if (worst < maxCow) {
                    maxCow = worst;
                }
                // System.out.println(x2 + " " + y2 + " " + upperLeft + " " + upperRight + " " + lowerLeft + " " + lowerRight + " " + worst + " maxCow: " + maxCow);
            }
        }
        p.println(maxCow);
        p.close();
        sc.close();
    }
}
