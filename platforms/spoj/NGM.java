import java.util.*;
import java.io.*;

public class NGM {
    public static void main(String[] args) throws Exception {
        Scanner stdin = new Scanner(System.in);
        int n = stdin.nextInt();
        if (n % 10 == 0) {
            System.out.println(2);
        } else {
            System.out.println(1);
            System.out.println(n % 10);
        }
        stdin.close();
    }
}

