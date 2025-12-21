import java.io.*;
import java.util.*;

public class MilkingOrder {
    public static List<Integer>[] constraints = new ArrayList[50000];
    public static List<Integer> ans = new ArrayList<>();
    public static boolean isValidOrder(int cowCount, int obsCount, int limit) {
        ans.clear();
        List<Set<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < cowCount; i++) {
            graph.add(new HashSet<>());
        }
        int[] inDegree = new int[cowCount];
        for (int i = 0; i < limit; i++) {
            if (constraints[i].size() <= 1) {
                continue;
            }
            for (int j = 1; j < constraints[i].size(); j++) {
                int from = constraints[i].get(j - 1);
                int to = constraints[i].get(j);
                graph.get(from).add(to);
            }
        }
        for (int i = 0; i < cowCount; i++) {
            for (int next : graph.get(i)) {
                inDegree[next]++;
            }
        }
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int i = 0; i < cowCount; i++) {
            if (inDegree[i] == 0) {
                queue.add(i);
            }
        }
        while (!queue.isEmpty()) {
            int current = queue.poll();
            ans.add(current);
            for (int neighbor : graph.get(current)) {
                inDegree[neighbor]--;
                if (inDegree[neighbor] == 0) {
                    queue.add(neighbor);
                }
            }
        }
        return ans.size() == cowCount;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("milkorder.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("milkorder.out")));
        StringTokenizer tokenizer = new StringTokenizer(br.readLine());
        int cowCount = Integer.parseInt(tokenizer.nextToken());
        int obsCount = Integer.parseInt(tokenizer.nextToken());
        for (int i = 0; i < obsCount; i++) {
            tokenizer = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(tokenizer.nextToken());
            constraints[i] = new ArrayList<>();
            for (int j = 0; j < k; j++) {
                constraints[i].add(Integer.parseInt(tokenizer.nextToken()) - 1);
            }
        }

        int low = 0;
        int high = obsCount;
        while (low < high) {
            int mid = (low + high + 1) / 2;
            if (isValidOrder(cowCount, obsCount, mid)) {
                low = mid;
            } else {
                high = mid - 1;
            }
        }

        isValidOrder(cowCount, obsCount, low);
        for (int i = 0; i < cowCount; i++) {
            pw.print((ans.get(i) + 1));
            pw.print(i != cowCount - 1 ? " " : "\n");
        }
        br.close();
        pw.close();
    }
}

