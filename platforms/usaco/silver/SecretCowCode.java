// USACO 2017 January Contest, Silver
// Problem 3. Secret Cow Code
import java.util.*;
import java.io.*;

public class SecretCowCode {

	public static String s;
	public static long len;

	public static void main(String[] args) throws Exception {
		Scanner stdin = new Scanner(new File("cowcode.in"));
		s = stdin.next();
		long index = stdin.nextLong()-1;
		len = s.length();
		PrintWriter out = new PrintWriter(new FileWriter("cowcode.out"));
		out.println(solve(index));
		out.close();
		stdin.close();
	}
	public static char solve(long index) {
	}
}
