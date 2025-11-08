import java.util.*;
import java.io.*;

public class Pareidolia {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String inputString = reader.readLine();
        
        long totalMatches = 0;
        long[] subseqCnt = new long[7];
        long remainingChars = inputString.length();

        for (char currentChar : inputString.toCharArray()) {
            subseqCnt[0]++;
            for (int i = 5; i >= 0; i--) {
                if (currentChar == "bessie".charAt(i)) {
                    subseqCnt[i + 1] += subseqCnt[i];
                    subseqCnt[i] = 0;
                }
            }
            totalMatches += subseqCnt[6] * remainingChars;
            subseqCnt[0] += subseqCnt[6];
            subseqCnt[6] = 0;
            remainingChars--;
        }

        System.out.println(totalMatches);
    }
}

