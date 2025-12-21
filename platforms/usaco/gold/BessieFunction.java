import java.io.*;
import java.util.*;

public class BessieFunction {
    static long totalCost = 0;
    static int root;
    static List<Boolean> visNodes;
    static List<Long> costs;
    static List<Integer> parents;
    static List<List<Integer>> childNodes;
    static Pair<Long, List<Integer>> helper(int r) {
        visNodes = new ArrayList<>();
        root = r;
        long ans = dp(r)[1];
        return new Pair<>(ans, visNodes);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        int N = Integer.parseInt(tokenizer.nextToken());
        parents = new ArrayList<>(N);
        costs = new ArrayList<>(N);
        tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 0; i < N; i++) {
            parents.add(Integer.parseInt(tokenizer.nextToken()) - 1);
        }
        
        tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 0; i < N; i++) {
            costs.add(Long.parseLong(tokenizer.nextToken()));
        }
        
        childNodes = new ArrayList<>(N);
        for (int i = 0; i < N; i++) {
            childNodes.add(new ArrayList<>());
        }
        
        for (int i = 0; i < N; i++) {
            int parent = parents.get(i);
            childNodes.get(parent).add(i);
            if (parent == i) costs.set(i, 0L);
        }
        
        List<Boolean> done = new ArrayList<>(Collections.nCopies(N, false));
        for (int i = 0; i < N; i++) {
            if (!done.get(i)) {
                int x = i;
                while (x != parents.get(x)) {
                    x = parents.get(x);
                }
                
                Pair<Long, List<Integer>> ans = helper(x);
                for (int j : ans.second) {
                    done.set(j, true);
                }
                totalCost += ans.first;
            }
        }
        
        System.out.println(totalCost);
    }

    public static long[] dp(int node) {
        visNodes.add(node);
        long[] ans = {0, costs.get(node)};
        
        for (int child : childNodes.get(node)) {
            if (child == root) {
                continue;
            }
            long[] childans = dp(child);
            ans[0] += childans[1];
            ans[1] += Math.min(childans[0], childans[1]);
        }
        
        return ans;
    }

    public static class Pair<T, U> {
        T first;
        U second;
        
        Pair(T first, U second) {
            this.first = first;
            this.second = second;
        }
    }
}

