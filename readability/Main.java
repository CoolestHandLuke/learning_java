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
        double charsWordsSentences[] = countCharsWordsSentences(textInput.toString());
        double readingScore = calculateReadingScore(charsWordsSentences);
        String ageRange = getAgeRange(Math.ceil(readingScore));

        System.out.println("The text is:\n" + textInput);

        System.out.println("\nWords: " + (int)charsWordsSentences[1]);
        System.out.println("Sentences: " + (int)charsWordsSentences[2]);
        System.out.println("Characters: " + (int)charsWordsSentences[0]);
        DecimalFormat df = new DecimalFormat("##.##");
        df.setRoundingMode(RoundingMode.DOWN);
        System.out.println("The score is: " + df.format(readingScore));
        System.out.println("This text should be understood by " + ageRange + " year-olds");
        
     }

     public static double[] countCharsWordsSentences(String buffer) {

        double charsWordsSentences[] = new double[4];        
        String[] words = buffer.split(" "); // An array of strings, each a word
        int charCount = 0;
        int sentenceCount = 0;
        int wordCount = words.length;
        for (int i = 0; i < words.length; i++) {
            charCount += words[i].length();
            if (words[i].matches(".*[.!?]") || i == words.length - 1) {
                sentenceCount++;
            } 
        }

        charsWordsSentences[0] = charCount;
        charsWordsSentences[1] = wordCount;
        charsWordsSentences[2] = sentenceCount;

        return charsWordsSentences;
     }
     public static double calculateReadingScore(double[] charsWordsSentences) {

        double score = 4.71 * (charsWordsSentences[0] / charsWordsSentences[1]) + 0.5 * (charsWordsSentences[1] / charsWordsSentences[2]) - 21.43;
        return score;
     }
     public static String getAgeRange(double score) {
        switch ((int)score) {
            case 1:
                return "5-6";
            case 2:
                return "6-7";
            case 3:
                return "7-8";
            case 4:
                return "8-9";
            case 5:
                return "9-10";
            case 6:
                return "10-11";
            case 7:
                return "11-12";
            case 8:
                return "12-13";
            case 9:
                return "13-14";
            case 10:
                return "14-15";
            case 11:
                return "15-16";
            case 12:
                return "16-17";
            case 13:
                return "17-18";
            case 14:
                return "18-19";
            default:
                return "NaN";
        }
     }
}
