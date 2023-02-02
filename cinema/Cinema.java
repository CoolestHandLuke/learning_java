/* My humble attemp at a cinema/movie theater ticket tracking system. Upon running the program the user
 * will be required to enter in the rows and seats per row. This will be the size of their movie theater. 
 * After that, they can interact with the system by buying tickets, displaying the seat layout to see which 
 * seats have already been sold, displaying statistics about all the sold tickets and possible income, and 
 * exiting the program. Working through this project helped me to better understand how to decompose larger 
 * programs in to smaller chunks of functions. I also learned more about exception/error handling, as this 
 * program can tolerate users entering bad data.
 */
package cinema;
import java.util.*;

public class Cinema {
    public static void main(String[] args) {

        // Initial startup, ask user for rows and seats per row
        int rows, seatsPerRow, userSelection, totalIncome = 0;
        int currentIncome = 0;
        int purchasedTickets = 0;
        double percentage = 0;
        boolean usingMenu = true;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of rows:");
        rows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        seatsPerRow = scanner.nextInt();
        System.out.println();
        char[][] seats = new char[rows][seatsPerRow];
        // Calculate total possible income
        if  (rows * seatsPerRow <= 60) {
            totalIncome = rows * seatsPerRow * 10;
        } else if (rows * seatsPerRow > 60) {
            totalIncome = (rows / 2) * seatsPerRow * 10 + (rows / 2 + rows % 2) * seatsPerRow * 8;
        }
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
                    currentIncome += buyTicket(seats);
                    purchasedTickets++;
                    percentage = ((double)purchasedTickets / ((double)rows * (double)seatsPerRow)) * 100;
                    break;
                case 3: 
                    showStatistics(purchasedTickets, percentage, currentIncome, totalIncome);
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
        System.out.println("3. Statistics");
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

    public static int buyTicket(char[][] theaterSeats) {

        // Let the user pick a seat and tell them the price
        int price = 0;
        int userRow, userSeatNumber = 0;
        Scanner scanner = new Scanner(System.in);
        // Loop until the user provides valid input
        while (true) {

            System.out.println("Enter a row number:");
            userRow = scanner.nextInt();
            System.out.println("Enter a seat number in that row:");
            userSeatNumber = scanner.nextInt();
            System.out.println();
            // Check to make sure that seat isn't already taken.
            if (userRow > theaterSeats.length || userSeatNumber > theaterSeats[0].length) {
                System.out.println("Wrong input!");
                System.out.println();
            } else if (theaterSeats[userRow - 1][userSeatNumber - 1] == 'B') {
                System.out.println("That ticket has already been purchased!");
                System.out.println();
            }
             else {
                break;
            }
        }
        // Calculate and print out the cost for the seat        
        if (theaterSeats.length * theaterSeats[0].length <= 60) {
            System.out.println("Ticket price: $10");
            price = 10;
        } else if (theaterSeats.length * theaterSeats[0].length > 60) {
            if (userRow <= theaterSeats.length / 2) {
                System.out.println("Ticket price: $10");
                price = 10;
            } else {
                System.out.println("Ticket price: $8");
                price = 8;
            }
        }
        System.out.println();
        // Set the seat to sold
        theaterSeats[userRow - 1][userSeatNumber - 1] = 'B';
        return price;
    }

    public static void showStatistics(int purchasedTickets, double percentage, int currentIncome, int totalIncome) {
        System.out.println();
        System.out.println("Number of purchased tickets: " + purchasedTickets);
        System.out.printf("Percentage: %.2f%%", percentage);
        System.out.println();
        System.out.println("Current income: $" + currentIncome);
        System.out.println("Total income: $" + totalIncome);
        System.out.println();
    }
        
 }

