import java.util.*;
import java.io.*;

public class DIV15 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine().trim());
        StringBuilder sb = new StringBuilder();
        while (t-- > 0) {
            String line = br.readLine().trim();
            sb.append(solve(line)).append('\n');
        }
        System.out.print(sb);
    }

    public static String solve(String s) {
        int[] freq = new int[10];
        for (char c : s.toCharArray()) {
            freq[c - '0']++;
        }
        String bestWith0 = freq[0] > 0 ? helper(freq, 0) : null;
        String bestWith5 = freq[5] > 0 ? helper(freq, 5) : null;
        String ans = better(bestWith0, bestWith5);
        return ans == null ? "impossible" : ans;
    }

    public static String helper(int[] freqOrig, int endDigit) {
        int[] freq = Arrays.copyOf(freqOrig, 10);
        int sum = 0;
        for (int d = 0; d <= 9; d++) {
            sum += d * freq[d];
        }
        int rem = sum % 3;
        if (rem != 0) {
            boolean fixed = false;
            for (int d = rem; d <= 9; d += 3) {
                int avail = freq[d] - (d == endDigit ? 1 : 0);
                if (avail > 0) {
                    freq[d]--;
                    fixed = true;
                    break;
                }
            }
            if (!fixed) {
                int need = (3 - rem) % 3;
                int remov = 0;
                for (int d = need; d <= 9 && remov < 2; d += 3) {
                    int avail = freq[d] - (d == endDigit ? 1 : 0);
                    while (avail > 0 && remov < 2) {
                        freq[d]--;
                        remov++;
                        avail = freq[d] - (d == endDigit ? 1 : 0);
                    }
                }
                if (remov < 2) {
                    return null;
                }
            }
        }
        freq[endDigit]--;
        StringBuilder sb = new StringBuilder();
        for (int d = 9; d >= 1; d--) {
            for (int i = 0; i < freq[d]; i++) {
                sb.append((char)('0' + d));
            }
        }
        for (int i = 0; i < freq[0]; i++) {
            sb.append('0');
        }
        sb.append((char)('0' + endDigit));
        String ans = sb.toString();
        int start = 0;
        while (start < ans.length() - 1 && ans.charAt(start) == '0') {
            start++;
        }
        return ans.substring(start);
    }

    public static String better(String a, String b) {
        if (a == null) {
            return b;
        }
        if (b == null) {
            return a;
        }
        if (a.equals("0") && b.equals("0")) {
            return "0";
        }
        if (a.equals("0")) {
            return b;
        }
        if (b.equals("0")) {
            return a;
        }
        if (a.length() != b.length()) {
            return a.length() > b.length() ? a : b;
        }
        return a.compareTo(b) >= 0 ? a : b;
    }
}
