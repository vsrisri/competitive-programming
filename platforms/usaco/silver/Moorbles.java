import java.io.*;
import java.util.*;

public class Moorbles {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(reader.readLine().trim());
        StringBuilder sb = new StringBuilder();

        for (int t = 0; t < tc; t++) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            int numMarbStart = Integer.parseInt(tokenizer.nextToken());
            int turns = Integer.parseInt(tokenizer.nextToken());
            int numPossMoves = Integer.parseInt(tokenizer.nextToken());
            int[][] moveEffects = inp(reader, turns, numPossMoves);
            int[] sBounds = findBounds(turns, moveEffects);
            String result = helper(numMarbStart, turns, moveEffects, sBounds);
            sb.append(result).append("\n");
        }

        System.out.print(sb);
    }

    public static int[][] inp(BufferedReader reader, int turns, int oTurns) throws Exception {
        int[][] moveEffects = new int[turns][2]; 
        for (int turn = 0; turn < turns; turn++) {
            Arrays.fill(moveEffects[turn], 10000); 
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            for (int option = 0; option < oTurns; option++) {
                int marbleCount = Integer.parseInt(tokenizer.nextToken());
                int parity = marbleCount % 2; 
                moveEffects[turn][parity] = Math.min(moveEffects[turn][parity], marbleCount);
                int temp =  Math.min(moveEffects[turn][(parity + 1) % 2], marbleCount * -1);
                moveEffects[turn][(parity + 1) % 2] = temp; 
            }
        }
        return moveEffects;
    }

    public static int[] findBounds(int turns, int[][] moveEffects) {
        int[] sBounds = new int[turns + 1]; 
        for (int turn = turns - 1; turn >= 0; turn--) {
            int maxMoveEf = Math.max(moveEffects[turn][0], moveEffects[turn][1]);
            sBounds[turn] = Math.max(0, sBounds[turn + 1] - maxMoveEf);
        }
        return sBounds;
    }

    public static String helper(int marbles, int turns, int[][] moveEffects, int[] sBounds) {
        if (marbles <= sBounds[0]) {
            return "-1";
        }

        StringBuilder sb = new StringBuilder();
        for (int turn = 0; turn < turns; turn++) {
            if (marbles + moveEffects[turn][0] > sBounds[turn + 1]) {
                sb.append("Even");
                marbles += moveEffects[turn][0];
            } else {
                sb.append("Odd");
                marbles += moveEffects[turn][1];
            }
            if (turn < turns - 1) {
                sb.append(" ");
            }
        }
        return sb.toString();
    }
}

