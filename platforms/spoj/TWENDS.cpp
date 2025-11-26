#include <bits/stdc++.h>
using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    int n, tc = 1;
    while (true) {
        cin >> n;
        if (!cin || n == 0) {
            break;
        }

        vector<int> a(n);
        vector<vector<int>> dp(n, vector<int>(n, 0));
        int sum = 0;
        for (int i = 0; i < n; i++) {
            cin >> a[i];
            sum += a[i];
        }

        for (int i = 0; i < n; i++) {
            dp[i][i] = a[i];
        }
        for (int len = 2; len <= n; len++) {
            for (int l = 0; l + len - 1 < n; l++) {
                int r = l + len - 1;
                int takeLeft;
                if (a[l + 1] >= a[r]) {
                    takeLeft = a[l] + ((l + 2 <= r) ? dp[l + 2][r] : 0);
                } else {
                    takeLeft = a[l] + ((l + 1 <= r - 1) ? dp[l + 1][r - 1] : 0);
                }

                int takeRight;
                if (a[l] >= a[r - 1]) {
                    takeRight = a[r] + ((l + 1 <= r - 1) ? dp[l + 1][r - 1] : 0);
                } else {
                    takeRight = a[r] + ((l <= r - 2) ? dp[l][r - 2] : 0);
                }

                dp[l][r] = max(takeLeft, takeRight);
            }
        }

        int first = dp[0][n - 1];
        int diff = first - (sum - first);
        cout << "In game " << tc++ << ", the greedy strategy might lose by as many as " << diff << " points.\n";
    }

    return 0;
}

