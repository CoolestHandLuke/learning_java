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
    public static void placeShipsOnBoard(String[][] player) {
        Scanner scanner = new Scanner(System.in);
        // Aircraft Carrier (5 cells)
        System.out.println("Enter the coordinates for the Aircraft Carrier (5 cells):");
        while (true) {
            String[] coordinates = scanner.nextLine().strip().split(" "); 

            // First check to make sure we have either horizontal or vertical placement. 
            if (coordinates[0].charAt(0) == coordinates[1].charAt(0) || coordinates[0].charAt(1) == coordinates[1].charAt(1)) {

            }
            // Check for either vertical or horizontal placement
        
            if (coordinates[0].charAt(0) == coordinates[1].charAt(0)) { // Horizontal
                // Check for the correct length of the piece
                if (Math.abs(Integer.parseInt(coordinates[0].substring(1))) - Integer.parseInt(coordinates[1].substring(1)) != 4) {
                    System.out.println("Error! Wrong length of the Aircraft Carrier! Try Again: \n");
                } else {
                    // Place the aircraft carrier on the board
                }
            } else if (coordinates[0].charAt(1) == coordinates[1].charAt(1)) { // Vertical

            } else { // Diagnonal placement

                // Error message here since this is illegal
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
        placeShipsOnBoard(player2);
        

    }
}
