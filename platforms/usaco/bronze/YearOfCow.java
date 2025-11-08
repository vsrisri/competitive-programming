// USACO 2021 February Contest, Bronze Problem 1. Year of the Cow
import java.util.*;
import java.io.*;

public class YearOfCow {
    final public static String[] ANIMALS = {"Ox", "Tiger", "Rabbit", "Dragon",
	"Snake", "Horse", "Goat", "Monkey", "Rooster", "Dog", "Pig", "Rat"};
    public static HashMap<String,Integer> map;
    public static HashMap<String,Integer> yearMap;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();
        map = new HashMap<String,Integer>();
        for (int idx = 0; idx < ANIMALS.length; idx++) {
            map.put(ANIMALS[idx], idx);
        }
        yearMap = new HashMap<String,Integer>();
        yearMap.put("Bessie", 0);
        for (int idx = 0; idx < n; idx++) {
            String[] line = new String[8];
            for (int i = 0; i < 8; i++) {
                line[i] = sc.next();
            }
            int start = (12000 + yearMap.get(line[7])) % 12;
            int startBefore = yearMap.get(line[7]);
            char relatLoc = line[3].charAt(0);
            int relat = 0;
            if (relatLoc == 'p') {
                relat = -1;
            } else {
                relat = 1;
            }
            int year = map.get(line[4]);
            if (start == year) {
                yearMap.put(line[0], start + 12 * relat);
            } else {
                if (year > start) {
                    if (relat == 1) {
                        yearMap.put(line[0], startBefore + year - start);
                    } else {
                        yearMap.put(line[0], startBefore + year - start - 12);
                    }
                } else {
                    if (relat == 1) {
                        yearMap.put(line[0], startBefore + year - start + 12);
                    } else {
                        yearMap.put(line[0],startBefore + year - start);
                    }

                }
            }

        }
        System.out.println(Math.abs(yearMap.get("Elsie")));
    }
}
