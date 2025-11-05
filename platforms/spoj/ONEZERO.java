import java.io.*;
import java.util.*;

public class ONEZERO  {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int K = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < K; tc++) {
            int n = Integer.parseInt(br.readLine());
            sb.append(helper(n)).append('\n');
        }
        System.out.print(sb);
        br.close();
    }

    public static String helper(int n) {
        boolean[] visited = new boolean[n];
        Queue<Node> q = new LinkedList<>();
        if (n == 1) {
            return "1";
        }
        q.add(new Node("1", 1 % n));
        visited[1 % n] = true;
        while (!q.isEmpty()) {
            Node curr = q.poll();
            if (curr.rem == 0) {
                return curr.val;
            }
            int rem0 = (curr.rem * 10) % n;
            int rem1 = (curr.rem * 10 + 1) % n;
            if (!visited[rem0]) {
                visited[rem0] = true;
                q.add(new Node(curr.val + "0", rem0));
            }
            if (!visited[rem1]) {
                visited[rem1] = true;
                q.add(new Node(curr.val + "1", rem1));
            }
        }
        return "";
    }

    public static class Node {
        String val;
        int rem;

        public Node(String v, int r) { 
            val = v; 
            rem = r; 
        }
    }
}

