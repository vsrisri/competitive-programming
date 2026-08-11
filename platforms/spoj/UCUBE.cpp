#include <bits/stdc++.h>
using namespace std;
int N;
char frArr[10][11], lArr[10][11], bArr[10][11], rArr[10][11], aArr[10][11], bwArr[10][11];
char frArrC[10][10], bArrC[10][10], lArrC[10][10], rArrC[10][10], aArrC[10][10], bC[10][10];
bool presArr[10][10][10];
int main() {
    int t;
    scanf("%d", &t);
    while (t--) {
        scanf("%d", &N);
        for (int r = 0; r < N; r++) {
            char f[11], l[11], b[11], rt[11], a[11], bl[11];
            scanf("%s %s %s %s %s %s", f, l, b, rt, a, bl);
            for (int c = 0; c < N; c++) {
                frArr[r][c] = f[c];
                lArr[r][c] = l[c];
                bArr[r][c] = b[c];
                rArr[r][c] = rt[c];
                aArr[r][c] = a[c];
                bwArr[r][c] = bl[c];
            }
        }
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                frArrC[r][c] = frArr[r][c];
                bArrC[r][c] = bArr[r][N - 1 - c];
            }
        }
        for (int r = 0; r < N; r++) {
            for (int d = 0; d < N; d++) {
                lArrC[r][d] = lArr[r][N - 1 - d];
                rArrC[r][d] = rArr[r][d];
            }
        }
        for (int d = 0; d < N; d++) {
            for (int c = 0; c < N; c++) {
                aArrC[d][c] = aArr[N - 1 - d][c];
                bC[d][c] = bwArr[d][c];
            }
        }
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                for (int d = 0; d < N; d++) {
                    presArr[r][c][d] = true;
                }
            }
        }
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                if (frArrC[r][c] == '.' || bArrC[r][c] == '.') {
                    for (int d = 0; d < N; d++) {
                        presArr[r][c][d] = false;
                    }
                }
            }
        }
        for (int r = 0; r < N; r++) {
            for (int d = 0; d < N; d++) {
                if (lArrC[r][d] == '.' || rArrC[r][d] == '.') {
                    for (int c = 0; c < N; c++) {
                        presArr[r][c][d] = false;
                    }
                }
            }
        }
        for (int d = 0; d < N; d++) {
            for (int c = 0; c < N; c++) {
                if (aArrC[d][c] == '.' || bC[d][c] == '.') {
                    for (int r = 0; r < N; r++) {
                        presArr[r][c][d] = false;
                    }
                }
            }
        }
        bool changed = true;
        while (changed) {
            changed = false;
            static int firstfrArr[10][10], firstbArr[10][10];
            static int firstlArr[10][10], firstrArr[10][10];
            static int firstaArr[10][10], firstb[10][10];
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {
                    firstfrArr[r][c] = -1;
                    for (int d = 0; d < N; d++) {
                        if (presArr[r][c][d]) {
                            firstfrArr[r][c] = d;
                            break;
                        }
                    }
                    firstbArr[r][c] = -1;
                    for (int d = N - 1; d >= 0; d--) {
                        if (presArr[r][c][d]) {
                            firstbArr[r][c] = d;
                            break;
                        }
                    }
                }
            }
            for (int r = 0; r < N; r++) {
                for (int d = 0; d < N; d++) {
                    firstlArr[r][d] = -1;
                    for (int c = 0; c < N; c++) {
                        if (presArr[r][c][d]) {
                            firstlArr[r][d] = c;
                            break;
                        }
                    }
                    firstrArr[r][d] = -1;
                    for (int c = N - 1; c >= 0; c--) {
                        if (presArr[r][c][d]) {
                            firstrArr[r][d] = c;
                            break;
                        }
                    }
                }
            }
            for (int d = 0; d < N; d++) {
                for (int c = 0; c < N; c++) {
                    firstaArr[d][c] = -1;
                    for (int r = 0; r < N; r++) {
                        if (presArr[r][c][d]) {
                            firstaArr[d][c] = r;
                            break;
                        }
                    }
                    firstb[d][c] = -1;
                    for (int r = N - 1; r >= 0; r--) {
                        if (presArr[r][c][d]) {
                            firstb[d][c] = r;
                            break;
                        }
                    }
                }
            }
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {
                    for (int d = 0; d < N; d++) {
                        if (!presArr[r][c][d]) {
                            continue;
                        }
                        char req = 0;
                        bool prob = false;
                        if (firstfrArr[r][c] == d && frArrC[r][c] != '.') {
                            req = frArrC[r][c];
                        }
                        if (firstbArr[r][c] == d && bArrC[r][c] != '.') {
                            if (req && req != bArrC[r][c]) {
                                prob = true;
                            }
                            req = bArrC[r][c];
                        }
                        if (firstlArr[r][d] == c && lArrC[r][d] != '.') {
                            if (req && req != lArrC[r][d]) {
                                prob = true;
                            }
                            req = lArrC[r][d];
                        }
                        if (firstrArr[r][d] == c && rArrC[r][d] != '.') {
                            if (req && req != rArrC[r][d]) {
                                prob = true;
                            }
                            req = rArrC[r][d];
                        }
                        if (firstaArr[d][c] == r && aArrC[d][c] != '.') {
                            if (req && req != aArrC[d][c]) {
                                prob = true;
                            }
                            req = aArrC[d][c];
                        }
                        if (firstb[d][c] == r && bC[d][c] != '.') {
                            if (req && req != bC[d][c]) {
                                prob = true;
                            }
                            req = bC[d][c];
                        }
                        if (prob) {
                            presArr[r][c][d] = false;
                            changed = true;
                        }
                    }
                }
            }
        }
        long long cnt = 0;
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                for (int d = 0; d < N; d++) {
                    if (presArr[r][c][d]) {
                        cnt++;
                    }
                }
            }
        }
        printf("%lld\n", cnt);
    }
    return 0;
}
