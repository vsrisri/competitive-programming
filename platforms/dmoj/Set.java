import java.io.*;
import java.util.*;

public class Set {
    public static int N, K;
    public static int[][] inArr;
    public static boolean[][][] used;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        inArr = new int[K][N];
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                inArr[i][j] = Integer.parseInt(st.nextToken()) - 1;
            }
        }

        List<List<int[]>> aSets = new ArrayList<>();
        int totSets = (int) Math.pow(K, N - 1);
        int[] shifts = new int[N];
        helper(1, shifts, aSets);
        for (List<int[]> set : aSets) {
            for (int[] card : set) {
                for (int i = 0; i < N; i++) {
                    System.out.print((card[i] + 1) + " ");
                }
                System.out.println();
            }
        }
    }

    public static void helper(int pos, int[] shifts, List<List<int[]>> aSets) {
        if (pos == N) {
            List<int[]> newSet = new ArrayList<>();
            for (int i = 0; i < K; i++) {
                int[] card = new int[N];
                card[0] = inArr[i][0];
                for (int j = 1; j < N; j++) {
                    card[j] = inArr[(i + shifts[j]) % K][j];
                }
                newSet.add(card);
            }
            boolean isInit = true;
            for (int i = 1; i < N; i++) {
                if (shifts[i] != 0) {
                    isInit = false;
                    break;
                }
            }
            if (!isInit) {
                aSets.add(newSet);
            }
            return;
        }

        for (int shift = 0; shift < K; shift++) {
            shifts[pos] = shift;
            helper(pos + 1, shifts, aSets);
        }
    }
}
