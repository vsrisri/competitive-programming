import java.io.*;
import java.util.*;

public class RevisedIOI {
    public static int n;
    public static long bestScore = Long.MIN_VALUE;
    public static int bestKey = 0;
    public static int[] freq = new int[16];
    public static int[] optFreq = new int[16];
    public static String[] ansArr = new String[4];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        for (int i = 0; i < 4; i++) {
            ansArr[i] = br.readLine();
        }

        for (int i = 0; i < n; i++) {
            int state = eArr(i);
            optFreq[state]++;
        }

        for (int mask = 0; mask < (1 << 16); mask++) {
            findFreqs(mask);
            long score = findScore();
            if (score > bestScore) {
                bestScore = score;
                bestKey = mask;
            }
        }

        StringBuilder ansSB = new StringBuilder();
        for (int i = 0; i < n; i++) {
            int encoded = eArr(i);
            if ((bestKey & (1 << encoded)) != 0) {
                ansSB.append('T');
            } else {
                ansSB.append('F');
            }
        }

        System.out.println(ansSB.toString());
    }

    public static int eArr(int idx) {
        int bitmask = 0;
        if (ansArr[0].charAt(idx) == 'T') {
            bitmask |= 1;
        }
        if (ansArr[1].charAt(idx) == 'T') {
            bitmask |= 2;
        }
        if (ansArr[2].charAt(idx) == 'T') {
            bitmask |= 4;
        }
        if (ansArr[3].charAt(idx) == 'T') {
            bitmask |= 8;
        }
        return bitmask;
    }

    public static void findFreqs(int mask) {
        for (int i = 0; i < 16; i++) {
            freq[i] = (mask & (1 << i)) != 0 ? optFreq[i] : 0;
        }
    }

    public static long findScore() {
        long totalScore = 0;
        for (int cont = 0; cont < 4; cont++) {
            long sum = -n;
            for (int i = 0; i < 16; i++) {
                if ((i & (1 << cont)) != 0) {
                    sum += 2 * freq[i];
                } else {
                    sum += 2 * (optFreq[i] - freq[i]);
                }
            }
            totalScore += sum * sum;
        }
        return totalScore;
    }
}

