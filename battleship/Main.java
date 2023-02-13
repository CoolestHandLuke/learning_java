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

        System.out.println();
        for (int i = 0; i < gameBoard.length; i++) {
            for (int j = 0; j < gameBoard[i].length; j++) {
                System.out.print(gameBoard[i][j] + " ");
            }
            System.out.println();
        }
    }
    public static void placeShipOnBoard(String[][] gameBoard, String ship, Scanner scanner) {

        int shipLength = 0;
        String shipName = "";  
        switch (ship) {
            case "Aircraft Carrier":
                shipName = "Aircraft Carrier";
                shipLength = 5;
                break;
            case "Battleship":
                shipName = "Battleship";
                shipLength = 4;
                break;
            case "Submarine":
                shipName = "Submarine";
                shipLength = 3;
                break;
            case "Cruiser":
                shipName = "Cruiser";
                shipLength = 3;
                break;
            case "Destroyer":
                shipName = "Destroyer";
                shipLength = 2;
                break;
            default:
                System.out.println("Invalid entry");
                break;
        }
       
        System.out.println("\nEnter the coordinates for the " + shipName + " (" + shipLength + " cells):\n");
        while (true) {
            String[] coordinates = scanner.nextLine().strip().toUpperCase().split(" "); 
            // Check for either vertical or horizontal placement
            if (coordinates[0].charAt(0) == coordinates[1].charAt(0)) { // Horizontal
                // Check for the correct length of the piece
                if (Math.abs(Integer.parseInt(coordinates[0].substring(1)) - Integer.parseInt(coordinates[1].substring(1))) != shipLength - 1) {
                    System.out.println("\nError! Wrong length of the " + shipName + "! Try Again: \n");
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
                        System.out.println("\nError! Wrong ship location! Try again:\n");
                    } else {
                        int shipStartIndex = Integer.parseInt(coordinates[0].substring(1));
                        int shipEndIndex = Integer.parseInt(coordinates[1].substring(1));
                        // Make sure the start index is smaller than the end index, to simplify things
                        if (shipStartIndex > shipEndIndex) {
                            int temp = shipStartIndex;
                            shipStartIndex = shipEndIndex;
                            shipEndIndex = temp;
                        }
                        // Check to make sure the start and end index are on the board
                        if (shipStartIndex < 1 || shipEndIndex > 10) {
                            System.out.println("\nError! Wrong ship location! Try again:\n");
                            continue;
                        }
                        // Check to make sure we aren't trying place a ship too close to another one
                        boolean tooClose = false;
                        for (int i = shipStartIndex; i <= shipEndIndex; i++) {
                            // For the very first element, we need to check to the left
                            if (i == shipStartIndex) {
                                if (gameBoard[indexRow][i - 1].equals("O")) {
                                    tooClose = true;
                                    System.out.println("\nError! You placed it too close to another one. Try again:\n");
                                    break;
                                }
                            }
                            // If not on the first element, then check above, below, and right, in that order
                            // Be careful when checking to the right or below as this can go out of bounds
                            if (gameBoard[indexRow - 1][i].equals("O") || (indexRow < 10 && gameBoard[indexRow + 1][i].equals("O")) || (i < 10 && gameBoard[indexRow][i + 1].equals("O"))) {
                                tooClose = true;
                                System.out.println("\nError! You placed it too close to another one. Try again:\n");
                                break;
                            } 
                        }
                        if (tooClose) {
                            continue;
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

                if (Math.abs((int)coordinates[0].charAt(0) - (int)coordinates[1].charAt(0)) != shipLength - 1) {
                    System.out.println("\nError! Wrong length of the " + shipName + "! Try Again: \n");
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
                        System.out.println("\nError! Wrong ship location! Try again:\n");
                    } else {
                        int shipStartIndex = (int)coordinates[0].charAt(0) - 64;
                        int shipEndIndex = (int)coordinates[1].charAt(0) - 64;
                        // Make sure the start index is smaller than the end index, to simplify things
                        if (shipStartIndex > shipEndIndex) {
                            int temp = shipStartIndex;
                            shipStartIndex = shipEndIndex;
                            shipEndIndex = temp;
                        }
                        // Check to make sure the start and end index are on the board
                        if (shipStartIndex < 1 || shipEndIndex > 10) {
                            System.out.println("\nError! Wrong ship location! Try again:\n");
                            continue;
                        }
                        // Check to make sure we aren't trying place a ship too close to another one
                        boolean tooClose = false;
                        for (int i = shipStartIndex; i <= shipEndIndex; i++) {
                            // For the very first element, we need to check above
                            if (i == shipStartIndex) {
                                if (gameBoard[i - 1][indexCol].equals("O")) {
                                    tooClose = true;
                                    System.out.println("\nError! You placed it too close to another one. Try again:\n");
                                    break;
                                }
                            }
                            // If not on the first element, then check left, right, and below, in that order
                            // Be careful with looking right and below as this can go out of bounds
                            if (gameBoard[i][indexCol - 1].equals("O") || (indexCol < 10 && gameBoard[i][indexCol + 1].equals("O")) || (i < 10 && gameBoard[i + 1][indexCol].equals("O"))) {
                                tooClose = true;
                                System.out.println("\nError! You placed it too close to another one. Try again:\n");
                                break;
                            } 
                        }
                        if (tooClose) {

                            continue;
                        }
                        // If we get to here then we should be able to place the piece on the board without issue
                        for (int i = shipStartIndex; i <= shipEndIndex; i++) {
                            gameBoard[i][indexCol] = "O";
                        }
                        break;
                    }
                    
                }
            
            } else { // Diagnonal placement

                System.out.println("\nError! Wrong ship location! Try again:\n");
                continue;
            }
        }
    }
    public static boolean takeAShot(String[][] gameBoard, String[][] foggedGameBoard, Scanner scanner) {

        System.out.println("\nTake a shot!\n");
        boolean hit = false;
        while (true) {
            String shotLocation = scanner.next().toUpperCase().strip();
            // Check for a valid shot location
            int shotRow = (int)shotLocation.charAt(0) - 64;
            int shotCol = Integer.parseInt(shotLocation.substring(1));
            if (shotRow < 1 || shotRow > 10 || shotCol < 1 || shotCol > 10) {
                System.out.println("\nError! You entered the wrong coordinates! Try again:\n");
                continue;
            }
            // Good shot, now check to see if anything is there
            if (gameBoard[shotRow][shotCol].equals("O")) {
                gameBoard[shotRow][shotCol] = "X";
                foggedGameBoard[shotRow][shotCol] = "X";
                hit = true;
            } else {
                gameBoard[shotRow][shotCol] = "M";
                foggedGameBoard[shotRow][shotCol] = "M";
            }
            return hit;

        }


    }

    public static void main(String[] args) {

        String[][] player1 = createGameBoard();
        String[][] player1Fogged = createGameBoard();
        Scanner scanner = new Scanner(System.in);
        printGameBoard(player1);
        placeShipOnBoard(player1, "Aircraft Carrier", scanner);
        printGameBoard(player1);
        placeShipOnBoard(player1, "Battleship", scanner);
        printGameBoard(player1);
        placeShipOnBoard(player1, "Submarine", scanner);
        printGameBoard(player1);
        placeShipOnBoard(player1, "Cruiser", scanner);
        printGameBoard(player1);
        placeShipOnBoard(player1, "Destroyer", scanner);
        printGameBoard(player1);

        System.out.println("\nThe game starts!");
        printGameBoard(player1Fogged);

        boolean shot = takeAShot(player1, player1Fogged, scanner);
        printGameBoard(player1Fogged);
        if (shot) {
            System.out.println("\nYou hit a ship!");
        } else {
            System.out.println("\nYou missed!");
        }
        printGameBoard(player1);

        scanner.close();
        

    }
}
