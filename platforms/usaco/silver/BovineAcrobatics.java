import java.io.*;
import java.util.*;
 
public class BovineAcrobatics {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(tokenizer.nextToken());
        int m = Integer.parseInt(tokenizer.nextToken());
        int k = Integer.parseInt(tokenizer.nextToken());
        CowGroup[] cowGroups = new CowGroup[n];
        for (int j = 0; j < n; j++) {
            tokenizer = new StringTokenizer(in.readLine());
            int weight = Integer.parseInt(tokenizer.nextToken());
            int amt = Integer.parseInt(tokenizer.nextToken());
            cowGroups[j] = new CowGroup(weight, amt);
        }
        Arrays.sort(cowGroups, Comparator.comparingInt(group -> group.weight));
 
        TreeSet<CowGroup> treeSet = new TreeSet<>(Comparator.comparingInt(group -> group.weight));
        int towers = 0;
        long answer = 0;
        for (CowGroup group : cowGroups) {
            int disp = group.amt;
            while (disp > 0) {
                CowGroup floor = treeSet.floor(new CowGroup(group.weight - k, 0));
                if (floor == null) {
                    break;
                }
                treeSet.remove(floor);
                if (floor.amt <= disp) {
                    disp -= floor.amt;
                } else {
                    treeSet.add(new CowGroup(floor.weight, floor.amt - disp));
                    disp = 0;
                }
            }
            towers -= group.amt - disp;
            int canAdd = Math.min(group.amt, m - towers);
            towers += canAdd;
            answer += canAdd;
            treeSet.add(new CowGroup(group.weight, canAdd));
        }
        System.out.println(answer);
    }
 
    static class CowGroup {
        final int weight;
        final int amt;
 
        CowGroup(int weight, int amt) {
            this.weight = weight;
            this.amt = amt;
        }
    }
}
