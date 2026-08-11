#include <bits/stdc++.h>
using namespace std;

int main() {
    int t;
    scanf("%d", &t);
    while (t--) {
        int n;
        scanf("%d", &n);
        vector<int> freq(n, 0);
        bool isPoss = true;
        for (int i = 0; i < n; i++) {
            int v;
            scanf("%d", &v);
            if (v < 0 || v > n - 1) {
                isPoss = false;
            } else {
                freq[v]++;
            }
        }
        if (isPoss) {
            for (int g = 0; g <= (n - 1) / 2; g++) {
                int other = n - 1 - g;
                int need = (g == other) ? 1 : 2;
                int cnt = (g == other) ? freq[g] : freq[g] + freq[other];
                if (cnt != need) {
                    isPoss = false;
                    break;
                }
            }
        }
        printf("%s\n", isPoss ? "YES" : "NO");
    }
    return 0;
}
