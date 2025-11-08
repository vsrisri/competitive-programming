import java.util.*;
import java.io.*;

public class ClosingTheFarm {
    public static void main(String[] args) throws Exception {
        Scanner stdin = new Scanner(new File("closing.in"));
        PrintWriter pw = new PrintWriter(new File("closing.out"));
        int n = stdin.nextInt();
        int m = stdin.nextInt();
        ArrayList<LinkedList<Integer>> adjList = new ArrayList<LinkedList<Integer>>();
        boolean[] badArr = new boolean[n + 1];
        LinkedList<Integer> openArr = new LinkedList<Integer>(); 

        for (int idx = 0; idx <= n; idx++) {
            adjList.add(new LinkedList<Integer>());
        }

        for (int idx = 1; idx <= n; idx++) {
            openArr.add(idx);
        }

        for (int idx = 0; idx < m; idx++) {
            int i1 = stdin.nextInt();
            int i2 = stdin.nextInt();
            adjList.get(i1).add(i2);
            adjList.get(i2).add(i1);
        }

        if (dfs(adjList, new boolean[n + 1], badArr, openArr.get(0), 1) == n) {
            pw.println("YES");
        } else {
            pw.println("NO");
        }

        for (int idx = 0; idx < n - 1; idx++) {
            int currBad = stdin.nextInt();
            badArr[currBad] = true;
            openArr.remove(openArr.indexOf(currBad));
            if (dfs(adjList, new boolean[n + 1], badArr, openArr.get(0), 1) == n - (idx + 1)) {
                 pw.println("YES");
            } else {
                 pw.println("NO");
            }
        }
        stdin.close();
        pw.close();

    }

    public static int dfs(ArrayList<LinkedList<Integer>> adjList, boolean[] visited,  boolean[] badArr, int vertex, int count) {
        visited[vertex] = true;
        for (int v : adjList.get(vertex)) {
            if (!visited[v] && !badArr[v]) {
                int num = count + 1;
                count = dfs(adjList, visited, badArr, v, num);
            }
        }
        return count;
    }

}
