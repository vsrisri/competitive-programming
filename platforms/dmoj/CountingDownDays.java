import java.util.*;
import java.io.*;

public class CountingDownDays {
    public static final int MOD = 998244353;
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(reader.readLine());
        for (int tc = 0; tc < t; tc++) {
            int n = Integer.parseInt(reader.readLine());
            int[] arr = new int[n];
            StringTokenizer st = new StringTokenizer(reader.readLine(), " ");
            int numZeros = 0;
            for (int idx = 0; idx < n; idx++) {
                arr[idx] = Integer.parseInt(st.nextToken());
                if (arr[idx] == 0) {
                    numZeros++;
                }
            }

            if (numZeros == n || (n == 1)) {
                System.out.println((arr[0] + MOD) % MOD);
            } else {
                long ans = helper(arr, n);
                if (ans < 0) {
                    System.out.println((ans + 1 + MOD) % MOD);
                } else {
                    System.out.println(powerOfTwo(ans) % MOD);
                }
            }
        }

        reader.close();
    }

    public static long helper(int[] arr, int n) {
        int numNeg = 0;
        boolean hasZero = false;
        long pow = 0;
        for (int idx = 0; idx < n; idx++) {
            if (arr[idx] < 0) {
                numNeg++;
            }
            if (arr[idx] == 0) {
                hasZero = true;
            }

            pow+= Math.abs(arr[idx] / 2);
        }

        if (!hasZero) {
            if (numNeg % 2 == 0 && n > 0) {
                return (pow);
            } else if (n == 0) {
                return -1;
            }  else if (numNeg % 2 == 1 && n > 0) {
                return (helper2(arr, n));
            }
        } else {
            return (helper3(arr, n));
        }
        return -1;
    }

    public static long helper2(int[] arr, int n) {
        if (arr.length == 0) {
            return -1;
        }

        if (arr.length == 1) {
            return arr[0] - 1;
        }

        int fromStart = 0; 
        int fromEnd = n - 1;
        long startPow = 0; 
        long endPow = 0;

        for (int idx = 0; idx < n; idx++) {
            if (arr[idx] < 0) {
                fromStart = idx;
                break;
            }
        }

        for (int idx = fromStart + 1; idx < n; idx++) {
            startPow+= Math.abs(arr[idx] / 2);
        }

        for (int idx = n - 1; idx >= 0; idx--) {
            if (arr[idx] < 0) {
                fromEnd = idx;
                break;
            }
        }

        for (int idx = fromEnd - 1; idx >= 0; idx--) {
            endPow+= Math.abs(arr[idx] / 2);
        }

        return Math.max(startPow, endPow);
    }

    public static long helper3(int[] arr, int n) {
        ArrayList<Integer> zeroIdxArr = new ArrayList<Integer>();
        for (int idx = 0; idx < n; idx++) {
            if (arr[idx] == 0) {
                zeroIdxArr.add(idx);
            }
        }

        if (zeroIdxArr.size() == arr.length) {
            return 0;
        }

        ArrayList<Long> intervalPows = new ArrayList<Long>();
        for (int idx = 1; idx < zeroIdxArr.size(); idx++) {
            int start = zeroIdxArr.get(idx - 1) + 1; 
            int end = zeroIdxArr.get(idx) - 1;
            int[] subStr = new int[end - start + 1];
            int counter = 0;

            for (int i = start; i <= end; i++) {
                subStr[counter] = arr[i];
                counter++;
            }

            intervalPows.add(helper(subStr, subStr.length));
        }

        if (zeroIdxArr.get(zeroIdxArr.size() - 1) < n - 1) {
            int start = zeroIdxArr.get(zeroIdxArr.size() - 1) + 1; int end = n - 1;
            int[] subStr = new int[end - start + 1];
            int counter = 0;
            for (int i = start; i <= end; i++) {
                subStr[counter] = arr[i];
                counter++;
            }
            intervalPows.add(helper(subStr, subStr.length));
        }

        if (zeroIdxArr.get(0) > 0) {
            int start = 0; int end = zeroIdxArr.get(0) - 1;
            int[] subStr = new int[end - start + 1];
            int counter = 0;
            for (int i = start; i <= end; i++) {
                subStr[counter] = arr[i];
                counter++;
            }
            intervalPows.add(helper(subStr, subStr.length));
        }

        Collections.sort(intervalPows);
        return Math.max((-1), intervalPows.get(intervalPows.size() - 1));
    }

    public static long powerOfTwo(long pow) {
        long prod = 1;
        for (long idx = 0; idx < pow; idx++) {
            if (prod > Long.MAX_VALUE / 2) {
                prod%= MOD;
                prod = prod * 2 % MOD;
            } else {
                prod = prod * 2 % MOD;
            }
        }
        return prod;
    }
}
