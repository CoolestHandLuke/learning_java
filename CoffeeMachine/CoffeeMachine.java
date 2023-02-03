package CoffeeMachine;
import java.util.*;

public class CoffeeMachine {
    public static void main(String[] args) {
        // Machine starting variables
        Scanner scanner = new Scanner(System.in);
        int[] moneyWaterMilkBeansCups = new int[] {550, 400, 540, 120, 9};

        // Print state
        printState(moneyWaterMilkBeansCups);

        // Get and process input
        System.out.println("Write action (buy, fill, take):");
        String selection = scanner.next();
        switch (selection) {
            case "buy":
                System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino:");
                int drinkChoice = scanner.nextInt();
                makeCoffee(moneyWaterMilkBeansCups, drinkChoice);
                break;
            case "fill":
                fillMachine(moneyWaterMilkBeansCups);
                break;
            case "take":
                System.out.printf("I gave you $%d\n", moneyWaterMilkBeansCups[0]);
                moneyWaterMilkBeansCups[0] = 0;
                break;
            default:
                System.out.println("Invalid choice");
                break;
        }
        // Print updated state
        System.out.println();
        printState(moneyWaterMilkBeansCups);
        scanner.close();

    }

    public static void printState(int[] machineContents) {
        System.out.println("The coffee machine has:");
        System.out.printf("%d ml of water\n", machineContents[1]);
        System.out.printf("%d ml of milk\n", machineContents[2]);
        System.out.printf("%d g of coffee beans\n", machineContents[3]);
        System.out.printf("%d disposable cups\n", machineContents[4]);
        System.out.printf("$%d of money\n\n", machineContents[0]);
    }

    public static void makeCoffee(int[] machineContents, int drinkChoice) {
        // moneyWaterMilkBeansCups
        System.out.println();
        if (drinkChoice == 1) { // Espresso
            machineContents[1] -= 250;
            machineContents[3] -= 16;
            machineContents[0] += 4;
            machineContents[4]--;
        } else if (drinkChoice == 2) { // Latte
            machineContents[1] -= 350;
            machineContents[2] -= 75;
            machineContents[3] -= 20;
            machineContents[0] += 7;
            machineContents[4]--;
        } else if (drinkChoice == 3) { // Cappuccino
            machineContents[1] -= 200;
            machineContents[2] -= 100;
            machineContents[3] -= 12;
            machineContents[0] += 6;
            machineContents[4]--;
        } else { // An invalid selection
            System.out.println("Invalid selection");
        }
    }
    public static void howMuchCoffee(int mlWater, int mlMilk, int gCoffeeBeans, int cupsRequested) {
        int mlWaterPerCup = 200;
        int mlMilkPerCup = 50;
        int gCoffeeBeansPerCup = 15;
        int cupsPossible = 0;
        int extraCupsPossible = 0;
        
        // Most cups we can make will be limited by our smallest quantity ingredient, so look for the min value, ignoring decimals
        cupsPossible = Math.min(mlWater / mlWaterPerCup, mlMilk / mlMilkPerCup);
        cupsPossible = Math.min(cupsPossible, gCoffeeBeans / gCoffeeBeansPerCup);
        extraCupsPossible = cupsPossible - cupsRequested; // Possibly negative

        // Check to see what's possible
        if (extraCupsPossible > 0) {
            System.out.printf("Yes, I can make that amount of coffee (and even %d more than that)\n", extraCupsPossible);
        } else if (cupsPossible == cupsRequested) {
            System.out.println("Yes, I can make that amount of coffee");
        } else {
            System.out.printf("No, I can only make %d cup(s) of coffee\n", cupsPossible);
        }
    }
    public static void makeCoffee() {
        System.out.println("Starting to make a coffee");
        System.out.println("Grinding coffee beans");
        System.out.println("Boiling water");
        System.out.println("Mixing boiled water with crushed coffee beans");
        System.out.println("Pouring coffee into the cup");
        System.out.println("Pouring some milk into the cup");
        System.out.println("Coffee is ready!");

    }
    public static void fillMachine(int[] machineContents) {
          // moneyWaterMilkBeansCups
        Scanner fill = new Scanner(System.in);
        System.out.println("Write how many ml of water you want to add:");
        machineContents[1] += fill.nextInt();
        System.out.println("Write how many ml of milk you want to add:");
        machineContents[2] += fill.nextInt();
        System.out.println("Write how many grams of coffee beans you want to add:");
        machineContents[3] += fill.nextInt();
        System.out.println("Write how many disposable cups you want to add:");
        machineContents[4] += fill.nextInt();
        fill.close();
    }
    public static void coffeeRequirements() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Write how many cups of coffee you will need:");
        int cupsOfCoffee = scanner.nextInt();
        int mlWater = 200 * cupsOfCoffee;
        int mlMilk = 50 * cupsOfCoffee;
        int gCoffeeBeans = 15 * cupsOfCoffee;
        System.out.println("For " + cupsOfCoffee + " cups of coffee you will need:");
        System.out.println(mlWater + " ml of water");
        System.out.println(mlMilk + " ml of milk");
        System.out.println(gCoffeeBeans + " g of coffee beans");
        scanner.close();

    }
}
