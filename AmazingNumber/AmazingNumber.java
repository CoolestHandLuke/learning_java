package AmazingNumber;
import java.util.Scanner;

public class AmazingNumber {

    // Define the properties for each number
    private long value;
    private boolean isEven;
    private boolean isOdd;
    private boolean isBuzz;
    private boolean isDuck;
    private boolean isPalindromic;

    AmazingNumber(long value) {
        this.value = value;
    }

    public static void printWelcome() {

        System.out.println("Welcome to Amazing Numbers!\n");
        System.out.println("Supported requests:");
        System.out.println("- enter a natural number to know its properties");
        System.out.println("- enter 0 to exit.\n");

    }

    public boolean isNatural() {
        if (this.value <= 0) {
            return  false;
        }
        return true;
    }

    public void parity() {
        if (this.value % 2 == 0) {
            this.isEven = true;
            this.isOdd = false;
        } else {
            this.isOdd = true;
            this.isEven = false;
        }
    }

    public void setBuzz() {
        
        boolean endsWith7 = this.value % 10 == 7 ? true : false;
        boolean divisibleBy7 = this.value % 7 == 0 ? true : false;
        if (endsWith7 || divisibleBy7) {
            this.isBuzz = true;
        } else {
            this.isBuzz = false;
        }

    }

    public void setDuck() {
        long temp = this.value;
        this.isDuck = false;
        while (temp > 0) {
            if (temp % 10 == 0) {
                this.isDuck = true;
                break;
            } else {
                temp = temp / 10;
            }
        }
    }

    public void setPalindromic() {

        long temp = this.value;
        long valueReversed = 0;
        while (temp > 0) {
            valueReversed *= 10;
            valueReversed += temp % 10;
            temp /= 10;
        }
        if (valueReversed == this.value) {
            this.isPalindromic = true;
        } else {
            this.isPalindromic = false;
        }
    }
    
    public void printProperties() {
        
        System.out.printf("\nProperties of %,d\n", this.value);
        System.out.printf("\teven: %b\n", this.isEven);
        System.out.printf("\t odd: %b\n", this.isOdd);
        System.out.printf("\tbuzz: %b\n", this.isBuzz);
        System.out.printf("\tduck: %b\n", this.isDuck);
        System.out.printf(" palindromic: %b\n\n", this.isPalindromic);

    }
    public void setProperties() {

        this.parity();
        this.setBuzz();
        this.setDuck();
        this.setPalindromic();

    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        printWelcome();
        while (true) {
            System.out.print("Enter a request: ");
            AmazingNumber amazingNum = new AmazingNumber(scanner.nextLong());
            if (amazingNum.value == 0) {
                System.out.println("\nGoodbye!");
                break;
            } else if (!amazingNum.isNatural()) {
                System.out.println("\nThe first parameter should be a natural number or zero.\n");
            } else {
                amazingNum.setProperties();
                amazingNum.printProperties();
            }
        }
        
        scanner.close();
    }
    
}
