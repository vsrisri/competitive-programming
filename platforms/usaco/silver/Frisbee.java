// USACO 2022 January Contest, Silver: Problem 2. Cow Frisbee
import java.util.*;
import java.io.*;

public class Frisbee {
    public static void main(String[] args) throws Exception {
        Scanner stdin = new Scanner(System.in);
        int n = stdin.nextInt();
        long[] arr = new long[n];
        for (int idx = 0; idx < n; idx++) {
            arr[idx] = stdin.nextLong();
        }
        stdin.close();
        long ans = computeStack(arr, n);
        long[] newArr = reverse(arr);
        ans+= computeStack(newArr, n);

        System.out.println(ans);
    }

    public static long computeStack(long[] arr, int n) {
        Stack<Integer> stack = new Stack<Integer>();
        long ans = 0;
        for (int idx = n - 1; idx >= 0; idx--) {
            while (!stack.isEmpty() && (arr[stack.peek()] < arr[idx])) {
                stack.pop();
            }
            if (!stack.isEmpty()) {
                //System.out.println(stack.peek() - idx + 1);
                ans+= stack.peek() - idx + 1;
            }
            stack.push(idx);
        }
        return ans;
    }

    public static long[] reverse(long[] arr) {
        long[] newArr = new long[arr.length];
        int newIdx = 0;
        for (int idx = arr.length - 1; idx >= 0; idx--) {
            newArr[newIdx] = arr[idx];
            newIdx++;
        }
        return newArr;
    }


}
