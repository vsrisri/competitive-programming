#include <bits/stdc++.h>
using namespace std;
const int N = 50003;
const int LOG = 16;
int binArr[LOG][N];
int depth[N];
int cnt[N];
vector<int> g[N];
int helper(int v, int d) {
    for (int j = 0; j < LOG; j++) {
        if ((d >> j) & 1) {
            v = binArr[j][v];
        }
    }
    return v;
}

int lca(int a, int b) {
    if (depth[a] < depth[b]) {
        swap(a, b);
    }
    a = helper(a, depth[a] - depth[b]);
    if (a == b) {
        return a;
    }
    for (int j = LOG - 1; j >= 0; j--) {
        if (binArr[j][a] != binArr[j][b]) {
            a = binArr[j][a];
            b = binArr[j][b];
        }
    }
    return binArr[0][a];
}

int main() {
    freopen("maxflow.in", "r", stdin);
    freopen("maxflow.out", "w", stdout);
    int n, k;
    scanf("%d %d", &n, &k);
    for (int i = 0; i < n - 1; i++) {
        int x, y;
        scanf("%d %d", &x, &y);
        g[x].push_back(y);
        g[y].push_back(x);
    }

    vector<int> order;
    order.push_back(1);
    for (int h = 0; h < (int) order.size(); h++) {
        int v = order[h];
        for (int u : g[v]) {
            if (u != binArr[0][v]) {
                binArr[0][u] = v;
                depth[u] = depth[v] + 1;
                order.push_back(u);
            }
        }
    }
    for (int j = 1; j < LOG; j++) {
        for (int i = 1; i <= n; i++) {
            binArr[j][i] = binArr[j - 1][binArr[j - 1][i]];
        }
    }
    for (int i = 0; i < k; i++) {
        int s, t;
        scanf("%d %d", &s, &t);
        int l = lca(s, t);
        cnt[s]++;
        cnt[t]++;
        cnt[l]--;
        cnt[binArr[0][l]]--;
    }
    for (int h = n - 1; h >= 1; h--) {
        int v = order[h];
        cnt[binArr[0][v]] += cnt[v];
    }
    int ans = 0;
    for (int i = 1; i <= n; i++) {
        ans = max(ans, cnt[i]);
    }
    printf("%d\n", ans);
    return 0;
}
