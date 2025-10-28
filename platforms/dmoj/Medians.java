import java.io.*;
import java.util.*;

public class Median {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] b = new int[n + 1];
        int lo = 1;
        int hi = 2 * n - 1;
        boolean[] visited = new boolean[200002];
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder out = new StringBuilder();
        for (int idx = 1; idx <= n; idx++) {
            b[idx] = Integer.parseInt(st.nextToken());
        }

        visited[b[1]] = true;
        out.append(b[1]);
        for (int idx = 2; idx <= n; idx++) {
            int curr = b[idx];
            int prev = b[idx - 1];
            if (curr == prev) {
                int left = helper(visited, lo, true);
                visited[left] = true;
                out.append(" ").append(left);
                lo = left + 1;
                int right = helper(visited, hi, false);
                visited[right] = true;
                out.append(" ").append(right);
                hi = right - 1;

            } else if (curr < prev) {
                if (visited[curr]) {
                    for (int idx2 = 0; idx2 < 2; idx2++) {
                        int left = helper(visited, lo, true);
                        visited[left] = true;
                        out.append(" ").append(left);
                        lo = left + 1;
                    }
                } else {
                    visited[curr] = true;
                    out.append(" ").append(curr);
                    int left = helper(visited, lo, true);
                    visited[left] = true;
                    out.append(" ").append(left);
                    lo = left + 1;
                }
            } else {
                if (visited[curr]) {
                    for (int idx2 = 0; idx2 < 2; idx2++) {
                        int right = helper(visited, hi, false);
                        visited[right] = true;
                        out.append(" ").append(right);
                        hi = right - 1;
                    }
                } else {
                    visited[curr] = true;
                    out.append(" ").append(curr);
                    int right = helper(visited, hi, false);
                    visited[right] = true;
                    out.append(" ").append(right);
                    hi = right - 1;
                }
            }
        }

        System.out.println(out);
    }

    public static int helper(boolean[] visited, int start, boolean inc) {
        while (visited[start]) {
            start += inc ? 1 : -1;
        }
        return start;
    }
}

