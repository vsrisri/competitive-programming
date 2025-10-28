import java.util.*;
import java.io.*;

public class Dna {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(reader.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());
        String str = reader.readLine();
        char[] s = new char[str.length()];
        for (int idx = 0; idx < s.length; idx++) {
            s[idx] = str.charAt(idx);
        }

        int[] aDP = new int[1000002];
        int[] bDP = new int[1000002];

        for (int idx = 0; idx < n; idx++) {
            if (s[idx] == 'A') {
			    aDP[idx + 1] = aDP[idx];
			    bDP[idx + 1] = 1 + Math.min(aDP[idx], bDP[idx]);
            } else {
			    bDP[idx + 1] = bDP[idx];
			    aDP[idx + 1] = 1 + Math.min(aDP[idx], bDP[idx]);
            }
		}

        System.out.println(aDP[n]);
        reader.close();
    }
}
