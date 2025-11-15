#include <bits/stdc++.h>
using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    int t;
    cin >> t;
    for (int tc = 0; tc < t; tc++) {
        int n;
        cin >> n;
        long long pos = 1;
        long long ans = 0;
        vector<long long> freq(n + 1, 0);
        for (int i = 0; i < n; i++) {
            string s;
            int r;
            cin >> s >> r;
            freq[r]++;
        }

        for (int i = 1; i <= n; i++) {
            while (freq[i]--) {
                ans += llabs(i - pos);
                pos++;
            }
        }

        cout << ans << '\n';
    }
}
