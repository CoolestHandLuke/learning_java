/* This is my attempt at the classic game of battleship. This was one of my favorite board games growing up and it was a very fun project
 * to implement. The most challenging part was the beginning: getting the game boards set up, figuring out how to set game pieces, how to 
 * check and make sure they were not too close to each other, etc. It was a fun an challenging project and I've definitely learned a lot. 
 * In the future I may come back to this guy and turn it in to a GUI. I'm not sure how hard it would be but it sounds kind of cool. 
 * Luke Shea, 2/17/2023
 */

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
    public static void placeShipsOnBoard(String[][] gameBoard, Scanner scanner) {
        System.out.println();
        printGameBoard(gameBoard);
        placeShipOnBoard(gameBoard, "Aircraft Carrier", scanner);
        System.out.println();
        printGameBoard(gameBoard);
        placeShipOnBoard(gameBoard, "Battleship", scanner);
        System.out.println();
        printGameBoard(gameBoard);
        placeShipOnBoard(gameBoard, "Submarine", scanner);
        System.out.println();
        printGameBoard(gameBoard);
        placeShipOnBoard(gameBoard, "Cruiser", scanner);
        System.out.println();
        printGameBoard(gameBoard);
        placeShipOnBoard(gameBoard, "Destroyer", scanner);
        System.out.println();
        printGameBoard(gameBoard);

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
    public static boolean[] takeAShot(String[][] gameBoardBeingShotAt, String[][] foggedGameBoardBeingShotAt, Scanner scanner) {

        boolean[] hitSunkAndSpotAlreadyShot = {false, false, false};
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
            if (gameBoardBeingShotAt[shotRow][shotCol].equals("O")) {
                gameBoardBeingShotAt[shotRow][shotCol] = "X";
                foggedGameBoardBeingShotAt[shotRow][shotCol] = "X";
                hitSunkAndSpotAlreadyShot[0] = true;
                hitSunkAndSpotAlreadyShot[1] = isShipSunk(gameBoardBeingShotAt, shotRow, shotCol);
            } else if (gameBoardBeingShotAt[shotRow][shotCol].equals("X")) {
                hitSunkAndSpotAlreadyShot[0] = true;
                hitSunkAndSpotAlreadyShot[1] = false;
                hitSunkAndSpotAlreadyShot[2] = true;
            } else {
                gameBoardBeingShotAt[shotRow][shotCol] = "M";
                foggedGameBoardBeingShotAt[shotRow][shotCol] = "M";
            }
            return hitSunkAndSpotAlreadyShot;

        }


    }
    public static boolean isShipSunk(String[][] gameBoard, int shotRow, int shotCol) {

        // For any given hit, you should have to move over no more than 4 steps in any direction to find an "O". If you don't, 
        // then the ship is sunk
        // Check above
        for (int i = 1; i < 5; i++) {
            if (shotRow - i > 1 && gameBoard[shotRow - i][shotCol].equals("O")) {
                return false;
            } else if (shotRow - i > 1 && gameBoard[shotRow - i][shotCol].equals("~")) {
                break;
            }
        }
        // Check below
        for (int i = 1; i < 5; i++) {
            if (shotRow + i < 10 && gameBoard[shotRow + i][shotCol].equals("O")) {
                return false;
            } else if (shotRow + i < 10 && gameBoard[shotRow + i][shotCol].equals("~")) {
                break;
            }
        }
        // Check left
        for (int i = 1; i < 5; i++) {
            if (shotCol - i > 1 && gameBoard[shotRow][shotCol - i].equals("O")) {
                return false;
            } else if (shotCol - i > 1 && gameBoard[shotRow][shotCol - i].equals("~")) {
                break;
            }
        }
        // Check right
        for (int i = 1; i < 5; i++) {
            if (shotCol + i < 10 && gameBoard[shotRow][shotCol + i].equals("O")) {
                return false;
            } else if (shotCol + i < 1 && gameBoard[shotRow][shotCol + i].equals("~")) {
                break;
            }
        }
        return true;
        

    }
    public static void main(String[] args) {

        // Since battleship is a two player game, start off the main loop by creating two gameboards, one for each player
        String[][] player1 = createGameBoard();
        String[][] player2 = createGameBoard();
        String[][] player1Fogged = createGameBoard();
        String[][] player2Fogged = createGameBoard();

        // Allow both players to place their ships on their boards
        Scanner scanner = new Scanner(System.in);
        System.out.println("Player 1, place your ships on the game field");
        placeShipsOnBoard(player1, scanner);
        System.out.println("Press Enter and pass the move to another player\n...");
        scanner.nextLine();
        System.out.println("Player 2, place your ships on the game field");
        placeShipsOnBoard(player2, scanner);
        System.out.println("Press Enter and pass the move to another player\n...");
        scanner.nextLine();
        
        // At this point the game can begin. It will continue until someone loses
        int player1Hits = 0;
        int player2Hits = 0;
        boolean player1Turn = true;
        while (true) {
            if (player1Turn) {
                printGameBoard(player2Fogged);
                System.out.println("---------------------");
                printGameBoard(player1);
                System.out.println("Player 1, it's your turn:\n");
                boolean[] shotResults = takeAShot(player2, player2Fogged, scanner);
                boolean hit = shotResults[0];
                boolean shipSunk = shotResults[1];
                boolean spotAlreadyShot = shotResults[2];
                if (hit) {
                    if (!spotAlreadyShot) {
                        player1Hits++;
                    }
                    if (player1Hits == 17) {
                        System.out.println("You sank the last ship. You won. Congratulations!");
                        break;
                    }
                    if (shipSunk) {
                        System.out.println("You sank a ship!");
                    } else {
                        System.out.println("\nYou hit a ship!");
                    }
                } else {
                    System.out.println("\nYou missed!");
                }
                player1Turn = !player1Turn;
                System.out.println("Press Enter and pass the move to another player\n...");
                scanner.nextLine();

            } else {
                printGameBoard(player1Fogged);
                System.out.println("---------------------");
                printGameBoard(player2);
                System.out.println("Player 2, it's your turn:\n");
                boolean[] shotResults = takeAShot(player1, player1Fogged, scanner);
                boolean hit = shotResults[0];
                boolean shipSunk = shotResults[1];
                boolean spotAlreadyShot = shotResults[2];
                if (hit) {
                    if (!spotAlreadyShot) {
                        player2Hits++;
                    }
                    if (player2Hits == 17) {
                        System.out.println("You sank the last ship. You won. Congratulations!");
                        break;
                    }
                    if (shipSunk) {
                        System.out.println("You sank a ship!");
                    } else {
                        System.out.println("\nYou hit a ship!");
                    }
                } else {
                    System.out.println("\nYou missed!");
                }
                player1Turn = !player1Turn;
                System.out.println("Press Enter and pass the move to another player\n...");
                scanner.nextLine();


            }
            scanner.nextLine();
            

        }


        scanner.close();
        

    }
}
