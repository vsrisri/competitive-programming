import java.util.*;
import java.io.*;

public class DISUBSTR {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(reader.readLine());
        for (int t = 0; t < tc; t++) {
            char[] str = reader.readLine().toCharArray();
            helper(str);
        }
    }

    public static void helper(char[] str) {
        Integer[] sArr = new Integer[str.length]; 
        for (int idx = 0; idx < str.length; idx++)
            sArr[idx] = idx;

        Arrays.sort(sArr, new Comparator<Integer>() {
            public int compare(Integer a, Integer b) {
                int idx = a;
                int idx2 = b;
                while (idx < str.length && idx2 < str.length) {
                    if (str[idx] != str[idx2]) {
                        return str[idx] - str[idx2];
                    }
                    idx++;
                    idx2++;
                }
                return idx2-idx;
            }
        });

        int[] arr = helper2(str, sArr);
        int ans = 0;
        for (int idx = 0; idx < str.length; idx++)
            ans += (str.length - sArr[idx]) - arr[idx];

        System.out.println(ans);
    }

    public static int[] helper2(char[] str, Integer[] sArr) {
        int[] arr = new int[str.length];
        for (int idx = 1; idx < str.length; idx++) {
            int idx1 = sArr[idx - 1];
            int idx2 = sArr[idx];
            while (idx1 < str.length && idx2 < str.length && str[idx1++] == str[idx2++])
                arr[idx]++;
        }

        return arr;
    }
}

