#include <bits/stdc++.h>
using namespace std;

static const int maxn = 1000000;
int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int T;
    cin >> T;
    static int sum[maxn + 1];
    static int pref[maxn + 1];
    int maxSum = 0;
    for (int i = 1; i <= maxn; i++) {
        for (int j = i; j <= maxn; j += i) {
            sum[j] += i;
        }
    }

    for (int i = 1; i <= maxn; i++) {
        if (sum[i] > maxSum) {
            maxSum = sum[i];
        }
    }

    vector<bool> prime(maxSum + 1, true);
    if (maxSum >= 0) {
        prime[0] = false;
    }
    if (maxSum >= 1) {
        prime[1] = false;
    }

    for (int i = 2; (long long) i * i <= maxSum; i++) {
        if (prime[i]) {
            for (int j = i * i; j <= maxSum; j += i) {
                prime[j] = false;
            }
        }
    }

    for (int i = 1; i <= maxn; i++) {
        pref[i] = pref[i - 1] + (prime[sum[i]] ? 1 : 0);
    }

    for (int tc = 0; tc < T; tc++) {
        int A, B;
        cin >> A >> B;
        cout << pref[B] - pref[A - 1] << '\n';
    }

    return 0;
}

