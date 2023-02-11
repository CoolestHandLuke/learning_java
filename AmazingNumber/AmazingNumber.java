/* This is my Amazing Number project for JetBrains academy. I spent more time on this project than anything I've written up until this point. 
* Probably the biggest takeaway from doing this whole time was learning to plan ahead and program defensively. I think I spent most of my time
* refactoring and modifying old code to handle new inputs rather than actually implementing new features. If I had thougth ahead and relized I 
* would be introducing new complicated features later it would have changed how I initially solved the problems. Lots of time would have been
* saved and the overall process would have been a lot smoother. Oh well, that's what building projects like these is about though, right?
* Luke Shea, 2/10/2023

* The program starts up and provides instructions for the user:
* Supported requests:
        - enter a natural number to know its properties
        - enter two natural numbers to obtain the properties of the list:
            * the first parameter represents a starting number
            * the second parameter shows how many consecutive numbers are to be processed
        - two natural numbers and properties to search for
        - a property preceded by minus must not be present in numbers
        - seperate the parameters with one space
        - enter 0 to exit.

* Properties that will be printed include:
*   buzz (number ends with 7 or is divisible by 7)
*   duck (number contains at least one 0 inside of it)
*   palindromic (number is the same forwards and backwards)
*   gapful (number is at least three digits long and is divisible by its first and last digits)
*   spy (sum of all a numbers digits is equal to the product of all its digits)
*   square (number can be presented as an integer multiplied by itself)
*   sunny (number is 1 short of being a square number)
*   jumping (all adjacenet digits in a number differ by 1)
*   happy (adding the square of the digits of a number, and repeating this process over and over, will eventually equal exactly 1)
*   sad (a number that is not happy)
*   even or odd (self explanatory)

 */

package AmazingNumber;
import java.util.ArrayList;
import java.util.Scanner;

public class AmazingNumber {

    // Define the properties for each number. Value stored as Long to handle large inputs
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
    private boolean isHappy;
    private boolean isSad;

