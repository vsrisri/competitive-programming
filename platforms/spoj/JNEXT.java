import java.util.*;
import java.io.*;

public class JNEXT {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(reader.readLine(), " ");
        int tc = Integer.parseInt(st.nextToken());

        for (int t = 0; t < tc; t++) {
            st = new StringTokenizer(reader.readLine(), " ");
            StringBuilder str = new StringBuilder();
            int n = Integer.parseInt(st.nextToken());
            int[] arr = new int[n];
            st = new StringTokenizer(reader.readLine(), " ");
            arr[0] = Integer.parseInt(st.nextToken());
            int i = -1;
            for (int idx = 1; idx < n; idx++) {
                arr[idx] = Integer.parseInt(st.nextToken());
                if (arr[idx] > arr[idx - 1]) {
                    i = idx - 1;
                }
            }

            if (i == -1) {
                System.out.println(-1);
            } else {
                for (int idx = n - 1; idx > i; idx--) {
                    if (arr[idx] > arr[i]) {
                        int c = 0;
                        int temp = arr[idx];
                        arr[idx] = arr[i];
                        arr[i] = temp;
                        for (int idx2 = i + 1; idx2 < (n + i + 1) / 2; idx2++) {
                            int temp2 = arr[idx2];
                            arr[idx2] = arr[n - 1 - c];
                            arr[n - 1 - c] = temp2;
                            c++;
                        }
                        break;
                    }
                }
                for (int idx = 0; idx < n; idx++) {
                    str.append(arr[idx]);
                }
                str.append("\n");
                System.out.print(str);
            }

        }
        reader.close();
    }
}

