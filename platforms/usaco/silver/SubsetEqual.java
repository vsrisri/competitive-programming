import java.io.*;
import java.util.*;

public class SubsetEqual {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String first = br.readLine();
        String second = br.readLine();
        int len = 18;
        int[][] countA = new int[first.length() + 1][len];
        int[][] countB = new int[second.length() + 1][len];
        for (int i = 0; i < first.length(); i++) {
            System.arraycopy(countA[i], 0, countA[i + 1], 0, len);
            countA[i + 1][first.charAt(i) - 'a']++;
        }

        for (int i = 0; i < second.length(); i++) {
            System.arraycopy(countB[i], 0, countB[i + 1], 0, len);
            countB[i + 1][second.charAt(i) - 'a']++;
        }

        boolean[][] pairOk = new boolean[len][len];
        for (int i = 0; i < len; i++) {
            for (int j = i; j < len; j++) {
                StringBuilder p = new StringBuilder();
                StringBuilder q = new StringBuilder();
                char c1 = (char) ('a' + i);
                char c2 = (char) ('a' + j);
                for (int k = 0; k < first.length(); k++) {
                    char ch = first.charAt(k);
                    if (ch == c1 || ch == c2) {
                        p.append(ch);
                    }
                }

                for (int k = 0; k < second.length(); k++) {
                    char ch = second.charAt(k);
                    if (ch == c1 || ch == c2) {
                        q.append(ch);
                    }
                }
                pairOk[i][j] = pairOk[j][i] = p.toString().equals(q.toString());
            }
        }

        int queries = Integer.parseInt(br.readLine());
        StringBuilder out = new StringBuilder();
        for (int qi = 0; qi < queries; qi++) {
            String line = br.readLine();
            boolean[] used = new boolean[len];
            for (int i = 0; i < line.length(); i++) {
                used[line.charAt(i) - 'a'] = true;
            }

            int sumA = 0;
            int sumB = 0;
            for (int i = 0; i < len; i++) {
                if (used[i]) {
                    sumA += countA[first.length()][i];
                    sumB += countB[second.length()][i];
                }
            }

            boolean ok = (sumA == sumB);
            if (ok) {
                for (int i = 0; i < len && ok; i++) {
                    if (!used[i]) {
                        continue;
                    }
                    for (int j = i + 1; j < len; j++) {
                        if (used[j] && !pairOk[i][j]) {
                            ok = false;
                            break;
                        }
                    }
                }
            }

            out.append(ok ? 'Y' : 'N');
        }

        System.out.print(out.toString());
        br.close();
    }
}

