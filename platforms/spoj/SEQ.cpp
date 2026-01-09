#include <bits/stdc++.h>
using namespace std;

static const long long MOD = 1000000000;
struct Matrix {
    int n;
    vector<vector<long long>> mat;
    Matrix(int n = 0, bool ident = false) : n(n), mat(n, vector<long long>(n, 0)) {
        if (ident) {
            for (int i = 0; i < n; i++) {
                mat[i][i] = 1;
            }
        }
    }
};

Matrix multiply(const Matrix &A, const Matrix &B) {
    int n = A.n;
    Matrix R(n);
    for (int i = 0; i < n; i++) {
        for (int k = 0; k < n; k++) {
            if (A.mat[i][k] == 0) {
                continue;
            }
            long long val = A.mat[i][k];
            for (int j = 0; j < n; j++) {
                R.mat[i][j] = (R.mat[i][j] + val * B.mat[k][j]) % MOD;
            }
        }
    }
    return R;
}

Matrix power(Matrix base, long long exp) {
    int n = base.n;
    Matrix done(n, true);
    while (exp > 0) {
        if (exp & 1) {
            done = multiply(done, base);
        }
        base = multiply(base, base);
        exp >>= 1;
    }
    return done;
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int C;
    cin >> C;
    for (int tc = 0; tc < C; tc++) {
        int k;
        cin >> k;
        vector<long long> start(k), coef(k);
        long long term;
        for (int i = 0; i < k; i++) {
            cin >> start[i];
        }
        for (int i = 0; i < k; i++) {
            cin >> coef[i];
        }

        cin >> term;
        if (term <= k) {
            cout << (start[term - 1] % MOD) << "\n";
            continue;
        }

        Matrix M(k);
        for (int j = 0; j < k; j++) {
            M.mat[0][j] = coef[j] % MOD;
        }
        for (int i = 1; i < k; i++) {
            M.mat[i][i - 1] = 1;
        }

        Matrix Mp = power(M, term - k);
        long long ans = 0;
        for (int i = 0; i < k; i++) {
            ans = (ans + Mp.mat[0][i] * (start[k - 1 - i] % MOD)) % MOD;
        }

        cout << ans << "\n";
    }
    return 0;
}

