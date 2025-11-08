import java.io.*;
import java.util.*;

public class FollowingDirections {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int size = Integer.parseInt(reader.readLine());
        char[][] dirArr = new char[size][];
        int[][] gridWeights = new int[size + 1][size + 1];
        
        for (int i = 0; i < size; i++) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            dirArr[i] = tokenizer.nextToken().toCharArray();
            gridWeights[i][size] = Integer.parseInt(tokenizer.nextToken());
        }
        
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        for (int j = 0; j < size; j++) {
            gridWeights[size][j] = Integer.parseInt(tokenizer.nextToken());
        }

        int[][] pathCount = new int[size + 1][size + 1];
        int ans = findInitialSum(size, gridWeights, pathCount, dirArr);

        StringBuilder output = new StringBuilder();
        output.append(ans).append('\n');
        
        for (int q = Integer.parseInt(reader.readLine()); q > 0; q--) {
            tokenizer = new StringTokenizer(reader.readLine());
            int row = Integer.parseInt(tokenizer.nextToken()) - 1;
            int col = Integer.parseInt(tokenizer.nextToken()) - 1;

            ans = helper(row, col, size, dirArr, gridWeights, pathCount, ans);
            output.append(ans).append('\n');
        }
        
        System.out.print(output);
    }
