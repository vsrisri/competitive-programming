import java.io.*;
import java.util.*;

public class MilkPails {
    public static void main(String[] args) throws Exception {
        Scanner stdin = new Scanner(new File("pails.in"));
        PrintWriter pw = new PrintWriter(new File("pails.out"));
        int x = stdin.nextInt();
        int y = stdin.nextInt();
        int k = stdin.nextInt();
        int m = stdin.nextInt();
        int ans = Integer.MAX_VALUE;
        int[] dist = new int[200 * 200];
        final int MAXINT = 100000000;
        Arrays.fill(dist, MAXINT);
        dist[0] = 0;
        LinkedList<Integer> queue = new LinkedList<Integer>();
        queue.offer(0);
        while (queue.size() > 0) {
            int curr = queue.poll();
            if (dist[curr] > k) {
                break;
            }
            int a = curr / 200;
            int b = curr % 200;
            ans = Math.min(ans, Math.abs(m - (a + b)));
            int left = 200 * x + b;
            int right = 200 * a + y;
            if (dist[left] == MAXINT) {
                queue.offer(left);
                dist[left] = dist[curr] + 1;
            }
            if (dist[right] == MAXINT) {
                queue.offer(right);
                dist[right] = dist[curr] + 1;
            }

            if (dist[b] == MAXINT) {
                queue.offer(b);
                dist[b] = dist[curr] + 1;
            }
            if (dist[a * 200] == MAXINT) {
                queue.offer(a * 200);
                dist[a * 200] = dist[curr] + 1;
            }

            int pour = Math.min(a, y - b);
            int lPour = 200 * (a - pour) + (b + pour);
            if (dist[lPour] == MAXINT) {
                queue.offer(lPour);
                dist[lPour] = dist[curr] + 1;
            }
            pour = Math.min(b, x - a);
            int rPour = 200 * (a + pour) + (b - pour);
            if (dist[rPour] == MAXINT) {
                queue.offer(rPour);
                dist[rPour] = dist[curr] + 1;
            }
        }
        pw.println(ans);
        stdin.close();
        pw.close();
    }

}