    AmazingNumber(long value) {
        this.value = value;
    }
    public static void printInstructions() {

        // Instuctions are provided upon first running the program, and again if the user hits Enter without providing any input
        System.out.println("Supported requests:");
        System.out.println("- enter a natural number to know its properties");
        System.out.println("- enter two natural numbers to obtain the properties of the list:");
        System.out.println("\t* the first parameter represents a starting number");
        System.out.println("\t* the second parameter shows how many consecutive numbers are to be processed");
        System.out.println("- two natural numbers and properties to search for");
        System.out.println("- a property preceded by minus must not be present in numbers");
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
        // Ternary operators are fun
        boolean endsWith7 = this.value % 10 == 7 ? true : false;
        boolean divisibleBy7 = this.value % 7 == 0 ? true : false;
        if (endsWith7 || divisibleBy7) {
            this.isBuzz = true;
        } else {
            this.isBuzz = false;
        }

    }
    public void setDuck() {
        // Check for 0 in the number by modulus division and checking the value
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
        // I remember solving this problem on leetcode
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
        // To determine gapful, we need the first and last digit of the number. To do this, modulus the number by 10 to get the last digit. 
        // Then, divide the number by 10 raised to the power of the length of the number minus 1. This gives us the leading digit, multiply
        // this number by 10 before adding it to the last digit we got earlier.
        String temp = String.valueOf(this.value);
        if (temp.length() < 3) {
            this.isGapful = false;
        } else {
            long firstAndLast = this.value % 10 + 10 * (this.value /(long)Math.pow(10, temp.length() - 1));
            if (this.value % firstAndLast == 0) {
                isGapful = true;
            } else {
                isGapful = false;
            }
        }
    }   
    public void setSpy() {
        // Pretty straightforward, find the sum of digits and compare to the product of digits
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
        // Sunny and Square numbers are mutually exclusive, so we set them both in the same function. 
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
        // This one was slightly more challenging to solve. For every digit, we need to look at the digits next to it and see if they are 
        // within 1. If successful, slice off the last digit and repeat the process. Once we get down to 2 digits in length (< 100), we have 
        // to change the logic a little to make sure we don't get a logic error.
        long temp = this.value;
        while (temp > 10) {
            long onesPlace = temp % 10;
            long tensPlace = (temp % 100) / 10;
            long hundredsPlace = (temp % 1000) / 100;
            if (temp < 100) {
                if ((onesPlace + 1 == tensPlace || onesPlace - 1 == tensPlace)) {
                    break;
                } else {
                    this.isJumping = false;
                    return;
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
    public void setHappyAndSad() {
        // Happy and Sad number are mutually exclusive so we set them both here.
        // If a number is happy then it will eventually reach 1. However, if it is sad, performing the check over and 
        // over will result in an infinite loop of checking the same values over and over. Therefore, we have to create
        // an ArrayList to store the values we have already checked so we can determine if the value we are currently checking
        // has already been checked. If this is true, then we have just started the infinite cycle and the number is sad.
        ArrayList<Long> valuesTried = new ArrayList<>();
        valuesTried.add(this.value);
        while (true) {
            long temp = sumOfSquares(valuesTried.get(valuesTried.size() - 1));
            if (temp == 1) {
                this.isHappy = true;
                this.isSad = false;
                break;
            } else if (valuesTried.contains(temp)){
                this.isHappy = false;
                this.isSad = true;
                break;
            } else {
                valuesTried.add(temp);
            }
        }
    }
    public static long sumOfSquares(long value) {
        // This method is used to calculate and return the sum of squares of an AmazingNumber.value. I had to create a static
        // method like this because I could not figure out a way to make an instance method do the same without modifying the .value
        // of the Object.
        long sum = 0;
        while (value > 0) {
            long temp = value % 10;
            sum += Math.pow(temp, 2);
            value /= 10;
        }
        
        return sum;
        
    }
    public boolean isFilter(String filter) {
        // Determines if an instance is a certain property
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
            case "happy":
                return this.isHappy;
            case "sad":
                return this.isSad;
            case "even":
                return this.isEven;
            case "odd":
                return this.isOdd;
            case "-buzz": 
                return !this.isBuzz;
            case "-duck":
                return !this.isDuck;
            case "-palindromic":
                return !this.isPalindromic;
            case "-gapful":
                return !this.isGapful;
            case "-spy":
                return !this.isSpy;
            case "-square":
                return !this.isSquare;
            case "-sunny":
                return !this.isSunny;
            case "-jumping":
                return !this.isJumping;
            case "-happy":
                return !this.isHappy;
            case "-sad":
                return !this.isSad;
            case "-even":
                return !this.isEven;
            case "-odd":
                return !this.isOdd;
            default:
                return false;
        }
    }
    public void printSingleProperties() {
        // Prints out the properties of a single number in a nicely formatted way
        System.out.printf("Properties of %,d\n", this.value);
        System.out.printf("\tbuzz: %b\n", this.isBuzz);
        System.out.printf("\tduck: %b\n", this.isDuck);
        System.out.printf(" palindromic: %b\n", this.isPalindromic);
        System.out.printf("      gapful: %b\n", this.isGapful);
        System.out.printf("\t spy: %b\n", this.isSpy);
        System.out.printf("      square: %b\n", this.isSquare);
        System.out.printf("       sunny: %b\n", this.isSunny);
        System.out.printf("     jumping: %b\n", this.isJumping);
        System.out.printf("       happy: %b\n", this.isHappy);
        System.out.printf("         sad: %b\n", this.isSad);
        System.out.printf("\teven: %b\n", this.isEven);
        System.out.printf("\t odd: %b\n", this.isOdd);
       
    }
    public void printListProperties() {
        // If the user has requested a list of numbers to view, then they will be printed out in the below format
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
        if (this.isHappy) {
            properties.append("happy, ");
        }
        if (this.isSad) {
            properties.append("sad, ");
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
        // Lump all the properties here so I only have to make one method call in the main function
        this.parity();
        this.setBuzz();
        this.setDuck();
        this.setPalindromic();
        this.setGapful();
        this.setSpy();
        this.setSquareAndSunny();
        this.setJumping();
        this.setHappyAndSad();

    }
    public static void oneArgProperties(String[] request) {
        // Method called when the user has only entered one value to check the properties of
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
        // Method called when the user requests a list of numbers with no filters
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

    public static void threeOrMoreArgProperties(String[] request) {
        // Method called when a user requests a list of numbers with filters provided. Any filter provided 
        // that is preceeded by a minus sign indicates that only numbers that do NOT have that property should be printed.
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
            // If we get to here then the user has provided properly formatted input but we haven't determined if the input is actually valid.
            // The ArrayList badFilters will contain anything the user typed in that is not a proper filter. If anything is in 
            // here then an error message is displayed
            ArrayList<String> includeFilters = new ArrayList<>();
            ArrayList<String> removeFilters = new ArrayList<>();
            ArrayList<String> badFilters = new ArrayList<>();
            String[] includeProperties = new String[] {"buzz", "duck", "palindromic", "gapful", "spy", "square", "sunny", "jumping", "happy", "sad", "even", "odd"};
            String[] removeProperties = new String[] {"-buzz", "-duck", "-palindromic", "-gapful", "-spy", "-square", "-sunny", "-jumping", "-happy", "-sad", "-even", "-odd"};
            for (int i = 2; i < request.length; i++) {
                boolean badFilter = true;
                for (int j = 0; j < includeProperties.length; j++) {
                    if (request[i].equalsIgnoreCase(includeProperties[j])) {
                        includeFilters.add(includeProperties[j]);
                        badFilter = false;
                        break;
                    }
                    if (request[i].equalsIgnoreCase(removeProperties[j])) {
                        removeFilters.add(removeProperties[j]);
                        badFilter = false;
                        break;
                    }

                }
                if (badFilter) {
                    badFilters.add(request[i]);
                }
            }
            // Checking for bad filter inputs from the user
            if (badFilters.size() == 1) {
                System.out.println("The property " + badFilters.toString().toUpperCase() + " is wrong.");
                System.out.println("Available properties: [BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, SQUARE, SUNNY, JUMPING, HAPPY, SAD, EVEN, ODD]");
            } else if (badFilters.size() > 1) {
                System.out.println("The properties " + badFilters.toString().toUpperCase() + " are wrong.");
                System.out.println("Available properties: [BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, SQUARE, SUNNY, JUMPING, HAPPY, SAD, EVEN, ODD]");
            } else {
                // Check for mutually exclusive properties in the includeFilters. Asking for numbers that are both even and odd, things like that.
                for (int i = 0; i < includeFilters.size(); i++) {
                    for (int j = i + 1; j < includeFilters.size(); j++) {
                        if (mutuallyExclusive(includeFilters.get(i), includeFilters.get(j))) {
                            ArrayList<String> mutuallyExclusiveFilters = new ArrayList<String>();
                            mutuallyExclusiveFilters.add(includeFilters.get(i));
                            mutuallyExclusiveFilters.add(includeFilters.get(j));
                            System.out.println("The request contains mutually exclusive properties: " + mutuallyExclusiveFilters.toString().toUpperCase());
                            System.out.println("There are no numbers with these properties.");
                            return;
                        }
                    }
                }
                // Check for mutually exclusive properties in the removeFilters. Same as before.
                for (int i = 0; i < removeFilters.size(); i++) {
                    for (int j = i + 1; j < removeFilters.size(); j++) {
                        if (mutuallyExclusive(removeFilters.get(i), removeFilters.get(j))) {
                            ArrayList<String> mutuallyExclusiveFilters = new ArrayList<String>();
                            mutuallyExclusiveFilters.add(removeFilters.get(i));
                            mutuallyExclusiveFilters.add(removeFilters.get(j));
                            System.out.println("The request contains mutually exclusive properties: " + mutuallyExclusiveFilters.toString().toUpperCase());
                            System.out.println("There are no numbers with these properties.");
                            return;
                        }
                    }
                }
                // Check for mutually exclusive properties between the includeFilters and removeFilters. Asking for numbers that 
                // are both even and NOT odd, things like that.
                for (int i = 0; i < removeFilters.size(); i++) {
                    for (int j = 0; j < includeFilters.size(); j++) {
                        if (mutuallyExclusive(removeFilters.get(i), includeFilters.get(j))) {
                            ArrayList<String> mutuallyExclusiveFilters = new ArrayList<String>();
                            mutuallyExclusiveFilters.add(removeFilters.get(i));
                            mutuallyExclusiveFilters.add(includeFilters.get(j));
                            System.out.println("The request contains mutually exclusive properties: " + mutuallyExclusiveFilters.toString().toUpperCase());
                            System.out.println("There are no numbers with these properties.");
                            return;
                        }
                    }
                }
                // We've made it here, so the users inputs are properly formatted, correct, and not logically impossible, so now it is time
                // to actually start looping through numbers and determining if they fit the criteria. We set two counters, one will count how 
                // many good numbers we have found, the other will increment for each loop we make (used to move to the next number). Once we 
                // have found the amount of good numbers the user requested then the loop exits.
                int numbersPrintedCounter = 0;
                int increment = 0;
                while (numbersPrintedCounter < Long.parseLong(request[1])) {
                    AmazingNumber amazingNum = new AmazingNumber(Long.parseLong(request[0]) + increment);
                    amazingNum.setProperties();
                    int filtersMatched = 0;
                    int removeFiltersMatched = 0;
                    for (int i = 0; i < includeFilters.size(); i++) {
                        if (amazingNum.isFilter(includeFilters.get(i))) {
                            filtersMatched++;
                        }
                    }
                    for (int i = 0; i < removeFilters.size(); i++) {
                        if (amazingNum.isFilter(removeFilters.get(i))) {
                            removeFiltersMatched++;
                        }
                    }
                        if (filtersMatched == includeFilters.size() && removeFiltersMatched == removeFilters.size()) {
                            amazingNum.printListProperties();
                            numbersPrintedCounter++;
                        }
                    
                    
                    increment++;
                }

            }
        }
    }

    public static boolean mutuallyExclusive(String filterOne, String filterTwo) {
        // Determines if the users requested filters are logically impossible (asking for numbers that are both even and odd, etc.)
        switch (filterOne) {
            case "even":
                return filterTwo.equals("odd");
            case "-even":
                return filterTwo.equals("-odd") || filterTwo.equals("even");
            case "odd":
                return filterTwo.equals("even");
            case "-odd":
                return filterTwo.equals("-even") || filterTwo.equals("odd");   
            case "duck":
                return filterTwo.equals("spy");
            case "-duck":
                return filterTwo.equals("-spy") || filterTwo.equals("duck");   
            case "spy":
                return filterTwo.equals("duck");
            case "-spy":
                return filterTwo.equals("-duck") || filterTwo.equals("spy");    
            case "sunny":
                return filterTwo.equals("square");
            case "-sunny":
                return filterTwo.equals("sunny");    
            case "square":
                return filterTwo.equals("sunny");
            case "-square":
                return filterTwo.equals("square");    
            case "jumping":
                return filterTwo.equals("-jumping");
            case "-jumping":
                return filterTwo.equals("jumping");
            case "happy":
                return filterTwo.equals("sad");
            case "-happy":
                return filterTwo.equals("-sad") || filterTwo.equals("happy");
            case "sad":
                return filterTwo.equals("happy");
            case "-sad":
                return filterTwo.equals("-happy") || filterTwo.equals("sad");
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
                default:
                    threeOrMoreArgProperties(request);;
                }
            }
        }
             
        scanner.close();
    }
    
}
