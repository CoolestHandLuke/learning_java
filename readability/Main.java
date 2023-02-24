package readability;

import java.util.Scanner;

public class Main {
    
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int textInput = scanner.nextLine().length();

        if (textInput > 100) {
            System.out.println("HARD");
        } else {
            System.out.println("EASY");
        }
        scanner.close();
     }
}
