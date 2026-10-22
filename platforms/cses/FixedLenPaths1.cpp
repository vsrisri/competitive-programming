#include <bits/stdc++.h>
using namespace std;

typedef long long ll;
int n, k;
vector<vector<int>> adj;
vector<int> sz;
vector<char> rem;
vector<int> cnt;
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

void collect(int u, int p, int d) {
    if (d > k) {
        return;
    }
    cur.push_back(d);
    for (int v : adj[u]) {
        if (v == p || rem[v]) {
            continue;
        }
        collect(v, u, d + 1);
    }
}

void helper(int u) {
    int total = helperc(u, -1);
    int c = getCentroid(u, -1, total);
    rem[c] = 1;
    cnt[0] = 1;
    int mx = 0;
    for (int v : adj[c]) {
        if (rem[v]) {
            continue;
        }
        cur.clear();
        collect(v, c, 1);
        for (int d : cur) {
            ans += cnt[k - d];
        }
        for (int d : cur) {
            cnt[d]++;
            mx = max(mx, d);
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
    scanf("%d %d", &n, &k);
    adj.assign(n, vector<int>());
    sz.assign(n, 0);
    rem.assign(n, 0);
    cnt.assign(n + 2, 0);
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
