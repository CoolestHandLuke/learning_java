package AmazingNumber;
import java.util.ArrayList;
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
    private boolean isHappy;
    private boolean isSad;

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
        
        long sum = 0;
        while (value > 0) {
            long temp = value % 10;
            sum += Math.pow(temp, 2);
            value /= 10;
        }
        
        return sum;
        
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

    public static void threeOrMoreArgProperties(String[] request) {
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
            if (badFilters.size() == 1) {
                System.out.println("The property " + badFilters.toString().toUpperCase() + " is wrong.");
                System.out.println("Available properties: [BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, SQUARE, SUNNY, JUMPING, HAPPY, SAD, EVEN, ODD]");
            } else if (badFilters.size() > 1) {
                System.out.println("The properties " + badFilters.toString().toUpperCase() + " are wrong.");
                System.out.println("Available properties: [BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, SQUARE, SUNNY, JUMPING, HAPPY, SAD, EVEN, ODD]");
            } else {
                // Check for mutually exclusive properties in the includeFilters
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
                // Check for mutually exclusive properties in the removeFilters
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
                // Check for mutually exclusive properties between the includeFilters and removeFilters
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
