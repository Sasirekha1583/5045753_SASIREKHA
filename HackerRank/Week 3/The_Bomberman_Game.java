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
     * Complete the 'bomberMan' function below.
     *
     * The function is expected to return a STRING_ARRAY.
     * The function accepts following parameters:
     *  1. INTEGER n
     *  2. STRING_ARRAY grid
     */

    public static List<String> bomberMan(int n, List<String> grid) {
    // Write your code here
        int rows = grid.size();
        int cols = grid.get(0).length();

        if (n == 1) {
            return grid;
        }

        if (n % 2 == 0) {
            List<String> fullGrid = new ArrayList<>();
            for (int i = 0; i < rows; i++) {
                String line = "";
                for (int j = 0; j < cols; j++) {
                    line += "O";
                }
                fullGrid.add(line);
            }
            return fullGrid;
        }

        char[][] board = new char[rows][cols];
        for (int i = 0; i < rows; i++) {
            board[i] = grid.get(i).toCharArray();
        }

        char[][] afterFirst = new char[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                afterFirst[i][j] = 'O';
            }
        }
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (board[i][j] == 'O') {
                    afterFirst[i][j] = '.';
                    if (i > 0) 
                        afterFirst[i - 1][j] = '.';
                    if (i < rows - 1) 
                        afterFirst[i + 1][j] = '.';
                    if (j > 0) 
                        afterFirst[i][j - 1] = '.';
                    if (j < cols - 1) 
                        afterFirst[i][j + 1] = '.';
                }
            }
        }

        char[][] afterSecond = new char[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                afterSecond[i][j] = 'O';
            }
        }
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (afterFirst[i][j] == 'O') {
                    afterSecond[i][j] = '.';
                    if (i > 0)
                        afterSecond[i - 1][j] = '.';
                    if (i < rows - 1) 
                        afterSecond[i + 1][j] = '.';
                    if (j > 0) 
                        afterSecond[i][j - 1] = '.';
                    if (j < cols - 1) 
                        afterSecond[i][j + 1] = '.';
                }
            }
        }

        char[][] finalGrid;
        if ((n - 3) % 4 == 0) {
            finalGrid = afterFirst;
        } 
        else {
            finalGrid = afterSecond;
        }

        List<String> result = new ArrayList<>();
        for (int i = 0; i < rows; i++) {
            result.add(new String(finalGrid[i]));
        }
        return result;

    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int r = Integer.parseInt(firstMultipleInput[0]);

        int c = Integer.parseInt(firstMultipleInput[1]);

        int n = Integer.parseInt(firstMultipleInput[2]);

        List<String> grid = IntStream.range(0, r).mapToObj(i -> {
            try {
                return bufferedReader.readLine();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        })
            .collect(toList());

        List<String> result = Result.bomberMan(n, grid);

        bufferedWriter.write(
            result.stream()
                .collect(joining("\n"))
            + "\n"
        );

        bufferedReader.close();
        bufferedWriter.close();
    }
}
