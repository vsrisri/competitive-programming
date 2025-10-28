import java.io.*;
import java.util.*;

public class ThreeColors {
    enum Color { RED, GREEN, BLUE }
    public static List<Integer>[] tree;
    public static int[][] maxDp;
    public static int[][] minDp;
    public static int nodeCount;
    public static int idx = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String treeShape = br.readLine();
        nodeCount = treeShape.length();
        tree = new ArrayList[nodeCount + 1];
        for (int i = 0; i <= nodeCount; i++) {
            tree[i] = new ArrayList<>();
        }
        helper(0, treeShape);
        maxDp = new int[3][nodeCount];
        minDp = new int[3][nodeCount];
        for (int[] row : maxDp) {
            Arrays.fill(row, -1);
        }
        for (int[] row : minDp) {
            Arrays.fill(row, -1);
        }

        int maxRed = 0;
        int minRed = Integer.MAX_VALUE;
        for (Color color : Color.values()) {
            maxRed = Math.max(maxRed, helper2(0, color, true));
            minRed = Math.min(minRed, helper2(0, color, false));
        }
        System.out.println(maxRed + " " + minRed);
        br.close();
    }

    public static void helper(int current, String treeShape) {
        if (idx >= treeShape.length()) {
            return;
        }
        char ch = treeShape.charAt(idx);
        if (ch == '0') {
            return;
        }

        if (ch == '1') {
            tree[current].add(idx + 1);
            idx++;
            helper(idx, treeShape);
        } else if (ch == '2') {
            int left = idx + 1;
            tree[current].add(left);
            idx++;
            helper(left, treeShape);
            int right = idx + 1;
            tree[current].add(right);
            idx++;
            helper(right, treeShape);
        }
    }

    public static int helper2(int node, Color color, boolean isMax) {
        int colorIdx = color.ordinal();
        int[][] dp = isMax ? maxDp : minDp;

        if (dp[colorIdx][node] != -1) {
            return dp[colorIdx][node];
        }

        List<Color> otherColors = new ArrayList<>();
        for (Color c : Color.values()) {
            if (c != color) {
                otherColors.add(c);
            }
        }

        int ans = (color == Color.RED) ? 1 : 0;
        List<Integer> children = tree[node];
        if (children.size() == 2) {
            int left = children.get(0), right = children.get(1);
            Color first = otherColors.get(0), second = otherColors.get(1);
            int leftFirstRightSecond = helper2(left, first, isMax) + helper2(right, second, isMax);
            int leftSecondRightFirst = helper2(left, second, isMax) + helper2(right, first, isMax);
            ans += isMax ? Math.max(leftFirstRightSecond, leftSecondRightFirst) : Math.min(leftFirstRightSecond, leftSecondRightFirst);
        } else if (children.size() == 1) {
            int child = children.get(0);
            int leftOption = helper2(child, otherColors.get(0), isMax);
            int rightOption = helper2(child, otherColors.get(1), isMax);
            ans += isMax ? Math.max(leftOption, rightOption) : Math.min(leftOption, rightOption);
        }

        dp[colorIdx][node] = ans;
        return ans;
    }
}
