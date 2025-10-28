import java.io.*;
import java.util.*;

public class BruceString {
    public static Map<Character, Integer> charIndex = Map.of('b', 0, 'r', 1, 'u', 2, 'c', 3, 'e', 4);
    public static List<Integer>[] permList = new ArrayList[125];
    public static int[] invCount = new int[125];
    public static List<Integer>[] charLoc = new ArrayList[5];
    public static int[] iArr = new int[5];
    public static Integer left(List<Integer> values, int limit) {
        int low = 0;
        int high = values.size() - 1;
        Integer best = null;

        while (low <= high) {
            int mid = (low + high) / 2;
            if (values.get(mid) < limit) {
                best = values.get(mid);
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return best;
    }

    public static Integer right(List<Integer> values, int limit) {
        int low = 0;
        int high = values.size() - 1;
        Integer best = null;

        while (low <= high) {
            int mid = (low + high) / 2;
            if (values.get(mid) > limit) {
                best = values.get(mid);
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return best;
    }

    public static void main(String[] args) throws Exception {
        for (int i = 0; i <= 120; i++) {
            permList[i] = new ArrayList<>();
        }

        List<Integer> num = Arrays.asList(0, 1, 2, 3, 4);
        for (int id = 1; id <= 120; id++) {
            permList[id].addAll(num);
            invCount[id] = count(num);
            helper(num);
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(reader.readLine());
        for (int tc = 0; tc < t; tc++) {
            String line = reader.readLine();
            char[] transArr = line.toCharArray();
            for (int k = 0; k < 5; k++) {
                charLoc[k] = new ArrayList<>();
            }

            for (int i = 0; i < transArr.length; i++) {
                if (charIndex.containsKey(transArr[i])) {
                    int mapped = charIndex.get(transArr[i]);
                    transArr[i] = (char) mapped;
                    charLoc[mapped].add(i);
                } else {
                    transArr[i] = 5;
                }
            }

            long ans = Long.MAX_VALUE;
            for (int center = 0; center < transArr.length; center++) {
                for (int permId = 1; permId <= 120; permId++) {
                    int middleChar = permList[permId].get(2);
                    if (transArr[center] != (char) middleChar) {
                        continue;
                    }

                    iArr[2] = center;
                    Integer left1 = left(charLoc[permList[permId].get(1)], iArr[2]);
                    if (left1 == null) {
                        continue;
                    }
                    iArr[1] = left1;

                    Integer left0 = left(charLoc[permList[permId].get(0)], iArr[1]);
                    if (left0 == null) {
                        continue;
                    }
                    iArr[0] = left0;

                    Integer right3 = right(charLoc[permList[permId].get(3)], iArr[2]);
                    if (right3 == null) {
                        continue;
                    }
                    iArr[3] = right3;

                    Integer right4 = right(charLoc[permList[permId].get(4)], iArr[3]);
                    if (right4 == null) {
                        continue;
                    }

                    iArr[4] = right4;
                    long curr = invCount[permId];
                    curr += Math.abs((iArr[2] - 1) - iArr[1]);
                    curr += Math.abs((iArr[2] - 2) - iArr[0]);
                    curr += Math.abs((iArr[2] + 1) - iArr[3]);
                    curr += Math.abs((iArr[2] + 2) - iArr[4]);
                    if (curr < ans) {
                        ans = curr;
                    }
                }
            }

            System.out.println(ans);
        }
    }

    public static int count(List<Integer> list) {
        int total = 0;
        for (int x = 0; x < list.size(); x++) {
            for (int y = x + 1; y < list.size(); y++) {
                if (list.get(x) > list.get(y)) {
                    total++;
                }
            }
        }
        return total;
    }

    public static void helper(List<Integer> arr) {
        int n = arr.size();
        int i = n - 2;
        while (i >= 0 && arr.get(i) >= arr.get(i + 1)) {
            i--;
        }

        if (i < 0) {
            return;
        }

        int j = n - 1;
        while (arr.get(j) <= arr.get(i)) {
            j--;
        }

        Collections.swap(arr, i, j);
        Collections.reverse(arr.subList(i + 1, n));
    }
}

