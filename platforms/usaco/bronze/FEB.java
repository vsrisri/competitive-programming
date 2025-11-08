import java.util.*;
import java.io.*;

public class FEB {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        String str = reader.readLine();
        int startIdx = 0;
        int minLevel = 0;
        int maxLevel = 0;

        while (startIdx < n) {
            if (str.charAt(startIdx) == 'F') {
                startIdx++;
                continue;
            }

            int endIdx = startIdx + 1;
            int fCount = 0;

            while (endIdx < n && str.charAt(endIdx) == 'F') {
                endIdx++;
                fCount++;
            }

            if (endIdx == n) {
                break;
            }

            if (str.charAt(startIdx) == str.charAt(endIdx)) {
                if ((fCount + 2) % 2 == 0) {
                    minLevel++;
                }

                maxLevel+= (fCount + 2) - 1;

            } else {
                if ((fCount + 2) % 2 == 1) {
                    minLevel++;
                }

                maxLevel+= (fCount + 2) - 2;
            }

            startIdx = endIdx;
        }

        int startF = 0;
        int endF = 0;

        while (startF < n && str.charAt(startF) == 'F') {
            startF++;
        }

        while (endF < n && str.charAt(n - 1 - endF) == 'F') {
            endF++;
        }

        if (startF == n) {
            minLevel = 0;
            maxLevel = n - 1;
        } else {
            maxLevel+= startF + endF;
        }

        ArrayList<Integer> arr = new ArrayList<Integer>();
        if (startF == 0 && endF == 0) {
            if ((maxLevel - minLevel) % 2 == 0) {
                for (int idx = minLevel; idx <= maxLevel; idx+= 2) {
                    arr.add(idx);
                }
            }
        } else {
            for (int idx = minLevel; idx <= maxLevel; idx++) {
                arr.add(idx);
            }
        }

        System.out.println(arr.size());
        for (int i : arr) {
            System.out.println(i);
        }
        reader.close();
    }
}
