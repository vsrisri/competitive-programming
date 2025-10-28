import java.io.*;
import java.util.*;

public class Dugovi {
    static int n;
    static long total = 0;
    static boolean[] done;   
    static int[] visited;         
    static int[] target;          
    static int[] reqArr;        
    static int[] indegArr;        
    static int[] curr;         

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        target = new int[n + 1];
        reqArr = new int[n + 1];
        indegArr = new int[n + 1];
        curr = new int[n + 1];
        done = new boolean[n + 1];
        visited = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            String[] parts = br.readLine().split(" ");
            target[i] = Integer.parseInt(parts[0]);
            reqArr[i] = Integer.parseInt(parts[1]);
            indegArr[target[i]]++;
        }

        for (int i = 1; i <= n; i++) {
            if (indegArr[i] == 0 && !done[i]) {
                helper(i);
            }
        }

        Arrays.fill(visited, 0);
        reqArr[0] = Integer.MAX_VALUE;
        curr[0] = 0;
        int label = 0;
        int start = 0;
        for (int idx = 1; idx <= n; idx++) {
            if (!done[idx]) {
                start = 0;
                int idx2 = idx;
                label++;
                while (visited[idx2] == 0) {
                    if (reqArr[idx2] - curr[idx2] < reqArr[start] - curr[start]) {
                        start = idx2;
                    }
                    visited[idx2] = label;
                    idx2 = target[idx2];
                }

                helper(start);
            }
        }

        System.out.println(total);
        br.close();
    }

    public static void helper(int v) {
        done[v] = true;
        if (curr[v] < reqArr[v]) {
            total += reqArr[v] - curr[v];
            curr[v] = reqArr[v];
        }

        curr[target[v]] += reqArr[v];
        indegArr[target[v]]--;

        if (indegArr[target[v]] == 0 && !done[target[v]]) {
            helper(target[v]);
        }
    }
}

