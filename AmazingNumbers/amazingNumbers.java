package AmazingNumbers;
import java.util.Scanner;

public class amazingNumbers {

    public static boolean isNatural(int n) {
        if (n <= 0) {
            return false;
        }
        return true;
    }

    public static void parity(int n) {
        if (n % 2 == 0) {
            System.out.println("This number is Even.");
        } else {
            System.out.println("This number is Odd.");
        }
    }

    public static void buzzNumber(int n) {
        
        boolean endsWith7 = n % 10 == 7 ? true : false;
        boolean divisibleBy7 = n % 7 == 0 ? true : false;
        if (endsWith7 || divisibleBy7) {
            System.out.println("It is a Buzz number.");
            System.out.println("Explanation:");
            // Both cases true
            if (endsWith7 && divisibleBy7) {
                System.out.printf("%d is divisible by 7 and ends with 7.\n", n);
            } else if (endsWith7) {
                System.out.printf("%d ends with 7.\n", n);
            } else {
                System.out.printf("%d is divisible by 7.\n", n);
            }
            
        } else {
            System.out.println("It is not a Buzz number.");
            System.out.println("Explanation:");
            System.out.printf("%d is neither divisible by 7 nor does it end with 7.\n", n);
        }

    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a natural number:");
        int num = scanner.nextInt();
        if (isNatural(num)) {
            parity(num);
            buzzNumber(num);
        } else {
            System.out.println("This number is not natural!");
        }
        scanner.close();
    }
    
}
