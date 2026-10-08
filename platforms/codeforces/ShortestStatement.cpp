#include <bits/stdc++.h>
using namespace std;
const int N = 100003;
const int LOG = 17;
const long long INF = LLONG_MAX / 4;
int binArr[LOG][N];
int depth[N];
int par[N];
long long dd[N];
vector<pair<int, int>> g[N];
vector<pair<int, int>> tg[N];
int helper2(int x) {
    if (par[x] == x) {
        return x;
    }
    par[x] = par[par[x]];
    return helper2(par[x]);
}

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

void dijkstra(int s, vector<long long> &d) {
    priority_queue<pair<long long, int>, vector<pair<long long, int>>, greater<pair<long long, int>>> pq;
    d[s] = 0;
    pq.push({0, s});
    while (!pq.empty()) {
        long long cd = pq.top().first;
        int v = pq.top().second;
        pq.pop();
        if (cd > d[v]) {
            continue;
        }

        for (auto &e : g[v]) {
            if (d[v] + e.second < d[e.first]) {
                d[e.first] = d[v] + e.second;
                pq.push({d[e.first], e.first});
            }
        }
    }
}

int main() {
    int n, m;
    scanf("%d %d", &n, &m);
    for (int i = 1; i <= n; i++) {
        par[i] = i;
    }
    vector<int> sp;
    for (int i = 0; i < m; i++) {
        int u, v, w;
        scanf("%d %d %d", &u, &v, &w);
        g[u].push_back({v, w});
        g[v].push_back({u, w});
        int a = helper2(u);
        int b = helper2(v);
        if (a != b) {
            par[a] = b;
            tg[u].push_back({v, w});
            tg[v].push_back({u, w});
        } else {
            sp.push_back(u);
            sp.push_back(v);
        }
    }

    sort(sp.begin(), sp.end());
    sp.erase(unique(sp.begin(), sp.end()), sp.end());
    vector<int> order;
    order.push_back(1);
    binArr[0][1] = 0;
    for (int h = 0; h < (int) order.size(); h++) {
        int v = order[h];
        for (auto &e : tg[v]) {
            int u = e.first;
            if (u != binArr[0][v]) {
                binArr[0][u] = v;
                depth[u] = depth[v] + 1;
                dd[u] = dd[v] + e.second;
                order.push_back(u);
            }
        }
    }
    for (int j = 1; j < LOG; j++) {
        for (int i = 1; i <= n; i++) {
            binArr[j][i] = binArr[j - 1][binArr[j - 1][i]];
        }
    }

    int k = sp.size();
    vector<vector<long long>> D(k, vector<long long>(n + 1, INF));
    for (int i = 0; i < k; i++) {
        dijkstra(sp[i], D[i]);
    }

    int q;
    scanf("%d", &q);
    while (q--) {
        int u, v;
        scanf("%d %d", &u, &v);
        int l = lca(u, v);
        long long ans = dd[u] + dd[v] - 2 * dd[l];
        for (int i = 0; i < k; i++) {
            ans = min(ans, D[i][u] + D[i][v]);
        }
        printf("%lld\n", ans);
    }
    return 0;
}
