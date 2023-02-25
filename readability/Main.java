/* This is my attempt a readability program. It takes a text file as an input, then analyzes the text in the file to determine the reading level
 * of the same. It uses a variety of different algorithms to determine different reading scores and gives a reccomended reading age as output. 
 * The implementation of this one was a little confusing as the test cases seemed to have random rounding and it was a little frustrating 
 * getting my code to pass the unit tests. Nonetheless, it's finished and works pretty well. I might need to add in more robust Exception handling
 * to prevent user errors. 
 * "Java is like baseball, we have to catch everything!"
 * 2/24/2023
 */

package readability;

import java.io.File;
import java.io.IOException;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Scanner;

public class Main {
    
    public static void main(String[] args) throws IOException {

        // Read the input file
        StringBuilder textInput = new StringBuilder();
        try {
            File file = new File(args[0]);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                textInput.append(scanner.nextLine());
        }
        scanner.close();

        } catch (IOException e) {
            System.out.println(e.getStackTrace());
        }

        // Start figuring out what we have
        double sentenceStats[] = getSentenceStats(textInput.toString());

        System.out.println("The text is:\n" + textInput);
        printSentenceStats(sentenceStats);
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the score you want to calculate (ARI, FK, SMOG, CL, all): ");
        String input = scanner.next();
        System.out.println();

        switch(input.toLowerCase().strip()) {
            case "ari":
                calculateARI(sentenceStats);
                break;
            case "fk":
                calculateFK(sentenceStats);
                break;
            case "smog":
                calculateSMOG(sentenceStats);
                break;
            case "cl":
                calculateCL(sentenceStats);
                break;
            case "all":
                int sumReadingAge = calculateARI(sentenceStats);
                sumReadingAge += calculateFK(sentenceStats);
                sumReadingAge += calculateSMOG(sentenceStats);
                sumReadingAge += calculateCL(sentenceStats);
                double readinAgeAvg = sumReadingAge / 4.0;
                System.out.println("\nThis text should be understood in average by " + readinAgeAvg + "-year-olds.");
                break;
            default:
                System.out.println("Invalid input");
                break;
        }

        scanner.close();            
        
     }
     public static void printSentenceStats(double[] sentenceStats) {

        System.out.println("\nWords: " + (int)sentenceStats[0]);
        System.out.println("Sentences: " + (int)sentenceStats[1]);
        System.out.println("Characters: " + (int)sentenceStats[2]);
        System.out.println("Syllables: " + (int)sentenceStats[3]);
        System.out.println("Polysyllables: " + (int)sentenceStats[4]);
     }

     public static double[] getSentenceStats(String buffer) {

        double sentenceStats[] = new double[5];        
        String[] words = buffer.split(" "); // An array of strings, each a word
        int wordCount = words.length;
        int sentenceCount = 0;
        int charCount = 0;
        int syllablesCount = 0;
        int polySyllablesCount = 0;

        for (int i = 0; i < words.length; i++) {
            int vowels = 0;
            charCount += words[i].length();
            if (words[i].matches(".*[.!?]") || i == words.length - 1) {
                sentenceCount++;
            } 
            for (int j = 0; j < words[i].length(); j++) {
                if (words[i].substring(j, j + 1).matches("[aeiouyAEIOUY]{1}")) {
                    // Check the previous character to make sure it's not a double vowel
                    if (j > 0 && words[i].substring(j - 1, j).matches("[aeiouyAEIOUY]")) {
                        continue;
                    // Next we check if we have found an 'e' at the end of the string
                    } else if (words[i].substring(j, j + 1).equalsIgnoreCase("e") && j == words[i].length() - 1) {
                        continue;
                    // Edge case: check for 'e' at the end of a word that also has a special character directly after it (comma, sentence end)
                    } else  if (words[i].substring(j, j + 1).equalsIgnoreCase("e") && j == words[i].length() - 2 && words[i].substring(j + 1, j + 2).matches("[.,!?]")) {
                        continue;
                    } else {
                        vowels++;
                    }
                }
            }
            // Check for 0 vowels
            if (vowels == 0) {
                vowels = 1;
            }
            syllablesCount += vowels;
            // Check for a polysyllable
            if (vowels > 2) {
                polySyllablesCount++;
            }
        }

        sentenceStats[0] = wordCount;
        sentenceStats[1] = sentenceCount;
        sentenceStats[2] = charCount;
        sentenceStats[3] = syllablesCount;
        sentenceStats[4] = polySyllablesCount;


        return sentenceStats;
     }
     public static int calculateARI(double[] sentenceStats) {

        double score = 4.71 * (sentenceStats[2] / sentenceStats[0]) + 0.5 * (sentenceStats[0] / sentenceStats[1]) - 21.43;
        String readingAge = getReadingAge(Math.round(score));
        DecimalFormat df = new DecimalFormat("##.##");
        df.setRoundingMode(RoundingMode.DOWN);
        System.out.println("Automated Readability Index: " + df.format(score) + " (about " + readingAge + "-years-olds).");
        return Integer.parseInt(readingAge);
     }
     public static int calculateFK(double[] sentenceStats) {

        double score = 0.39 * (sentenceStats[0] / sentenceStats[1]) + 11.8 * (sentenceStats[3] / sentenceStats[0]) - 15.59;
        String readingAge = getReadingAge(Math.round(score));
        DecimalFormat df = new DecimalFormat("##.##");
        df.setRoundingMode(RoundingMode.DOWN);
        System.out.println("Flesch-Kincaid readability tests: " + df.format(score) + " (about " + readingAge + "-years-olds).");
        return Integer.parseInt(readingAge);
     }
     public static int calculateSMOG(double[] sentenceStats) {

        double score = 1.043 * Math.sqrt(sentenceStats[4] * (30 / sentenceStats[1])) + 3.1291;
        String readingAge = getReadingAge(Math.round(score));
        DecimalFormat df = new DecimalFormat("##.##");
        df.setRoundingMode(RoundingMode.DOWN);
        System.out.println("Simple Measure of Gobbledygook: " + df.format(score) + " (about " + readingAge + "-years-olds).");
        return Integer.parseInt(readingAge);

     }
     public static int calculateCL(double[] sentenceStats) {

        double score = 0.0588 * (100 * sentenceStats[2] / sentenceStats[0]) - 0.296 * (100 * sentenceStats[1] / sentenceStats[0]) - 15.8;
        String readingAge = getReadingAge(Math.round(score));
        DecimalFormat df = new DecimalFormat("##.##");
        df.setRoundingMode(RoundingMode.DOWN);
        System.out.println("Coleman-Liau index: " + df.format(score) + " (about " + readingAge + "-years-olds).");
        return Integer.parseInt(readingAge);
     }
        
     public static String getReadingAge(double score) {
        switch ((int)score) {
            case 1:
                return "6";
            case 2:
                return "7";
            case 3:
                return "8";
            case 4:
                return "9";
            case 5:
                return "10";
            case 6:
                return "11";
            case 7:
                return "12";
            case 8:
                return "13";
            case 9:
                return "14";
            case 10:
                return "15";
            case 11:
                return "16";
            case 12:
                return "17";
            case 13:
                return "18";
            case 14:
                return "22";
            default:
                return "NaN";
        }
     }
}
