import java.util.*;
import java.io.*;

public class PalindromeGame {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(reader.readLine(), " ");
        int t = Integer.parseInt(st.nextToken());
        for (int idx = 0; idx < t; idx++) {
            st = new StringTokenizer(reader.readLine(), " ");
            int s = Integer.parseInt(st.nextToken());
            if (s % 10 == 0) {
                System.out.println("E");
            } else {
                System.out.println("B");
            }
        }
        reader.close();
    }

}
