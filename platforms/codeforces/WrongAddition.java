public class WrongAddition {
    private static void run() throws IOException {
        char[] a = in.next().toCharArray();
        char[] s = in.next().toCharArray();
        ArrayList<Integer> ans = new ArrayList<>();
 
        int p_a = a.length - 1;
        int p_s = s.length - 1;
        while (p_s >= 0) {
            int now_p = s[p_s] - '0';
            int now_a = p_a >= 0 ? a[p_a] - '0' : 0;
            if (now_p < now_a) {
                p_s--;
                if (p_s < 0) {
                    break;
                }
                now_p += 10 * (s[p_s] - '0');
            }
            ans.add(now_p - now_a);
            p_s--;
            p_a--;
        }
 
        if (p_a >= 0) {
            out.println(-1);
            return;
        }
        for (int now : ans) {
            if (now >= 10 || now < 0) {
                out.println(-1);
                return;
            }
        }
 
        int sz = ans.size();
        boolean leading_zero_check = true;
        for (int i = sz - 1; i >= 0; i--) {
            int now = ans.get(i);
            if (leading_zero_check && now == 0) continue;
            if (now != 0) leading_zero_check = false;
            out.print(now);
        }
        out.println();
    }
 
    public static void main(String[] args) throws IOException {
        in = new Reader();
        out = new PrintWriter(new OutputStreamWriter(System.out));
 
        int t = in.nextInt();
        for (int i = 0; i < t; i++) {
            run();
        }
 
        out.flush();
        in.close();
        out.close();
    }
 
    private static int gcd(int a, int b) {
        if (a == 0 || b == 0)
            return 0;
        while (b != 0) {
            int tmp;
            tmp = a % b;
            a = b;
            b = tmp;
        }
        return a;
    }
 
    static final long mod = 1000000007;
 
    static long pow_mod(long a, long b) {
        long result = 1;
        while (b != 0) {
            if ((b & 1) != 0) result = (result * a) % mod;
            a = (a * a) % mod;
            b >>= 1;
        }
        return result;
    }
 
    private static long add_mod(long... longs) {
        long ans = 0;
        for (long now : longs) {
            ans = (ans + now) % mod;
            if (ans < 0) ans += mod;
        }
        return ans;
    }
 
    private static long multiplied_mod(long... longs) {
        long ans = 1;
        for (long now : longs) {
            ans = (ans * now) % mod;
        }
        return ans;
    }
}
