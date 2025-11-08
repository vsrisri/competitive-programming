//USACO 2018 US Open Contest, Bronze Problem 1. Team Tic Tac Toe
import java.util.*;
import java.io.*;

public class TeamTicTacToe {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(new File("tttt.in"));
        PrintWriter p = new PrintWriter(new File("tttt.out"));
        char[][] inArr = new char[3][3];
        String s1 = sc.nextLine();
        String s2 = sc.nextLine();
        String s3 = sc.nextLine();
        inArr[0][0] = s1.charAt(0);
        inArr[0][1] = s1.charAt(1);
        inArr[0][2] = s1.charAt(2);
        inArr[1][0] = s2.charAt(0);
        inArr[1][1] = s2.charAt(1);
        inArr[1][2] = s2.charAt(2);
        inArr[2][0] = s3.charAt(0);
        inArr[2][1] = s3.charAt(1);
        inArr[2][2] = s3.charAt(2);

        int out1 = 0;
        int out2 = 0;
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        for (int idx = 0; idx < 26; idx++) {
            char cow = alphabet.charAt(idx);
            out1+= TeamTicTacToe.checkCow(inArr, cow);
        }
        for (int idx = 0; idx < 26; idx++) {
            char cow1 = alphabet.charAt(idx);
            for (int idx2 = idx + 1; idx2 < 26; idx2++) {
                char cow2 = alphabet.charAt(idx2);
                out2+= TeamTicTacToe.checkTeam(cow1, cow2, inArr);
            }
        }
        p.print(out1);
        p.println();
        p.print(out2);
        sc.close();
        p.close();
    }

    public static int checkCow(char[][] inArr, char cow) {
        if ((inArr[0][0] == cow) && (inArr[1][1] == cow) && (inArr[2][2] == cow)) {
            return 1;
        }
        if ((inArr[0][2] == cow) && (inArr[1][1] == cow) && (inArr[2][0] == cow)) {
            return 1;
        }
        for (int idx = 0; idx < 3; idx++) {
            if ((inArr[0][idx] == cow) && (inArr[1][idx] == cow) && (inArr[2][idx] == cow)) {
                return 1;
            }
            if ((inArr[idx][0] == cow) && (inArr[idx][1] == cow) && (inArr[idx][2] == cow)) {
                return 1;
            }
        }
         return 0;
    }

    public static boolean checkString(char cow1, char cow2, String s) {
        if ((s.charAt(0) != cow1) && (s.charAt(1) != cow1) && (s.charAt(2) != cow1)) {
            return false;
        }
        if ((s.charAt(0) != cow2) && (s.charAt(1) != cow2) && (s.charAt(2) != cow2)) {
            return false;
        }
        if ((s.charAt(0) != cow1) && (s.charAt(0) != cow2)) {
            return false;
        } 
        if ((s.charAt(1) != cow1) && (s.charAt(1) != cow2)) {
            return false;
        }
        if ((s.charAt(2) != cow1) && (s.charAt(2) != cow2)) {
            return false;
        }
        return true;
    }

    public static int checkTeam(char cow1, char cow2, char[][] inArr) {
        String diag1 = Character.toString(inArr[0][0]) + Character.toString(inArr[1][1]) + Character.toString(inArr[2][2]);
        String diag2 = Character.toString(inArr[0][2]) + Character.toString(inArr[1][1]) + Character.toString(inArr[2][0]);
        if (TeamTicTacToe.checkString(cow1, cow2, diag1)) {
            return 1;
        }
        if (TeamTicTacToe.checkString(cow1, cow2, diag2)) {
            return 1;
        }
        for (int idx = 0; idx < 3; idx++) {
            String str1 = Character.toString(inArr[0][idx]) + Character.toString(inArr[1][idx]) + Character.toString(inArr[2][idx]);
            String str2 = Character.toString(inArr[idx][0]) + Character.toString(inArr[idx][1]) + Character.toString(inArr[idx][2]);
            if (TeamTicTacToe.checkString(cow1, cow2, str1)) {
                return 1;
            }
            if (TeamTicTacToe.checkString(cow1, cow2, str2)) {
                return 1;
            }
        }
        return 0;
    }

}
