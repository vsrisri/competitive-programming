import java.util.*;
import java.io.*;

public class HUBULLU {
    public static void main(String[] args) throws Exception {
        Scanner stdin = new Scanner(System.in);
        int t = stdin.nextInt();
        while (t > 0) {
            int n = stdin.nextInt();
            int s = stdin.nextInt();
            if (s == 0) {
                System.out.println("Airborne wins.");
            } else {
                System.out.println("Pagfloyd wins.");
            }
            t--;
        }
    }
}
