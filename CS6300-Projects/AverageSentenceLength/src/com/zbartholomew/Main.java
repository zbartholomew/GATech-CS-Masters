package com.zbartholomew;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        String filepath = "", delimiters = "";
        int wordLengthLimit = 0;

        // check if command line arguments are present
        if (args.length > 0) {
            for (int i = 0; i < args.length - 1; i++) {
                String token = args[i];

                if (token.startsWith("-d")) {
                    delimiters = args[i + 1];
                    i++;

                } else if (token.startsWith("w/-l")) {
                    wordLengthLimit = Integer.parseInt(args[i + 1]);
                    i++;

                } else if (token.startsWith("/")) {
                    filepath = token;
                }
            }

            System.out.println("filepath: " + filepath + "\n" +
                    "delimiters: " + delimiters + "\n" +
                    "word length limit: " + wordLengthLimit);
        } else {
            // Prompt user for required fields if not supplied
            Scanner in = new Scanner(System.in);

            System.out.println("Please enter filepath of essay: ");
            filepath = in.next();

            System.out.println("Please enter delimiters that identify a sentence: ");
            delimiters = in.next();

            System.out.println("Please enter word length limit of a sentence that should be considered: ");
            wordLengthLimit = in.nextInt();
            in.close();

            System.out.println("filepath: " + filepath + "\n" +
                    "delimiters: " + delimiters + "\n" +
                    "word length limit: " + wordLengthLimit);
        }

        AverageSentenceLength avgLength = new AverageSentenceLength(filepath, delimiters, wordLengthLimit);
        System.out.println(avgLength.computeAverageSentenceLength());
    }
}
