/* Here is my humble attempt at the beginners Tic-Tac-Toe program. Sure, it's not glamorous, flashy, or even
 * optimized. But it's mine, damnit, and I wrote every line of it. 
 * Luke Shea, 2/1/2023
 */
package tictactoe;
import java.util.*;
import java.util.Arrays;

public class tictactoe {
    public static void main(String[] args) {
        
        // Initialize the game board to all blank spaces, create the flags used for determining if X or O has won, 
        // and the flag for determing who's move it is
        Scanner scanner = new Scanner(System.in);
        char[][] gameBoard = new char[3][3];
        Arrays.fill(gameBoard[0], ' ');
        Arrays.fill(gameBoard[1], ' ');
        Arrays.fill(gameBoard[2], ' ');
        boolean xWins = false;
        boolean oWins = false;
        boolean whosTurn = true;
        int numMoves = 0;

        printBoard(gameBoard);
        // Start game loop
        do {
            // Get input from the user about the next move
            String nextMove = scanner.nextLine();
            int row = 0;
            int col = 0;
            try {
                row = Character.getNumericValue(nextMove.charAt(0));
                col = Character.getNumericValue(nextMove.charAt(2));
            } 
            catch (Exception e) {
                System.out.println("You should enter numbers!");
                continue;
            }
            
            // Check for invalid inputs
            // Check if the user entered invalid numbers
            if (row < 1 || row > 3 || col < 1 || col > 3) {
                System.out.println("Coordinates should be from 1 to 3!");
                continue;
            // Check if the user is trying to play on a space someone has already played on
            } else if (gameBoard[row - 1][col - 1] != ' ') {
                System.out.println("This cell is occupied! Choose another one!");
                continue;
            // Input is valid, set the space to X or O depending on whosTurn, and flip whosTurn
            } else {
                if (whosTurn) {
                    gameBoard[row - 1][col - 1] = 'X';
                    whosTurn = !whosTurn;
                } else {
                    gameBoard[row - 1][col - 1] = 'O';
                    whosTurn = !whosTurn;
                }
                // Iterate numMoves, if this variable reaches 9 then either someone has won or we have a draw
                numMoves++;
            }
            // Check for wins or a draw
            printBoard(gameBoard);
            xWins = checkForWin(gameBoard, 'X');
            oWins = checkForWin(gameBoard, 'O');

        } while (xWins == false && oWins == false && numMoves < 9);
        
        // Someone has won, or there is a draw. Figure out which, print the result, and close the scanner
        if (xWins) {
            System.out.println("X wins");
        } else if (oWins) {
            System.out.println("O wins");
        } else {
            System.out.println("Draw");
        }
        scanner.close();
    }

    static void printBoard(char[][] state) {
        System.out.println("---------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(state[i][j] + " ");
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }

    // Takes in the gameBoard array and either 'X' or 'O' and determines if it has won
    static boolean checkForWin(char[][] gameBoard, char player) {

        boolean win = false;
        for (int i = 0; i < gameBoard.length; i++) {
            // Check for horizontal win
            if (gameBoard [i][0] == player && gameBoard[i][0] == gameBoard[i][1] && gameBoard[i][1] == gameBoard[i][2]) {
                win = true;
            }
            // Check for vertical win
            if (gameBoard [0][i] == player && gameBoard[0][i] == gameBoard[1][i] && gameBoard[1][i] == gameBoard[2][i]) {
                win = true;   
            }
            // Check for diagnonal wins
            if (i == 1) {
                if (gameBoard[i][i] == player && gameBoard[i][i] == gameBoard[i - 1][i - 1] && gameBoard[i - 1][i - 1] == gameBoard[i + 1][i + 1]) {
                    win = true;     
                }
                if (gameBoard [i][i] == player && gameBoard[i][i] == gameBoard[i + 1][i - 1] && gameBoard[i + 1][i - 1] == gameBoard[i - 1][i + 1]) {
                    win = true;
                }
            }
        }
        return win;
    }
}
