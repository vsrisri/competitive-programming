#include <bits/stdc++.h>
using namespace std;
int main() {
    ios::sync_with_stdio(false);
    cin.tie(0);
    int k, n, m;
    while (cin >> k >> n >> m && (k || n || m)) {
        vector<int> cost(n + 1), val(n + 1);
        for (int i = 1; i <= n; i++) {
            cin >> cost[i] >> val[i];
        }

        struct State {
            int benefit;
            int cost;
            bool valid;
        };

        int B = m + 1;
        int D = n + 1;
        vector<vector<vector<State>>> dp(B, vector<vector<State>>(D, vector<State>(3, {0, 0, false})));
        dp[0][0][0] = {0, 0, true};
        struct Prev { 
            int b, d, s; 
        };
        vector<vector<vector<vector<Prev>>>> prev(k + 1, vector<vector<vector<Prev>>>(B, vector<vector<Prev>>(D, vector<Prev>(3, {-1, -1, -1}))));
        for (int day = 1; day <= k; day++) {
            vector<vector<vector<State>>> ndp(B, vector<vector<State>>(D, vector<State>(3, {0, 0, false})));
            for (int b = 0; b < B; b++) {
                for (int pd = 0; pd < D; pd++) {
                    for (int ps = 0; ps < 3; ps++) {
                        if (!dp[b][pd][ps].valid) {
                            continue;
                        }

                        int cb = dp[b][pd][ps].benefit;
                        int cc = dp[b][pd][ps].cost;
                        for (int nd = 1; nd <= n; nd++) {
                            int nb = b + cost[nd];
                            if (nb > m) {
                                continue;
                            }

                            int ns, add;
                            if (nd != pd) {
                                ns = 1;
                                add = 2 * val[nd];
                            } else {
                                ns = 2;
                                add = (ps == 1) ? val[nd] : 0;
                            }

                            int nbenefit = cb + add;
                            int ncost = cc + cost[nd];
                            State &cur = ndp[nb][nd][ns];
                            if (!cur.valid || nbenefit > cur.benefit ||
                               (nbenefit == cur.benefit && ncost < cur.cost)) {
                                cur = {nbenefit, ncost, true};
                                prev[day][nb][nd][ns] = {b, pd, ps};
                            }
                        }
                    }
                }
            }
            dp = ndp;
        }

        int bestBen = -1;
        int bestCost = INT_MAX;
        int bestB = -1, bestD = -1, bestS = -1;
        for (int b = 0; b < B; b++) {
            for (int d = 1; d <= n; d++) {
                for (int s = 1; s <= 2; s++) {
                    if (!dp[b][d][s].valid) {
                        continue;
                    }
                    int ben = dp[b][d][s].benefit;
                    int co = dp[b][d][s].cost;
                    if (ben > bestBen || (ben == bestBen && co < bestCost)) {
                        bestBen = ben;
                        bestCost = co;
                        bestB = b;
                        bestD = d;
                        bestS = s;
                    }
                }
            }
        }

        if (bestBen <= 0) {
            printf("0.0\n");
            continue;
        }

        printf("%.1f\n", bestBen / 2.0;
        vector<int> menu(k + 1);
        int cb = bestB, cd = bestD, cs = bestS;
        for (int day = k; day >= 1; day--) {
            menu[day] = cd;
            Prev p = prev[day][cb][cd][cs];
            cb = p.b;
            cd = p.d;
            cs = p.s;
        }
        for (int day = 1; day <= k; day++) {
            printf("%d ", menu[day]);
        }
        printf("\n");
    }
    return 0;
}
