#include <bits/stdc++.h>
using namespace std;
const int N = 50003;
const int LOG = 16;
const int INF = INT_MAX;
int binArr[LOG][N];
int minpArr[LOG][N];
int depth[N];
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

void helper2(int v, int d, int r) {
    for (int j = 0; j < LOG; j++) {
        if ((d >> j) & 1) {
            minpArr[j][v] = min(minpArr[j][v], r);
            v = binArr[j][v];
        }
    }
}

int main() {
    freopen("disrupt.in", "r", stdin);
    freopen("disrupt.out", "w", stdout);
    int n, m;
    scanf("%d %d", &n, &m);
    vector<int> endX(n - 1), endY(n - 1);
    for (int i = 0; i < n - 1; i++) {
        scanf("%d %d", &endX[i], &endY[i]);
        g[endX[i]].push_back(endY[i]);
        g[endY[i]].push_back(endX[i]);
    }

    vector<int> bfsO;
    bfsO.push_back(1);
    for (int h = 0; h < (int) bfsO.size(); h++) {
        int v = bfsO[h];
        for (int u : g[v]) {
            if (u != binArr[0][v]) {
                binArr[0][u] = v;
                depth[u] = depth[v] + 1;
                bfsO.push_back(u);
            }
        }
    }

    for (int j = 1; j < LOG; j++) {
        for (int i = 1; i <= n; i++) {
            binArr[j][i] = binArr[j - 1][binArr[j - 1][i]];
        }
    }

    for (int j = 0; j < LOG; j++) {
        for (int i = 0; i <= n; i++) {
            minpArr[j][i] = INF;
        }
    }

    for (int i = 0; i < m; i++) {
        int p, q, r;
        scanf("%d %d %d", &p, &q, &r);
        int l = lca(p, q);
        helper2(p, depth[p] - depth[l], r);
        helper2(q, depth[q] - depth[l], r);
    }

    for (int j = LOG - 1; j >= 1; j--) {
        for (int v = 1; v <= n; v++) {
            if (minpArr[j][v] != INF) {
                int mid = binArr[j - 1][v];
                minpArr[j - 1][v] = min(minpArr[j - 1][v], minpArr[j][v]);
                minpArr[j - 1][mid] = min(minpArr[j - 1][mid], minpArr[j][v]);
            }
        }
    }
    for (int i = 0; i < n - 1; i++) {
        int c = depth[endX[i]] > depth[endY[i]] ? endX[i] : endY[i];
        if (minpArr[0][c] == INF) {
            printf("-1\n");
        } else {
            printf("%d\n", minpArr[0][c]);
        }
    }
    return 0;
}
