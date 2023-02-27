package encryptdecrypt;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    
    public static void main(String[] args) {

        // Next iteration, we need to add two more flags, -in and -out
        // These specify input and output files to read/write data
        // If none are given then -data needs to work like normal
        String mode = "enc";
        int key = 0;
        String data = "";
        String in = "";
        String out = "";
        
        // Loop over the command line arguments to determine what to do
        int i = 0;
        while (i < args.length) {
            switch (args[i]) {
                case "-mode": {
                    mode = args[i + 1];
                    i += 2;
                    break;
                }
                case "-key": {
                    key = Integer.parseInt(args[i + 1]);
                    i += 2;
                    break;
                }
                case "-data": {
                    data = args[i + 1];
                    i += 2;
                    break;
                }
                case "-in": {
                    in = args[i + 1];
                    i += 2;
                    break;
                }
                case "-out": {
                    out = args[i + 1];
                    i += 2;
                    break;
                }
                default: System.out.println("Hello, World!");
                break;
            }
        }
        // Need to check if we are reading/writing from a file, or just processing data input as usual
        if (in.equals("")) { // No files used

            switch (mode) {
                case "enc": {
                    System.out.println(enc(data, key));
                    break;
                }
                case "dec": {
                    System.out.println(dec(data, key));
                    break;
                }
                default: {
                    System.out.println("Invalid input");
                    break;
                }
            }
        } else { // Files used, need to open file handlers for reading/writing

            File inputFile = new File(in);
            File outputFile = new File(out);
            try {
                Scanner scanner = new Scanner(inputFile);
                while (scanner.hasNext()) {
                    data = scanner.nextLine();
                }
                scanner.close();
            } catch(FileNotFoundException e) {
                System.out.println("Error:" + Arrays.toString(e.getStackTrace()));
            }
            // Perform whichever operation the user defined
            switch (mode) {
                case "enc": {
                    String encodedData = enc(data, key);
                    try {
                        FileWriter outputFileWriter = new FileWriter(outputFile);
                        outputFileWriter.write(encodedData);
                        outputFileWriter.close();
                    } catch (IOException e) {
                        System.out.println("Error: " + Arrays.toString(e.getStackTrace()));
                    }
                    break;
                }
                case "dec": {
                    String decodedData = dec(data, key);
                    try {
                        FileWriter outputFileWriter = new FileWriter(outputFile);
                        outputFileWriter.write(decodedData);
                        outputFileWriter.close();
                    } catch (IOException e) {
                        System.out.println("Error: " + Arrays.toString(e.getStackTrace()));

                    }
                    break;
                }
                default: System.out.println("Invalid input");
            }
    }
}

    public static String enc(String messageToEncrypt, int key) {

        // Encrypt everything now, not just characters, so don't need to check anymore
        StringBuilder encryptedMessage = new StringBuilder();
        for (int i = 0; i < messageToEncrypt.length(); i++) {
            // Convert it to ciphertext in lowercase's based on the input key
            encryptedMessage.append(Character.toChars(messageToEncrypt.charAt(i) + key));
        }
        return encryptedMessage.toString();        

    }

    public static String dec(String messageToDecrypt, int key) {

        // Same as the encrypt function, but in reverse
        StringBuilder decryptedMessage = new StringBuilder();
        for (int i = 0; i < messageToDecrypt.length(); i++) {
            // Convert it to cyphertext in lowercases based on the input key
            decryptedMessage.append(Character.toChars(messageToDecrypt.charAt(i) - key));
        }
        return decryptedMessage.toString();

    }
}
