#include <bits/stdc++.h>
using namespace std;

const int MAXN = 100005;
int p[MAXN];
vector<int> tree[MAXN];
long long incl[MAXN], excl[MAXN];
int stk[MAXN], childIdx[MAXN], par[MAXN];
bool onCycle[MAXN];
int visited[MAXN];
int pathArr[MAXN];
int posArr[MAXN];
void dfs2(int root, int parent) {
    int top = 0;
    stk[top] = root;
    par[top] = parent;
    childIdx[top] = 0;
    incl[root] = 1;
    excl[root] = 0;
    while (top >= 0) {
        int node = stk[top];
        int nodeParent = par[top];
        bool pushed = false;
        while (childIdx[top] < (int)tree[node].size()) {
            int child = tree[node][childIdx[top]++];
            if (child == nodeParent) {
                continue;
            }
            incl[child] = 1;
            excl[child] = 0;
            top++;
            stk[top] = child;
            par[top] = node;
            childIdx[top] = 0;
            pushed = true;
            break;
        }
        if (!pushed) {
            top--;
            if (top >= 0) {
                int parentNode = stk[top];
                incl[parentNode] += excl[node];
                excl[parentNode] += max(incl[node], excl[node]);
            }
        }
    }
}

int main() {
    int T;
    scanf("%d", &T);
    memset(posArr, -1, sizeof(posArr));
    while (T--) {
        int N;
        scanf("%d", &N);
        for (int i = 1; i <= N; i++) {
            tree[i].clear();
            visited[i] = 0;
            onCycle[i] = false;
        }
        for (int i = 1; i <= N; i++) {
            scanf("%d", &p[i]);
        }
        for (int i = 1; i <= N; i++) {
            if (p[i] != i) {
                tree[p[i]].push_back(i);
                tree[i].push_back(p[i]);
            }
        }

        long long ans = 0;
        for (int i = 1; i <= N; i++) {
            if (visited[i]) {
                continue;
            }

            int pathLen = 0;
            int curr = i;
            while (!visited[curr] && posArr[curr] == -1) {
                posArr[curr] = pathLen;
                pathArr[pathLen++] = curr;
                curr = p[curr];
            }

            int cycleStart = -1, cycleLen = 0;
            if (!visited[curr] && posArr[curr] != -1) {
                cycleStart = posArr[curr];
                cycleLen = pathLen - cycleStart;
                for (int j = cycleStart; j < pathLen; j++) {
                    onCycle[pathArr[j]] = true;
                }
            }

            for (int j = 0; j < pathLen; j++) {
                visited[pathArr[j]] = 1;
                posArr[pathArr[j]] = -1;
            }

            if (cycleStart != -1) {
                for (int j = cycleStart; j < pathLen; j++) {
                    int node = pathArr[j];
                    incl[node] = 1;
                    excl[node] = 0;
                }
                for (int j = cycleStart; j < pathLen; j++) {
                    int node = pathArr[j];
                    for (int child : tree[node]) {
                        if (!onCycle[child]) {
                            dfs2(child, node);
                            incl[node] += excl[child];
                            excl[node] += max(incl[child], excl[child]);
                        }
                    }
                }

                int L = cycleLen;
                if (L == 1) {
                    int node = pathArr[cycleStart];
                    ans += excl[node];
                } else {
                    long long NEG = LLONG_MIN / 2;
                    long long dp0, dp1, pdp0, pdp1;
                    pdp0 = excl[pathArr[cycleStart]];
                    pdp1 = NEG;
                    for (int j = 1; j < L; j++) {
                        int node = pathArr[cycleStart + j];
                        dp0 = max(pdp0, pdp1) + excl[node];
                        dp1 = pdp0 + incl[node];
                        pdp0 = dp0;
                        pdp1 = dp1;
                    }

                    long long best = max(pdp0, pdp1);
                    pdp0 = NEG;
                    pdp1 = incl[pathArr[cycleStart]];
                    for (int j = 1; j < L; j++) {
                        int node = pathArr[cycleStart + j];
                        dp0 = max(pdp0, pdp1) + excl[node];
                        dp1 = pdp0 + incl[node];
                        pdp0 = dp0;
                        pdp1 = dp1;
                    }
                    best = max(best, pdp0);
                    ans += best;
                }
            }
        }

        printf("%d\n", N - (int) ans);
    }
    return 0;
}
