import java.io.*;
import java.util.*;

public class WalkingAlongFence {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int p = Integer.parseInt(st.nextToken());
        int[] x = new int[200002];
        int[] y = new int[200002];
        int[][] distArr = new int[1002][1002];

        for (int i = 1; i <= p; i++) {
            st = new StringTokenizer(br.readLine());
            x[i] = Integer.parseInt(st.nextToken());
            y[i] = Integer.parseInt(st.nextToken());
        }

        x[p + 1] = x[1];
        y[p + 1] = y[1];
        int ans = 1;
        for (int i = 1; i <= p; i++) {
            int mX = 0, mY = 0;
            if (x[i] < x[i + 1]) {
                mX = 1;
            }
            if (x[i] > x[i + 1]) {
                mX = -1;
            }
            if (y[i] < y[i + 1]) {
                mY = 1;
            }
            if (y[i] > y[i + 1]) {
                mY = -1;
            }
            for (int xIdx = x[i] + mX, yIdx = y[i] + mY; ; xIdx += mX, yIdx += mY) {
                ans++;
                distArr[xIdx][yIdx] = ans;
                if (xIdx == x[i + 1] && yIdx == y[i + 1]) {
                    break;
                }
            }
        }
        distArr[x[1]][y[1]] = 1;
        ans--;

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            int dist = distArr[x2][y2] - distArr[x1][y1];
            if (dist < 0) {
                dist += ans;
            }

            sb.append(Math.min(dist, ans - dist)).append('\n');
        }
        System.out.print(sb);
    }
}

