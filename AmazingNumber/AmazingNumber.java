package AmazingNumber;
import java.util.Scanner;

public class AmazingNumber {

    // Define the properties for each number
    private int value;
    private boolean isEven;
    private boolean isOdd;
    private boolean isBuzz;
    private boolean isDuck;

    AmazingNumber(int value) {
        this.value = value;
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
        int temp = this.value;
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
    
    public void printProperties() {
        
        System.out.printf("Properties of %d\n", this.value);
        System.out.printf("even: %b\n", this.isEven);
        System.out.printf("odd: %b\n", this.isOdd);
        System.out.printf("buzz: %b\n", this.isBuzz);
        System.out.printf("duck: %b\n", this.isDuck);

    }
    public void setProperties() {

        this.parity();
        this.setBuzz();
        this.setDuck();
        
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a natural number:");
        AmazingNumber amazingNum = new AmazingNumber(scanner.nextInt());
        if (!amazingNum.isNatural()) {
            System.out.println("This number is not natural!");
        } else {
            amazingNum.setProperties();
            amazingNum.printProperties();
        }
        scanner.close();
    }
    
}
