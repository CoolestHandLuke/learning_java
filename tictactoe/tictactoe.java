package tictactoe;
import java.util.*;

public class tictactoe {
    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);
        char[][] state = new char[3][3];
        boolean xWins = false;
        boolean oWins = false;
        int numX = 0;
        int numO = 0;
        int numSpace = 0;
        String input = scanner.next();
        scanner.close();
        System.out.println("---------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                state[i][j] = input.charAt(j + i * 3);
                if (state[i][j] == 'X') {
                    numX++;
                } else if (state[i][j] == 'O') {
                    numO++;
                } else {
                    numSpace++;
                }
                System.out.print(input.charAt(j + i * 3) + " ");
            }
            System.out.println("|");
        }
        System.out.println("---------");
        // Check for wins
        for (int i = 0; i < state.length; i++) {
            // Check for horizontal win
            if (state[i][0] == state[i][1] && state[i][1] == state[i][2]) {
                if (state[i][0] == 'X') {
                    xWins = true;
                }
                if (state[i][0] == 'O') {
                    oWins = true;
                }
            }
            // Check for vertical win
            if (state[0][i] == state[1][i] && state[1][i] == state[2][i]) {
                if (state[0][i] == 'X') {
                    xWins = true;
                }
                if (state[0][i] == 'O') {
                    oWins = true;
                }
            }
            // Check for diagnonal wins
            if (i == 1) {
                if (state[i][i] == state[i - 1][i - 1] && state[i - 1][i - 1] == state[i + 1][i + 1]) {
                    if(state[i][i] == 'X') {
                        xWins = true;
                    }
                    if (state[i][i] == 'O') {
                        oWins = true;
                    }
                }
                if (state[i][i] == state[i + 1][i - 1] && state[i + 1][i - 1] == state[i - 1][i + 1]) {
                    if(state[i][i] == 'X') {
                        xWins = true;
                    }
                    if (state[i][i] == 'O') {
                        oWins = true;
                    }
                }
            }
        }
        // Print results
        if (xWins && oWins || Math.abs(numX - numO) >= 2) {
            System.out.println("Impossible");
        } else if (xWins == false && oWins == false && numSpace == 0) {
            System.out.println("Draw");
        } else if (xWins == false && oWins == false && numSpace >= 0) {
            System.out.println("Game not Finished");
        } else if (xWins) {
            System.out.println("X wins");
        } else if (oWins) {
            System.out.println("O wins");
        }

    }
}
