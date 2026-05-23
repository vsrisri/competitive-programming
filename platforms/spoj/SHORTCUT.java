import java.util.*;
import java.io.*;

public class SHORTCUT {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine().trim());
        StringBuilder sb = new StringBuilder();
        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine().trim());
            String route = br.readLine().trim();
            int[] x = new int[n + 1];
            int[] y = new int[n + 1];
            for (int i = 0; i < n; i++) {
                char c = route.charAt(i);
                if (c == 'N') {
                    x[i + 1] = x[i];
                    y[i + 1] = y[i] + 1;
                } else if (c == 'S') {
                    x[i + 1] = x[i];
                    y[i + 1] = y[i] - 1;
                } else if (c == 'E') {
                    x[i + 1] = x[i] + 1;
                    y[i + 1] = y[i];
                } else {
                    x[i + 1] = x[i] - 1;
                    y[i + 1] = y[i];
                }
            }

            int bestLen = Integer.MAX_VALUE;
            int bestB = Integer.MAX_VALUE;
            int bestE = -1;
            char bestDir = ' ';
            HashMap<Integer, List<Integer>> byX = new HashMap<>();
            HashMap<Integer, List<Integer>> byY = new HashMap<>();
            for (int i = 0; i <= n; i++) {
                byX.computeIfAbsent(x[i], k -> new ArrayList<>()).add(i);
                byY.computeIfAbsent(y[i], k -> new ArrayList<>()).add(i);
            }

            for (List<Integer> pts : byX.values()) {
                if (pts.size() < 2) {
                    continue;
                }

                pts.sort(Comparator.comparingInt(i -> y[i]));
                for (int k = 0; k + 1 < pts.size(); k++) {
                    int p1 = pts.get(k);
                    int p2 = pts.get(k + 1);
                    int b = Math.min(p1, p2);
                    int e = Math.max(p1, p2);
                    int scLen = Math.abs(y[e] - y[b]);
                    int routeDist = e - b;
                    if (scLen < routeDist) {
                        boolean better = helper(scLen, b, e, bestLen, bestB, bestE);
                        if (better) {
                            bestLen = scLen;
                            bestB = b;
                            bestE = e;
                            bestDir = (y[e] > y[b]) ? 'N' : 'S';
                        }
                    }
                }
            }

            for (List<Integer> pts : byY.values()) {
                if (pts.size() < 2) {
                    continue;
                }
                pts.sort(Comparator.comparingInt(i -> x[i]));
                for (int k = 0; k + 1 < pts.size(); k++) {
                    int p1 = pts.get(k);
                    int p2 = pts.get(k + 1);
                    int b = Math.min(p1, p2);
                    int e = Math.max(p1, p2);
                    int scLen = Math.abs(x[e] - x[b]);
                    int routeDist = e - b;
                    if (scLen < routeDist) {
                        boolean better = helper(scLen, b, e, bestLen, bestB, bestE);
                        if (better) {
                            bestLen = scLen;
                            bestB = b;
                            bestE = e;
                            bestDir = (x[e] > x[b]) ? 'E' : 'W';
                        }
                    }
                }
            }

            sb.append(bestLen).append(' ').append(bestB).append(' ').append(bestE).append(' ').append(bestDir).append('\n');
        }

        System.out.print(sb);
    }

    public static boolean helper(int scLen, int b, int e, int bestLen, int bestB, int bestE) {
        if (scLen < bestLen) {
            return true;
        }
        if (scLen == bestLen && b < bestB) {
            return true;
        }
        if (scLen == bestLen && b == bestB && e > bestE) {
            return true;
        }
        return false;
    }
}
