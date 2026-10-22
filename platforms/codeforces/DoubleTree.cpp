#include <bits/stdc++.h>
using namespace std;
typedef long long ll;

struct Edge {
    int to;
    int id;
};

int n;
vector<vector<Edge>> adj;
vector<ll> w[2];
vector<int> sz;
vector<char> rem;
vector<int> lev;
vector<ll> h;
static ll D[20][300003][4];
static int cen[20][300003];

int helperC(int u, int p) {
    sz[u] = 1;
    for (const Edge &edge : adj[u]) {
        if (edge.to == p || rem[edge.to]) {
            continue;
        }
        sz[u] += helperC(edge.to, u);
    }
    return sz[u];
}

int getCentroid(int u, int p, int total) {
    for (const Edge &edge : adj[u]) {
        if (edge.to == p || rem[edge.to]) {
            continue;
        }
        if (sz[edge.to] * 2 > total) {
            return getCentroid(edge.to, u, total);
        }
    }
    return u;
}

void dfs(int v, int p, int c, int l, int id) {
    cen[l][v] = c;
    for (int a = 0; a < 2; a++) {
        for (int x = 0; x < 2; x++) {
            ll best = LLONG_MAX;
            for (int s = 0; s < 2; s++) {
                ll cur = (a == s ? 0 : h[v]) + w[s][id] + D[l][p][s * 2 + x];
                best = min(best, cur);
            }
            D[l][v][a * 2 + x] = best;
        }
    }
    for (const Edge &edge : adj[v]) {
        if (edge.to == p || rem[edge.to]) {
            continue;
        }
        dfs(edge.to, v, c, l, edge.id);
    }
}

void helper(int u, int l) {
    int total = helperC(u, -1);
    int c = getCentroid(u, -1, total);
    lev[c] = l;
    cen[l][c] = c;
    for (int a = 0; a < 2; a++) {
        for (int x = 0; x < 2; x++) {
            D[l][c][a * 2 + x] = (a == x ? 0 : h[c]);
        }
    }
    for (const Edge &edge : adj[c]) {
        if (rem[edge.to]) {
            continue;
        }
        dfs(edge.to, c, c, l, edge.id);
    }
    rem[c] = 1;
    for (const Edge &edge : adj[c]) {
        if (rem[edge.to]) {
            continue;
        }
        helper(edge.to, l + 1);
    }
}

int main() {
    scanf("%d", &n);
    h.assign(n, 0);
    for (int i = 0; i < n; i++) {
        scanf("%lld", &h[i]);
    }
    adj.assign(n, vector<Edge>());
    w[0].assign(n, 0);
    w[1].assign(n, 0);
    sz.assign(n, 0);
    rem.assign(n, 0);
    lev.assign(n, 0);
    for (int i = 0; i < n - 1; i++) {
        int x, y;
        scanf("%d %d %lld %lld", &x, &y, &w[0][i], &w[1][i]);
        x--;
        y--;
        adj[x].push_back({y, i});
        adj[y].push_back({x, i});
    }

    priority_queue<pair<ll, int>, vector<pair<ll, int>>, greater<pair<ll, int>>> pq;
    for (int i = 0; i < n; i++) {
        pq.push({h[i], i});
    }

    while (!pq.empty()) {
        pair<ll, int> top = pq.top();
        pq.pop();
        if (top.first > h[top.second]) {
            continue;
        }

        int u = top.second;
        for (const Edge &edge : adj[u]) {
            ll nd = h[u] + w[0][edge.id] + w[1][edge.id];
            if (nd < h[edge.to]) {
                h[edge.to] = nd;
                pq.push({nd, edge.to});
            }
        }
    }

    helper(0, 0);
    int q;
    scanf("%d", &q);
    while (q--) {
        int u, v;
        scanf("%d %d", &u, &v);
        u--;
        v--;
        int a = u & 1;
        int b = v & 1;
        int tu = u >> 1;
        int tv = v >> 1;
        int lim = min(lev[tu], lev[tv]);
        ll ans = LLONG_MAX;
        for (int l = 0; l <= lim; l++) {
            if (cen[l][tu] != cen[l][tv]) {
                break;
            }
            for (int x = 0; x < 2; x++) {
                ans = min(ans, D[l][tu][a * 2 + x] + D[l][tv][b * 2 + x]);
            }
        }
        printf("%lld\n", ans);
    }
    return 0;
}
