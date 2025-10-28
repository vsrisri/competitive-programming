import java.io.*;
import java.util.*;

public class BITMAP {
   public static void main (String[] args) throws Exception {
       BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
       StringTokenizer st = new StringTokenizer(reader.readLine());
       int t = Integer.parseInt(st.nextToken());
       for (int tc = 0; tc < t; tc++) {
           StringBuilder string = new StringBuilder();
           int[] xNums = {1, -1, 0, 0};
           int[] yNums = {0, 0, 1, -1};
           st = new StringTokenizer(reader.readLine());
           int r = Integer.parseInt(st.nextToken());
           int c = Integer.parseInt(st.nextToken());
           char[][] grid = new char[r + 2][c + 2];
           int[][] ans = new int[r + 2][c + 2];

           for (int idx = 0; idx < c + 2; idx++) {
               grid[0][idx] = '#';
               grid[r + 1][idx] = '#';
           }

           LinkedList<Bit> queue = new LinkedList<Bit>();
           boolean[][] visited = new boolean[r + 2][c + 2];
           Bit curr = null;

           for (int idx = 1; idx < r + 1; idx++){
               String line = reader.readLine();
               grid[idx][0] = '#';
               for (int idx2 = 1; idx2 < c + 1; idx2++) {
                   grid[idx][idx2] = line.charAt(idx2 - 1);
                   ans[idx][idx2] = 500;
                   if (grid[idx][idx2] == '1') {
                       queue.add(new Bit(idx, idx2, 0));
                       visited[idx][idx2] = true;
                    }
               }
            grid[idx][c + 1] = '#';
           }
         
         while (!queue.isEmpty()) {
            curr = queue.remove();
            ans[curr.x][curr.y] = Math.min(curr.dist, ans[curr.x][curr.y]);
            for (int i = 0; i < 4; i++){
               int x = curr.x + xNums[i];
               int y = curr.y + yNums[i];
               if (!visited[x][y] && grid[x][y] != '#'){
                  queue.add(new Bit(x, y, curr.dist + 1));
                  visited[x][y] = true;
               }
            }
         }

         for (int rIdx = 1; rIdx < r + 1; rIdx++){
            for (int cIdx = 1; cIdx < c + 1; cIdx++){
               if (cIdx == c){
                  string.append(ans[rIdx][cIdx] + "\n");
               } else {
                  string.append(ans[rIdx][cIdx] + " ");
               }
            }
         }
         System.out.println(string);
      }
   }

   public static class Bit {
      public int x;
      public int y;
      public int dist;

      public Bit(int x, int y, int dist) {
         this.x = x;
         this.y = y;
         this.dist = dist;
      }
   }
}
