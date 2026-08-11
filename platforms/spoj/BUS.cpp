#include <cstdio>
#include <cstddef>
#include <climits>
#include <vector>
#include <set>
#include <algorithm>
using namespace std;

long long gcd(long long a, long long b, long long &x, long long &y) {
    if (b == 0) {
        x = 1;
        y = 0;
        return a;
    }
    long long x1, y1;
    long long g = gcd(b, a % b, x1, y1);
    x = y1;
    y = x1 - (a / b) * y1;
    return g;
}

bool crt(long long a1, long long m1, long long a2, long long m2, long long &t0, long long &L) {
    long long x, y;
    long long g = gcd(m1, m2, x, y);
    if (((a2 - a1) % g + g) % g != 0) {
        return false;
    }
    L = m1 / g * m2;
    long long m2g = m2 / g;
    long long diff = (a2 - a1) / g;
    long long xm = ((x % m2g) + m2g) % m2g;
    long long dm = ((diff % m2g) + m2g) % m2g;
    long long k = (xm * dm) % m2g;
    long long t = a1 + m1 * k;
    t %= L;
    if (t < 0) {
        t += L;
    }
    t0 = t;
    return true;
}

long long helper(const vector<long long> &R, long long L, long long num) {
    long long ans = LLONG_MAX;
    for (size_t idx = 0; idx < R.size(); idx++) {
        long long r = R[idx];
        long long cand;
        if (r >= num) {
            cand = r;
        } else {
            long long diff = num - r;
            long long k = (diff + L - 1) / L;
            cand = r + k * L;
        }
        if (cand < ans) {
            ans = cand;
        }
    }
    return ans;
}

struct MeetObj {
    int i;
    int j;
    vector<long long> R;
    long long L;
};

int main() {
    int n;
    while (scanf("%d", &n) == 1) {
        if (n == 0) {
            break;
        }
        vector<int> s(n);
        vector<vector<int>> stops(n);
        for (int i = 0; i < n; i++) {
            scanf("%d", &s[i]);
            stops[i].resize(s[i]);
            for (int k = 0; k < s[i]; k++) {
                scanf("%d", &stops[i][k]);
            }
        }
        vector<MeetObj> pairs;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                long long g = __gcd((long long) s[i], (long long) s[j]);
                long long L = (long long) s[i] / g * s[j];
                set<long long> Rset;
                for (int a = 0; a < s[i]; a++) {
                    for (int b = 0; b < s[j]; b++) {
                        if (stops[i][a] == stops[j][b]) {
                            long long t0, LL;
                            if (crt(a, s[i], b, s[j], t0, LL)) {
                                Rset.insert(t0);
                            }
                        }
                    }
                }
                if (!Rset.empty()) {
                    MeetObj pd;
                    pd.i = i;
                    pd.j = j;
                    pd.R.assign(Rset.begin(), Rset.end());
                    pd.L = L;
                    pairs.push_back(pd);
                }
            }
        }
        vector<unsigned long long> curr(n);
        for (int i = 0; i < n; i++) {
            curr[i] = 1ULL << i;
        }
        unsigned long long full = (n >= 64) ? ~0ULL : ((1ULL << n) - 1);
        long long timeIdx = 0;
        bool done = false;
        long long ans = -1;
        bool allFull = true;
        for (int i = 0; i < n; i++) {
            if (curr[i] != full) {
                allFull = false;
                break;
            }
        }
        if (allFull) {
            done = true;
            ans = 0;
        }
        while (!done) {
            long long ansT = LLONG_MAX;
            int bi = -1;
            int bj = -1;
            for (size_t idx = 0; idx < pairs.size(); idx++) {
                MeetObj &p = pairs[idx];
                if (curr[p.i] == curr[p.j]) {
                    continue;
                }
                long long cand = helper(p.R, p.L, timeIdx);
                if (cand < ansT) {
                    ansT = cand;
                    bi = p.i;
                    bj = p.j;
                }
            }
            if (bi == -1) {
                break;
            }
            unsigned long long nm = curr[bi] | curr[bj];
            curr[bi] = nm;
            curr[bj] = nm;
            timeIdx = ansT;
            bool af = true;
            for (int i = 0; i < n; i++) {
                if (curr[i] != full) {
                    af = false;
                    break;
                }
            }
            if (af) {
                done = true;
                ans = timeIdx;
            }
        }
        if (done) {
            printf("%lld\n", ans);
        } else {
            printf("NEVER\n");
        }
    }
    return 0;
}
