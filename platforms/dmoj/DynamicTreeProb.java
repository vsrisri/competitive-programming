import java.io.*;
import java.util.*;

public class DynamicTreeProb {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] parent = new int[N + 1];
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            adj.add(new ArrayList<>());
        }

        if (N > 1) {
            st = new StringTokenizer(br.readLine());
            for (int i = 2; i <= N; i++) {
                int p = Integer.parseInt(st.nextToken());
                parent[i] = p;
                adj.get(p).add(i);
            }
        }

        int[] depth = new int[N + 1];
        findDepths(1, 1, adj, depth);
        List<Integer>[] leavesByDepth = new ArrayList[N + 1];
        for (int i = 0; i <= N; i++) {
            leavesByDepth[i] = new ArrayList<>();
        }

        for (int i = 1; i <= N; i++) {
            if (adj.get(i).isEmpty()) {
                leavesByDepth[depth[i]].add(i);
            }
        }

        boolean[] picked = new boolean[N + 1];
        List<Integer> collectedApplesCounts = new ArrayList<>(); 
        for (int currentDepth = N; currentDepth >= 1; currentDepth--) {
            for (int leafId : leavesByDepth[currentDepth]) {
                int currApples = 0;
                int temp = leafId;
                List<Integer> pathNodes = new ArrayList<>();

                while (temp != 0) {
                    if (!picked[temp]) {
                        currApples++;
                    }
                    pathNodes.add(temp);
                    temp = parent[temp];
                }

                if (currApples > 0) {
                    collectedApplesCounts.add(currApples);
                    for (int node : pathNodes) {
                        picked[node] = true;
                    }
                }
            }
        }

        int[] countsOfAppleValues = new int[N + 1];
        for (int value : collectedApplesCounts) {
            countsOfAppleValues[value]++;
        }

        int[] collArr = new int[K];
        int currentKIndex = 0;
        for (int appleValue = N; appleValue >= 1; appleValue--) {
            if (currentKIndex == K) {
                break;
            }
            for (int count = 0; count < countsOfAppleValues[appleValue]; count++) {
                if (currentKIndex == K) {
                    break;
                }
                collArr[currentKIndex++] = appleValue;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            sb.append(collArr[i % K]);
            if (i < M - 1) {
                sb.append(" ");
            }
        }
        System.out.println(sb.toString());
    }

    public static void findDepths(int u, int d, List<List<Integer>> adj, int[] depth) {
        depth[u] = d;
        for (int v : adj.get(u)) {
            findDepths(v, d + 1, adj, depth);
        }
    }

    static class Leaf {
        int nodeId;
        int depth;

        Leaf(int nodeId, int depth) {
            this.nodeId = nodeId;
            this.depth = depth;
        }
    }
}
