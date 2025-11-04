import java.io.*;
import java.util.*;

public class NHAY {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String lenStr;
        boolean first = true;
        while ((lenStr = br.readLine()) != null) {
            lenStr = lenStr.trim();
            if (lenStr.isEmpty()) {
                break;
            }
            int needleLen = Integer.parseInt(lenStr);
            String needle = br.readLine();
            String haystack = br.readLine();
            List<Integer> ans = kmpHelper(haystack, needle);
            if (!first) {
                System.out.println();
            }
            first = false;
            for (int idx : ans) {
                System.out.println(idx);
            }
        }
        br.close();
    }

    public static List<Integer> kmpHelper(String haystack, String needle) {
        List<Integer> posArr = new ArrayList<>();
        int[] lps = helper(needle);
        int i = 0, j = 0;
        while (i < haystack.length()) {
            if (needle.charAt(j) == haystack.charAt(i)) {
                i++;
                j++;
            }
            if (j == needle.length()) {
                posArr.add(i - j);
                j = lps[j - 1];
            } else if (i < haystack.length() && needle.charAt(j) != haystack.charAt(i)) {
                if (j != 0) {
                    j = lps[j - 1];
                } else {
                    i++;
                }
            }
        }
        return posArr;
    }

    public static int[] helper(String needle) {
        int[] lps = new int[needle.length()];
        int length = 0;
        int i = 1;
        while (i < needle.length()) {
            if (needle.charAt(i) == needle.charAt(length)) {
                length++;
                lps[i] = length;
                i++;
            } else {
                if (length != 0) {
                    length = lps[length - 1];
                } else {
                    lps[i] = 0;
                    i++;
                }
            }
        }
        return lps;
    }
}

