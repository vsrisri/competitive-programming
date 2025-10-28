import java.util.*;
import java.io.*;

public class DIVSTR {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(reader.readLine());
        for (int t = 0; t < tc; t++) {
            String line1 = reader.readLine();
            String line2 = reader.readLine();
            System.out.println(helper(line1, line2));
        }
        reader.close();
    }

    public static int helper(String line1, String line2) {
        int idx = 0;
        int numTimes = 0;
        if (line1.length() < line2.length()) {
            return line1.length();
        }
        for (int idx2 = 0; idx2 < line1.length(); idx2++) {
            if (line1.charAt(idx2) == line2.charAt(idx)) {
                idx++;
                if (idx == line2.length()) {
                    idx = 0;
                    numTimes++;
                }
            }
        }
        return line1.length() - numTimes * line2.length();
    }
}
