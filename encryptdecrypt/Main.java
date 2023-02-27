package encryptdecrypt;
import java.util.Scanner;

public class Main {
    
    public static void main(String[] args) {

        // read a target operation, then a string, then a key
        // Make a decision based on the inputs and print out the result

        Scanner scanner = new Scanner(System.in);
        String flag = scanner.nextLine();
        int key = 0;

        switch(flag) {
            case "enc":
                String messageToEncrypt = scanner.nextLine();
                key = scanner.nextInt();
                scanner.nextLine(); // eat the \n from the previous input
                enc(messageToEncrypt, key);
                break;
            case "dec":
                String messageToDecrypt = scanner.nextLine();
                key = scanner.nextInt();
                scanner.nextLine(); // eat the \n from the previous input
                dec(messageToDecrypt, key);
                break;
            default:
                System.out.println("Invalid input");

        }

        
        
        scanner.close();
        

    }

    public static void enc(String messageToEncrypt, int key) {

        // Encrypt everything now, not just characters, so don't need to check anymore
        StringBuilder encryptedMessage = new StringBuilder();
        for (int i = 0; i < messageToEncrypt.length(); i++) {
            // Convert it to cyphertext in lowercases based on the input key
            encryptedMessage.append(Character.toChars(Character.valueOf(messageToEncrypt.charAt(i)) + key));
        }
        // Print the results
        System.out.println(encryptedMessage.toString());

    }

    public static void dec(String messageToDecrypt, int key) {

        // Same as the encrypt function, but in reverse
        StringBuilder decryptedMessage = new StringBuilder();
        for (int i = 0; i < messageToDecrypt.length(); i++) {
            // Convert it to cyphertext in lowercases based on the input key
            decryptedMessage.append(Character.toChars(Character.valueOf(messageToDecrypt.charAt(i)) - key));
        }
        // Print the results
        System.out.println(decryptedMessage.toString());

    }
}
