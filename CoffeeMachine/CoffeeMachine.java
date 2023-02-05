/* Another project learning how to write in Java. This one models a coffee machine, it presents a menu to the user to select an option
 * of either buying a coffee, filling the machine, cleaning out the machines money stash, or simply presenting the remaining supplies 
 * in the machine. Typing "exit" allows them to quit the machine. 
 * Writing this program helped to give me a better understanding of control flow and decomposing projects down in to smaller pieces.
 * It also forced me to try to think/plan ahead to better factor my code for future iterations and design improvements. 
 * Time willing, I may refactor this code in to a GUI and make it even more interactive, just as an exercise. We shall see!
 * Luke Shea, 2/4/2023
 */
package CoffeeMachine;
import java.util.*;

public class CoffeeMachine {
    public static void main(String[] args) {
        // Machine starting variables
        Scanner scanner = new Scanner(System.in);
        int[] moneyWaterMilkBeansCups = new int[] {550, 400, 540, 120, 9};
        boolean usingMachine = true;

        // Get and process input
        do {
        System.out.println("Write action (buy, fill, take, remaining, exit):");
        String selection = scanner.next();
        switch (selection) {
            case "buy":
                System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:");
                String drinkChoice = scanner.next();
                if (drinkChoice.equalsIgnoreCase("back")) {
                    break;
                } else {
                    makeCoffee(moneyWaterMilkBeansCups, Integer.parseInt(drinkChoice));
                }
                break;
            case "fill":
                fillMachine(moneyWaterMilkBeansCups);
                break;
            case "take":
                System.out.printf("I gave you $%d\n", moneyWaterMilkBeansCups[0]);
                moneyWaterMilkBeansCups[0] = 0;
                break;
            case "remaining":
                printState(moneyWaterMilkBeansCups);
                break;
            case "exit":
                usingMachine = false;
                break;
            default:
                System.out.println("Invalid choice");
                break;
            }
        } while (usingMachine == true);
        
        scanner.close();

    }

    public static void printState(int[] machineContents) {
        System.out.println();
        System.out.println("The coffee machine has:");
        System.out.printf("%d ml of water\n", machineContents[1]);
        System.out.printf("%d ml of milk\n", machineContents[2]);
        System.out.printf("%d g of coffee beans\n", machineContents[3]);
        System.out.printf("%d disposable cups\n", machineContents[4]);
        System.out.printf("$%d of money\n\n", machineContents[0]);
    }

    public static void makeCoffee(int[] machineContents, int drinkChoice) {
        // moneyWaterMilkBeansCups
        if (enoughResources(machineContents, drinkChoice)) {
            System.out.println("I have enough resources, making you a coffee!\n");
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
    }

    public static boolean enoughResources(int[] machineContents, int drinkChoice) {
        if (drinkChoice == 1) { // Espresso
            if (machineContents[1] < 250) {
                System.out.println("Sorry, not enough water!\n");
                return false;
            } else if (machineContents[3] < 16) {
                System.out.println("Sorry, not enough coffee beans!\n");
                return false;
            } else if (machineContents[4] == 0) {
                System.out.println("Sorry, no cups left!\n");
                return false;
            } else {
                return true;
            }
        } else if (drinkChoice == 2) { // Latte
            if (machineContents[1] < 350) {
                System.out.println("Sorry, not enough water!\n");
                return false;
            } else if (machineContents[2] < 75) {
                System.out.println("Sorry, not enough milk!\n");
                return false;
            } else if (machineContents[3] < 20) {
                System.out.println("Sorry, not enough coffee beans!\n");
                return false;
            } else if (machineContents[4] == 0) {
                System.out.println("Sorry, no cups left!\n");
                return false;
            } else {
                return true;
            }
        } else if (drinkChoice == 3) { // Cappuccino
            if (machineContents[1] < 200) {
                System.out.println("Sorry, not enough water!\n");
                return false;
            } else if (machineContents[2] < 100) {
                System.out.println("Sorry, not enough milk!\n");
                return false;
            } else if (machineContents[3] < 12) {
                System.out.println("Sorry, not enough coffee beans!\n");
                return false;
            } else if (machineContents[4] == 0) {
                System.out.println("Sorry, no cups left!\n");
                return false;
            } else {
                return true;
            }
        } else { // An invalid choice
            System.out.println("Invalid choice\n");
            return false;
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
        System.out.println();
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
