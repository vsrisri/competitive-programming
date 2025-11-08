import java.util.*;

public class DaisyChains {
  public static boolean contains(double i, double[] arr, int sIdx, int eIdx) {
    for (int idx = sIdx; idx < eIdx; idx++) {
      if (arr[idx] == i) {
        return true;
      }
    }
    return false;
  }

  public static void main(String[] args) {
    Scanner s = new Scanner(System.in);
    double[]  arr = new double[s.nextInt()];
    int numAvgFlowers = arr.length;
    s.nextLine();
    int i = 0;

    while (s.hasNextInt()) {
      arr[i] = s.nextInt();
      i++;
    }

    double sum = 0;
    for (int idx = 0; idx < arr.length; idx++) {
      for (int i2 = 2; i2 <= arr.length - idx; i2++) {
        sum = 0;
        for (int i3 = idx; i3 < idx + i2; i3++) {
          sum += arr[i3];
        }
        if (DecemberBronze2.contains((sum / i2), arr, idx, (idx + i2))) {
            numAvgFlowers++;
        }
      }
    }
    System.out.println(numAvgFlowers);
  }
}

