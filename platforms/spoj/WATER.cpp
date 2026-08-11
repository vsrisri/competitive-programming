#include <bits/stdc++.h>
using namespace std;

int main() {
    int t;
    scanf("%d", &t);
    while (t--) {
        int n, m;
        scanf("%d %d", &n, &m);
        vector<vector<int>> h(n, vector<int>(m));
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                scanf("%d", &h[i][j]);
            }
        }
        vector<vector<char>> visited(n, vector<char>(m, 0));
        priority_queue<tuple<int,int,int>, vector<tuple<int,int,int>>, greater<tuple<int,int,int>>> pq;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (i == 0 || i == n - 1 || j == 0 || j == m - 1) {
                    if (!visited[i][j]) {
                        visited[i][j] = 1;
                        pq.push(make_tuple(h[i][j], i, j));
                    }
                }
            }
        }
        long long volume = 0;
        int dx[4] = {-1, 1, 0, 0};
        int dy[4] = {0, 0, -1, 1};
        while (!pq.empty()) {
            tuple<int,int,int> curr = pq.top();
            pq.pop();
            int level = get<0>(curr);
            int x = get<1>(curr);
            int y = get<2>(curr);
            for (int d = 0; d < 4; d++) {
                int nx = x + dx[d];
                int ny = y + dy[d];
                if (nx < 0 || nx >= n || ny < 0 || ny >= m) {
                    continue;
                }
                if (visited[nx][ny]) {
                    continue;
                }
                visited[nx][ny] = 1;
                int nlevel = h[nx][ny];
                if (nlevel < level) {
                    volume += level - nlevel;
                    nlevel = level;
                }
                pq.push(make_tuple(nlevel, nx, ny));
            }
        }
        printf("%lld\n", volume);
    }
    return 0;
}
