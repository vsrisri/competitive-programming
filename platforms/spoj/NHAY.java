import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder out = new StringBuilder();
        String line;
        boolean isf = true;
        while ((line = br.readLine()) != null) {
            if (line.isEmpty()) {
                continue;
            }
            int m = Integer.parseInt(line);
            String needle = br.readLine();
            String stack = br.readLine();
            if (!isf) {
                out.append("\n");
            }
            isf = false;
            List<Integer> positions = helper(needle, stack);
            for (int pos : positions) {
                out.append(pos).append("\n");
            }
        }
        System.out.print(out.toString());
    }

    public static List<Integer> helper(String needle, String stack) {
        List<Integer> ans = new ArrayList<>();
        int n = stack.length();
        int m = needle.length();
        for (int i = 0; i <= n - m; i++) {
            int j = 0;
            for (; j < m; j++) {
                if (stack.charAt(i + j) != needle.charAt(j)) {
                    break;
                }
            }
            if (j == m) {
                ans.add(i);
            }
        }
        return ans;
    }
}

