#include <bits/stdc++.h>
using namespace std;

int bin[30][200003];
int main() {
    int n, q;
    scanf("%d %d", &n, &q);
    for (int i = 1; i <= n; i++) {
        scanf("%d", &bin[0][i]);
    }

    for (int j = 1; j < 30; j++) {
        for (int i = 1; i <= n; i++) {
            bin[j][i] = bin[j - 1][bin[j - 1][i]];
        }
    }

    while (q--) {
        int x, k;
        scanf("%d %d", &x, &k);
        for (int j = 0; j < 30; j++) {
            if ((k >> j) & 1) {
                x = bin[j][x];
            }
        }
        printf("%d\n", x);
    }
    return 0;
}
