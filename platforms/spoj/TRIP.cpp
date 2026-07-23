#include <bits/stdc++.h>
using namespace std;

string a, b;
int dp[85][85];
set<string> arr[85][85];
bool vis[85][85];
set<string> helper(int i, int j) {
    if (vis[i][j]) {
        return arr[i][j];
    }

    vis[i][j] = true;
    set<string> &ans = arr[i][j];
    if (i == a.size() || j == b.size()) {
        ans.insert("");
        return ans;
    }

    if (a[i] == b[j]) {
        auto t = helper(i + 1, j + 1);
        for (auto &s : t) {
            ans.insert(a[i] + s);
        }
    }
    else {
        if (dp[i + 1][j] >= dp[i][j + 1]) {
            auto t = helper(i + 1, j);
            ans.insert(t.begin(), t.end());
        }

        if (dp[i][j + 1] >= dp[i + 1][j]) {
            auto t = helper(i, j + 1);
            ans.insert(t.begin(), t.end());
        }
    }

    return ans;
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    int T;
    cin >> T;
    while (T--) {
        cin >> a >> b;
        int n = a.size();
        int m = b.size();
        memset(dp, 0, sizeof(dp));
        for (int i = n - 1; i >= 0; i--) {
            for (int j = m - 1; j >= 0; j--) {
                if (a[i] == b[j]) {
                    dp[i][j] = dp[i + 1][j + 1] + 1;
                }
                else {
                    dp[i][j] = max(dp[i + 1][j], dp[i][j + 1]);
                }
            }
        }

        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                vis[i][j] = false;
                arr[i][j].clear();
            }
        }

        auto ans = helper(0, 0);
        for (auto &s : ans) {
            cout << s << '\n';
        }

        if (T) {
            cout << '\n';
        }
    }

    return 0;
}
