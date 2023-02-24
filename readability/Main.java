package readability;

import java.util.Scanner;

public class Main {
    
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String textInput = scanner.nextLine();
        String[] splitSentence = textInput.split(" ");
        String sentenceFinder = ".+[.!?]";
        double wordCount = splitSentence.length;
        double sentenceCount = 0; 
        for (int i = 0; i < splitSentence.length; i++) {
            if (splitSentence[i].matches(sentenceFinder)) {
                sentenceCount++;
            } else if (i == splitSentence.length - 1) {
                sentenceCount++;
            }
        }
        double averageWordsPerSentece = Math.ceil(wordCount / sentenceCount);
        System.out.println(wordCount);
        System.out.println(sentenceCount);
        
        if (averageWordsPerSentece > 10) {
            System.out.println("HARD");
        } else {
            System.out.println("EASY");
        }
        
        scanner.close();
     }
}
