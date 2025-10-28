import java.util.*;
import java.io.*;

public class CHOCOLA {
    public static void main(String[] args) throws Exception {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            int t = Integer.parseInt(reader.readLine());
            for (int tc = 0; tc < t; tc++) {
                reader.readLine();
                StringTokenizer st = new StringTokenizer(reader.readLine(), " ");
                int m = Integer.parseInt(st.nextToken());
                int n = Integer.parseInt(st.nextToken());
                int[] h = new int[n - 1];
                int[] v = new int[m - 1];
                for (int idx = 0; idx < v.length; idx++) {
                    v[idx] = Integer.parseInt(reader.readLine());
                }

                for (int idx = 0; idx < h.length; idx++) {
                    h[idx] = Integer.parseInt(reader.readLine());
                }

                Arrays.sort(h);
                Arrays.sort(v);
                int[] hCost = new int[n - 1];
                int[] vCost = new int[m - 1];
                for (int idx = 0; idx < hCost.length; idx++) {
                    hCost[idx] = h[hCost.length - 1 - idx];
                }
                for (int idx = 0; idx < vCost.length; idx++) {
                    vCost[idx] = v[vCost.length - 1 - idx];
                }

                int i = 0; int j = 0; int cost = 0;
                int numH = 1; int numV = 1;

                while (i < n - 1 && j < m - 1) {
                    if (hCost[i] >= vCost[j]) {
                        cost+= hCost[i] * numV;
                        numH++;
                        i++;
                    } else {
                        cost+= vCost[j] * numH;
                        numV++;
                        j++;
                    }
                }

                while (i < n - 1) {
                    cost+= hCost[i] * numV;
                    numH++;
                    i++;
                }

                while (j < m - 1) {
                    cost+= vCost[j] * numH;
                    numV++;
                    j++;
                }

                System.out.println(cost);
            }
            reader.close();
        } catch (Exception e) {
            return;
        }
    }
}
