import java.util.*;

public class KnowYourAbcs {
  public static void main(String[] args) {
    Scanner s = new Scanner(System.in);
    int[] arr = new int[7];
    int idx = 0;

    while (s.hasNextInt()) {
      arr[idx] = s.nextInt();
      idx++;
    }
    Arrays.sort(arr);
    int bc = arr[5];
    int a = arr[0];
    int b = arr[1];
    System.out.println(a + " " + b + " " + (bc - b));
  }
}
    
