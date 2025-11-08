// USACO 2020 December Contest, Silver
// Problem 2. Rectangular Pasture
import java.util.*;
import java.io.*;

public class RectangularPastureSilver {
    public static void main(String[] args) throws Exception {
        Scanner stdin = new Scanner(System.in);
        int n = stdin.nextInt();
        int[] xVals = new int[n];
        TreeSet<Integer> xSet = new TreeSet<Integer>();
        int[] yVals = new int[n];
        TreeSet<Integer> ySet = new TreeSet<Integer>();

        for (int idx = 0; idx < n; idx++) {
            xVals[idx] = stdin.nextInt();
            xSet.add(xVals[idx]);
            yVals[idx] = stdin.nextInt();
            ySet.add(yVals[idx]);
        }

        HashMap<Integer,Integer> xMap = coordCompress(xSet);
        HashMap<Integer,Integer> yMap = coordCompress(ySet);
        replace(xMap, xVals);
        replace(yMap, yVals);
        int[][] simulate = new int[n + 1][n + 1];

        for (int idx = 0; idx < n; idx++) {
            simulate[xVals[idx] + 1][yVals[idx] + 1]++;
        }

        for (int idx = 0; idx <= n; idx++) {
            for (int idx2 = 1; idx2 <= n; idx2++) {
                simulate[idx][idx2]+= simulate[idx][idx2 - 1];
            }
        }

        for (int idx = 0; idx <= n; idx++) {
            for (int idx2 = 1; idx2 <= n; idx2++) {
                simulate[idx2][idx]+= simulate[idx2 - 1][idx];
            }
        }

        long ans = n + 1;
        for (int idx = 0; idx < n; idx++) {
            for (int idx2 = idx + 1; idx2 < n; idx2++) {
                int xMin = Math.min(xVals[idx], xVals[idx2]);
                int xMax = Math.max(xVals[idx], xVals[idx2]) + 1;
                int yMin = Math.min(yVals[idx], yVals[idx2]);
                int yMax = Math.max(yVals[idx], yVals[idx2]) + 1;
                long lhs = simulate[xMax][yMin] - simulate[xMin][yMin];
                long rhs = simulate[xMax][n] - simulate[xMin][n] - simulate[xMax][yMax] + simulate[xMin][yMax];
                ans+= (lhs + 1) * (rhs + 1);
            }
        }
        System.out.println(ans);
        stdin.close();
    }

    public static HashMap<Integer,Integer> coordCompress(TreeSet<Integer> set) {
        int idx = 0;
        HashMap<Integer,Integer> map = new HashMap<Integer,Integer>();
        while (set.size() > 0) {
            map.put(set.pollFirst(), idx++);
        }
        return map;
    }

    public static void replace(HashMap<Integer,Integer> map, int[] arr) {
        for (int idx = 0; idx < arr.length; idx++) {
            arr[idx] = map.get(arr[idx]);
        }
    }
}

