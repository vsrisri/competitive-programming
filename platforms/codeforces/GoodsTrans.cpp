#include <bits/stdc++.h>
using namespace std;

int main(){
    int n; long long c;
    scanf("%d %lld", &n, &c);
    vector<long long> p(n), s(n);
    for (auto &x: p) {
        scanf("%lld", &x);
    }
    for (auto &x: s) {
        scanf("%lld", &x);
    }
    const long long INF = LLONG_MAX/2;
    vector<long long> dp(n + 1, INF);
    dp[0] = 0;
    for (int i = 0; i < n; i++) {
        vector<long long> ndp(n + 1, INF);
        for (int k = 0; k <= i; k++) {
            if (dp[k] >= INF) {
                continue;
            }
            long long cand1 = dp[k] + p[i] + c*k;
            if (cand1 < ndp[k]) {
                ndp[k] = cand1;
            }
            long long cand0 = dp[k] + s[i];
            if (cand0 < ndp[k + 1]) {
                ndp[k + 1] = cand0;
            }
        }
        dp = ndp;
    }
    long long ans = INF;
    for (auto v : dp) {
        ans = min(ans, v);
    }
    printf("%lld\n", ans);
    return 0;
}

