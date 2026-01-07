import java.io.*;
import java.util.*;

public class TOE2 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder out = new StringBuilder();
        while (true) {
            String s = br.readLine();
            if (s.equals("end")) {
                break;
            }

            char[][] g = new char[3][3];
            int idx = 0;
            int x = 0;
            int o = 0;
            int dot = 0;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    g[i][j] = s.charAt(idx++);
                }
            }

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (g[i][j] == 'X') {
                        x++;
                    } else if (g[i][j] == 'O') {
                        o++;
                    } else {
                        dot++;
                    }
                }
            }

            boolean winX = helper(g, 'X');
            boolean winO = helper(g, 'O');
            boolean valid = true;
            if (!(x == o || x == o + 1)) {
                valid = false;
            }
            if (winX && winO) {
                valid = false;
            }
            if (winX && x != o + 1) {
                valid = false;
            }
            if (winO && x != o) {
                valid = false;
            }

            if (!winX && !winO && dot > 0) {
                valid = false;
            }
            out.append(valid ? "valid\n" : "invalid\n");
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

