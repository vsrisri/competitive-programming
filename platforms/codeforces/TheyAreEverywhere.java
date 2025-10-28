import java.util.*;
import java.io.*; 

public class TheyAreEverywhere {
    public static void main(String[] args) throws Exception  {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        String flats = sc.nextLine();

        HashSet<Character> types = new HashSet<>();
        for (int f = 0; f < n; f++) {
            types.add(flats.charAt(f));
        }

        int min = Integer.MAX_VALUE;
        int closestLeft = 0;
        HashMap<Character, Integer> currCaught = new HashMap<>();

        for (int right = 0; right < n; right++) {
            char newC = flats.charAt(right);
            currCaught.put(newC, currCaught.getOrDefault(newC, 0) + 1);

            while (closestLeft + 1 <= right && currCaught.getOrDefault(flats.charAt(closestLeft), 0) > 1) {
                currCaught.put( flats.charAt(closestLeft), currCaught.get(flats.charAt(closestLeft)) - 1);
                closestLeft++;
            }

            if (currCaught.size() == types.size()) {
                min = Math.min(min, right - closestLeft + 1);
            }
        }

        System.out.println(min);
    }
}
