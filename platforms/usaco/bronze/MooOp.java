import java.util.*;
import java.io.*;

public class P3 {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        for (int t = 0; t < n; t++) {
            String str = reader.readLine();
            LinkedList<Character> list = new LinkedList<Character>();
            for (int idx = 0; idx < str.length(); idx++) {
                list.add(str.charAt(idx));
            }
            ArrayList<Integer> changeFirst = new ArrayList<Integer>();
            ArrayList<Integer> changeLast = new ArrayList<Integer>();
            ArrayList<Integer> changeBoth = new ArrayList<Integer>();
            ArrayList<Integer> changeNone = new ArrayList<Integer>();
            boolean doesWork = false;

            for (int idx = 0; idx <= str.length() - 3; idx++) {
                if (list.get(idx) == 'O' && list.get(idx + 1) == 'O' && list.get(idx + 2) == 'O') {
                    changeFirst.add(idx);
                    doesWork = true;
                }
                if (list.get(idx) == 'M' && list.get(idx + 1) == 'O' && list.get(idx + 2) == 'M') {
                    changeLast.add(idx);
                    doesWork = true;
                }
                if (list.get(idx) == 'O' && list.get(idx + 1) == 'O' && list.get(idx + 2) == 'M') {
                    changeBoth.add(idx);
                    doesWork = true;
                }
                if (list.get(idx) == 'M' && list.get(idx + 1) == 'O' && list.get(idx + 2) == 'O') {
                    changeNone.add(idx);
                    doesWork = true;
                }
            }

            if (!doesWork) {
                System.out.println("-1");
            } else {
                System.out.println(helper(changeFirst, changeLast, changeBoth, changeNone, list));
            }
        }
        reader.close();
    }

    public static int helper(ArrayList<Integer> changeFirst, ArrayList<Integer> changeLast, ArrayList<Integer> changeBoth, ArrayList<Integer> changeNone, LinkedList<Character> list) {
        int min = Integer.MAX_VALUE;
        for (int idx = 0; idx < changeFirst.size(); idx++) {
            int curr = changeFirst.get(idx) + (list.size() - (changeFirst.get(idx) + 3)) + 1;
            if (curr < min) {
                min = curr;
            }
        }

        for (int idx = 0; idx < changeLast.size(); idx++) {
            int curr = changeLast.get(idx) + (list.size() - (changeLast.get(idx) + 3)) + 1;
            if (curr < min) {
                min = curr;
            }
        }

        for (int idx = 0; idx < changeBoth.size(); idx++) {
            int curr = changeBoth.get(idx) + (list.size() - (changeBoth.get(idx) + 3)) + 2;
            if (curr < min) {
                min = curr;
            }
        }

        for (int idx = 0; idx < changeNone.size(); idx++) {
            int curr = changeNone.get(idx) + (list.size() - (changeNone.get(idx) + 3));
            if (curr < min) {
                min = curr;
            }
        }
        return min;
    }
} 
