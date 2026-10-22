#include <bits/stdc++.h>
using namespace std;
typedef long long ll;
int n, k1, k2;
vector<vector<int>> adj;
vector<int> sz;
vector<char> rem;
vector<int> cnt;
vector<int> pre;
vector<int> cur;
ll ans = 0;

int helperc(int u, int p) {
    sz[u] = 1;
    for (int v : adj[u]) {
        if (v == p || rem[v]) {
            continue;
        }
        sz[u] += helperc(v, u);
    }
    return sz[u];
}

int getCentroid(int u, int p, int total) {
    for (int v : adj[u]) {
        if (v == p || rem[v]) {
            continue;
        }

        if (sz[v] * 2 > total) {
            return getCentroid(v, u, total);
        }
    }
    return u;
}

void helper2(int u, int p, int d) {
    if (d > k2) {
        return;
    }

    cur.push_back(d);
    for (int v : adj[u]) {
        if (v == p || rem[v]) {
            continue;
        }

        helper2(v, u, d + 1);
    }
}

void helper(int u) {
    int total = helperc(u, -1);
    int c = getCentroid(u, -1, total);
    rem[c] = 1;
    vector<vector<int>> groups;
    for (int v : adj[c]) {
        if (rem[v]) {
            continue;
        }

        cur.clear();
        helper2(v, c, 1);
        groups.push_back(cur);
    }
    sort(groups.begin(), groups.end(), [](const vector<int> &a, const vector<int> &b) {
        return a.size() < b.size();
    });

    int mx = 0;
    cnt[0] = 1;
    pre[0] = 1;
    for (vector<int> &g : groups) {
        int gm = 0;
        for (int d : g) {
            gm = max(gm, d);
            int lo = max(0, k1 - d);
            int hi = min(k2 - d, mx);
            if (hi >= lo) {
                ans += pre[hi] - (lo > 0 ? pre[lo - 1] : 0);
            }
        }
        for (int d : g) {
            cnt[d]++;
        }
        mx = max(mx, gm);
        int run = 0;
        for (int i = 0; i <= mx; i++) {
            run += cnt[i];
            pre[i] = run;
        }
    }

    for (int i = 0; i <= mx; i++) {
        cnt[i] = 0;
    }

    for (int v : adj[c]) {
        if (rem[v]) {
            continue;
        }
        helper(v);
    }
}

int main() {
    scanf("%d %d %d", &n, &k1, &k2);
    adj.assign(n, vector<int>());
    sz.assign(n, 0);
    rem.assign(n, 0);
    cnt.assign(n + 3, 0);
    pre.assign(n + 3, 0);
    for (int i = 0; i < n - 1; i++) {
        int a, b;
        scanf("%d %d", &a, &b);
        a--;
        b--;
        adj[a].push_back(b);
        adj[b].push_back(a);
    }

    helper(0);
    printf("%lld\n", ans);
    return 0;
}
