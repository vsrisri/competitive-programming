#include <bits/stdc++.h>
using namespace std;

int main() {
    int t;
    scanf("%d", &t);
    while (t--) {
        int n;
        long long k;
        scanf("%d %lld", &n, &k);
        vector<long long> s(n);
        for (int i = 0; i < n; i++) {
            scanf("%lld", &s[i]);
        }

        int m = n - 1;
        int sz = m + 2;
        const long long INF = LLONG_MAX / 4;
        vector<long long> val(sz);
        vector<int> left(sz), right(sz);
        vector<bool> dArr(sz, false);
        val[0] = INF;
        val[sz - 1] = INF;
        for (int i = 0; i < m; i++) {
            val[i + 1] = s[i + 1] - s[i];
        }
        for (int i = 0; i < sz; i++) {
            left[i] = i - 1;
            right[i] = (i + 1 < sz) ? i + 1 : -1;
        }

        priority_queue<pair<long long, int>, vector<pair<long long, int>>, greater<pair<long long, int>>> pq;
        for (int i = 1; i <= m; i++) {
            pq.push({val[i], i});
        }
        long long ans = 0, cnt = 0;
        while (cnt < k) {
            pair<long long, int> top = pq.top();
            pq.pop();
            long long v = top.first;
            int i = top.second;
            if (dArr[i]) {
                continue;
            }
            ans += v;
            cnt++;
            int l = left[i], r = right[i];
            dArr[i] = true;
            dArr[l] = true;
            dArr[r] = true;
            long long newVal = val[l] + val[r] - v;
            int newL = left[l];
            int newR = right[r];
            val[i] = newVal;
            left[i] = newL;
            right[i] = newR;
            dArr[i] = false;
            if (newL != -1) {
                right[newL] = i;
            }
            if (newR != -1) {
                left[newR] = i;
            }
            pq.push({newVal, i});
        }
        printf("%lld\n", ans);
    }
    return 0;
}
