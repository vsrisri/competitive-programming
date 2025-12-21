import java.io.*;
import java.util.*;

public class MedianHeap {
    public static final int MAXN = 200005;
    public static int numNodes, numQueries;
    public static int[] values = new int[MAXN];
    public static int[] state = new int[MAXN];
    public static long[] cost = new long[MAXN];
    public static long[] dp[] = new long[MAXN][3];
    public static long[] results = new long[MAXN];
    public static List<Integer> uniquePositions = new ArrayList<>();
    public static Map<Integer, List<Integer>> nodeIndices = new HashMap<>();
    public static Map<Integer, List<Integer>> queryIndices = new HashMap<>();
    public static int median(int x, int y, int z) {
        return x ^ y ^ z ^ Math.min(Math.min(x, y), z) ^ Math.max(Math.max(x, y), z);
    }
    
    public static void helper2(int idx, int newState) {
        state[idx] = newState;
        while (true) {
            helper(idx);
            if (idx == 0) {
                break;
            }
            idx = (idx - 1) / 2;
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        numNodes = Integer.parseInt(br.readLine());
        for (int i = 0; i < numNodes; i++) {
            st = new StringTokenizer(br.readLine());
            int val = Integer.parseInt(st.nextToken());
            values[i] = val;
            cost[i] = Long.parseLong(st.nextToken());
            nodeIndices.computeIfAbsent(val, k -> new ArrayList<>()).add(i);
            uniquePositions.add(val);
        }
        
        numQueries = Integer.parseInt(br.readLine());
        for (int i = 0; i < numQueries; i++) {
            int queryVal = Integer.parseInt(br.readLine());
            queryIndices.computeIfAbsent(queryVal, k -> new ArrayList<>()).add(i);
            uniquePositions.add(queryVal);
        }
        
        Collections.sort(uniquePositions);
        uniquePositions = new ArrayList<>(new LinkedHashSet<>(uniquePositions));
        for (int i = numNodes - 1; i >= 0; i--) {
            helper(i);
        }
        
        for (int pos : uniquePositions) {
            List<Integer> nodes = nodeIndices.getOrDefault(pos, new ArrayList<>());
            Collections.reverse(nodes);
            for (int idx : nodes) {
                helper2(idx, 1);
            }
            for (int queryIdx : queryIndices.getOrDefault(pos, new ArrayList<>())) {
                results[queryIdx] = dp[0][1];
            }
            for (int idx : nodes) {
                helper2(idx, 2);
            }
        }
        
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numQueries; i++) {
            sb.append(results[i]).append("\n");
        }
        System.out.print(sb);
    }

    public static void helper(int idx) {
        long[] tempCost = {cost[idx], cost[idx], cost[idx]};
        tempCost[state[idx]] = 0;
        if (2 * idx + 1 >= numNodes) {
            System.arraycopy(tempCost, 0, dp[idx], 0, 3);
            return;
        }
        
        int leftChild = 2 * idx + 1;
        int rightChild = 2 * idx + 2;
        Arrays.fill(dp[idx], Long.MAX_VALUE);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    int med = median(i, j, k);
                    dp[idx][med] = Math.min(dp[idx][med], tempCost[i] + dp[leftChild][j] + dp[rightChild][k]);
                }
            }
        }
    }
}

