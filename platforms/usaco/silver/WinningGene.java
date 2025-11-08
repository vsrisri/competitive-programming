import java.io.*;
import java.util.*;

public class WinninGene {
    static int N;
    static String S;
    static int[][] piArr; 
    static int[] result; 
    static int[] prefix; 

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        S = br.readLine();

        piArr = new int[N][N]; 
        result = new int[N + 1]; 
        prefix = new int[N + 1]; 

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (S.charAt(i) == S.charAt(j)) {
                    piArr[i][j] = (i == j) ? 1 : piArr[i + 1][j + 1] + 1;
                } else {
                    piArr[i][j] = 0;
                }
            }
        }

        for (int pi = 0; pi < N; pi++) {
            for (int L = 1; L <= N; L++) {
                int ai = -1;
                int bi = N;

                for (int i = 0; i <= pi - L; i++) {
                    if (S.substring(i, i + L).compareTo(S.substring(pi, pi + L)) <= 0) {
                        ai = i;
                    }
                }

                for (int i = pi + L; i < N; i++) {
                    if (S.substring(i, i + L).compareTo(S.substring(pi, pi + L)) < 0) {
                        bi = i;
                    }
                }

                int maxK = (bi + L) - ai - 2;
                if (maxK > 0) {
                    prefix[0]++; 
                }
            }
        }
    }
} 

