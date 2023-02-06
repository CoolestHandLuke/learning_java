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
    public boolean isGapful;

    AmazingNumber(long value) {
        this.value = value;
    }

    public static void printInstructions() {

        System.out.println("Supported requests:");
        System.out.println("- enter a natural number to know its properties");
        System.out.println("- enter two natural numbers to obtain the properties of the list:");
        System.out.println("\t* the first parameter represents a starting number");
        System.out.println("\t* the second parameter shows how many consecutive numbers are to be processed");
        System.out.println("- seperate the parameters with one space");
        System.out.println("- enter 0 to exit.");

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

    public void setGapful() {

        String temp = String.valueOf(this.value);
        if (temp.length() < 3) {
            this.isGapful = false;
        } else {
            // Modulus 10 gets the one's digit, then the second part is a division of 10 raised to the power of one less than the length of
            // the value in string form. In other words, the first digit of value, however big it is. Multiply it by 10 before adding it to the
            // result of the first part.
            long firstAndLast = this.value % 10 + 10 * (this.value /(long)Math.pow(10, temp.length() - 1));
            if (this.value % firstAndLast == 0) {
                isGapful = true;
            } else {
                isGapful = false;
            }
        }
    }   
    
    public void printSingleProperties() {
        
        System.out.printf("Properties of %,d\n", this.value);
        System.out.printf("\tbuzz: %b\n", this.isBuzz);
        System.out.printf("\tduck: %b\n", this.isDuck);
        System.out.printf(" palindromic: %b\n", this.isPalindromic);
        System.out.printf("      gapful: %b\n", this.isGapful);
        System.out.printf("\teven: %b\n", this.isEven);
        System.out.printf("\t odd: %b\n", this.isOdd);
       
    }

    public void printListProperties() {

        String properties = "is ";
        if (this.isBuzz) {
            properties = properties.concat("buzz, ");
        }
        if (this.isDuck) {
            properties = properties.concat("duck, ");
        }
        if (this.isPalindromic) {
            properties = properties.concat("palindromic, ");
        }
        if (this.isGapful) {
            properties = properties.concat("gapful, ");
        }
        if (this.isEven) {
            properties = properties.concat("even");
        }
        if (this.isOdd) {
            properties = properties.concat("odd");
        }

        System.out.printf("%,d " + properties + "\n", this.value);
    }
    public void setProperties() {

        this.parity();
        this.setBuzz();
        this.setDuck();
        this.setPalindromic();
        this.setGapful();

    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Amazing Numbers!\n");
        printInstructions();
        while (true) {
            System.out.print("\nEnter a request: ");
            String[] request = (scanner.nextLine()).split(" ");
            System.out.println();
            if (request[0].equals("")) {

                printInstructions();

            } else if (request.length == 1) {

                AmazingNumber amazingNum = new AmazingNumber(Long.parseLong(request[0]));
                if (amazingNum.value == 0) {
                    System.out.println("Goodbye!");
                    System.out.println("\nProcess finished with exit code 0");
                    break;
                } else if (!amazingNum.isNatural()) {
                    System.out.println("The first parameter should be a natural number or zero.");
                } else {
                    amazingNum.setProperties();
                    amazingNum.printSingleProperties();
                }

            } else {
                if (Long.parseLong(request[0]) < 0) {

                    System.out.println("The first parameter should be a natural number or zero.");

                }
                else if (Long.parseLong(request[1]) <= 0) {

                    System.out.println("The second parameter should be a natural number.");

                } else {
                
                    for (int i = 0; i < Long.parseLong(request[1]); i++) {

                        AmazingNumber amazingNum = new AmazingNumber(Long.parseLong(request[0]) + i);
                        amazingNum.setProperties();
                        amazingNum.printListProperties();

                    }
                }
            }
        }
        
        scanner.close();
    }
    
}
