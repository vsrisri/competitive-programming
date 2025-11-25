#include <bits/stdc++.h>
using namespace std;

int helper(vector<long long> &arr) {
    if (arr.size() <= 2) {
        return arr.size();
    }
    long long toWithdraw = arr[0];
    int ans = 1;
    for (int idx = 1; idx < (int) arr.size(); idx++) {
        long long next = (idx + 1 < (int) arr.size() ? arr[idx + 1] : LLONG_MAX);
        if (toWithdraw + arr[idx] < next) {
            toWithdraw += arr[idx];
            ans++;
        }
    }
    return ans;
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    int tc;
    if (!(cin >> tc)) {
        return 0;
    }

    for (int t = 1; t <= tc; t++) {
        int n;
        cin >> n;
        vector<long long> arr(n);
        for (int i = 0; i < n; i++) {
            cin >> arr[i];
        }
        sort(arr.begin(), arr.end());
        int ans = helper(arr);
        cout << "Case #" << t << ": " << ans << "\n";
    }

    return 0;
}

