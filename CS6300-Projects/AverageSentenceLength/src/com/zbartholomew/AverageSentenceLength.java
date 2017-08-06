package com.zbartholomew;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class AverageSentenceLength {

    private String filepath = "", delimiters = "";
    private int wordLengthLimit = 0;
    private final String VALID_DELIMITER = ".?!:;/|%";

    public AverageSentenceLength(String filepath, String delimiters, int wordLengthLimit) {
        this.filepath = filepath;
        if (delimiters.equals("")) {
            this.delimiters = parseDelimiters(".?!");
        } else {
            this.delimiters = parseDelimiters(delimiters);
        }
        if (wordLengthLimit >= 1) {
            this.wordLengthLimit = wordLengthLimit;
        } else {
            throw new IllegalArgumentException("Word length limit must be 1 or greater." +
                    "  This value represents the the length of a word we will consider to be a word.");
        }
    }

    public double computeAverageSentenceLength() {

        String essay = readFile();
        String[] sentenceArray = essay.split("--");
        String[] words;

        double sentenceCount = 0;
        double wordCount = 0;

        for (String sentence : sentenceArray) {
            words = sentence.split("[\r\n\\s]");

            for (String word : words) {
                if (word.length() >= wordLengthLimit && !word.equals("\"")) {
                    wordCount++;
                }
            }
            sentenceCount++;
        }

        return Math.round((wordCount / sentenceCount) * 100d) / 100d;
    }

    private String readFile() {

        Scanner sentence = null;
        StringBuilder essay = new StringBuilder();

        try {
            sentence = new Scanner(new BufferedReader(new FileReader(filepath)));
            sentence.useDelimiter(delimiters);

            while (sentence.hasNext()) {
                String next = sentence.next();
                if (!next.equals("")) {
                    essay.append(next).append("--");
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println("File not found.  Please check filepath is correct");
        } finally {
            if (sentence != null) {
                sentence.close();
            }
        }

        return essay.toString();
    }

    private String parseDelimiters(String delimiters) {

        StringBuilder delimiterBuilder = new StringBuilder();

        for (int i = 0; i < delimiters.length(); i++) {
            if (VALID_DELIMITER.indexOf(delimiters.charAt(i)) >= 0) {
                if (delimiters.charAt(i) == '.') {
                    delimiterBuilder.append("\\").append(delimiters.charAt(i)).append("|");
                } else if (delimiters.charAt(i) == '|') {
                    delimiterBuilder.append("\\").append(delimiters.charAt(i)).append("|");
                } else if (delimiters.charAt(i) == '?') {
                    delimiterBuilder.append("\\").append(delimiters.charAt(i)).append("|");
                } else {
                    delimiterBuilder.append(delimiters.charAt(i)).append("|");
                }
            } else {
                throw new IllegalArgumentException("At least one Delimiter is invalid.  Here is list of possible delimiters: " + VALID_DELIMITER);
            }
        }

        delimiterBuilder.deleteCharAt(delimiterBuilder.length() - 1);
        return delimiterBuilder.toString();
    }
}
