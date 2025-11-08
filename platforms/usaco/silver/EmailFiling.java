// USACO 2022 Feb Silver
import java.util.*;
import java.io.*;

public class EmailFiling {
    public static void main(String[] args) throws Exception {
        Scanner stdin = new Scanner(System.in);
        int tc = stdin.nextInt();
        for (int t = 0; t < tc; t++) {
            int m = stdin.nextInt();
            int n = stdin.nextInt();
            int k = stdin.nextInt();
            int[] folders = new int[n];
            int[] numArr = new int[m];
            for (int idx = 0; idx < n; idx++) {
                folders[idx] = stdin.nextInt() - 1;
                numArr[folders[idx]]++;
            }

            TreeSet<Integer> msgs = new TreeSet<Integer>();
            if (helper(folders, numArr, msgs, m, n, k) == true) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
    }

    public static boolean helper(int[] folders, int[] numArr, TreeSet<Integer> msgs, int m, int n, int k) {
        TreeSet<Integer> notSeen = new TreeSet<Integer>();
        for (int idx = 1; idx <= n; idx++) {
            notSeen.add(idx);
        }
        int low = 1;
        int high = k;
        for (int idx = 0; idx < k; idx++) {
            msgs.add(folders[idx] * (n + 1) + (idx + 1));
        }
        for (int idx = 0 ; idx <= m - k; idx++) {
            while (true) {
                boolean b = false;
                if (idx < m - k && numArr[idx] == 0) {
                    break;
                }
                Integer nextMsg = msgs.higher(idx * (n + 1));
                if (nextMsg == null) {
                    if (notSeen.size() == 0) {
                        return true;
                    }
                    return false;
                }
                int folder = nextMsg / (n + 1);
                int num = nextMsg % (n + 1);
                if (folder < idx + k) {
                    b = true;
                    msgs.remove(nextMsg);
                    numArr[folder]--;
                    notSeen.remove(num);

                    if (notSeen.size() == 0) {
                        return true;
                    }
                    if (notSeen.higher(high) != null) {
                        if (num == low) {
                            low = notSeen.higher(low);
                        }
                        high = notSeen.higher(high);
                        msgs.add(folders[high - 1] * (n + 1) + high);
                    } else {
                        if (num == high) {
                            high = notSeen.lower(high);
                        }
                        if (notSeen.lower(low) != null) {
                            low = notSeen.lower(low);
                            msgs.add(folders[low - 1] * (n + 1) + low);
                        }
                    }
                } else {
                    if (notSeen.higher(high) != null) {
                        b = true;
                        int lowNum = folders[low - 1] * (n + 1) + low;
                        low = notSeen.higher(low);
                        msgs.remove(lowNum);
                        high = notSeen.higher(high);
                        msgs.add(folders[high - 1] * (n + 1) + high);
                    }
                }
                if (b == false) {
                    break;
                }
            }
        }
        if (notSeen.size() == 0) {
            return true;
        }
        return false;
    }

}

