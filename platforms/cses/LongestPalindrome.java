import java.util.*;
import java.io.*;
public class LongestPalindrome {
  public static void main(String[] args) throws Exception {
    Scanner stdin = new Scanner(System.in);
    String str = stdin.nextLine();
    String ans = str.substring(0, 1);
    for (int idx = 0; idx < str.length(); idx++) {
      String odd = helper(str, idx, idx);
      if (odd.length() > ans.length()) {
        ans = odd;
      }
      String even = helper(str, idx, idx + 1);
      if (even.length() > ans.length()) {
        ans = even;
      }
    }
    System.out.println(ans);
    stdin.close();
  }

  public static String helper(String str, int start, int end) {
    while (start >= 0 && end < str.length() && str.charAt(start) == str.charAt(end)) {
      start--;
      end++;
    }
    return str.substring(start + 1, end);
  }}
