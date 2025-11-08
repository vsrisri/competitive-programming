import java.util.*;
import java.io.*;

public class CheeseBlock {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        int ans = 0;
        int n = Integer.parseInt(tokenizer.nextToken());
        int q = Integer.parseInt(tokenizer.nextToken());
        int[][] xDir = new int[n][n];
        int[][] yDir = new int[n][n];
        int[][] zDir = new int[n][n];

        for (int idx = 0; idx < q; idx++) {
            tokenizer = new StringTokenizer(reader.readLine());
            int x = Integer.parseInt(tokenizer.nextToken());
            int y = Integer.parseInt(tokenizer.nextToken());
            int z = Integer.parseInt(tokenizer.nextToken());
            if (++xDir[y][z] == n) {
                ans++;
            } if (++yDir[x][z] == n) {
                ans++;
            } if (++zDir[x][y] == n) {
                ans++;
            }
            System.out.println(ans);
        }

        reader.close();
    }
}

