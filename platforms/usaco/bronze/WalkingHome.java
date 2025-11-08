import java.io.*;
import java.util.*;
public class WalkingHome {
    public static void main(String[] args) throws Exception {
        Scanner stdin = new Scanner(System.in);
        int tc = stdin.nextInt();
        for (int testCase = 0; testCase < tc; testCase++) {
            int n = stdin.nextInt();
            int k = stdin.nextInt();
            char[][] arr = new char[n][n];
            for (int i = 0; i < n; i++) {
                String str = stdin.next();
                for (int j = 0; j < n; j++) {
                    arr[i][j] = str.charAt(j);
                }
            }
            int ans = 0;
            if (k >= 1) {
                int topRight = 1;
                int botLeft = 1;
                for (int idx = 0; idx < n; idx++) {
                    if (arr[0][idx] == 'H' || arr[idx][n - 1] == 'H') {
                        topRight = 0;
                    }
                    if (arr[idx][0] == 'H' || arr[n - 1][idx] == 'H') {
                        topRight = 0;
                    }
                    ans+= topRight + botLeft;
                }
            } 
            if (k >= 2) {
                for (int idx = 1; idx < n - 1; idx++) {
                    int isPoss = 1;
                    for (int i = 0; i < n; i++) {
                        if (arr[idx][i] == 'H') {
                            isPoss = 0;
                        }
                        if (i < idx && arr[0][i] == 'H') {
                            isPoss = 0;
                        }
                        if (i > idx && arr[n - 1][i] == 'H') {
                            isPoss = 0;
                        }
                    }
                    ans+= isPoss;
                }

                for (int idx = 1; idx < n - 1; idx++) {
                    int isPoss = 1;
                    for (int i = 0; i < n; i++) {
                        if (arr[i][idx] == 'H') {
                            isPoss = 0;
                        }
                        if (idx < i && arr[i][0] == 'H') {
                            isPoss = 0;
                        }
                        if (idx > i && arr[i][n - 1] == 'H') {
                            isPoss = 0;
                        }
                    }
                    ans+= isPoss;
                }
            }
            if (k >= 3) {
                for (int idx = 1; idx < n - 1; idx++) {
                    for (int idx2 = 1; idx2 < n - 1; idx2++) {
                        int num = 1;
                        if (arr[idx][idx2] == 'H') {
                            num = 0;
                        }
                        for (int i = 0; i < n; i++) {
                            if ((i <= idx && arr[i][idx2] == 'H') || (i >= idx && arr[i][n - 1] == 'H') || (i <= idx2 && arr[0][i] == 'H' || i >= idx2 && arr[idx][i] == 'H')) {
                                num = 0;
                            }
                        }
                        ans+= num;

                        num = 1;
                        if (arr[idx][idx2] == 'H') {
                            num = 0;
                        }
                        for (int i = 0; i < n; i++) {
                            if ((i <= idx && arr[i][0] == 'H') || (i >= idx && arr[i][idx2] == 'H') || (i <= idx2 && arr[idx][i] == 'H' || i >= idx2 && arr[n - 1][i] == 'H')) {
                                num = 0;
                            }
                        }
                    }
                }
            }
            System.out.println(ans);
        }

    }

}
