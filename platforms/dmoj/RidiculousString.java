import java.io.*;
import java.util.*;

public class RidiculousString {
    public static int findNextPos(List<Integer> posArr, int currentPos) {
        int pos = Collections.binarySearch(posArr, currentPos + 1);
        if (pos < 0) {
            pos = -pos - 1;
        }
        return pos < posArr.size() ? posArr.get(pos) : -1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        List<Integer>[] charposArr = new ArrayList[200];
        String[] lengths = br.readLine().split(" ");
        int sLength = Integer.parseInt(lengths[0]);
        int tLength = Integer.parseInt(lengths[1]);
        String s = br.readLine();
        String t = br.readLine();
        int currentPos = -1;
        int cycleCount = 0;

        for (int i = 0; i < 200; i++) {
            charposArr[i] = new ArrayList<>();
        }

        for (int idx = 0; idx < sLength; idx++) {
            charposArr[s.charAt(idx)].add(idx);
        }

        for (char targetChar : t.toCharArray()) {
            List<Integer> posArr = charposArr[targetChar];
            if (posArr.isEmpty()) {
                System.out.println(-1);
                return;
            }

            int nextPos = findNextPos(posArr, currentPos);
            if (nextPos == -1) {
                cycleCount++;
                nextPos = posArr.get(0);
            }

            currentPos = nextPos;
        }

        long totalLen = (long) (cycleCount * sLength + currentPos + 1);
        System.out.println(totalLen);
        br.close();
    }
}

