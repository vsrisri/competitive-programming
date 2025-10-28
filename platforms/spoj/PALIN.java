import java.util.*;
import java.io.*;

public class PALIN {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(reader.readLine());

        for (int idx = 0; idx < tc; idx++) {
            String a = reader.readLine();
            int[] num = new int[a.length()];
            for (int idx2 = 0; idx2 < a.length(); idx2++) {
                char c = a.charAt(idx2);
                int cInt = Integer.parseInt(Character.toString(c));
                num[idx2] = cInt;
            }

            num = helper(num);
            StringBuilder str = new StringBuilder();
            for (int idx2 = 0; idx2 < num.length; idx2++) {
                str.append(num[idx2]);
            }
            System.out.println(str.toString());
        }

        reader.close();
    }

    public static int[] helper(int[] num) {
        boolean hasFoundLarger = false;
        int mid = num.length / 2;
        int a = mid; int b = mid;
        if (num.length % 2 == 0) {
            a--;
        }
        int lhs = a; int rhs = b;

        while (lhs >= 0) {
            if (num[lhs] > num[rhs]) {
                hasFoundLarger = true;
                num[rhs] = num[lhs];
            } else if (num[lhs] == num[rhs] || hasFoundLarger) {
                num[rhs] = num[lhs];
            } else {
                break;
            }
            lhs--; rhs++;
        }
        if (hasFoundLarger) {
            return num;
        }

        lhs = a; rhs = b;
        int add = 1;

        while (lhs >= 0) {
            int currDig = num[lhs];
            num[lhs] = (currDig + add) % 10;
            add = (currDig + add) / 10;
            num[rhs] = num[lhs];
            lhs--; rhs++;
        }

        if (add > 0) {
            int[] num2 = new int[num.length + 1];
            num2[0] = add;
            for (int idx = 0; idx < num.length; idx++) {
                num2[idx + 1] = num[idx];
            }
            num = num2;
        }

        lhs = 0; rhs = num.length - 1;
        while (lhs <= rhs) {
            num[rhs] = num[lhs];
            lhs++; rhs--;

        }

        return num;
    }
}

