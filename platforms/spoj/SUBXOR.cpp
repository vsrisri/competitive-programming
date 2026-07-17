#include <bits/stdc++.h>
using namespace std;
const int BITS = 20;
int ch[2200005][2];
int count_[2200005];
int nodeCount;
int initNode(){
    ch[nodeCount][0] = -1;
    ch[nodeCount][1] = -1;
    count_[nodeCount] = 0;
    return nodeCount++;
}

void trHelper(int x){
    int curr = 0;
    for (int i = BITS; i >= 0; i--){
        int b = (x >> i) & 1;
        if (ch[curr][b] == -1){
            ch[curr][b] = initNode();
        }
        curr = ch[curr][b];
        count_[curr]++;
    }
}

long long query(int x, int K){
    int curr = 0;
    long long ans = 0;
    for (int i = BITS; i >= 0 && curr != -1; i--){
        int b = (x >> i) & 1;
        int kb = (K >> i) & 1;
        if (kb == 1){
            int child0 = ch[curr][b];
            if (child0 != -1){
                ans += count_[child0];
            }
            curr = ch[curr][1 - b];
        } else {
            curr = ch[curr][b];
        }
    }
    return ans;
}

int main(){
    int T;
    scanf("%d", &T);
    while (T--){
        int N, K;
        scanf("%d %d", &N, &K);
        vector<int> A(N);
        for (int i = 0; i < N; i++){
            scanf("%d", &A[i]);
        }
        nodeCount = 0;
        initNode();
        trHelper(0);
        long long ans = 0;
        int px = 0;
        for (int i = 0; i < N; i++){
            px ^= A[i];
            ans += query(px, K);
            trHelper(px);
        }
        printf("%lld\n", ans);
    }
    return 0;
}
