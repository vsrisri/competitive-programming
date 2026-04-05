import java.io.*;
import java.util.*;

public class MATRIX2 {
    public static int N, M, A, B, C, D;
    public static int[][] P;
    public static long[][] pre;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine().trim());
        StringBuilder sb = new StringBuilder();
        while (t-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            D = Integer.parseInt(st.nextToken());
            P = new int[N + 1][M + 1];
            for (int i = 1; i <= N; i++) {
                P[i][1] = Integer.parseInt(br.readLine().trim());
                for (int j = 2; j <= M; j++) {
                    P[i][j] = (P[i][j - 1] * 71 + 17) % 100 + 1;
                }
            }

            pre = new long[N + 1][M + 1];
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= M; j++) {
                    pre[i][j] = P[i][j] + pre[i - 1][j] + pre[i][j - 1] - pre[i - 1][j - 1];
                }
            }
            long ans = Long.MIN_VALUE;
            boolean hasK = (C > 0 && D > 0);
            if (!hasK) {
                for (int r = 1; r + A - 1 <= N; r++) {
                    for (int c = 1; c + B - 1 <= M; c++) {
                        long qSum = rectSum(r, c, r + A - 1, c + B - 1);
                        if (qSum > ans) {
                            ans = qSum;
                        }
                    }
                }
            } else {
                int KR = N - C + 1;
                int KC = M - D + 1;
                long[][] kSum = new long[KR + 1][KC + 1];
                for (int r = 1; r <= KR; r++) {
                    for (int c = 1; c <= KC; c++) {
                        kSum[r][c] = rectSum(r, c, r + C - 1, c + D - 1);
                    }
                }

                int inRows = A - 2 - C + 1;
                int inCols = B - 2 - D + 1;
                long[][] rowMin = new long[KR + 1][KC + 1];
                int[] dq = new int[KC + 2];
                for (int r = 1; r <= KR; r++) {
                    int head = 0, tail = 0;
                    long[] kSumR = kSum[r];
                    long[] rowMinR = rowMin[r];
                    for (int c = 1; c <= KC; c++) {
                        while (head < tail && kSumR[dq[tail - 1]] >= kSumR[c]) {
                            tail--;
                        }
                        dq[tail++] = c;
                        if (dq[head] < c - inCols + 1) {
                            head++;
                        }
                        if (c >= inCols) {
                            rowMinR[c - inCols + 1] = kSumR[dq[head]];
                        }
                    }
                }

                int goodC = KC - inCols + 1;
                long[][] rowMinT = new long[goodC + 1][KR + 1];
                for (int r = 1; r <= KR; r++) {
                    for (int c = 1; c <= goodC; c++) {
                        rowMinT[c][r] = rowMin[r][c];
                    }
                }
                long[][] minKWindow = new long[KR + 1][goodC + 1];
                dq = new int[KR + 2];
                for (int c = 1; c <= goodC; c++) {
                    int head = 0, tail = 0;
                    long[] col = rowMinT[c];
                    for (int r = 1; r <= KR; r++) {
                        while (head < tail && col[dq[tail - 1]] >= col[r]) {
                            tail--;
                        }
                        dq[tail++] = r;
                        if (dq[head] < r - inRows + 1) {
                            head++;
                        }
                        if (r >= inRows) {
                            minKWindow[r - inRows + 1][c] = col[dq[head]];
                        }
                    }
                }

                int goodQR = N - A + 1;
                int goodQC = M - B + 1;
                for (int r = 1; r <= goodQR; r++) {
                    long[] mkRow = minKWindow[r + 1];
                    for (int c = 1; c <= goodQC; c++) {
                        long qSum = rectSum(r, c, r + A - 1, c + B - 1);
                        long val = qSum - mkRow[c + 1];
                        if (val > ans) {
                            ans = val;
                        }
                    }
                }
            }

            sb.append(ans).append('\n');
        }
        System.out.print(sb);
        br.close();
    }

    public static long rectSum(int r1, int c1, int r2, int c2) {
        return pre[r2][c2] - pre[r1 - 1][c2] - pre[r2][c1 - 1] + pre[r1 - 1][c1 - 1];
    }
}
