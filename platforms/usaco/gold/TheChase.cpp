// Java solution only passes 11/21 cases, rest TLE
#include <bits/stdc++.h>
using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    int N, F;
    cin >> N >> F;
    vector<int> road(N + 1);
    for (int i = 1; i <= N; i++) {
        cin >> road[i];
    }

    vector<bool> hasFarmer(N + 1, false);
    for (int i = 0; i < F; i++) {
        int s; cin >> s;
        hasFarmer[s] = true;
    }

    vector<bool> inLoop(N + 1, false);
    vector<int> whichLoop(N + 1, -1);
    vector<int> posInLoop(N + 1, 0);
    vector<int> visited(N + 1, 0);
    vector<int> path(N + 1);
    vector<int> pathIdx(N + 1);
    int numLoops = 0;
    vector<int> loopLenVec;
    for (int start = 1; start <= N; start++) {
        if (visited[start] != 0) {
            continue;
        }
        int pathLen = 0;
        int curr = start;
        while (visited[curr] == 0) {
            visited[curr] = 1;
            path[pathLen] = curr;
            pathIdx[curr] = pathLen;
            pathLen++;
            curr = road[curr];
        }
        if (visited[curr] == 1) {
            int loopStart = pathIdx[curr];
            int ln = pathLen - loopStart;
            loopLenVec.push_back(ln);
            for (int j = loopStart; j < pathLen; j++) {
                inLoop[path[j]] = true;
                whichLoop[path[j]] = numLoops;
                posInLoop[path[j]] = j - loopStart;
            }
            numLoops++;
        }
        for (int j = 0; j < pathLen; j++) {
            visited[path[j]] = 2;
        }
    }

    vector<int> loopLen(loopLenVec.begin(), loopLenVec.end());
    vector<int> treeRoot(N + 1, 0);
    vector<vector<int>> treeEdges(N + 1);
    for (int i = 1; i <= N; i++) {
        if (!inLoop[i]) {
            treeEdges[road[i]].push_back(i);
        }
    }

    vector<int> distToLoop(N + 1, -1);
    deque<int> q;
    for (int i = 1; i <= N; i++) {
        if (inLoop[i]) {
            distToLoop[i] = 0;
            treeRoot[i] = i;
            q.push_back(i);
        }
    }
    while (!q.empty()) {
        int u = q.front(); q.pop_front();
        for (int v : treeEdges[u]) {
            if (distToLoop[v] == -1) {
                distToLoop[v] = distToLoop[u] + 1;
                treeRoot[v] = treeRoot[u];
                q.push_back(v);
            }
        }
    }

    vector<long long> firstArrival(N + 1, LLONG_MAX);
    deque<int> bfsQ;
    for (int i = 1; i <= N; i++) {
        if (hasFarmer[i]) {
            firstArrival[i] = 0;
            bfsQ.push_back(i);
        }
    }
    while (!bfsQ.empty()) {
        int u = bfsQ.front(); bfsQ.pop_front();
        int v = road[u];
        if (firstArrival[v] == LLONG_MAX) {
            firstArrival[v] = firstArrival[u] + 1;
            bfsQ.push_back(v);
        }
    }

    vector<vector<int>> fDeltas(numLoops);
    for (int i = 1; i <= N; i++) {
        if (!hasFarmer[i]) {
            continue;
        }
        int entry = treeRoot[i];
        int lk = whichLoop[entry];
        int ln = loopLen[lk];
        int steps = distToLoop[i];
        int delta = ((posInLoop[entry] - steps) % ln + ln) % ln;
        fDeltas[lk].push_back(delta);
    }

    vector<vector<bool>> blocked(numLoops);
    vector<vector<int>> nextFree(numLoops);
    vector<bool> fullBlock(numLoops, false);
    for (int k = 0; k < numLoops; k++) {
        int ln = loopLen[k];
        blocked[k].assign(ln, false);
        for (int off : fDeltas[k]) {
            blocked[k][off] = true;
        }
        bool fb = true;
        for (int p = 0; p < ln; p++) {
            if (!blocked[k][p]) {
                fb = false;
                break;
            }
        }
        fullBlock[k] = fb;
        nextFree[k].resize(ln, 0);
        if (!fb) {
            for (int round = 0; round < 2; round++) {
                for (int p = ln - 1; p >= 0; p--) {
                    if (!blocked[k][p]) {
                        nextFree[k][p] = 0;
                    } else {
                        int nxt = (p + 1 < ln) ? p + 1 : 0;
                        nextFree[k][p] = 1 + nextFree[k][nxt];
                    }
                }
            }
        }
    }

    string out;
    out.reserve(N * 4);
    for (int i = 1; i <= N; i++) {
        if (hasFarmer[i]) {
            out += "-1\n";
            continue;
        }
        if (firstArrival[i] == LLONG_MAX) {
            out += "-2\n";
            continue;
        }
        long long maxRest = firstArrival[i] - 1;
        int root = treeRoot[i];
        int lk = whichLoop[root];
        int ln = loopLen[lk];
        int rp = posInLoop[root];
        int dd = distToLoop[i];
        if (fullBlock[lk]) {
            out += "-1\n";
            continue;
        }
        int shifted = ((rp - dd) % ln + ln) % ln;
        long long startOff = ((shifted - maxRest) % ln + ln) % ln;
        int sp = (int) startOff;
        int fwd = nextFree[lk][sp];
        long long ans = maxRest - fwd;
        if (ans < 0) {
            out += "-1\n";
        } else {
            out += to_string(ans);
            out += '\n';
        }
    }
    cout << out;
    return 0;
}
