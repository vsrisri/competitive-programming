import java.io.*;
import java.util.*;

public class TestTubes {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(reader.readLine().trim());

        while (tc-- > 0) {
            StringTokenizer st = new StringTokenizer(reader.readLine());
            int n = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());
            List<List<Integer>> tubeArr = new ArrayList<>();
            tubeArr.add(updArr(reader.readLine()));
            tubeArr.add(updArr(reader.readLine()));
            tubeArr.add(new ArrayList<>());
            StringBuilder sb = helper(n, p, tubeArr);
            System.out.print(sb.toString());
        }
        reader.close();
    }

    static StringBuilder helper(int n, int p, List<List<Integer>> tubeArr) {
        StringBuilder sb = new StringBuilder();
        if (tubeArr.get(0).get(0).equals(tubeArr.get(1).get(0))) {
            tubeArr.get(0).add(0, tubeArr.get(0).get(0) ^ 3);
        }

        int movesNeeded = tubeArr.get(0).size() + tubeArr.get(1).size() - 2;
        if (movesNeeded > 1) {
            movesNeeded++;
        }

        sb.append(movesNeeded).append("\n");
        if (p == 1) {
            return sb;
        }

        List<int[]> ops = new ArrayList<>();
        balance(tubeArr, ops);
        execOps(tubeArr, ops, sb);
        return sb;
    }

    static List<Integer> updArr(String str) {
        List<Integer> res = new ArrayList<>();
        for (char ch : str.toCharArray()) {
            int num = ch - '0';
            if (res.isEmpty() || !res.get(res.size() - 1).equals(num)) {
                res.add(num);
            }
        }
        return res;
    }

    static void balance(List<List<Integer>> tubeArr, List<int[]> ops) {
        if (tubeArr.get(0).get(tubeArr.get(0).size() - 1).equals(tubeArr.get(1).get(tubeArr.get(1).size() - 1))) {
            int from = tubeArr.get(0).size() > tubeArr.get(1).size() ? 0 : 1;
            move(from, 1 - from, tubeArr, ops);
        }

        for (int i = 0; i < 2; i++) {
            if (tubeArr.get(i).size() > 1) {
                move(i, 2, tubeArr, ops);
                int idx = 0;
                if (tubeArr.get(idx).get(0).equals(tubeArr.get(2).get(0))) idx ^= 1;
                empty(idx, tubeArr, ops);
                idx ^= 1;
                empty(idx, tubeArr, ops);
                move(2, idx, tubeArr, ops);
                break;
            }
        }
    }

    static void move(int src, int dst, List<List<Integer>> tubeArr, List<int[]> ops) {
        ops.add(new int[]{src, dst});
        if (tubeArr.get(dst).isEmpty() || !tubeArr.get(dst).get(tubeArr.get(dst).size() - 1).equals(tubeArr.get(src).get(tubeArr.get(src).size() - 1))) {
            tubeArr.get(dst).add(tubeArr.get(src).get(tubeArr.get(src).size() - 1));
        }
        tubeArr.get(src).remove(tubeArr.get(src).size() - 1);
    }

    static void empty(int src, List<List<Integer>> tubeArr, List<int[]> ops) {
        while (tubeArr.get(src).size() > 1) {
            int dst = tubeArr.get(src).get(tubeArr.get(src).size() - 1).equals(tubeArr.get(2).get(0)) ? 2 : src ^ 1;
            move(src, dst, tubeArr, ops);
        }
    }

    static void execOps(List<List<Integer>> tubeArr, List<int[]> ops, StringBuilder sb) {
        for (int[] op : ops) {
            sb.append(1 + op[0]).append(" ").append(1 + op[1]).append("\n");
        }
    }
}

