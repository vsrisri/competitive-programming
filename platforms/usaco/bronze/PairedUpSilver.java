import java.io.*;
import java.util.*;
public class PairedUpSilver {
  public static void main(String[] args) throws Exception {
    Scanner sc = new Scanner(new File("pairup.in"));
    PrintWriter pw = new PrintWriter(new File("pairup.out"));
    int n = sc.nextInt();
    sc.nextLine();
    ArrayList<Integer> arr = new ArrayList<Integer>();
    //HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();

    while(sc.hasNextInt()) {
      int numElements = sc.nextInt();
      int value = sc.nextInt();
      for (int i = 0; i < numElements; i++) {
        //map.put(value, numElements);
        arr.add(value);
      }
      sc.nextLine();
    }
    Collections.sort(arr);

    int answer = Integer.MAX_VALUE;
    while (arr.size() > 0) {
      int pair = arr.get(0) + arr.get(arr.size());
      if (pair < answer) {
        answer = pair;
      }
      arr.remove(0);
      arr.remove(arr.size());
    }

    pw.print(answer);
    sc.close();
    pw.close();
  }
}
