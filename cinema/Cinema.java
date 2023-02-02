package cinema;
import java.util.*;

public class Cinema {
    public static void main(String[] args) {

        char[][] seats = new char[7][8];
        int rowIndex = 1;
        int rows, seatsPerRow, totalIncome = 0;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the number of rows:");
        rows = scanner.nextInt();
        System.out.println("Please enter the number of seats in each row:");
        seatsPerRow = scanner.nextInt();
        System.out.println("Total income:");
        if (rows * seatsPerRow <= 60) {
            totalIncome = rows * seatsPerRow * 10;
            System.out.println("$" + totalIncome);
        } else if (rows * seatsPerRow > 60) {
            totalIncome = ((rows / 2) * seatsPerRow) * 10 + ((rows / 2 + rows % 2) * seatsPerRow) * 8;
            System.out.println("$" + totalIncome);
        }

        /*
        for (int i = 0; i < seats.length; i++) {
            Arrays.fill(seats[i], 'S');
        }
        System.out.println("Cinema:");
        System.out.println("  1 2 3 4 5 6 7 8");
        for (int i = 0; i < seats.length; i++) {
            System.out.print(rowIndex + " ");
            for (int j = 0; j < seats[i].length; j++) {
                System.out.print(seats[i][j] + " ");
            }
            rowIndex++;
            System.out.println();
        }
        */
    }
}
