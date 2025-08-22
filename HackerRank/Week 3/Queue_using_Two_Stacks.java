import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner in = new Scanner(System.in);
        int queries = in.nextInt();
        Stack<Integer> fin = new Stack<>();   
        Stack<Integer> out = new Stack<>();  
        for (int i=0; i<queries; i++) {
            int type = in.nextInt();
            switch (type) {
                case 1: // enqueue
                    fin.push(in.nextInt());
                    break;
                case 2: // dequeue
                    if (out.isEmpty()) {
                        while (!fin.isEmpty()) {
                            out.push(fin.pop());
                        }
                    }
                    if (!out.isEmpty()) {
                        out.pop();
                    }
                    break;
                case 3: // print front
                    if (out.isEmpty()) {
                        while (!fin.isEmpty()) {
                            out.push(fin.pop());
                        }
                    }
                    if (!out.isEmpty()) {
                        System.out.println(out.peek());
                    }
                    break;
            }
        }
    }
}
