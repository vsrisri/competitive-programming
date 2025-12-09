import java.io.*;
import java.util.*;

public class FAVDICE {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < t; tc++) {
            int n = Integer.parseInt(br.readLine());
            double sum = 0;
            for (int i = 1; i <= n; i++) {
                sum += 1.0 / i;
            }
            double eVal = n * sum;
            System.out.printf("%.2f\n", eVal);
        }
        br.close();
    }
}

