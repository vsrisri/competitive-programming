import java.io.*;
import java.util.*;

public class VocabQuiz {
    static List<Integer>[] adj;
    static Set<Integer> endWords;
    static Map<Integer, Integer> wordLengths;
    static int[] ans;
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());

        adj = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) {
            adj[i] = new ArrayList<>();
        }

        st = new StringTokenizer(br.readLine());
        for (int idx = 1; idx <= n; idx++) {
            int parent = Integer.parseInt(st.nextToken());
            adj[parent].add(idx);
        }

        endWords = new HashSet<>();
        wordLengths = new HashMap<>();
        st = new StringTokenizer(br.readLine());
        int m = st.countTokens();
        ans = new int[m];

        for (int idx = 0; idx < m; idx++) {
            endWords.add(Integer.parseInt(st.nextToken()));
        }

        dfs(0, 0);

        for (int idx = 0; idx < m; idx++) {
            System.out.println(ans[idx]);
        }
    }

    public static void dfs(int node, int depth) {
        if (endWords.contains(node)) {
            wordLengths.put(node, depth);
        }

        for (int child : adj[node]) {
            dfs(child, depth + 1);
        }

        if (endWords.contains(node)) {
            int idx = 0;
            for (int word : endWords) {
                if (word == node) {
                    ans[idx] = wordLengths.get(node);
                }
                idx++;
            }
        }
    }
}

