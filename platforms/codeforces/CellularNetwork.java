import java.util.*;
import java.io.*;

public class CellularNetwork {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[] cities = new int[n];
        int[] cells = new int[m];
        sc.nextLine();

        for (int i = 0; i < n; i++) {
            cities[i] = sc.nextInt();
        }

        for (int i = 0; i < m; i++) {
            cells[i] = sc.nextInt();
        }

        double r = 0;

        for (int i = 0; i < n; i++) {
            double curr = cities[i];
            double left = -1 * 10e9;
            double right = 10e9;
            for (int j = 0; j < m; j++) {
                if (cells[j] < curr) {
                    if (Math.abs(curr -cells[j]) < left) {
                        left = cells[j];
                    }
                } else if (cells[j] > curr) {
                    if (Math.abs(curr -cells[j]) < right) {
                        right = cells[j];
                    }
                }
            }
            double val = Math.max(Math.abs(curr - left), Math.abs(curr - right));
            if (val > r) {
                r = val;
            }
        }

        System.out.println(r);
    }
}

