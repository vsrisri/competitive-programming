import java.io.*;
import java.util.*;

public class PointElim {
    public static class Obj {
        public long min;
        public long max;
        public boolean ok;
        public Obj(long a, long b, boolean c) { min = a; max = b; ok = c; }
    }

    public static Obj helper(int[] arr) {
        Arrays.sort(arr);
        int n = arr.length;
        for (int i = 0; i < n; i += 2) {
            if (arr[i + 1] - arr[i] >= 2) {
                return new Obj(0, 0, false);
            }
        }
        long lo = 0;
        for (int i = 0; i < n; i += 2) {
            if (arr[i] != arr[i + 1]) {
                lo++;
            }
        }
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int v : arr) {
            map.put(v, map.getOrDefault(v, 0) + 1);
        }
        long hi = 0;
        while (!map.isEmpty()) {
            int cur = map.firstKey();
            int freq = map.get(cur);
            map.remove(cur);
            if (map.containsKey(cur + 1)) {
                int take = Math.min(freq, map.get(cur + 1));
                if (((freq - take) & 1) == 1) {
                    take--;
                }
                map.put(cur + 1, map.get(cur + 1) - take);
                if (map.get(cur + 1) == 0) {
                    map.remove(cur + 1);
                }
                hi += take;
            }
        }
        return new Obj(lo, hi, true);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        StringBuilder out = new StringBuilder();
        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine());
            int[] xs = new int[N];
            int[] ys = new int[N];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                xs[i] = Integer.parseInt(st.nextToken());
                ys[i] = Integer.parseInt(st.nextToken());
            }
            Obj resX = helper(xs);
            Obj resY = helper(ys);
            if (!resX.ok || !resY.ok) {
                out.append("NO\n");
                continue;
            }
            boolean ans = false;
            for (long idx = resX.min; idx <= resX.max; idx += 2) {
                long rem = N / 2 - idx;
                if (rem >= resY.min && rem <= resY.max && (rem - resY.min) % 2 == 0) {
                    ans = true;
                    break;
                }
            }
            out.append(ans ? "YES\n" : "NO\n");
        }
        System.out.print(out);
    }
}
