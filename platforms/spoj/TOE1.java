import java.io.*;
import java.util.*;

public class TOE1 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine().trim());
        StringBuilder out = new StringBuilder();

        for (int tc = 0; tc < t; tc++) {
            char[][] g = new char[3][3];
            for (int i = 0; i < 3; i++) {
                g[i] = br.readLine().trim().toCharArray();
            }

            if (t > 0) {
                br.readLine();
            }

            int x = 0, o = 0;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (g[i][j] == 'X') {
                        x++;
                    } else if (g[i][j] == 'O') {
                        o++;
                    }
                }
            }


            boolean winX = helper(g, 'X');
            boolean winO = helper(g, 'O');
            boolean ans = true;
            if (!(x == o || x == o + 1)) {
                ans = false;
            }

            if (winX && winO) {
                ans = false;
            }

            if (winX && x != o + 1) {
                ans = false;
            }

            if (winO && x != o) {
                ans = false;
            }


            out.append(ans ? "yes\n" : "no\n");
        }

        System.out.print(out);
        br.close();
    }

    public static boolean helper(char[][] g, char c) {
        for (int i = 0; i < 3; i++) {
            if (g[i][0] == c && g[i][1] == c && g[i][2] == c) {
                return true;
            }

            if (g[0][i] == c && g[1][i] == c && g[2][i] == c) {
                return true;
            }
        }

        if (g[0][0] == c && g[1][1] == c && g[2][2] == c) {
            return true;
        }

        if (g[0][2] == c && g[1][1] == c && g[2][0] == c) {
            return true;
        }

        return false;
    }
}

