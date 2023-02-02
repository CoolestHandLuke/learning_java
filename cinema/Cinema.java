package cinema;
import java.util.*;

public class Cinema {
    public static void main(String[] args) {

        // Initial startup, ask user for rows and seats per row
        int rows, seatsPerRow, userSelection = 0;
        boolean usingMenu = true;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of rows:");
        rows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        seatsPerRow = scanner.nextInt();
        System.out.println();
        char[][] seats = new char[rows][seatsPerRow];
        // Fill array with S's
        for (int i = 0; i < seats.length; i++) {
            Arrays.fill(seats[i], 'S');
        }
        // Start the main loop, continue asking for input until exit condition is met
        do {
            printMenu();
            userSelection = scanner.nextInt();
            switch (userSelection) {
                case 1: 
                    showSeats(seats);
                    break;
                case 2: 
                    buyTicket(seats);
                    break;
                case 0:
                    usingMenu = false;
                    scanner.close();
                    break;
                default:
                    System.out.println("Invalid Input");
                    System.out.println();

            }

        } while (usingMenu == true);
    }

    public static void printMenu() {
        System.out.println("1. Show the seats");
        System.out.println("2. Buy a ticket");
        System.out.println("0. Exit");
    }
    
    public static void showSeats(char[][] theaterSeats) {

        System.out.println("Cinema:");
        System.out.print(" ");
        // Print out the seat column numbers
        for (int i = 1; i <= theaterSeats[0].length; i++) {
            System.out.print(" " + i);
        }
        System.out.println();
        // Print out the row numbers and seat indicators
        for (int i = 0; i < theaterSeats.length; i++) {
            System.out.print(i + 1 + " ");
            for (int j = 0; j < theaterSeats[i].length; j++) {
                System.out.print(theaterSeats[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void buyTicket(char[][] theaterSeats) {

        // Let the user pick a seat and tell them the price
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a row number:");
        int userRow = scanner.nextInt();
        System.out.println("Enter a seat number in that row:");
        int userSeatNumber = scanner.nextInt();
        System.out.println();
        // Calculate and print out the cost for the seat        
        if (theaterSeats.length * theaterSeats[0].length <= 60) {
            System.out.println("Ticket price: $10");
        } else if (theaterSeats.length * theaterSeats[0].length > 60) {
            if (userRow <= theaterSeats.length / 2) {
                System.out.println("Ticket price: $10");
            } else {
                System.out.println("Ticket price: $8");
            }
        }
        System.out.println();
        // Set the seat to sold
        theaterSeats[userRow - 1][userSeatNumber - 1] = 'B';
    }
        
 }

