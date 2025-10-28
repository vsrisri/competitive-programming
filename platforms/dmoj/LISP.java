import java.util.*;
import java.io.*;

public class LISP {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(reader.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        StringBuilder str = new StringBuilder(reader.readLine());
        while (reader.ready()) {
            str.append(reader.readLine());
        }

        int[] arr = new int[m];
        int mCount = 0;
        int total = 0;

        for (int idx = 0; idx < str.length(); idx++) {
            if (str.charAt(idx) == '(') {
                total++;
            }

            if (str.charAt(idx) == ')') {
                total--;
            }

            if (str.charAt(idx) == ']') {
                if (mCount < m - 1) {
                    arr[mCount] = 1;
                    total--;
                    mCount++;
                } else {
                    continue;
                }
            }
        }

        int check = 0;
        mCount = 0;
        boolean isPoss = true;

        if (m == 0) {
            if (total == 0) {
                System.out.println(1);
            } else {
                System.out.println(0);
            }
        } else {
            if (total > 0) {
                for (int idx = 0; idx < str.length(); idx++) {
                    if (str.charAt(idx) == '(') {
                        check++;
                    }

                    if (str.charAt(idx) == ')') {
                        check--;
                    }

                    if (str.charAt(idx) == ']') {
                        if (mCount < m - 1) {
                            check--;
                            mCount++;
                        } else {
                            check -= total;
                        }
                    }

                    if (check < 0) {
                        isPoss = false;
                        break;
                    }
                }
                if (check != 0 || !isPoss) {
                    System.out.println(0);
                } else {
                    System.out.println(1);
                    arr[m - 1] = total;
                    for (int idx = 0; idx < m; idx++) {
                        System.out.println(arr[idx]);
                    }
                }
            } else {
                 System.out.println(0);
            }
        }
        reader.close();
     }
}
