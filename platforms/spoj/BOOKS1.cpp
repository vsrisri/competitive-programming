// Incomplete
#include <bits/stdc++.h>
using namespace std;

int m, k;
long long a[600];

bool helper(long long lim) {
    int count = 1;
    long long sum = 0;
    for (int i = 0; i < m; i++) {
        if (a[i] > lim) {
            return false;
        }
        if (sum + a[i] > lim) {
            count++;
            sum = a[i];
        } else {
            sum += a[i];
        }
    }
    return count <= k;
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(NULL);

    int T;
    cin >> T;

    for (int tc = 0; tc < T; tc++) {
        cin >> m >> k;
        long long sum = 0;
        long long lo = 0, hi = 0;
        vector<bool> ansArr(m);

        for (int i = 0; i < m; i++) {
            cin >> a[i];
            hi += a[i];
        }

        while (lo < hi) {
            long long mid = (lo + hi) / 2;
            if (helper(mid)) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }

        long long lim = lo;
        int num = k - 2;
        sum = 0;

        for (int i = m - 1; i >= 0; i--) {
            if (sum + a[i] > lim || i + 1 < num) {
                ansArr[i] = true;
                num--;
                sum = a[i];
            } else {
                sum += a[i];
            }
        }

        for (int i = 0; i < m; i++) {
            cout << a[i];
            if (i < m - 1) {
                cout << " ";
            }
            if (i < m - 1 && ansArr[i]) {
                cout << "/ ";
            }
        }
        if (tc < T - 1) {
            cout << "\n";
        }
    }

    return 0;
}

