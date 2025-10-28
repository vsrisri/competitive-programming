import java.util.*;
import java.io.*;

public class EGYPIZZA {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        int a = 0;
        int b = 0;
        int c = 0;

        for (int idx = 0; idx < n; idx++) {
            String line = reader.readLine();
            int num = Character.getNumericValue(line.charAt(0));
            int denom = Character.getNumericValue(line.charAt(2));
            if (num == 1 && denom == 2) {
                a++;
            } else if (num == 3 && denom == 4) {
                b++;
            } else if (num == 1 && denom == 4) {
                c++;
            }
        }

        int ans = 1;
        ans+= a / 2 + a % 2 + b;
        if (c > b) {
            if (a % 2 == 1) {
                if ((c - b) > 2) {
                    int num = c - b - 2;
                    ans += num / 4;
                    num %= 4;
                    ans += num;
                }
            } else {
                ans += (c - b) / 4;
                if ((c - b) % 4 > 0) {
                    ans++;
                }
            }
        }

        System.out.println(ans);
        reader.close();
    }
}

