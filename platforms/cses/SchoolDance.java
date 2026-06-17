import java.util.*;
import java.io.*;

public class SchoolDance {
    public static int n, m, k;
    public static List<Integer>[] adj;
    public static int[] matchGirl;
    public static boolean[] visited;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        adj = new List[n + 1];
        for (int i = 1; i <= n; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            adj[a].add(b);
        }
        matchGirl = new int[m + 1];
        int ans = 0;
        for (int boy = 1; boy <= n; boy++) {
            visited = new boolean[m + 1];
            if (helper(boy)) {
                ans++;
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append(ans).append("\n");
        for (int girl = 1; girl <= m; girl++) {
            if (matchGirl[girl] != 0) {
                sb.append(matchGirl[girl]).append(" ").append(girl).append("\n");
            }
        }
        System.out.print(sb);
    }

    public static boolean helper(int boy) {
        for (int girl : adj[boy]) {
            if (!visited[girl]) {
                visited[girl] = true;
                if (matchGirl[girl] == 0 || helper(matchGirl[girl])) {
                    matchGirl[girl] = boy;
                    return true;
                }
            }
        }
        return false;
    }
}
