import java.io.*;
import java.util.*;

public class BABTWR {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;
        StringTokenizer st;
        StringBuilder out = new StringBuilder();
        while ((line = br.readLine()) != null) {
            line = line.trim();
            if (line.isEmpty()) {
                continue;
            }

            int n = Integer.parseInt(line);
            ArrayList<Block> list = new ArrayList<>(n * 3);
            if (n == 0) {
                break;
            }

            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                int y = Integer.parseInt(st.nextToken());
                int x = Integer.parseInt(st.nextToken());
                int z = Integer.parseInt(st.nextToken());
                int a, b, c;
                a = Math.min(x, z); b = Math.max(x, z);
                list.add(new Block(a, y, b));
                a = Math.min(y, z); b = Math.max(y, z);
                list.add(new Block(a, x, b));
                a = Math.min(x, y); b = Math.max(x, y);
                list.add(new Block(a, z, b));
            }

            Collections.sort(list);
            int m = list.size();
            int[] dp = new int[m];
            int ans = 0;
            for (int i = 0; i < m; i++) {
                dp[i] = list.get(i).y;
                for (int j = 0; j < i; j++) {
                    if (list.get(j).x > list.get(i).x && list.get(j).z > list.get(i).z) {
                        dp[i] = Math.max(dp[i], dp[j] + list.get(i).y);
                    }
                }
                ans = Math.max(ans, dp[i]);
            }
            out.append(ans).append('\n');
        }

        System.out.print(out);
        br.close();
    }

    public static class Block implements Comparable<Block> {
        public int x, y, z;
        public Block(int x, int y, int z) {
            this.x = x; 
            this.y = y; 
            this.z = z;
        }

        public int compareTo(Block o) {
            if (x != o.x) {
                return o.x - x;
            }
            return o.z - z;
        }
    }
}
