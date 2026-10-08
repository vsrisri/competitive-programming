#include <bits/stdc++.h>
using namespace std;
const int N = 200003;
const int LOG = 18;
int binArr[LOG][N];
int maxW[LOG][N];
int depth[N];
int pArr[N];
vector<pair<int, int>> g[N];
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

int helper2(int x) {
    while (pArr[x] != x) {
        pArr[x] = pArr[pArr[x]];
        x = pArr[x];
    }
    return x;
}


int helper3(int v, int d) {
    int ans = 0;
    for (int j = 0; j < LOG; j++) {
        if ((d >> j) & 1) {
            ans = max(ans, maxW[j][v]);
            v = binArr[j][v];
        }
    }
    return ans;
}

int main() {
    int n, m;
    scanf("%d %d", &n, &m);
    vector<int> edgeU(m), edgeV(m), edgeW(m), idx(m);
    for (int i = 0; i < m; i++) {
        scanf("%d %d %d", &edgeU[i], &edgeV[i], &edgeW[i]);
        idx[i] = i;
    }
    sort(idx.begin(), idx.end(), [&](int a, int b) {
        return edgeW[a] < edgeW[b];
    });

    for (int i = 1; i <= n; i++) {
        pArr[i] = i;
    }
    long long total = 0;
    for (int i : idx) {
        int a = helper2(edgeU[i]);
        int b = helper2(edgeV[i]);
        if (a != b) {
            pArr[a] = b;
            total += edgeW[i];
            g[edgeU[i]].push_back({edgeV[i], edgeW[i]});
            g[edgeV[i]].push_back({edgeU[i], edgeW[i]});
        }
    }
    vector<int> order;
    order.push_back(1);
    for (int h = 0; h < (int) order.size(); h++) {
        int v = order[h];
        for (auto &e : g[v]) {
            int u = e.first;
            if (u != binArr[0][v]) {
                binArr[0][u] = v;
                maxW[0][u] = e.second;
                depth[u] = depth[v] + 1;
                order.push_back(u);
            }
        }
    }

    for (int j = 1; j < LOG; j++) {
        for (int i = 1; i <= n; i++) {
            int hId = binArr[j - 1][i];
            binArr[j][i] = binArr[j - 1][hId];
            maxW[j][i] = max(maxW[j - 1][i], maxW[j - 1][hId]);
        }
    }

    for (int i = 0; i < m; i++) {
        int l = lca(edgeU[i], edgeV[i]);
        int pm = max(helper3(edgeU[i], depth[edgeU[i]] - depth[l]), helper3(edgeV[i], depth[edgeV[i]] - depth[l]));
        printf("%lld\n", total - pm + edgeW[i]);
    }
    return 0;
}
