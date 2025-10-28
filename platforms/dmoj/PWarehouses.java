import java.util.*;
import java.io.*;

public class PWarehouses {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(reader.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());
        ArrayList<Integer> arr = new ArrayList<Integer>();

        for (int idx = 0; idx < n; idx++) {
            st = new StringTokenizer(reader.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            arr.add(a);
        }
        boolean is0 = true;

        int prev = arr.get(0);
        for (int idx = 1; idx < n; idx++) {
            if (arr.get(idx) + 1 != prev) {
                is0 = false;
                break;
            }
            prev = arr.get(idx);
        }

        if (is0) {
            System.out.println(0);
        } else {
            if (helper(arr)) {
                System.out.println(1);
            } else {
                System.out.println(2);
            }
        }

        reader.close();
    }

    public static boolean helper(ArrayList<Integer> arr) {
        Stack<Integer> I = new Stack<Integer>();
        Stack<Integer> D = new Stack<Integer>();
        Stack<Integer> inter = new Stack<Integer>();
        int n = arr.size();
        for (int idx = n - 1; idx >= 0; idx--) {
            I.push(arr.get(idx));
        }

        int next = n;
        while (!(I.size() == 0 && inter.size() > 0 && inter.peek() != next) && !(I.size() == 0 && inter.size() == 0)) {
            if (I.size() > 0 && I.peek() == next) {
                D.push(I.peek());
                I.pop();
                next--;
            } else if (inter.size() > 0 && inter.peek() == next) {
                D.push(inter.peek());
                inter.pop();
                next--;
            } else {
                inter.push(I.peek());
                I.pop();
            }
        }

        if (inter.size() == 0) {
            return true;
        } 
        return false;
    }
}
