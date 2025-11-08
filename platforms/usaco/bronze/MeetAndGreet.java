import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

public class MeetAndGreet { 
    public static int meetAndGreet() throws FileNotFoundException {
        File inFile = new File("/Users/srjagann/Work/usaco/greetings.in");
        Scanner s = new Scanner(inFile);
        ArrayList<String> arr = new ArrayList<String>();
        while (s.hasNextLine()) {
            arr.add(s.nextLine());
        }
        s.close();
        int bLen = Character. getNumericValue(arr.get(0).charAt(0));
        int eLen = Character. getNumericValue(arr.get(0).charAt(2));
        // System.out.println(bLen + " " + eLen);
        int b = 0;
        int e = 0;
        int move = 0;
        int total = 0;
        int bIdx = 1;
        int eIdx = bLen + 1;
/*
        for (int bIdx = 1; bIdx < bLen; bIdx++) {
            move = Character. getNumericValue(arr.get(bIdx).charAt(0));
            if (arr.get(bIdx).charAt(2) == 'L') {
                move = move * -1;
            }
            b+= move;
            System.out.println("b: " + b);

            for (int eIdx = bLen + 1; eIdx < arr.size(); eIdx++) {
                move = Character. getNumericValue(arr.get(eIdx).charAt(0));
                if (arr.get(eIdx).charAt(2) == 'L') {
                    move = move * -1;
                }
                e+= move;
                System.out.println("e: " + e);
                if (e == b) {
                    total++;
                }
            }
        }
*/
        while ((bIdx <= bLen) && (eIdx <= arr.size() - 1)) {
            if (bIdx != bLen) {
                move = Character. getNumericValue(arr.get(bIdx).charAt(0));
                if (arr.get(bIdx).charAt(2) == 'L') {
                    move = move * -1;
                }
                b+= move;
                bIdx++;
            }   
            if (eIdx != arr.size() - 1) {
                move = Character. getNumericValue(arr.get(eIdx).charAt(0));
                if (arr.get(eIdx).charAt(2) == 'L') {
                    move = move * -1;
                }
                e+= move;
                eIdx++;
            }
            if ((bIdx == bLen) && (eIdx == arr.size() - 1)) {
                break;
            }

            System.out.println("e: " + e + "  b: " + b);

            if (e == b) {
                total++;
            }
        }
        return total;
    }

    public static void main(String[] args) {
        try {
            System.out.println(meetAndGreet());
        } catch (FileNotFoundException e) {
            System.out.println("this file does not exist");
        }
    }
}
