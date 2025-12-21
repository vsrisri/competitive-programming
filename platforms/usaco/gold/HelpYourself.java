import java.io.*;
import java.util.*;

public class HelpYourself {
    public static void main(String[] args) throws IOException {
        BufferedReader rinput = new BufferedReader(new FileReader("help.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("help.out")));
        int N = Integer.parseInt(rinput.readLine());
        int MOD = 1000000007;

        int[][] segments = new int[N][2];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(rinput.readLine());
            segments[i][0] = Integer.parseInt(st.nextToken());
            segments[i][1] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(segments, Comparator.comparingInt(a -> a[0]));

        int[] overlap = new int[2 * N + 2];
        int[] powerOfTwo = new int[N + 1];
        powerOfTwo[0] = 1;
        for (int i = 1; i <= N; i++) {
            powerOfTwo[i] = (2 * powerOfTwo[i - 1]) % MOD;
        }

        for (int[] seg : segments) {
            overlap[seg[0]]++;
            overlap[seg[1] + 1]--;
        }

        for (int i = 1; i <= 2 * N; i++) {
            overlap[i] += overlap[i - 1];
        }

        long result = 0;
        for (int i = 1; i <= 2 * N; i++) {
            if (overlap[i] > 0 && overlap[i - 1] == 0) {  // New connected component
                result = (result + powerOfTwo[N - overlap[i]]) % MOD;
            }
        }

        out.println(result);
        out.close();
    }
}

