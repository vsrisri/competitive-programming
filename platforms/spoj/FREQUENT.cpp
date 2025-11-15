// Incomplete
#include <bits/stdc++.h>
using namespace std;

int n;
vector<int> arr;
vector<int> leftVal, rightVal, leftFreq, rightFreq, maxFreq;

struct Node {
    int leftVal, rightVal, leftFreq, rightFreq, maxFreq;
    Node(int lv, int rv, int lf, int rf, int mf) {
        leftVal = lv;
        rightVal = rv;
        leftFreq = lf;
        rightFreq = rf;
        maxFreq = mf;
    }
};

Node merge(Node a, Node b) {
    if (a.leftVal == -1) {
        return b;
    }
    if (b.leftVal == -1) {
        return a;
    }
    
    int lv = a.leftVal;
    int rv = b.rightVal;
    int lf = a.leftFreq;
    int rf = b.rightFreq;
    int mf = max(a.maxFreq, b.maxFreq);
    
    if (a.rightVal == b.leftVal) {
        int mid = a.rightFreq + b.leftFreq;
        mf = max(mf, mid);
        if (a.leftVal == a.rightVal) {
            lf = mid;
        }
        if (b.leftVal == b.rightVal) {
            rf = mid;
        }
    }
    return Node(lv, rv, lf, rf, mf);
}

void build(int idx, int l, int r) {
    int mid = (l + r) / 2;
    if (l == r) {
        leftVal[idx] = arr[l];
        rightVal[idx] = arr[r];
        leftFreq[idx] = 1;
        rightFreq[idx] = 1;
        maxFreq[idx] = 1;
        return;
    }

    build(idx * 2, l, mid);
    build(idx * 2 + 1, mid + 1, r);
    Node merged = merge(
        Node(leftVal[idx * 2], rightVal[idx * 2], leftFreq[idx * 2], rightFreq[idx * 2], maxFreq[idx * 2]),
        Node(leftVal[idx * 2 + 1], rightVal[idx * 2 + 1], leftFreq[idx * 2 + 1], rightFreq[idx * 2 + 1], maxFreq[idx * 2 + 1])
    );
    leftVal[idx] = merged.leftVal;
    rightVal[idx] = merged.rightVal;
    leftFreq[idx] = merged.leftFreq;
    rightFreq[idx] = merged.rightFreq;
    maxFreq[idx] = merged.maxFreq;
}

Node query(int idx, int l, int r, int ql, int qr) {
    int mid = (l + r) / 2;
    if (qr < l || r < ql) {
        return Node(-1, -1, -1, -1, -1);
    }
    if (ql <= l && r <= qr) {
        return Node(leftVal[idx], rightVal[idx], leftFreq[idx], rightFreq[idx], maxFreq[idx]);
    }
    Node left = query(idx * 2, l, mid, ql, qr);
    Node right = query(idx * 2 + 1, mid + 1, r, ql, qr);
    return merge(left, right);
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    string line;
    while (getline(cin, line)) {
        if (line == "0") {
            break;
        }
        
        stringstream ss(line);
        ss >> n;
        int q;
        ss >> q;
        arr.assign(n + 1, 0);
        for (int i = 1; i <= n; ++i) {
            cin >> arr[i];
        }
        
        int size = 4 * n;
        leftVal.assign(size, 0);
        rightVal.assign(size, 0);
        leftFreq.assign(size, 0);
        rightFreq.assign(size, 0);
        maxFreq.assign(size, 0);
        build(1, 1, n);
        
        for (int i = 0; i < q; ++i) {
            int l, r;
            cin >> l >> r;
            Node res = query(1, 1, n, l, r);
            cout << res.maxFreq << '\n';
        }
    }

    return 0;
}

