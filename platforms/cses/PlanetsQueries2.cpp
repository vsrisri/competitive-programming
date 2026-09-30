#include <bits/stdc++.h>
using namespace std;
const int N = 200003;
const int LOG = 18;
int bin[LOG][N];
int deg[N];
int dist[N];
int cycle[N];
int pos[N];
int clen[N];
int helper(int v, int d) {
    for (int j = 0; j < LOG; j++) {
        if ((d >> j) & 1) {
            v = bin[j][v];
        }
    }
    return v;
}

int main() {
    int n, q;
    scanf("%d %d", &n, &q);
    for (int i = 1; i <= n; i++) {
        scanf("%d", &bin[0][i]);
        deg[bin[0][i]]++;
    }

    for (int j = 1; j < LOG; j++) {
        for (int i = 1; i <= n; i++) {
            bin[j][i] = bin[j - 1][bin[j - 1][i]];
        }
    }

    vector<int> order;
    for (int i = 1; i <= n; i++) {
        if (deg[i] == 0) {
            order.push_back(i);
        }
    }

    for (int h = 0; h < (int) order.size(); h++) {
        int v = order[h];
        int u = bin[0][v];
        deg[u]--;
        if (deg[u] == 0) {
            order.push_back(u);
        }
    }

    for (int i = 1; i <= n; i++) {
        cycle[i] = -1;
    }

    int c = 0;
    for (int i = 1; i <= n; i++) {
        if (deg[i] > 0 && cycle[i] == -1) {
            int len = 0;
            int u = i;
            do {
                cycle[u] = c;
                pos[u] = len++;
                u = bin[0][u];
            } while (u != i);
            clen[c] = len;
            c++;
        }
    }

    for (int h = (int) order.size() - 1; h >= 0; h--) {
        int v = order[h];
        dist[v] = dist[bin[0][v]] + 1;
    }

    while (q--) {
        int a, b;
        scanf("%d %d", &a, &b);
        if (a == b) {
            printf("0\n");
            continue;
        }

        if (cycle[b] == -1) {
            int d = dist[a] - dist[b];
            if (d < 0) {
                printf("-1\n");
                continue;
            }
            printf("%d\n", helper(a, d) == b ? d : -1);
            continue;
        }

        int r = helper(a, dist[a]);
        if (cycle[r] != cycle[b]) {
            printf("-1\n");
            continue;
        }

        int len = clen[cycle[b]];
        printf("%d\n", dist[a] + (pos[b] - pos[r] + len) % len);
    }
    return 0;
}
