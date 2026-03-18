import java.util.*;
import java.io.*;

public class GoodCycShifts {
    public static int[] bit;
    public static int bitSize;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine().trim());
        StringBuilder sb = new StringBuilder();
        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine().trim());
            int[] perm = new int[n];
            long[] rangeDiff = new long[n + 1];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                perm[i] = Integer.parseInt(st.nextToken());
            }

            for (int i = 0; i < n; i++) {
                int v = perm[i];
                int lo1 = 0, hi1 = v - 2 - i;
                if (hi1 >= lo1 && lo1 < n) {
                    int rCap = Math.min(hi1, n - 1);
                    if (rCap >= lo1) {
                        rangeDiff[lo1]++;
                        if (rCap + 1 < n) {
                            rangeDiff[rCap + 1]--;
                        }
                    }
                }
                int lo2 = n - i;
                int hi2 = v + n - 2 - i;
                if (hi2 >= lo2 && lo2 < n) {
                    int rCap = Math.min(hi2, n - 1);
                    if (rCap >= lo2) {
                        rangeDiff[lo2]++;
                        if (rCap + 1 < n) {
                            rangeDiff[rCap + 1]--;
                        }
                    }
                }
            }

            long[] badCount = new long[n];
            long cur = 0;
            long fVal = 0;
            for (int i = 0; i < n; i++) {
                cur += rangeDiff[i];
                badCount[i] = cur;
            }

            for (int i = 0; i < n; i++) {
                fVal += Math.abs(perm[i] - (i + 1));
            }

            bitSize = n;
            bit = new int[n + 1];
            long invCount = 0;
            upd(perm[0], 1);
            for (int i = 1; i < n; i++) {
                invCount += query(perm[i] + 1, n);
                upd(perm[i], 1);
            }

            List<Integer> ans = new ArrayList<>();
            for (int s = 0; s < n; s++) {
                if (2 * invCount <= fVal) {
                    ans.add(s);
                }
                long nextInv = invCount - (n - perm[n - 1 - s]) + (perm[n - 1 - s] - 1);
                long nextF = fVal + (2 * (n - badCount[s]) - n) + (2 * perm[n - 1 - s] - n - 2);
                invCount = nextInv;
                fVal = nextF;
            }

            sb.append(ans.size()).append('\n');
            if (!ans.isEmpty()) {
                for (int i = 0; i < ans.size(); i++) {
                    if (i > 0) {
                        sb.append(' ');
                    }
                    sb.append(ans.get(i));
                }
                sb.append('\n');
            } else {
                sb.append('\n');
            }
        }
        System.out.print(sb);
        br.close();
    }

    public static void upd(int i, int val) {
        for (; i <= bitSize; i += i & -i) {
            bit[i] += val;
        }
    }

    public static int query(int i) {
        int sum = 0;
        for (; i > 0; i -= i & -i) {
            sum += bit[i];
        }
        return sum;
    }

    public static int query(int l, int r) {
        if (l > r) {
            return 0;
        }
        return query(r) - query(l - 1);
    }

}
