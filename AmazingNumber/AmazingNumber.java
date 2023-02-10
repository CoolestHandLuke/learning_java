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
    private boolean isSquare;
    private boolean isSunny;
    private boolean isJumping;

    AmazingNumber(long value) {
        this.value = value;
    }
    public static void printInstructions() {

        System.out.println("Supported requests:");
        System.out.println("- enter a natural number to know its properties");
        System.out.println("- enter two natural numbers to obtain the properties of the list:");
        System.out.println("\t* the first parameter represents a starting number");
        System.out.println("\t* the second parameter shows how many consecutive numbers are to be processed");
        System.out.println("- two natural numbers and properties to search for");
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
    public void setSquareAndSunny() {
        this.isSquare = false;
        this.isSunny = false;
        if (Math.sqrt(this.value) == (int)Math.sqrt(this.value)) {
            this.isSquare = true;
        }
        if (Math.sqrt(this.value + 1) == (int)Math.sqrt(this.value + 1)) {
            this.isSunny = true;
        }
    }
    public void setJumping() {
        long temp = this.value;
        while (temp > 10) {
            long onesPlace = temp % 10;
            long tensPlace = (temp % 100) / 10;
            long hundredsPlace = (temp % 1000) / 100;
            if (temp < 100) {
                if ((onesPlace + 1 == tensPlace || onesPlace - 1 == tensPlace)) {
                    break;
                }
            }
            else if ((onesPlace + 1 == tensPlace || onesPlace - 1 == tensPlace) && (hundredsPlace + 1 == tensPlace || hundredsPlace - 1 == tensPlace)) {
                temp /= 10;
            } else {
                this.isJumping = false;
                return;
            }
            
        }
        this.isJumping = true;
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
            case "square":
                return this.isSquare;
            case "sunny":
                return this.isSunny;
            case "jumping":
                return this.isJumping;
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
        System.out.printf("      square: %b\n", this.isSquare);
        System.out.printf("       sunny: %b\n", this.isSunny);
        System.out.printf("     jumping: %b\n", this.isJumping);
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
        if (this.isSquare) {
            properties.append("square, ");
        }
        if (this.isSunny) {
            properties.append("sunny, ");
        }
        if (this.isJumping) {
            properties.append("jumping, ");
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
        this.setSquareAndSunny();
        this.setJumping();

    }
    public static void oneArgProperties(String[] request) {
        try {
            Long.parseLong(request[0]);
        }
        catch(NumberFormatException e) {
            System.out.println("The first parameter should be a natural number or zero.");
            return;
        }
        AmazingNumber amazingNum = new AmazingNumber(Long.parseLong(request[0]));
        if (!amazingNum.isNatural()) {
            System.out.println("The first parameter should be a natural number or zero.");
        } else {
            amazingNum.setProperties();
            amazingNum.printSingleProperties();
        }
    }

    public static void twoArgProperties(String[] request) {
        try {
            Long.parseLong(request[0]);
        }
        catch(NumberFormatException e) {
            System.out.println("The first parameter should be a natural number or zero.");
            return;
        }
        try {
            Long.parseLong(request[1]);
        }
        catch(NumberFormatException e) {
            System.out.println("The second parameter should be a natural number.");
            return;
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
    }

    public static void threeArgProperties(String[] request) {
        try {
            Long.parseLong(request[0]);
        }
        catch(NumberFormatException e) {
            System.out.println("The first parameter should be a natural number or zero.");
            return;
        }
        try {
            Long.parseLong(request[1]);
        }
        catch(NumberFormatException e) {
            System.out.println("The second parameter should be a natural number.");
            return;
        }
        if (Long.parseLong(request[0]) < 0) {

            System.out.println("The first parameter should be a natural number or zero.");

        }
        else if (Long.parseLong(request[1]) <= 0) {

            System.out.println("The second parameter should be a natural number.");

        } else {
            StringBuilder filter = new StringBuilder();
            String[] properties = new String[] {"buzz", "duck", "palindromic", "gapful", "spy", "square", "sunny", "jumping", "even", "odd"};
            for (int i = 0; i < properties.length; i++) {
                if (request[2].equalsIgnoreCase(properties[i])) {
                    filter.append(properties[i]);
                    break;
                }
            }
            if (filter.toString().equals("")) {
                System.out.println("The property [" + request[2].toUpperCase() + "] is wrong.");
                System.out.println("Available properties: [BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, SQUARE, SUNNY, JUMPING, EVEN, ODD]");
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
    public static void fourArgProperties(String[] request) {

        if (request[2].equalsIgnoreCase(request[3])) {
            threeArgProperties(request);
            return;
        }
        try {
            Long.parseLong(request[0]);
        }
        catch(NumberFormatException e) {
            System.out.println("The first parameter should be a natural number or zero.");
            return;
        }
        try {
            Long.parseLong(request[1]);
        }
        catch(NumberFormatException e) {
            System.out.println("The second parameter should be a natural number.");
            return;
        }
        if (Long.parseLong(request[0]) < 0) {

            System.out.println("The first parameter should be a natural number or zero.");

        }
        else if (Long.parseLong(request[1]) <= 0) {

            System.out.println("The second parameter should be a natural number.");

        } else {
            StringBuilder filterOne = new StringBuilder();
            StringBuilder filterTwo = new StringBuilder();
            String[] properties = new String[] {"buzz", "duck", "palindromic", "gapful", "spy", "square", "sunny", "jumping", "even", "odd"};
            for (int i = 0; i < properties.length; i++) {
                if (request[2].equalsIgnoreCase(properties[i])) {
                    filterOne.append(properties[i]);
                }
                if (request[3].equalsIgnoreCase(properties[i])) {
                    filterTwo.append(properties[i]);
                }
            }
            if (filterOne.toString().equals("") && filterTwo.toString().equals("")) {
                System.out.println("The properties [" + request[2].toUpperCase() + ", " + request[3].toUpperCase() + "] are wrong.");
                System.out.println("Available properties: [BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, SQUARE, SUNNY, JUMPING, EVEN, ODD]");
            } else if(filterOne.toString().equals("")) {
                System.out.println("The property [" + request[2].toUpperCase() + "] is wrong.");
                System.out.println("Available properties: [BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, SQUARE, SUNNY, JUMPING, EVEN, ODD]");
            } else if (filterTwo.toString().equals("")) {
                System.out.println("The property [" + request[3].toUpperCase() + "] is wrong.");
                System.out.println("Available properties: [BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, SQUARE, SUNNY, JUMPING, EVEN, ODD]");
            }
            else if (mutuallyExclusive(filterOne.toString(), filterTwo.toString())) {
                System.out.println("The request contains mutually exclusive properties: [" + request[2].toUpperCase() + ", " + request[3].toUpperCase() + "]");
                System.out.println("There are no numbers with these properties.");
            }
            else {
                int counter = 0;
                int i = 0;
                while (counter < Long.parseLong(request[1])) {
                    AmazingNumber amazingNum = new AmazingNumber(Long.parseLong(request[0]) + i);
                    amazingNum.setProperties();
                    if (amazingNum.isFilter(filterOne.toString()) && amazingNum.isFilter(filterTwo.toString())) {
                        amazingNum.printListProperties();
                        counter++;
                    }
                    i++;
                }

            }
        }
    }
    public static boolean mutuallyExclusive(String filterOne, String filterTwo) {
        switch (filterOne) {
            case "even":
                return filterTwo.equals("odd");
            case "odd":
                return filterTwo.equals("even");
            case "duck":
                return filterTwo.equals("spy");
            case "spy":
                return filterTwo.equals("duck");
            case "sunny":
                return filterTwo.equals("square");
            case "square":
                return filterTwo.equals("sunny");
            case "jumping":
                return false;
            default:
                return false;
        }
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

            } else if (request[0].equals("0")) {
                System.out.println("Goodbye!");
                System.out.println("\nProcess finished with exit code 0");
                break;
            } else { 
                switch (request.length) {
                case 1:
                    oneArgProperties(request);
                    break;
                case 2:
                    twoArgProperties(request);
                    break;
                case 3:
                    threeArgProperties(request);
                    break;
                case 4:
                    fourArgProperties(request);
                    break;
                default:
                    System.out.println("You shouldn't see me");
                }
            }
        }
             
        scanner.close();
    }
    
}
