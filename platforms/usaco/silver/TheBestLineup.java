import java.io.*;
import java.util.*;

public class TheBestLineup {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int t = Integer.parseInt(st.nextToken());
        StringBuilder ans = new StringBuilder();

        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());
            int[] a = new int[n];
            st = new StringTokenizer(br.readLine());
            for (int idx = 0; idx < n; idx++) {
                a[idx] = Integer.parseInt(st.nextToken());
            }

            int[] maxSuffix = new int[n];
            maxSuffix[n - 1] = a[n - 1];
            for (int idx = n - 2; idx >= 0; idx--) {
                maxSuffix[idx] = Math.max(a[idx], maxSuffix[idx + 1]);
            }
            int moveIdx = -1;
            for (int idx = 0; idx < n - 1; idx++) {
                if (maxSuffix[idx + 1] > a[idx]) {
                    if (moveIdx == -1 || a[idx] < a[moveIdx]) {
                        moveIdx = idx;
                    }
                }
            }

            List<Integer> b = new ArrayList<>();
            if (moveIdx == -1) {
                for (int idx = 0; idx < n; idx++) {
                    b.add(a[idx]);
                }
            }
            else {
                int toMove = a[moveIdx];
                boolean moved = false;
                for (int idx = 0; idx < n; idx++) {
                    if (idx == moveIdx) {
                        continue;
                    }

                    if (!moved && a[idx] > toMove) {
                        b.add(toMove);
                        moved = true;
                    }

                    b.add(a[idx]);
                }

                if (!moved) {
                    b.add(toMove);
                }
            }

            for (int idx = 0; idx < n; idx++) {
                ans.append(b.get(idx)).append(" ");
            }
            ans.append("\n");
        }

        System.out.print(ans);
    }
}

