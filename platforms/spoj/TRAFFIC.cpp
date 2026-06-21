#include <bits/stdc++.h>
using namespace std;

const long long INF = LLONG_MAX / 4;
void dijkstra(vector<vector<pair<int, int>>> &g, int n, int src, vector<long long> &dist) {
    dist.assign(n + 1, INF);
    dist[src] = 0;
    priority_queue<pair<long long, int>, vector<pair<long long, int>>, greater<pair<long long, int>>> pq;
    pq.push(make_pair(0LL, src));
    vector<bool> vis(n + 1, false);
    while (!pq.empty()) {
        pair<long long, int> top = pq.top();
        pq.pop();
        long long d = top.first;
        int u = top.second;
        if (vis[u]) {
            continue;
        }
        vis[u] = true;
        for (size_t i = 0; i < g[u].size(); i++) {
            int v = g[u][i].first;
            int w = g[u][i].second;
            long long nd = d + w;
            if (nd < dist[v]) {
                dist[v] = nd;
                pq.push(make_pair(nd, v));
            }
        }
    }
}

int main() {
    int T;
    scanf("%d", &T);
    while (T--) {
        int n, m, k, s, t;
        scanf("%d %d %d %d %d", &n, &m, &k, &s, &t);
        vector<vector<pair<int, int>>> adj(n + 1);
        vector<vector<pair<int, int>>> radj(n + 1);
        for (int i = 0; i < m; i++) {
            int d, c, l;
            scanf("%d %d %d", &d, &c, &l);
            adj[d].push_back(make_pair(c, l));
            radj[c].push_back(make_pair(d, l));
        }
        vector<int> ku(k);
        vector<int> kv(k);
        vector<int> kq(k);
        for (int i = 0; i < k; i++) {
            scanf("%d %d %d", &ku[i], &kv[i], &kq[i]);
        }
        vector<long long> distS;
        vector<long long> distT;
        dijkstra(adj, n, s, distS);
        dijkstra(radj, n, t, distT);
        long long ans = distS[t];
        for (int i = 0; i < k; i++) {
            int u = ku[i];
            int v = kv[i];
            long long q = kq[i];
            if (distS[u] < INF && distT[v] < INF) {
                long long cand = distS[u] + q + distT[v];
                if (cand < ans) {
                    ans = cand;
                }
            }
            if (distS[v] < INF && distT[u] < INF) {
                long long cand = distS[v] + q + distT[u];
                if (cand < ans) {
                    ans = cand;
                }
            }
        }
        if (ans >= INF) {
            printf("-1\n");
        } else {
            printf("%lld\n", ans);
        }
    }
    return 0;
}
