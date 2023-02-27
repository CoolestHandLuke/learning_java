package encryptdecrypt;
import java.util.Scanner;

public class Main {
    
    public static void main(String[] args) {

        
        // Take in a string and int key from the user
        Scanner scanner = new Scanner(System.in);
        String messageToEncrypt = scanner.nextLine().toLowerCase();
        int key = scanner.nextInt();
        scanner.nextLine(); // eat the \n from this input
        
        StringBuilder encryptedMessage = new StringBuilder();
        
        // Check for a character, not a special or whitespace
        for (int i = 0; i < messageToEncrypt.length(); i++) {
            if (messageToEncrypt.charAt(i) < 97 || messageToEncrypt.charAt(i) > 122) {
                encryptedMessage.append(messageToEncrypt.charAt(i));
                continue;
            } 

            // Convert it to cyphertext in lowercases based on the input key
            // Need to loop to make sure we go back to the beginning of the alphabet if we exceed 122
            
            int temp = Character.valueOf(messageToEncrypt.charAt(i)) + key;
            while (temp > 122) {
                temp = temp - 26;
            }          
            encryptedMessage.append(Character.toChars(temp));

            }
            
        

        // Print the results
        System.out.println(encryptedMessage.toString());
        scanner.close();
        

    }
}
