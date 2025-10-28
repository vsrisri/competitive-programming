import java.util.*;
import java.io.*;

public class Ccc04s3 {
    public static void main(String[] args) throws Exception {
        Scanner stdin = new Scanner(System.in);
        String[][] table1 = new String[10][10];
        boolean[][] isVisited = new boolean[10][10];
        for (int idx = 0; idx < 10; idx++) {
            String str = stdin.nextLine();
            String[] arr = str.split(" ");
            for (int idx2 = 1; idx2 <= 9; idx2++) {
                table1[idx][idx2] = arr[idx2 - 1];
            }
        }
        for (int idx = 0; idx < 10; idx++) {
            for (int idx2 = 1; idx2 <= 9; idx2++) {
                isVisited = new boolean[10][10];
                dfs(idx, idx2, table1, isVisited);
            }
        }

        for (int idx = 0; idx < 10; idx++) {
            for (int idx2 = 1; idx2 <= 9; idx2++) {
                System.out.print(table1[idx][idx2] + " ");
            }
            System.out.println();
        }
    }

    public static int dfs(int row, int col, String[][] table1, boolean[][] isVisited) {
        boolean b = true;
        String str = table1[row][col];
        if (str == null) {
            b = false;
        } else {
            try {
                int num =  Integer.parseInt(str);
            } catch (NumberFormatException e) {
                b = false;
            }
        }

        if (b) {
            return Integer.parseInt(table1[row][col]);
        }
        if (isVisited[row][col] || table1[row][col] == "*") {
            return -1;
        }
        int ans = 0; 
        isVisited[row][col] = true;
        String[] cells = table1[row][col].split("\\+");
        for (int idx = 0; idx < cells.length; idx++) {
            int num = dfs(cells[idx].charAt(0) - 'A', cells[idx].charAt(1) - '0', table1, isVisited);
            if (num == -1) {
                table1[row][col] = "*";
                return -1;
            }
            ans+= num;
        }
        table1[row][col] = String.valueOf(ans);
        return ans;
    }
}

