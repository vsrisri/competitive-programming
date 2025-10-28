import java.util.*;
import java.io.*;

public class CCO10P6 {
    public static void main(String[] args) throws Exception {
        Scanner stdin = new Scanner(System.in);
        String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZ_";
        HashMap<Character, Character> map = new HashMap<Character, Character>();
        for (int idx = 0; idx < 27; idx++) {
            map.put(str.charAt(idx), stdin.next().charAt(0));
        }

        int n = stdin.nextInt();
        String end = stdin.next();
        HashMap<Character, Character> endMap = new HashMap<Character, Character>();
        HashMap<Character, Boolean> hasSeen = new HashMap<Character, Boolean>();
        for (int idx = 0; idx < 27; idx++) {
            hasSeen.put(str.charAt(idx), false);
            endMap.put(str.charAt(idx), ' ');
        }

        for (int idx = 0; idx < 27; idx++) {
            Character start = str.charAt(idx);
            if (hasSeen.get(start)) {
                continue;
            }

            Character temp = map.get(str.charAt(idx));
            int cycle = 1;

            while (temp != start) {
                temp = map.get(temp);
                cycle++;
            }

            hasSeen.put(start, true);
            endMap.put(start, start);
            for (int idx2 = 0; idx2 < n % cycle; idx2++) {
                char c = endMap.get(start);
                endMap.put(start, map.get(c));
            }

            char a = map.get(start);
            char b = map.get(endMap.get(start));
            while (a != start) {
                endMap.put(a, b);
                hasSeen.put(a, true);
                a = map.get(a);
                b = map.get(b);
            }
        }


        for (int idx = 0; idx < end.length(); idx++) {
            System.out.print(endMap.get(end.charAt(idx)));
        }

        stdin.close();
    }
}
