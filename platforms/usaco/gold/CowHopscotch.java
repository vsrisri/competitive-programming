import java.io.*;
import java.util.*;

public class CowHopscotch {
    public static final int MAX = 755;
    public static final int MOD = (int)1e9 + 7;
    public static int rows, cols, colorCount;
    public static int[][] grid = new int[MAX][MAX];
    public static long[] dp = new long[MAX];

    public static class SegmentTree {
        int size;
        long[] tree;

        SegmentTree(int n) {
            size = n;
            tree = new long[2 * size];
        }

        void update(int pos, long value) {
            pos += size;
            tree[pos] = (tree[pos] + value) % MOD;
            while (pos > 1) {
                pos >>= 1;
                tree[pos] = (tree[2 * pos] + tree[2 * pos + 1]) % MOD;
            }
        }

        long query(int left, int right) {
            long resLeft = 0, resRight = 0;
            left += size;
            right += size + 1;
            while (left < right) {
                if ((left & 1) == 1) {
                    resLeft = (resLeft + tree[left++]) % MOD;
                }
                if ((right & 1) == 1) {
                    resRight = (tree[--right] + resRight) % MOD;
                }
                left >>= 1;
                right >>= 1;
            }
            return (resLeft + resRight) % MOD;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader input = new BufferedReader(new FileReader("hopscotch.in"));
        BufferedWriter output = new BufferedWriter(new FileWriter("hopscotch.out"));
        StringTokenizer st = new StringTokenizer(input.readLine());
        rows = Integer.parseInt(st.nextToken());
        cols = Integer.parseInt(st.nextToken());
        colorCount = Integer.parseInt(st.nextToken());
        List<Integer>[] positions = new ArrayList[colorCount + 3];
        Map<Integer, Integer>[] indexMap = new HashMap[colorCount + 3];
        SegmentTree[] colorSegTree = new SegmentTree[colorCount + 3];
        SegmentTree totalSegTree = new SegmentTree(cols + 2);
        for (int i = 1; i <= colorCount; i++) {
            positions[i] = new ArrayList<>();
            indexMap[i] = new HashMap<>();
        }

        for (int i = 1; i <= rows; i++) {
            st = new StringTokenizer(input.readLine());
            for (int j = 1; j <= cols; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
                positions[grid[i][j]].add(j);
            }
        }

        for (int i = 1; i <= colorCount; i++) {
            Collections.sort(positions[i]);
            int idx = 0;
            for (int val : positions[i]) {
                if (!indexMap[i].containsKey(val)) {
                    indexMap[i].put(val, idx++);
                }
            }
            colorSegTree[i] = new SegmentTree(indexMap[i].size() + 2);
        }

        totalSegTree.update(1, 1);
        colorSegTree[grid[1][1]].update(indexMap[grid[1][1]].get(1), 1);

        for (int i = 2; i <= rows; i++) {
            for (int j = 2; j <= cols; j++) {
                int color = grid[i][j];
                int mappedIndex = indexMap[color].get(j);
                long ways = (totalSegTree.query(1, j - 1) - colorSegTree[color].query(1, mappedIndex - 1) + MOD) % MOD;
                dp[j] = ways;
            }
            for (int j = 2; j <= cols; j++) {
                int color = grid[i][j];
                int mappedIndex = indexMap[color].get(j);
                totalSegTree.update(j, dp[j]);
                colorSegTree[color].update(mappedIndex, dp[j]);
            }
        }

        output.write(Long.toString(dp[cols]));
        output.newLine();

        input.close();
        output.close();
    }
}

