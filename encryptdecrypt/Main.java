package encryptdecrypt;
import java.util.Scanner;

public class Main {
    
    public static void main(String[] args) {

        // Now we're going to take command line arguments
        // -mode, -key, -data. can be in any order
        // Are guaranteed. Defaults are mode = enc, key = 0, data = ""
        String mode = "enc";
        int key = 0;
        String data = "";
        
        // Loop over the command line arguments to determine what to do
        int i = 0;
        while (i < args.length) {
            switch(args[i]) {
                case "-mode":
                    mode = args[i + 1];
                    i += 2;
                    break;
                case "-key":
                    key = Integer.parseInt(args[i + 1]);
                    i += 2;
                    break;
                case "-data":
                    data = args[i + 1];
                    i += 2;
                    break;
                default:
                    System.out.println("Hello, World!");
            }
        }
        switch(mode) {
            case "enc":
                enc(data, key);
                break;
            case "dec":
                dec(data, key);
                break;
            default:
                System.out.println("Invalid input");

        }
        
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
