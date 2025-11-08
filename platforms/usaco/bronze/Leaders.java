import java.util.*;
import java.io.*;

public class P1 {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        String str = reader.readLine();
        StringTokenizer st = new StringTokenizer(reader.readLine(), " ");
        int[] listEndsArr = new int[n + 1];

        for (int idx = 0; idx < n; idx++) {
            int ei = Integer.parseInt(st.nextToken()) - 1;
            listEndsArr[idx] = ei;
        }

        int firstG = -1; int lastG = -1;
        int firstH = -1; int lastH = -1;
        boolean g = false; boolean h = false;
        int ans = 0;

        for (int idx  = 0; idx < n; idx++) {
            if ((g == false) && (str.charAt(idx) == 'G')) {
                firstG = idx;
                g = true;
            }

            if ((h == false) && (str.charAt(idx) == 'H')) {
                firstH = idx;
                h = true;
            }

            if ((g == true) && (str.charAt(idx) == 'G')) {
                lastG = idx;
            }

            if ((h == true) && (str.charAt(idx) == 'H')) {
                lastH = idx;
            }
        }

        // System.out.println("firstG: " + firstG + " lastG: " + lastG + " firstH: " + firstH + " lastH: " + lastH);

        if ((listEndsArr[firstG] >= lastG) || ((firstG <= firstH) && (listEndsArr[firstG] >= firstH))) {
            if ((listEndsArr[firstH] >= lastH) || ((firstH <= firstG) && (listEndsArr[firstH] >= firstG))) {
                ans++;
            }
        }

        if (listEndsArr[firstG] >= lastG) {
            for (int idx  = 0; idx < firstG; idx++) {
                if (idx > firstH) {
                    if ((listEndsArr[idx] >= firstG) && (str.charAt(idx) == 'H')) {
                        ans++;
                    }
                }
            }
        }


        if (listEndsArr[firstH] >= lastH) {
            for (int idx  = 0; idx < firstH; idx++) {
                if (idx > firstG) {
                    if ((listEndsArr[idx] >= firstH) && (str.charAt(idx) == 'G')) {
                        ans++;
                    }
                }
            }
        }

        System.out.println(ans);
        reader.close();
    }
}

