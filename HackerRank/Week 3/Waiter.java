import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class Result {

    /*
     * Complete the 'waiter' function below.
     *
     * The function is expected to return an INTEGER_ARRAY.
     * The function accepts following parameters:
     *  1. INTEGER_ARRAY number
     *  2. INTEGER q
     */

    public static List<Integer> waiter(List<Integer> number, int q) {
    // Write your code here
        List<Integer> answers = new ArrayList<>();
        List<Integer> primes = new ArrayList<>();
        int n = 2;
        while (primes.size() < q) {
            boolean isPrime = true;
            for (int i = 2; i <= n/2; i++) {
                if (n % i == 0) {
                    isPrime = false;
                    break;
                }
            }
            if (isPrime) {
                primes.add(n);
            }
            n++;
        }

        Stack<Integer> A = new Stack<>();
        for (int i = number.size() - 1; i >= 0; i--) {
            A.push(number.get(i));
        }
        for (int i = 0; i < q; i++) {
            Stack<Integer> B = new Stack<>();
            Stack<Integer> newA = new Stack<>();

            while (!A.isEmpty()) {
                int plate = A.pop();
                if (plate % primes.get(i) == 0) {
                    B.push(plate);
                } 
                else {
                    newA.push(plate);
                }
            }
            answers.addAll(B);
            A = newA;
        }
        answers.addAll(A);
        return answers;
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(firstMultipleInput[0]);

        int q = Integer.parseInt(firstMultipleInput[1]);

        List<Integer> number = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
            .map(Integer::parseInt)
            .collect(toList());

        List<Integer> result = Result.waiter(number, q);

        bufferedWriter.write(
            result.stream()
                .map(Object::toString)
                .collect(joining("\n"))
            + "\n"
        );

        bufferedReader.close();
        bufferedWriter.close();
    }
}
