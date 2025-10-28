import java.util.*;
import java.io.*;

public class SmallestStringConcat {
    public static void main(String[] args) throws Exception {
        Scanner stdin = new Scanner(System.in);
        int n = stdin.nextInt();
        int[] arr = new int[n];
        for (int idx = 0; idx < n; idx++) {
            arr[idx] = new MyStr(stdin.next());
        }
        Arrays.sort(arr);
        for (int idx = 0; idx < n; idx++) {
            System.out.print(arr[idx].str);
        }
        stdin.close();
    }

    static class MyString implements Comparable<Customer> {
        private String str;
        MyString(String str) {
            this.str = str;
        }

        public int compareTo(MyString other) {
            return this.str + other.str < other.str + this.str;
        }
    }

}
