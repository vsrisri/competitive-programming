import java.io.*;
import java.util.*;

public class Ccc07j1 {
    public static void main(String args[]) throws Exception {
        Scanner stdin = new Scanner(System.in);
        int a = stdin.nextInt();
        int b = stdin.nextInt();
        int c = stdin.nextInt();
        int[] list = new int[3];
        list[0] = a;
        list[1] = b;
        list[2] = c;
        Arrays.sort(list);
        System.out.println(list[1]);
        stdin.close();
    }
}
