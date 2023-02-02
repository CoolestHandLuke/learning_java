package cinema;
import java.util.Arrays;

public class Cinema {
    public static void main(String[] args) {

        char[][] seats = new char[7][8];
        int row = 1;
        for (int i = 0; i < seats.length; i++) {
            Arrays.fill(seats[i], 'S');
        }
        System.out.println("Cinema:");
        System.out.println("  1 2 3 4 5 6 7 8");
        for (int i = 0; i < seats.length; i++) {
            System.out.print(row + " ");
            for (int j = 0; j < seats[i].length; j++) {
                System.out.print(seats[i][j] + " ");
            }
            row++;
            System.out.println();
        }
    }
}
