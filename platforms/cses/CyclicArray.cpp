#include <bits/stdc++.h>
using namespace std;

const int LOG = 18;
int bin[LOG][400001];
long long p[400001];

int main() {
    int n;
    long long k;
    scanf("%d %lld", &n, &k);
    int m = 2 * n;
    vector<long long> x(n);
    for (int i = 0; i < n; i++) {
        scanf("%lld", &x[i]);
    }

    for (int i = 0; i < m; i++) {
        p[i + 1] = p[i] + x[i % n];
    }

    int j = 0;
    for (int i = 0; i < m; i++) {
        if (j < i) {
            j = i;
        }
        while (j < m && p[j + 1] - p[i] <= k) {
            j++;
        }
        bin[0][i] = j;
    }

    bin[0][m] = m;
    for (int b = 1; b < LOG; b++) {
        for (int i = 0; i <= m; i++) {
            bin[b][i] = bin[b - 1][bin[b - 1][i]];
        }
    }

    int ans = n;
    for (int i = 0; i < n; i++) {
        int pos = i;
        int cnt = 0;
        for (int b = LOG - 1; b >= 0; b--) {
            if (bin[b][pos] < i + n) {
                pos = bin[b][pos];
                cnt += 1 << b;
            }
        }
        ans = min(ans, cnt + 1);
    }
    printf("%d\n", ans);
    return 0;
}
