package cinema;
import java.util.*;

public class Cinema {
    public static void main(String[] args) {

        int rows, seatsPerRow, userRow, userSeatNumber = 0;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the number of rows:");
        rows = scanner.nextInt();
        System.out.println("Please enter the number of seats in each row:");
        seatsPerRow = scanner.nextInt();
        char[][] seats = new char[rows][seatsPerRow];

        // Fill array with S's
        for (int i = 0; i < seats.length; i++) {
            Arrays.fill(seats[i], 'S');
        }
        System.out.println("Cinema:");
        System.out.print(" ");
        // Print out the ceat column numbers
        for (int i = 1; i <= seatsPerRow; i++) {
            System.out.print(" " + i);
        }
        System.out.println();
        // Print out the row numbers and seat indicators
        for (int i = 0; i < seats.length; i++) {
            System.out.print(i + 1 + " ");
            for (int j = 0; j < seats[i].length; j++) {
                System.out.print(seats[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();

        // Let the user pick a seat and tell them the price
        System.out.println("Enter a row number:");
        userRow = scanner.nextInt();
        System.out.println("Enter a seat number in that row:");
        userSeatNumber = scanner.nextInt();
        System.out.println();
        // Calculate and print out the cost for the seat        
        if (rows * seatsPerRow <= 60) {
            System.out.println("Ticket price: $10");
        } else if (rows * seatsPerRow > 60) {
            if (userRow <= rows / 2) {
                System.out.println("Ticket price: $10");
            } else {
                System.out.println("Ticket price: $8");
            }
        }
        System.out.println();
        // Set the seat to sold
        seats[userRow - 1][userSeatNumber - 1] = 'B';
        // Print out the updated seating arrangement
        System.out.println("Cinema:");
        System.out.print(" ");
        // Print out the ceat column numbers
        for (int i = 1; i <= seatsPerRow; i++) {
            System.out.print(" " + i);
        }
        System.out.println();
        // Print out the row numbers and seat indicators
        for (int i = 0; i < seats.length; i++) {
            System.out.print(i + 1 + " ");
            for (int j = 0; j < seats[i].length; j++) {
                System.out.print(seats[i][j] + " ");
            }
            System.out.println();
        }
        
    }
}
