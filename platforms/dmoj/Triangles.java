import java.io.*;
import java.util.*;

public class Triangles {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine().trim());
        Triangle[] arr = new Triangle[n];
        long[] xPosArr = new long[n + 1];
        long maxNum = 0;
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            arr[i] = new Triangle();
            arr[i].x = Long.parseLong(st.nextToken());
            arr[i].y = Long.parseLong(st.nextToken());
            arr[i].m = Integer.parseInt(st.nextToken());
            xPosArr[i] = arr[i].x;
            maxNum = Math.max(maxNum, arr[i].x + arr[i].m);
        }
        
        xPosArr[n] = maxNum;
        Arrays.sort(arr, (a, b) -> Long.compare(a.y, b.y));
        Arrays.sort(xPosArr);
        double tot = 0.0;
        for (int i = 0; i < n; i++) {
            long stripStart = xPosArr[i];
            long stripEnd = xPosArr[i + 1];
            if (stripEnd == stripStart) {
                continue;
            }
            long rightEdge = stripStart;
            long hLvl = 0;
            for (Triangle tri : arr) {
                long triEnd = tri.x + tri.m;
                boolean coversStrip = (tri.x <= stripStart) && (triEnd > stripStart);
                if (!coversStrip) {
                    continue;
                }
                if (rightEdge != stripStart) {
                    long hDel = tri.y - hLvl;
                    rightEdge = Math.max(stripStart, rightEdge - hDel);
                    hLvl = tri.y;
                }
                
                if (triEnd <= rightEdge) {
                    continue;
                }
                
                tot += helper(stripStart, stripEnd, triEnd);
                if (rightEdge > stripStart) {
                    tot -= helper(stripStart, stripEnd, rightEdge);
                }
                
                rightEdge = triEnd;
                hLvl = tri.y;
            }
        }
        
        long ans = Math.round(tot * 2.0);
        if (ans % 2 == 0) {
            System.out.println(ans / 2 + ".0");
        } else {
            System.out.println(ans / 2 + ".5");
        }
        
        br.close();
    }
    
    public static double helper(long start, long end, long bound) {
        double fullTri = Math.pow(bound - start, 2) / 2.0;
        double cut = Math.pow(bound - end, 2) / 2.0;
        if (bound <= end) {
            return fullTri;
        }
        return fullTri - cut;
    }
    
    public static class Triangle {
        long x;
        long y;
        int m;
    }
}
