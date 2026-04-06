#include <bits/stdc++.h>
using namespace std;

bool matchPart(char* pat, int patLen, bool star, const string& s) {
    int sLen = s.size();
    if (star) {
        if (sLen < patLen) {
            return false;
        }
    } else {
        if (sLen != patLen) {
            return false;
        }
    }
    for (int i = 0; i < patLen; i++) {
        if (pat[i] != '?' && pat[i] != s[i]) {
            return false;
        }
    }
    return true;
}

string helper(int nameLen, bool nameStar, bool hasDot, bool extStar, int extLen, vector<string>& delNames, vector<string>& delExts, vector<bool>& delHasExt, vector<string>& keepNames, vector<string>& keepExts, vector<bool>& keepHasExt) {
    char namePat[8] = {};
    char extPat[3] = {};
    for (int i = 0; i < (int) delNames.size(); i++) {
        const string& dn = delNames[i];
        const string& de = delExts[i];
        bool dHasExt = delHasExt[i];
        if (nameStar) {
            if ((int) dn.size() < nameLen) {
                return "";
            }
        } else {
            if ((int) dn.size() != nameLen) {
                return "";
            }
        }

        if (!hasDot) {
            if (dHasExt) {
                return "";
            }
        } else if (extStar) {
            if (!dHasExt && extLen > 0) {
                return "";
            }
            if (dHasExt && (int) de.size() < extLen) {
                return "";
            }
        } else {
            if (extLen == 0) {
                if (dHasExt && (int) de.size() != 0) {
                    return "";
                }
            } else {
                if (!dHasExt || (int) de.size() != extLen) {
                    return "";
                }
            }
        }

        for (int j = 0; j < nameLen; j++) {
            char dc = dn[j];
            if (namePat[j] == 0) {
                namePat[j] = dc;
            } else if (namePat[j] != dc) {
                namePat[j] = '?';
            }
        }

        for (int j = 0; j < extLen; j++) {
            char dc = de[j];
            if (extPat[j] == 0) {
                extPat[j] = dc;
            } else if (extPat[j] != dc) {
                extPat[j] = '?';
            }
        }
    }

    for (int j = 0; j < nameLen; j++) {
        if (namePat[j] == 0) {
            namePat[j] = '?';
        }
    }
    for (int j = 0; j < extLen; j++) {
        if (extPat[j] == 0) {
            extPat[j] = '?';
        }
    }

    for (int i = 0; i < (int) keepNames.size(); i++) {
        const string& kn = keepNames[i];
        const string& ke = keepExts[i];
        bool kHasExt = keepHasExt[i];
        if (!matchPart(namePat, nameLen, nameStar, kn)) {
            continue;
        }

        if (!hasDot) {
            if (!kHasExt || ke.empty()) {
                return "";
            }
            continue;
        } else if (extStar) {
            if (extLen == 0 && !kHasExt) {
                return "";
            }
            if (!kHasExt) {
                continue;
            }
            if ((int) ke.size() < extLen) {
                continue;
            }
            if (!matchPart(extPat, extLen, true, ke)) {
                continue;
            }
            return "";
        } else {
            if (extLen == 0) {
                if (!kHasExt || ke.empty()) {
                    return "";
                }
                continue;
            } else {
                if (!kHasExt || (int) ke.size() != extLen) {
                    continue;
                }
                if (!matchPart(extPat, extLen, false, ke)) {
                    continue;
                }
                return "";
            }
        }
    }

    string res;
    for (int j = 0; j < nameLen; j++) {
        res += namePat[j];
    }
    if (nameStar) {
        res += '*';
    }
    if (hasDot) {
        res += '.';
        for (int j = 0; j < extLen; j++) {
            res += extPat[j];
        }
        if (extStar) {
            res += '*';
        }
    }
    return res;
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    int M;
    cin >> M;
    string tmp;
    getline(cin, tmp);
    getline(cin, tmp);
    string out;
    for (int ds = 0; ds < M; ds++) {
        vector<string> delNames, delExts, keepNames, keepExts;
        vector<bool> delHasExt, keepHasExt;
        string line;
        while (getline(cin, line)) {
            while (!line.empty() && (line.back() == '\r' || line.back() == ' ')) {
                line.pop_back();
            }
            if (line.empty()) {
                break;
            }
            char sign = line[0];
            string fname = line.substr(1);
            int dot = fname.find('.');
            string nm = (dot == -1) ? fname : fname.substr(0, dot);
            string ex = (dot == -1) ? ""    : fname.substr(dot + 1);
            bool hasExt = (dot != -1);
            if (sign == '-') {
                delNames.push_back(nm);
                delExts.push_back(ex);
                delHasExt.push_back(hasExt);
            } else {
                keepNames.push_back(nm);
                keepExts.push_back(ex);
                keepHasExt.push_back(hasExt);
            }
        }

        string ans = "";
        for (int nameLen = 1; nameLen <= 8 && ans.empty(); nameLen++) {
            for (int nameStar = 0; nameStar <= 1 && ans.empty(); nameStar++) {
                for (int extMode = 0; extMode <= 8 && ans.empty(); extMode++) {
                    bool hasDot;
                    bool extStar;
                    int extLen;
                    if (extMode == 0) {
                        hasDot = false; extStar = false; extLen = 0;
                    } else if (extMode == 1) {
                        hasDot = true; extStar = true; extLen = 0;
                    } else if (extMode == 2) {
                        hasDot = true; extStar = false; extLen = 0;
                    } else if (extMode <= 5) {
                        hasDot = true; extStar = false; extLen = extMode - 2;
                    } else {
                        hasDot = true; extStar = true; extLen = extMode - 5;
                    }

                    string cand = helper(nameLen, nameStar == 1, hasDot, extStar, extLen, delNames, delExts, delHasExt, keepNames, keepExts, keepHasExt);
                    if (!cand.empty()) {
                        ans = cand;
                    }
                }
            }
        }

        if (ds > 0) {
            out += "\n";
        }
        out += ans.empty() ? "IMPOSSIBLE\n" : "DEL " + ans + "\n";
    }
    cout << out;
    return 0;
}
