import java.util.*;
import java.io.*;

public class JuryMarks {
    public static void main(String[] args) {
          Scanner in = new Scanner(System.in);
          int n = in.nextInt();
		  int k = in.nextInt();
		  int a[] = new int[n];
		  int b[] = new int[k];
		  int sum[] = new int[n];

		  for (int i=0;i<n;i++) {
              a[i] = in.nextInt();
          }
		  for (int i=0;i<k;i++) {
              b[i] = in.nextInt();
          }
		  sum[0] = a[0];

		  for (int i = 1; i < n; i++) {
              sum[i] = sum[i - 1] + a[i];
          }

		  HashSet<Integer> hs = new HashSet<Integer>();
		  for (int i = 0;i < k; i++) {
			  int temp = b[i];
			  for (int j = 0;j < n; j++)
			      hs.add(temp - sum[j]);
		  }

		  int ans =0;
		  Iterator<Integer> itr = hs.iterator();
          while (itr.hasNext()) {
			  int temp = itr.next();
			  HashSet<Integer> hm = new HashSet<Integer>();
			  for (int i = 0; i < n; i++) {
                  hm.add(sum[i]+temp);
              }
			  int z = 1;
			  for (int i = 0; i < k && z == 1; i++)
				  if (!hm.contains(b[i]))
					  z = 0;
		      ans+= z;
              hm.clear();
		  }
		  System.out.print(ans);
    }
}
