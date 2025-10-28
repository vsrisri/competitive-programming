import java.util.*;
import java.io.*;

public class COCI1443{
    public static void main(String[] args) throws Exception {
        Scanner stdin = new Scanner(System.in);
        int n = stdin.nextInt();
        int ans = 0;
        int total = 0;
        for (int idx = 0; idx < n; idx++) {
            int i = stdin.nextInt();
            total+= i;
            ans = Math.max(ans, i);
        }
        ans = Math.max(2 * ans, total);
        System.out.println(ans);
        stdin.close();
    }
}
