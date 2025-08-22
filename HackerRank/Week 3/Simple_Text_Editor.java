import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner in = new Scanner(System.in);
        int ops = in.nextInt();
        StringBuilder s = new StringBuilder();
        Stack<String> st = new Stack<>();

        for (int i = 0; i < ops; i++) {
            int type = in.nextInt();
            if (type == 1) {
                String w = in.next();
                st.push(s.toString());  
                s.append(w);
            }
            else if (type == 2) {
                int k = in.nextInt();
                st.push(s.toString());
                int l = s.length();
                s.delete(l - k, l);
            }
            else if (type == 3) {
                int k = in.nextInt();
                System.out.println(s.charAt(k - 1));
            }
            else if (type == 4) {
                if (!st.isEmpty()) {
                    s = new StringBuilder(st.pop());
                }
            }
        }
    }
}