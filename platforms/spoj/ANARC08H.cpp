#include <bits/stdc++.h>
using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int numC, step;
    while (cin >> numC >> step) {
        if (numC == 0 && step == 0) {
            break;
        }

        int wPos = 1;
        for (int curr = 2; curr <= numC; curr++) {
            wPos = (wPos + step - 1) % curr + 1;
        }

        cout << numC << " " << step << " " << wPos << "\n";
    }

    return 0;
}

