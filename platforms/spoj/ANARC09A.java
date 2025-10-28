import java.io.*;
import java.util.*;

public class ANARC09A{
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        while (true) {
            st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            if (n1 == 0) {
                break;
            }
            int[] a = new int[n1];
            for (int i = 0; i < n1; i++) {
                a[i] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(br.readLine());
            int n2 = Integer.parseInt(st.nextToken());
            int[] b = new int[n2];
            for (int i = 0; i < n2; i++) {
                b[i] = Integer.parseInt(st.nextToken());
            }

            int i = 0, j = 0;
            long s1 = 0;
            long s2 = 0;
            long ans = 0;
            while (i < n1 && j < n2) {
                if (a[i] < b[j]) {
                    s1 += a[i++];
                }
                else if (a[i] > b[j]) {
                    s2 += b[j++];
                }
                else {
                    ans += Math.max(s1, s2) + a[i];
                    s1 = 0;
                    s2 = 0;
                    i++;
                    j++;
                }
            }
            while (i < n1) {
                s1 += a[i++];
            }
            while (j < n2) {
                s2 += b[j++];
            }
            ans += Math.max(s1, s2);
            System.out.println(ans);
        }
        br.close();
    }
}

