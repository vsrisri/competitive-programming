#include <bits/stdc++.h>
using namespace std;
eonst int N = 300003;
const int LOG = 19;
int bin[LOG][N];
int depth[N];
vector<int> g[N];

int jump(int v, int d) {
    for (int j = 0; j < LOG; j++) {
        if ((d >> j) & 1) {
            v = bin[j][v];
        }
    }
    return v;
}

int lca(int a, int b) {
    if (depth[a] < depth[b]) {
        swap(a, b);
    }

    a = jump(a, depth[a] - depth[b]);
    if (a == b) {
        return a;
    }

    for (int j = LOG - 1; j >= 0; j--) {
        if (bin[j][a] != bin[j][b]) {
            a = bin[j][a];
            b = bin[j][b];
        }
    }
    return bin[0][a];
}

int main() {
    int n;
    scanf("%d", &n);
    for (int i = 0; i < n - 1; i++) {
        int u, v;
        scanf("%d %d", &u, &v);
        g[u].push_back(v);
        g[v].push_back(u);
    }

    vector<int> order;
    order.push_back(1);
    bin[0][1] = 0;
    depth[1] = 0;
    for (int h = 0; h < (int) order.size(); h++) {
        int v = order[h];
        for (int u : g[v]) {
            if (u != bin[0][v]) {
                bin[0][u] = v;
                depth[u] = depth[v] + 1;
                order.push_back(u);
            }
        }
    }

    for (int j = 1; j < LOG; j++) {
        for (int i = 1; i <= n; i++) {
            bin[j][i] = bin[j - 1][bin[j - 1][i]];
        }
    }

    int q;
    scanf("%d", &q);
    while (q--) {
        int a, b, c;
        scanf("%d %d %d", &a, &b, &c);
        int l = lca(a, b);
        int da = depth[a] - depth[l];
        int db = depth[b] - depth[l];
        int ans = da + db;
        if (c > ans) {
            c = ans;
        }
        if (c <= da) {
            printf("%d\n", jump(a, c));
        } else {
            printf("%d\n", jump(b, ans - c));
        }
    }
    return 0;
}
