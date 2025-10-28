import java.io.*;
import java.util.*;

public class BackFromSummer19P3 {
    public static int limit;
    public static final int[] base = new int[26];
    public static final int[] tempFreq = new int[26];
    public static final int[] temp = new int[26];
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String inputStr = br.readLine();
        StringTokenizer tokenizer = new StringTokenizer(br.readLine());
        int maxSubLen = Integer.parseInt(tokenizer.nextToken());
        int maxRem = Integer.parseInt(tokenizer.nextToken());
        int oLen = inputStr.length();

        if (maxSubLen + maxRem >= oLen) {
            System.out.println(0);
            return;
        }

        System.out.println(helper(inputStr, maxSubLen, maxRem));
    }

    public static long helper(String s, int L, int K) {
        int n = s.length();
        int[] total = new int[26];
        int[] win = new int[26];

        for (int i = 0; i < n; i++) {
            total[s.charAt(i) - 'a']++;
        }

        for (int i = 0; i < L; i++) {
            win[s.charAt(i) - 'a']++;
        }

        for (int c = 0; c < 26; c++) {
            base[c] = total[c] - win[c];
        }

        limit = K;
        long best = scoreCurr();

        for (int start = 1; start + L <= n; start++) {
            char out = s.charAt(start - 1);
            char in = s.charAt(start + L - 1);
            win[out - 'a']--;
            base[out - 'a']++;
            win[in - 'a']++;
            base[in - 'a']--;
            best = Math.min(best, scoreCurr());
        }

        return best;
    }

    private static long scoreCurr() {
        int hi = 0;
        for (int x : base) {
            hi = Math.max(hi, x);
        }

        int lo = 0;
        int cap = hi;
        while (lo <= hi) {
            int mid = (lo + hi) >>> 1;
            if (isPoss(mid)) {
                cap = mid;
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }

        return eval(cap);
    }

    public static boolean isPoss(int cap) {
        int need = 0;
        for (int x : base) {
            if (x > cap) {
                need += x - cap;
            }
        }
        return need <= limit;
    }

    public static long eval(int cap) {
        int left = limit;
        int[] f = new int[26];
        System.arraycopy(base, 0, f, 0, 26);

        for (int i = 0; i < 26; i++) {
            if (f[i] > cap) {
                left -= f[i] - cap;
                f[i] = cap;
            }
        }

        while (left > 0) {
            int idx = 0;
            for (int i = 1; i < 26; i++) {
                if (f[i] > f[idx]) {
                    idx = i;
                }
            }
            f[idx]--;
            left--;
        }

        long score = 0;
        for (int x : f) {
            score += 1L * x * x;
        }

        return score;
    }
}

