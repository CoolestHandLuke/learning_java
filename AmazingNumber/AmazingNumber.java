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
    private boolean isGapful;
    private boolean isSpy;

    AmazingNumber(long value) {
        this.value = value;
    }

    public static void printInstructions() {

        System.out.println("Supported requests:");
        System.out.println("- enter a natural number to know its properties");
        System.out.println("- enter two natural numbers to obtain the properties of the list:");
        System.out.println("\t* the first parameter represents a starting number");
        System.out.println("\t* the second parameter shows how many consecutive numbers are to be processed");
        System.out.println("- two natural numbers and a property to search for");
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
    
    public void setSpy() {

        long temp = this.value;
        long sumOfDigits = 0;
        long productOfDigits = 1;
        while (temp > 0) {
            productOfDigits *= temp % 10;
            sumOfDigits += temp % 10;
            temp /= 10;
        }
        if (sumOfDigits == productOfDigits) {
            this.isSpy = true;
        } else {
            this.isSpy = false;
        }

    }

    public boolean isFilter(String filter) {

        switch (filter) {
            case "buzz": 
                return this.isBuzz;
            case "duck":
                return this.isDuck;
            case "palindromic":
                return this.isPalindromic;
            case "gapful":
                return this.isGapful;
            case "spy":
                return this.isSpy;
            case "even":
                return this.isEven;
            case "odd":
                return this.isOdd;
            default:
                return false;
        }
    }
    public void printSingleProperties() {
        
        System.out.printf("Properties of %,d\n", this.value);
        System.out.printf("\tbuzz: %b\n", this.isBuzz);
        System.out.printf("\tduck: %b\n", this.isDuck);
        System.out.printf(" palindromic: %b\n", this.isPalindromic);
        System.out.printf("      gapful: %b\n", this.isGapful);
        System.out.printf("\t spy: %b\n", this.isSpy);
        System.out.printf("\teven: %b\n", this.isEven);
        System.out.printf("\t odd: %b\n", this.isOdd);
       
    }

    public void printListProperties() {

        StringBuilder properties = new StringBuilder("is ");

        if (this.isBuzz) {
            properties.append("buzz, ");
        }
        if (this.isDuck) {
            properties.append("duck, ");
        }
        if (this.isPalindromic) {
            properties.append("palindromic, ");
        }
        if (this.isGapful) {
            properties.append("gapful, ");
        }
        if (this.isSpy) {
            properties.append("spy, ");
        }
        if (this.isEven) {
            properties.append("even");
        }
        if (this.isOdd) {
            properties.append("odd");
        }

        System.out.printf("%,d " + properties.toString() + "\n", this.value);
    }
    public void setProperties() {

        this.parity();
        this.setBuzz();
        this.setDuck();
        this.setPalindromic();
        this.setGapful();
        this.setSpy();

    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Amazing Numbers!\n");
        printInstructions();
        while (true) {
            System.out.print("\nEnter a request: ");
            String[] request = (scanner.nextLine().strip()).split(" ");
            System.out.println();
            if (request[0].equals("")) {

                printInstructions();

            } else if (request.length == 1) {

                try {
                    Long.parseLong(request[0]);
                }
                catch(NumberFormatException e) {
                    System.out.println("The first parameter should be a natural number or zero.");
                    continue;
                }
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

            } else if (request.length == 2) {
                try {
                    Long.parseLong(request[0]);
                }
                catch(NumberFormatException e) {
                    System.out.println("The first parameter should be a natural number or zero.");
                    continue;
                }
                try {
                    Long.parseLong(request[1]);
                }
                catch(NumberFormatException e) {
                    System.out.println("The second parameter should be a natural number.");
                    continue;
                }
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
            } else {
                StringBuilder filter = new StringBuilder();
                String[] properties = new String[] {"buzz", "duck", "palindromic", "gapful", "spy", "even", "odd"};
                for (int i = 0; i < properties.length; i++) {
                    if (request[2].equalsIgnoreCase(properties[i])) {
                        filter.append(properties[i]);
                        break;
                    }
                }
                if (filter.toString().equals("")) {
                    System.out.println("The property [" + request[2].toUpperCase() + "] is wrong.");
                    System.out.println("Available properties: [BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, EVEN, ODD]");
                } else {
                    int counter = 0;
                    int i = 0;
                    while (counter < Long.parseLong(request[1])) {
                        AmazingNumber amazingNum = new AmazingNumber(Long.parseLong(request[0]) + i);
                        amazingNum.setProperties();
                        if (amazingNum.isFilter(filter.toString())) {
                            amazingNum.printListProperties();
                            counter++;
                        }
                        i++;
                    }

                }
            }
        }
        
        scanner.close();
    }
    
}
