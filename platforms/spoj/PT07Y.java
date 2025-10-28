import java.io.*;
import java.util.*;

public class PT07Y {
    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);
        int n = stdin.nextInt();
        int m = stdin.nextInt();
        ArrayList<List<Integer>> adjList = new ArrayList<List<Integer>>();
        for (int i = 0; i < n; i++) {
            adjList.add(new LinkedList<Integer>());
        }
        int e = m;
        while (e > 0) {
            int e1 = stdin.nextInt();
            int e2 = stdin.nextInt();
            adjList.get(e1 - 1).add(e2 - 1);
            adjList.get(e2 - 1).add(e1 - 1);
            e--;
        }

        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        boolean[] visited = new boolean[n];
        int numNodesVisited = 0;
        while (!stack.isEmpty()) {
            int curr = stack.pop();
            if (!visited[curr]) {
                visited[curr] = true;
                numNodesVisited++;
            }
            
            for (int idx = 0; idx < adjList.get(curr).size(); idx++) {
                if (!visited[adjList.get(curr).get(idx)]) {
                    stack.add(adjList.get(curr).get(idx));
                }
            }
        }
        String ans = "NO";
        if ((numNodesVisited == n && stack.isEmpty()) && n == m + 1|| n == 1) {
            ans = "YES";
        } 
        System.out.println(ans);
        stdin.close();
    }
    /*
    Disjoint Set Union: Find method for later
    public static int find(int v, int[] vertArr) {
        int curr = vertArr[v];
        if (curr < 0) {
            return v;
        } 
        return find(curr, vertArr);

    }
    */
}
