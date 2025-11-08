import java.util.*;
import java.io.*;

public class StampGrid {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(reader.readLine());
        for (int tc = 0; tc < t; tc++) {
            reader.readLine();
            int n = Integer.parseInt(reader.readLine());
            char[][] desired = new char[n][n];
            for (int idx = 0; idx < n; idx++) {
                String str = reader.readLine();
                for (int idx2 = 0; idx2 < n; idx2++) {
                    desired[idx][idx2] = str.charAt(idx2);
                }
            }

            int k = Integer.parseInt(reader.readLine());
            char[][] stamp = new char[k][k];
            for (int idx = 0; idx < k; idx++) {
                String str = reader.readLine();
                for (int idx2 = 0; idx2 < k; idx2++) {
                    stamp[idx][idx2] = str.charAt(idx2);
                }
            }

            char[][] ans = new char[n][n];
            for (int idx = 0; idx < n; idx++) {
                for (int idx2 = 0; idx2 < n; idx2++) {
                    ans[idx][idx2] = '.';
                }
            }

            for (int rotation = 0; rotation < 4; rotation++) {
                for (int idx = 0; idx < n - k + 1; idx++) {
                    for (int idx2 = 0; idx2 < n - k + 1; idx2++) {
                        boolean bool = true;
                        for (int i = 0; i < k; i++) {
                            for (int j = 0; j < k; j++) {
                                if (!((desired[idx + i][idx2 + j] == '*') || (stamp[i][j] == '.'))) {
                                    bool = false;
                                }
                            }
                        }

                        if (bool) {
                            for (int i = 0; i < k; i++) {
                                for (int j = 0; j < k; j++) {
                                    if (stamp[i][j] == '*') {
                                        ans[idx + i][idx2 + j] = '*';
                                    }
                                }
                            }
                        }   
                    }
                }

                char[][] newStamp = new char[k][k];

                for (int i = 0; i < k; i++) {
                    for (int j = 0; j < k; j++) {
                        newStamp[i][j] = stamp[j][k - 1 - i];
                    }
                }
                stamp = newStamp;
            }

            String outStr = "YES";
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (ans[i][j] != desired[i][j]) {
                        outStr = "NO";
                        break;
                    }
                }
                if (outStr.charAt(0) == 'N') {
                    break;
                }
            }
            System.out.println(outStr);
        }
        reader.close();
    }
}
