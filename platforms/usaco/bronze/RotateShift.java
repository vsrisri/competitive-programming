import java.util.*;
import java.io.*;

public class RotateShift {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(reader.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int t = Integer.parseInt(st.nextToken());
        int[] arr = new int[1 + k];
        st = new StringTokenizer(reader.readLine());

        for (int idx = 0; idx < k; idx++) {
            arr[idx] = Integer.parseInt(st.nextToken());
        }
        arr[k] = n;
        int[] outArr = new int[n];

        for (int idx = 0; idx < k; idx++) {
            for (int cow = arr[idx]; cow < arr[idx + 1]; cow++) {
                int mins = t - (cow - arr[idx] + 1);
                int numShifts = 1 + mins / (arr[idx + 1] - arr[idx]);
                int pos = (cow  + (numShifts) * (arr[idx + 1] - arr[idx])) % n;
                if (mins < 0) {
                    pos = cow;
                }

                outArr[pos] = cow;
            }
        }

        for (int idx = 0; idx < n - 1; idx++) {
            System.out.print(outArr[idx] + " ");
        }

        System.out.print(outArr[n - 1]);
        System.out.println();
        reader.close();
    }
}
