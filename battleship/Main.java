package battleship;

import java.util.Scanner;

public class Main {
    

    public static String[][] createGameBoard() {

        String[][] gameBoard = new String[][] {
            {" ", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"},
            {"A", "~", "~", "~", "~", "~", "~", "~", "~", "~", "~"}, 
            {"B", "~", "~", "~", "~", "~", "~", "~", "~", "~", "~"}, 
            {"C", "~", "~", "~", "~", "~", "~", "~", "~", "~", "~"}, 
            {"D", "~", "~", "~", "~", "~", "~", "~", "~", "~", "~"},
            {"E", "~", "~", "~", "~", "~", "~", "~", "~", "~", "~"}, 
            {"F", "~", "~", "~", "~", "~", "~", "~", "~", "~", "~"}, 
            {"G", "~", "~", "~", "~", "~", "~", "~", "~", "~", "~"},
            {"H", "~", "~", "~", "~", "~", "~", "~", "~", "~", "~"}, 
            {"I", "~", "~", "~", "~", "~", "~", "~", "~", "~", "~"}, 
            {"J", "~", "~", "~", "~", "~", "~", "~", "~", "~", "~"}
        };
        return gameBoard;
    }
    public static void printGameBoard(String[][] gameBoard) {

        for (int i = 0; i < gameBoard.length; i++) {
            for (int j = 0; j < gameBoard[i].length; j++) {
                System.out.print(gameBoard[i][j] + " ");
            }
            System.out.println();
        }
    }
    public static void placeShipsOnBoard(String[][] gameBoard) {
        Scanner scanner = new Scanner(System.in);
        // Aircraft Carrier (5 cells)
        System.out.println("Enter the coordinates for the Aircraft Carrier (5 cells):");
        while (true) {
            String[] coordinates = scanner.nextLine().strip().split(" "); 

            // Check for either vertical or horizontal placement
            if (coordinates[0].charAt(0) == coordinates[1].charAt(0)) { // Horizontal
                // Check for the correct length of the piece
                if (Math.abs(Integer.parseInt(coordinates[0].substring(1)) - Integer.parseInt(coordinates[1].substring(1))) != 4) {
                    System.out.println("Error! Wrong length of the Aircraft Carrier! Try Again: \n");
                } else {
                    // Place the aircraft carrier on the board
                    // Find the row we need to be on
                    String indexChar = coordinates[0].substring(0, 1);
                    int indexRow = -1;
                    for (int i = 0; i < gameBoard.length; i++) {
                        if (gameBoard[i][0].equalsIgnoreCase(indexChar)) {
                            indexRow = i;
                            break;
                        }
                    }
                    // Check for failure
                    if (indexRow == -1) {
                        System.out.println("Error! Those coordinates do not exist! Try Again: \n");
                    } else {
                        int shipStartIndex = Integer.parseInt(coordinates[0].substring(1));
                        int shipEndIndex = Integer.parseInt(coordinates[1].substring(1));
                        // Make sure the start index is smaller than the end index, to simplify things
                        if (shipStartIndex > shipEndIndex) {
                            int temp = shipStartIndex;
                            shipStartIndex = shipEndIndex;
                            shipEndIndex = temp;
                        }
                        // Check to make sure we aren't trying place a ship too close to another one
                        for (int i = shipStartIndex; i < shipEndIndex; i++) {
                            // For the very first element, we need to check to the left
                            if (i == shipStartIndex) {
                                if (gameBoard[indexRow][i - 1].equals("O")) {
                                    System.out.println("Error! You placed it too close to another one. Try again:\n");
                                    continue;
                                }
                            }
                            // If not on the first element, then check above, below, and right, in that order
                            if (gameBoard[indexRow - 1][i].equals("O") || gameBoard[indexRow + 1][i].equals("O") || gameBoard[indexRow][i + 1].equals("O")) {
                                System.out.println("Error! You placed it too close to another one. Try again:\n");
                                continue;
                            } 
                        }
                        // If we get to here then we should be able to place the piece on the board without issue
                        for (int i = shipStartIndex; i <= shipEndIndex; i++) {
                            gameBoard[indexRow][i] = "O";
                        }
                        break;
                    }
                }
            } else if (coordinates[0].charAt(1) == coordinates[1].charAt(1)) { // Vertical
                // Check for the correct length of the piece

                if (Math.abs((int)coordinates[0].charAt(0) - (int)coordinates[1].charAt(0)) != 4) {
                    System.out.println("Error! Wrong length of the Aircraft Carrier! Try Again: \n");
                } else {
                    // Place the aircraft carrier on the board
                    // Find the column we need to be in
                    String colNumber = coordinates[0].substring(1);
                    int indexCol = -1;
                    for (int i = 0; i < gameBoard[0].length; i++) {
                        if (gameBoard[0][i].equalsIgnoreCase(colNumber)) {
                            indexCol = i;
                            break;
                        }
                    }
                    // Check for failure
                    if (indexCol == -1) {
                        System.out.println("Error! Those coordinates do not exist! Try Again: \n");
                    } else {
                        int shipStartIndex = (int)coordinates[0].charAt(0) - 64;
                        int shipEndIndex = (int)coordinates[1].charAt(0) - 64;
                        // Make sure the start index is smaller than the end index, to simplify things
                        if (shipStartIndex > shipEndIndex) {
                            int temp = shipStartIndex;
                            shipStartIndex = shipEndIndex;
                            shipEndIndex = temp;
                        }
                        // Check to make sure we aren't trying place a ship too close to another one
                        for (int i = shipStartIndex; i < shipEndIndex; i++) {
                            // For the very first element, we need to check above
                            if (i == shipStartIndex) {
                                if (gameBoard[i - 1][indexCol].equals("O")) {
                                    System.out.println("Error! You placed it too close to another one. Try again:\n");
                                    continue;
                                }
                            }
                            // If not on the first element, then check left, right, and below, in that order
                            if (gameBoard[i][indexCol - 1].equals("O") || gameBoard[i][indexCol + 1].equals("O") || gameBoard[i + 1][indexCol].equals("O")) {
                                System.out.println("Error! You placed it too close to another one. Try again:\n");
                                continue;
                            } 
                        }
                        // If we get to here then we should be able to place the piece on the board without issue
                        for (int i = shipStartIndex; i <= shipEndIndex; i++) {
                            gameBoard[i][indexCol] = "O";
                        }
                        break;
                    }
                    
                }
            
            } else { // Diagnonal placement

                System.out.println("Error! Pieces cannot be placed diagonally! Try again:\n");
                continue;
            }
        }
        // Battleship (4 cells)

        // Submarine (3 cells)

        // Cruiser (3 cells)

        // Destroyer (2 cells)
    }
    public static void main(String[] args) {

        String[][] player1 = createGameBoard();
        String[][] player2 = createGameBoard();

        placeShipsOnBoard(player1);
        printGameBoard(player1);
        //placeShipsOnBoard(player2);
        

    }
}
