#include <bits/stdc++.h>
using namespace std;
const int N = 300003;
const int LOG = 19;
int binArr[LOG][N];
int dep[N];
long long a[N];
long long pw[N];
long long dp[N];
long long fArr[N];
long long pref[N];
vector<pair<int, int>> g[N];
int helper(int v, int d) {
    for (int j = 0; j < LOG; j++) {
        if ((d >> j) & 1) {
            v = binArr[j][v];
        }
    }
    return v;
}

int lca(int x, int y) {
    if (dep[x] < dep[y]) {
        swap(x, y);
    }
    x = helper(x, dep[x] - dep[y]);
    if (x == y) {
        return x;
    }
    for (int j = LOG - 1; j >= 0; j--) {
        if (binArr[j][x] != binArr[j][y]) {
            x = binArr[j][x];
            y = binArr[j][y];
        }
    }
    return binArr[0][x];
}

int main() {
    int n, q;
    scanf("%d %d", &n, &q);
    for (int i = 1; i <= n; i++) {
        scanf("%lld", &a[i]);
    }
    for (int i = 0; i < n - 1; i++) {
        int u, v, w;
        scanf("%d %d %d", &u, &v, &w);
        g[u].push_back({v, w});
        g[v].push_back({u, w});
    }
    vector<int> order;
    order.push_back(1);
    binArr[0][1] = 0;
    for (int h = 0; h < (int) order.size(); h++) {
        int v = order[h];
        for (auto &e : g[v]) {
            int u = e.first;
            if (u != binArr[0][v]) {
                binArr[0][u] = v;
                pw[u] = e.second;
                dep[u] = dep[v] + 1;
                order.push_back(u);
            }
        }
    }

    for (int j = 1; j < LOG; j++) {
        for (int i = 1; i <= n; i++) {
            binArr[j][i] = binArr[j - 1][binArr[j - 1][i]];
        }
    }

    for (int i = 1; i <= n; i++) {
        dp[i] = a[i];
    }

    for (int h = n - 1; h >= 1; h--) {
        int v = order[h];
        dp[binArr[0][v]] += max(0LL, dp[v] - 2 * pw[v]);
    }

    fArr[1] = dp[1];
    pref[1] = 0;
    for (int h = 1; h < n; h++) {
        int v = order[h];
        int p = binArr[0][v];
        long long g1 = max(0LL, dp[v] - 2 * pw[v]);
        fArr[v] = dp[v] + max(0LL, fArr[p] - g1 - 2 * pw[v]);
        pref[v] = pref[p] + dp[p] - g1 - pw[v];
    }

    while (q--) {
        int u, v;
        scanf("%d %d", &u, &v);
        int l = lca(u, v);
        long long ans = fArr[l];
        if (u != l) {
            int cu = helper(u, dep[u] - dep[l] - 1);
            ans += dp[u] + pref[u] - pref[cu];
            ans -= max(0LL, dp[cu] - 2 * pw[cu]);
            ans -= pw[cu];
        }
        if (v != l) {
            int childArr = helper(v, dep[v] - dep[l] - 1);
            ans += dp[v] + pref[v] - pref[childArr];
            ans -= max(0LL, dp[childArr] - 2 * pw[childArr]);
            ans -= pw[childArr];
        }
        printf("%lld\n", ans);
    }
    return 0;
}
