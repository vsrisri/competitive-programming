import java.io.*;
import java.util.*;

public class FollowDirect {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine().trim());
        char[][] dArr = new char[n + 2][n + 2];
        int[][] price = new int[n + 2][n + 2];
        long[][] count = new long[n + 2][n + 2];
        for (int i = 1; i <= n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String s = st.nextToken();
            for (int j = 1; j <= n; j++) {
                dArr[i][j] = s.charAt(j - 1);
                count[i][j] = 1;
            }
            price[i][n + 1] = Integer.parseInt(st.nextToken());
        }

        StringTokenizer stLast = new StringTokenizer(br.readLine());
        for (int j = 1; j <= n; j++) {
            price[n + 1][j] = Integer.parseInt(stLast.nextToken());
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (dArr[i][j] == 'R') {
                    count[i][j + 1] += count[i][j];
                } else {
                    count[i + 1][j] += count[i][j];
                }
            }
        }

        long ans = 0;
        for (int i = 1; i <= n; i++) {
            ans += count[i][n + 1] * price[i][n + 1];
        }
        for (int j = 1; j <= n; j++) {
            ans += count[n + 1][j] * price[n + 1][j];
        }

        StringBuilder out = new StringBuilder();
        out.append(ans).append('\n');
        int q = Integer.parseInt(br.readLine().trim());
        for (int qi = 0; qi < q; qi++) {
            StringTokenizer stq = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(stq.nextToken());
            int c = Integer.parseInt(stq.nextToken());
            long flow = count[r][c];
            int x = r, y = c;
            if (dArr[r][c] == 'R') {
                while (x <= n && y <= n) {
                    count[x][y] -= flow;
                    if (dArr[x][y] == 'R') {
                        y++;
                    }
                    else {
                        x++;
                    }
                }
                count[x][y] -= flow;
                dArr[r][c] = 'D';
                x = r; y = c;
                while (x <= n && y <= n) {
                    count[x][y] += flow;
                    if (dArr[x][y] == 'R') {
                        y++;
                    }
                    else {
                        x++;
                    }
                }
                count[x][y] += flow;
            } else {
                while (x <= n && y <= n) {
                    count[x][y] -= flow;
                    if (dArr[x][y] == 'R') {
                        y++;
                    }
                    else {
                        x++;
                    }
                }
                count[x][y] -= flow;
                dArr[r][c] = 'R';
                x = r; y = c;
                while (x <= n && y <= n) {
                    count[x][y] += flow;
                    if (dArr[x][y] == 'R') {
                        y++;
                    }
                    else {
                        x++;
                    }
                }
                count[x][y] += flow;
            }

            ans = 0;
            for (int i = 1; i <= n; i++) {
                ans += count[i][n + 1] * price[i][n + 1];
            }
            for (int j = 1; j <= n; j++) {
                ans += count[n + 1][j] * price[n + 1][j];
            }
            out.append(ans).append('\n');
        }
        System.out.print(out.toString());
    }
}

