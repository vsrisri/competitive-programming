#include <bits/stdc++.h>
using namespace std;

int n;
vector<vector<int>> adj;
vector<int> sz;
vector<char> rem;
vector<char> rk;
int helperC(int u, int p) {
    sz[u] = 1;
    for (int v : adj[u]) {
        if (v == p || rem[v]) {
            continue;
        }
        sz[u] += helperC(v, u);
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

void helper(int u, int depth) {
    int total = helperC(u, -1);
    int c = getCentroid(u, -1, total);
    rk[c] = 'A' + depth;
    rem[c] = 1;
    for (int v : adj[c]) {
        if (rem[v]) {
            continue;
        }
        helper(v, depth + 1);
    }
}

int main() {
    scanf("%d", &n);
    adj.assign(n, vector<int>());
    sz.assign(n, 0);
    rem.assign(n, 0);
    rk.assign(n, 0);
    for (int i = 0; i < n - 1; i++) {
        int a, b;
        scanf("%d %d", &a, &b);
        a--;
        b--;
        adj[a].push_back(b);
        adj[b].push_back(a);
    }
    helper(0, 0);
    for (int i = 0; i < n; i++) {
        printf("%c%c", rk[i], i + 1 < n ? ' ' : '\n');
    }
    return 0;
}
