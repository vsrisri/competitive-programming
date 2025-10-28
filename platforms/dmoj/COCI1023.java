import java.util.*;
import java.io.*;

public class COCI1023 {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        String a = reader.readLine();
        char[] arr = new char[n];
        for (int idx = 0; idx < a.length(); idx++) {
            arr[idx] = a.charAt(idx);
        }

        TreeMap<Character, TreeSet<Integer>> charFreq = new TreeMap<Character, TreeSet<Integer>>();
        for (int idx = 0; idx < 26; idx++) {
            charFreq.put((char) (97 + idx), new TreeSet<Integer>());
        }

        for (int idx = 0; idx < a.length(); idx++) {
            char c = a.charAt(idx);
            TreeSet<Integer> temp = charFreq.get(c);
            temp.add(idx);
            charFreq.remove(c);
            charFreq.put(c, temp);
        }

        for (int idx = 0; idx < 26; idx++) {
            char c = (char) (97 + idx);
            if (charFreq.get(c).size() == 0) {
                charFreq.remove(c);
            }
        }

        String mirko = "";
        String slavko = "";
        int endPtr = n - 1;

        while (charFreq.size() > 0) {
            mirko+= arr[endPtr];
            charFreq.get(arr[endPtr]).remove(endPtr);

            if (charFreq.get(arr[endPtr]).size() == 0) {
                charFreq.remove(arr[endPtr]);
            }

            arr[endPtr] = '.';
            endPtr--;

            int idx = charFreq.get(charFreq.firstKey()).last();
            slavko+= Character.toString(arr[idx]);
            charFreq.get(arr[idx]).remove(idx);
            arr[idx] = '.';
            for (int i = endPtr; i > 0; i--) {
                if (arr[i] != '.') {
                    endPtr = i; 
                    break;
                }
            }

            if (charFreq.get(charFreq.firstKey()).size() == 0) {
                charFreq.remove(charFreq.firstKey());
            }

        }

        if (slavko.compareTo(mirko) < 0) {
            System.out.println("DA");
        } else {
            System.out.println("NE");
        }

        System.out.println(slavko);
        reader.close();
    }
}
