#include <bits/stdc++.h>
using namespace std;
const int N = 100003;
const int LOG = 17;
const int INF = INT_MAX;
int bin[LOG][N];
int arr[LOG][N][10];
int depth[N];
vector<int> g[N];

void helper(int *ans, const int *b) {
    int temp[10];
    int i = 0;
    int j = 0;
    for (int t = 0; t < 10; t++) {
        if (ans[i] <= b[j]) {
            temp[t] = ans[i++];
        } else {
            temp[t] = b[j++];
        }
    }
    for (int t = 0; t < 10; t++) {
        ans[t] = temp[t];
    }
}

int lca(int a, int b) {
    if (depth[a] < depth[b]) {
        swap(a, b);
    }
    int d = depth[a] - depth[b];
    for (int j = 0; j < LOG; j++) {
        if ((d >> j) & 1) {
            a = bin[j][a];
        }
    }
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

void helper2(int v, int d, int *ans) {
    for (int j = 0; j < LOG; j++) {
        if ((d >> j) & 1) {
            helper(ans, arr[j][v]);
            v = bin[j][v];
        }
    }
}

int main() {
    int n, m, q;
    scanf("%d %d %d", &n, &m, &q);
    for (int i = 0; i < n - 1; i++) {
        int a, b;
        scanf("%d %d", &a, &b);
        g[a].push_back(b);
        g[b].push_back(a);
    }
    for (int j = 0; j < LOG; j++) {
        for (int i = 0; i <= n; i++) {
            for (int t = 0; t < 10; t++) {
                arr[j][i][t] = INF;
            }
        }
    }
    vector<int> cntc(n + 1, 0);
    for (int i = 1; i <= m; i++) {
        int c;
        scanf("%d", &c);
        if (cntc[c] < 10) {
            arr[0][c][cntc[c]++] = i;
        }
    }
    vector<int> order;
    order.push_back(1);
    depth[1] = 0;
    bin[0][1] = 0;
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
            int mid = bin[j - 1][i];
            bin[j][i] = bin[j - 1][mid];
            for (int t = 0; t < 10; t++) {
                arr[j][i][t] = arr[j - 1][i][t];
            }
            helper(arr[j][i], arr[j - 1][mid]);
        }
    }
    while (q--) {
        int v, u, a;
        scanf("%d %d %d", &v, &u, &a);
        int l = lca(v, u);
        int ans[10];
        for (int t = 0; t < 10; t++) {
            ans[t] = INF;
        }

        helper2(v, depth[v] - depth[l], ans);
        helper2(u, depth[u] - depth[l], ans);
        helper(ans, arr[0][l]);
        int k = 0;
        while (k < a && k < 10 && ans[k] != INF) {
            k++;
        }
        printf("%d", k);
        for (int t = 0; t < k; t++) {
            printf(" %d", ans[t]);
        }
        printf("\n");
    }
    return 0;
}
