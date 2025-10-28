import java.util.*;
import java.io.*;

public class Keks {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(reader.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        String str = reader.readLine();
        ArrayList<Integer> arr = new ArrayList<Integer>();
        for (int idx = 0; idx < str.length(); idx++) {
            arr.add(Integer.parseInt(Character.toString(str.charAt(idx))));
        }

        TreeMap<Integer, ArrayList<Integer>> numMap = new TreeMap<Integer, ArrayList<Integer>>();
        for (int idx = 0; idx <= 9; idx++) {
            numMap.put(idx, new ArrayList<Integer>());
        }

        for (int idx = 0; idx < str.length(); idx++) {
            numMap.get(arr.get(idx)).add(idx);
        }

        int[] startVals = new int[10];
        int num = n - k;
        int currIdx = -1;
        int startIdx = -1;
        ArrayList<String> outStr = new ArrayList<String>();

        while (num > 0) {
            boolean bool = false;
            int curr = -1;
            for (int digit = 9; digit >= 0; digit--) {
                if (numMap.get(digit).size() == startVals[digit]) {
                    continue;
                }

                int idx = numMap.get(digit).get(startVals[digit]);

                if (idx > startIdx && (n - idx - 1 >= num - 1)) {
                    curr = digit;
                    currIdx = idx;
                    break;
                }
            }

            for (int idx = startIdx + 1; idx <= currIdx; idx++) {
                int dig = Integer.parseInt(Character.toString(str.charAt(idx)));
                startVals[dig]+= 1;
            }

            outStr.add(Integer.toString(curr));
            num--;
            startIdx = currIdx;
            currIdx = -1;
        }

        for (String s : outStr) {
            System.out.print(s);
        }

        reader.close();
    }
}

