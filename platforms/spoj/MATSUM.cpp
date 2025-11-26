#include <bits/stdc++.h>
using namespace std;

int N;
vector<int> bit, arr;
void update(int x, int y, int val) {
    for (int dx = x; dx <= N; dx += dx & -dx) {
        for (int dy = y; dy <= N; dy += dy & -dy) {
            bit[(dx - 1) * N + (dy - 1)] += val;
        }
    }
}

int query(int x, int y) {
    int sum = 0;
    for (int dx = x; dx > 0; dx -= dx & -dx) {
        for (int dy = y; dy > 0; dy -= dy & -dy) {
            sum += bit[(dx - 1) * N + (dy - 1)];
        }
    }
    return sum;
}

int rangeSum(int x1, int y1, int x2, int y2) {
    return query(x2, y2) - query(x1 - 1, y2) - query(x2, y1 - 1) + query(x1 - 1, y1 - 1);
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    int t;
    cin >> t;
    while (t--) {
        cin >> N;
        bit.assign(N * N, 0);
        arr.assign(N * N, 0);
        while (true) {
            string comm;
            cin >> comm;
            if (comm == "END") {
                break;
            }

            if (comm == "SET") {
                int x, y, v;
                cin >> x >> y >> v;
                int idx = x * N + y;
                int diff = v - arr[idx];
                arr[idx] = v;
                update(x + 1, y + 1, diff);
            } else {
                int x1, y1, x2, y2;
                cin >> x1 >> y1 >> x2 >> y2;
                cout << rangeSum(x1 + 1, y1 + 1, x2 + 1, y2 + 1) << "\n";
            }
        }
        cout << "\n";
    }
}

