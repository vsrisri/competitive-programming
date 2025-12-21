import java.io.*;
import java.util.*;

public class BestSubseq {
    public static final int M = 1000000007;
    public static long ex(long b, long e) {
        long res = 1, x = b;
        while (e > 0) {
            if (e % 2 != 0)
                res = (res * x) % M;
            x = (x * x) % M;
            e /= 2;
        }
        return res;
    }

    public static long inv(long i) {
        return ex(i, M - 2);
    }

    public static class Query {
        int l, r, k;
        
        Query(int L, int R, int K) {
            l = L;
            r = R;
            k = K;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());
        ArrayList<Pair> updates = new ArrayList<>();
        ArrayList<Query> queries = new ArrayList<>();
        ArrayList<Integer> coords = new ArrayList<>();
        
        coords.add(1);
        coords.add(n + 1);
        
        while (m-- > 0) {
            st = new StringTokenizer(br.readLine());
            int l = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken()) + 1;
            updates.add(new Pair(l, r));
            coords.add(l);
            coords.add(r);
        }
        
        while (q-- > 0) {
            st = new StringTokenizer(br.readLine());
            int l = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken()) + 1;
            int k = Integer.parseInt(st.nextToken());
            queries.add(new Query(l, r, k));
            coords.add(l);
            coords.add(r);
        }
        
        Collections.sort(coords);
        coords = new ArrayList<>(new HashSet<>(coords));
        int t = coords.size();
        
        Map<Integer, Integer> index = new HashMap<>();
        for (int i = 0; i < t; i++)
            index.put(coords.get(i), i);
        
        int[] s = new int[t];
        for (Pair update : updates) {
            s[index.get(update.l)] ^= 1;
            s[index.get(update.r)] ^= 1;
        }
        
        for (int i = 1; i < t; i++) {
            s[i] ^= s[i - 1];
        }
        
        int[] zeros = new int[t];
        for (int i = 1; i < t; i++) {
            zeros[i] = zeros[i - 1];
            if (s[i - 1] == 0)
                zeros[i] += coords.get(i) - coords.get(i - 1);
        }
        
        long[] suffix = new long[t];
        for (int i = t - 2; i >= 0; i--) {
            suffix[i] = suffix[i + 1];
            if (s[i] == 1)
                suffix[i] = (suffix[i] + M + ex(2, n + 1 - coords.get(i)) - ex(2, n + 1 - coords.get(i + 1))) % M;
        }
        
        for (Query q1 : queries) {
            int lpos = index.get(q1.l), rpos = index.get(q1.r);
            int zpos = lowerBound(zeros, lpos, rpos, zeros[lpos] + q1.r - q1.l - q1.k);
            long ans = (M + suffix[zpos] - suffix[rpos]) * inv(ex(2, n + 1 - q1.r)) % M;
            int ones = Math.min(coords.get(zpos) - q1.l - (zeros[zpos] - zeros[lpos]), q1.k - (q1.r - coords.get(zpos)));
            ans += (ex(2, ones) - 1) * ex(2, q1.k - ones);
            System.out.println(ans % M);
        }
    }
    
    public static int lowerBound(int[] arr, int l, int r, int key) {
        int left = l, right = r;
        while (left < right) {
            int mid = (left + right) / 2;
            if (arr[mid] >= key) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
    
    public static class Pair {
        int l, r;
        
        Pair(int l, int r) {
            this.l = l;
            this.r = r;
        }
    }
}

