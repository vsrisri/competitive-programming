#include <bits/stdc++.h>
using namespace std;

bool helper(vector<vector<string>>& a, vector<vector<string>>& b, int n, int m) {
    vector<string> init(m);
    vector<string> fin(m);
    for (int j = 0; j < m; j++) {
        string s;
        for (int i = 0; i < n; i++) {
            s += (a[i][j] == "RED" ? '1' : '0');
        }
        init[j] = s;
    }
    for (int j = 0; j < m; j++) {
        string s;
        for (int i = 0; i < n; i++) {
            s += (b[i][j] == "RED" ? '1' : '0');
        }
        fin[j] = s;
    }

    unordered_map<string, int> finCount;
    for (string s : fin) {
        finCount[s]++;
    }

    for (int k = 0; k < m; k++) {
        string flip;
        for (int i = 0; i < n; i++) {
            flip += (init[0][i] == fin[k][i] ? '0' : '1');
        }

        unordered_map<string, int> count;
        bool isOk = true;
        for (int j = 0; j < m; j++) {
            string trans;
            for (int i = 0; i < n; i++) {
                trans += (init[j][i] == flip[i] ? '0' : '1');
            }

            string column = trans;
            int value = count[column] + 1;
            count[column] = value;
            auto it = finCount.find(column);
            int tar = (it == finCount.end() ? 0 : it->second);
            if (value > tar) {
                isOk = false;
                break;
            }
        }

        if (isOk && count == finCount) {
            return true;
        }
    }

    return false;
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    int k;
    cin >> k;
    while (k-- > 0) {
        int n, m;
        cin >> n >> m;
        vector<vector<string>> init(n, vector<string>(m));
        vector<vector<string>> fin(n, vector<string>(m));
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                cin >> init[i][j];
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                cin >> fin[i][j];
            }
        }

        cout << (helper(init, fin, n, m) ? "YES" : "NO") << '\n';
    }

    return 0;
}
