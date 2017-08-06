package com.zbartholomew.tests;

import com.zbartholomew.AverageSentenceLength;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AverageSentenceLengthTest {

    private AverageSentenceLength asl;
    private String fileDir = "C:\\Users\\Zach\\Documents\\GitHub\\GATech-CS-Masters\\CS6300-Projects\\AverageSentenceLength\\src\\com\\zbartholomew\\test\\resources\\";

    @Test
    public void testComputeAverageSentenceLength1() {
        String delimiters = ".";
        int wordLength = 0;
        asl = new AverageSentenceLength(fileDir + "multi.txt", delimiters, wordLength);
        assertEquals(10, asl.computeAverageSentenceLength(), 0);
    }

    @Test
    public void testComputeAverageSentenceLength2() {
        String delimiters = "|%";
        int wordLength = 0;
        asl = new AverageSentenceLength(fileDir + "file.txt", delimiters, wordLength);
        assertEquals(1, asl.computeAverageSentenceLength(), 0);
    }

    @Test
    public void testComputeAverageSentenceLength3() {
        String delimiters = "";
        int wordLength = 0;
        asl = new AverageSentenceLength(fileDir + "essay.txt", delimiters, wordLength);
        assertEquals(10, asl.computeAverageSentenceLength(), 0);
    }

    @Test
    public void testComputeAverageSentenceLength4() {
        String delimiters = ".";
        int wordLength = 5;
        asl = new AverageSentenceLength(fileDir + "essay.txt", delimiters, wordLength);
        assertEquals(4, asl.computeAverageSentenceLength(), 0);
    }

    @Test
    public void testComputeAverageSentenceLength5() {
        String delimiters = "/|";
        int wordLength = 1;
        asl = new AverageSentenceLength(fileDir + "numbers.txt", delimiters, wordLength);
        assertEquals(1, asl.computeAverageSentenceLength(), 0);
    }

    // Test to verify if the different delimiters specified.
    @Test
    public void verifyQuestionmarkDelimiters() {
        String delimiters = "?";
        int wordLength = 4;
        asl = new AverageSentenceLength(fileDir + "questionDelim.txt", delimiters, wordLength);
        assertEquals(2, asl.computeAverageSentenceLength(), 0);
    }

    @Test
    public void verifyColonDelimiters() {
        String delimiters = ":";
        int wordLength = 1;
        asl = new AverageSentenceLength(fileDir + "colonDelimited.txt", delimiters, wordLength);
        assertEquals(9, asl.computeAverageSentenceLength(), 0);
    }

    @Test
    public void verifyExclamationDelimiters() {
        String delimiters = "!";
        int wordLength = 2;
        asl = new AverageSentenceLength(fileDir + "exclamationDelimited.txt", delimiters, wordLength);
        assertEquals(15, asl.computeAverageSentenceLength(), 0);

    }

    @Test
    public void verifySemicolonDelimiters() {
        String delimiters = ";";
        int wordLength = 0;
        asl = new AverageSentenceLength(fileDir + "semicolonDelim.txt", delimiters, wordLength);
        assertEquals(11, asl.computeAverageSentenceLength(), 0);
    }

    @Test
    public void verifyPeriodDelimiters() {
        String delimiters = ".";
        int wordLength = 0;
        asl = new AverageSentenceLength(fileDir + "periodDelim.txt", delimiters, wordLength);
        assertEquals(4.17, asl.computeAverageSentenceLength(), 0);

    }

    @Test
    public void verifyRequirements() {
        String delimiters = "";
        int wordLength = 0;
        asl = new AverageSentenceLength(fileDir + "requirementDocEx.txt", delimiters, wordLength);
        assertEquals(5, asl.computeAverageSentenceLength(), 0);

    }

    @Test
    public void verifyCombinationDelimiters() {
        String delimiters = ".?:;";
        int wordLength = 5;
        asl = new AverageSentenceLength(fileDir + "combination.txt", delimiters, wordLength);
        assertEquals( 3,asl.computeAverageSentenceLength(), 0);

    }

    /** ----- NEGATIVE SCENARIOS ---- */

    @Test
    public void verifyNoDelimitersPresent() {
        // Test when no input delimiter does not exist.
        String delimiters = ".";
        int wordLength = 50;
        asl = new AverageSentenceLength(fileDir + "noDelim.txt", delimiters, wordLength);
        assertEquals(0, asl.computeAverageSentenceLength(), 0);
    }

    @Test
    public void verifyEmptyFile() {
        String delimiters = ".";
        int wordLength = 5;
        asl = new AverageSentenceLength(fileDir + "emptyfile.txt", delimiters, wordLength);
        assertEquals(0, asl.computeAverageSentenceLength(), 0);
    }

    // This is a test to verify rounding logic.
    @Test
    public void verifyRoundingMath() {
        // 10 lines, 19 words. 1.9 ~ 1
        String delimiters = ":";
        int wordLength = 1;
        asl = new AverageSentenceLength(fileDir + "roundingLogicEx.txt", delimiters, wordLength);
        assertEquals(1, asl.computeAverageSentenceLength(), 0);
    }

    @Test
    public void verifyZeroWordCount() {
        // no words of length 900. expected output 0
        String delimiters = ":";
        int wordLength = 900;
        asl = new AverageSentenceLength(fileDir + "roundingLogicEx.txt", delimiters, wordLength);
        assertEquals(0, asl.computeAverageSentenceLength(), 0);
    }

    @Test (expected = IllegalArgumentException.class)
    public void verifyNegativeWordLength() {
        // Should throw an error.
        String delimiters = ":";
        int wordLength = -1;
        asl = new AverageSentenceLength(fileDir + "roundingLogic.txt", delimiters, wordLength);
    }

}