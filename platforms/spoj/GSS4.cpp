#include <bits/stdc++.h>
using namespace std;

struct Node {
    long long sum;
    bool done;
};

vector<Node> seg;
vector<long long> arr;

void pull(int idx) {
    seg[idx].sum = seg[idx << 1].sum + seg[idx << 1 | 1].sum;
    seg[idx].done = seg[idx << 1].done && seg[idx << 1 | 1].done;
}

void build(int idx, int l, int r) {
    int m = (l + r) >> 1;
    if (l == r) {
        seg[idx].sum = arr[l];
        seg[idx].done = arr[l] <= 1;
        return;
    }
    build(idx << 1, l, m);
    build(idx << 1 | 1, m + 1, r);
    pull(idx);
}

void update(int idx, int l, int r, int ql, int qr) {
	int m = (l + r) >> 1;
    if (seg[idx].done || r < ql || l > qr) {
        return;
    }
    if (l == r) {
        seg[idx].sum = (long long)floor(sqrt((long double)seg[idx].sum));
        seg[idx].done = seg[idx].sum <= 1;
        return;
    }
   
    update(idx << 1, l, m, ql, qr);
    update(idx << 1 | 1, m + 1, r, ql, qr);
    pull(idx);
}

long long query(int idx, int l, int r, int ql, int qr) {
    int m = (l + r) >> 1;
    if (r < ql || l > qr) {
        return 0;
    }
    if (ql <= l && r <= qr) {
        return seg[idx].sum;
    }
    return query(idx << 1, l, m, ql, qr) + query(idx << 1 | 1, m + 1, r, ql, qr);
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    int n;
    int tc = 1;
    while (cin >> n) {
        arr.assign(n + 1, 0);
        for (int i = 1; i <= n; i++) {
            cin >> arr[i];
        }
        seg.assign(n * 4, {0, false});
        build(1, 1, n);
        int m;
        cin >> m;
        cout << "Case #" << tc++ << ":\n";
        while (m--) {
            int t, x, y;
            cin >> t >> x >> y;
            if (x > y) {
                swap(x, y);
            }
            if (t == 0) {
                update(1, 1, n, x, y);
            } else {
                cout << query(1, 1, n, x, y) << "\n";
            }
        }
        cout << "\n";
    }
    return 0;
}
