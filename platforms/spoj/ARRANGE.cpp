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
        vector<long long> a(n);
        for (int i = 0; i < n; i++) {
            cin >> a[i];
        }

        sort(a.begin(), a.end(), greater<long long>());
        int ones = 0;
        vector<long long> rest;
        for (auto x : a) {
            if (x == 1) {
                ones++;
            } else {
                rest.push_back(x);
            }
        }

        if (rest.size() >= 2 && rest[0] == 3 && rest[1] == 2) {
            swap(rest[0], rest[1]);
        }

        bool first = true;
        for (int i = 0; i < ones; i++) {
            if (!first) {
                cout << ' ';
            }
            cout << 1;
            first = false;
        }
        for (auto x : rest) {
            if (!first) {
                cout << ' ';
            }
            cout << x;
            first = false;
        }
        cout << '\n';
    }
}

