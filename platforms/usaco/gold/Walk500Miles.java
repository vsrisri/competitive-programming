import java.io.*;
import java.util.*;

public class Walk500Miles {
    public static final long MOD = 2019201997L;
    public static final long F1 = 2019201913L;
    public static final long F2 = 2019201949L;
    public static long calcLength(long a, long b) {
        a++;
        b++;
        return (a * F1 + b * F2) % MOD;
    }

    public static long[] prim(int N) {
        long[] dist = new long[N];
        Arrays.fill(dist, MOD);
        boolean[] visited = new boolean[N];
        for (int i = 0; i < N; i++) {
            int minDistNode = -1;
            for (int j = 0; j < N; j++) {
                if (!visited[j] && (minDistNode < 0 || dist[j] < dist[minDistNode])) {
                    minDistNode = j;
                }
            }

            visited[minDistNode] = true;
            for (int j = 0; j < N; j++) {
                if (!visited[j]) {
                    dist[j] = Math.min(dist[j], calcLength(Math.min(minDistNode, j), Math.max(minDistNode, j)));
                }
            }
        }
        return dist;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("walk.in"));
        BufferedWriter bw = new BufferedWriter(new FileWriter("walk.out"));
        String[] input = br.readLine().split(" ");
        int N = Integer.parseInt(input[0]);
        int K = Integer.parseInt(input[1]);
        br.close();
        long[] mst = prim(N);
        Arrays.sort(mst);
        bw.write(String.valueOf(mst[N - K]) + "\n");
        bw.close();
    }
}
