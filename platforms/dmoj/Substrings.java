//Incomplete
import java.util.*;
import java.io.*;

public class Substrings {
    public static void main(String[] args) throws Exception {
        Scanner stdin = new Scanner(System.in);
        int tc = stdin.nextInt();
        stdin.nextLine();

        for (int testCase = 0; testCase < tc; testCase++) {
            String str = stdin.nextLine();
            int ans = 2;

            for (int idx = 1; idx < str.length(); idx++) {
                for (int idx2 = 0; idx2 <= str.length() - idx; idx2++) {
                    String substr = str.substring(idx2, idx2 + idx);
                    if (str.indexOf(substr) == idx2) {
                        ans++;
                    }
                }
            }

            System.out.println(ans);
        }
        stdin.close();


    }
}

