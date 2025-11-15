import java.io.*;
import java.util.*;

public class ARRANGE {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int t = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < t; tc++) {
            int n = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            int[] amp = new int[n];
            for (int i = 0; i < n; i++) {
                amp[i] = Integer.parseInt(st.nextToken());
            }
            
            Arrays.sort(amp);
            for (int i = 0; i < n / 2; i++) {
                int temp = amp[i];
                amp[i] = amp[n - i - 1];
                amp[n - i - 1] = temp;
            }
            
            for (int i = 0; i < n; i++) {
                System.out.print(amp[i] + " ");
            }
            System.out.println();
        }
        br.close();
    }
}

