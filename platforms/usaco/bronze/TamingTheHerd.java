import java.util.*;
import java.io.*;

public class TamingTheHerd {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(new File("taming.in"));
        PrintWriter out = new PrintWriter(new FileWriter("taming.out"));
        int n = sc.nextInt(); 
        int[] vals = new int[n];
        for (int i = 0; i < n; i++)
            vals[i] = sc.nextInt();
            
        boolean isOk = true;
        int[] newArr = new int[n];
        Arrays.fill(newArr, -1); 
        for (int i = n - 1; i >= 0; i--) {
            if (vals[i] >= 0) {
                for (int j = i, k=vals[i]; k >= 0; j--,k--) {
                    if (newArr[j] != -1 && newArr[j] != k) {
                        isOk = false;
                    }   
                    newArr[j] = k; 
                }   
            }   
        }   
        for (int i =0; i<n; i++) {
            if (i == 0 && vals[i] > 0) isOk = false;
            if (vals[i] >= 0 && vals[i] != newArr[i])
                isOk = false;
        }       
        vals[0] = 0;
        newArr[0] = 0; 
        
        if (!isOk)
            out.println(-1);
        else {
            int min = 0;
            for (int i = 0; i < n; i++)
                if (newArr[i] == 0)
                    min++;
            int max = min;
            for (int i = 0; i < n; i++)
                if (newArr[i] == -1)
                    max++;
                   
            out.println(min + " " + max);
        }   
        out.close();
        sc.close();
    }   
    
}
                         
