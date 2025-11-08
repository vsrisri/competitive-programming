import java.util.*;
import java.io.*;

public class BucketBrigade {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(new File("buckets.in"));
        PrintWriter p = new PrintWriter(new File("buckets.out"));

        int[] B = new int[2];
        int[] R = new int[2];
        int[] L = new int[2];

        for (int i = 0; i < 10; i++) {
            String line = sc.nextLine();
            for (int j = 0; j < 10; j++) {
                if (line.charAt(j) == 'B') {
                    B[0] = i;
                    B[1] = j;
                }
                if (line.charAt(j) == 'R') {
                    R[0] = i;
                    R[1] = j;
                }
                if (line.charAt(j) == 'L') {
                    L[0] = i;
                    L[1] = j;
                }
            }
        }

        int minDist = (Math.abs(B[0] - L[0]) + Math.abs(B[1] - L[1])) - 1;
        int bdist = (Math.abs(B[0] - R[0]));
        int ldist = (Math.abs(L[0] - R[0]));
        int tdist = (Math.abs(L[0] - B[0]));
        int bdist2 = (Math.abs(B[1] - R[1]));
        int ldist2 = (Math.abs(L[1] - R[1]));
        int tdist2 = (Math.abs(L[1] - B[1]));


        if ((B[0] == L[0] && B[0] == R[0]) && (bdist + ldist == tdist)) {
            minDist+= 2;
        }

        if ((B[1] == L[1] && B[1] == R[1]) && (bdist2 + ldist2 == tdist2)) {
            minDist+= 2;
        }

        p.print(minDist);
        sc.close();
        p.close();
    }
}
