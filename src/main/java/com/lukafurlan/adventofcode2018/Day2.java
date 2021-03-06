package com.lukafurlan.adventofcode2018;

import com.lukafurlan.adventofcode2018.helpers.FileReadHelper;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Luka Furlan <luka.furlan9@gmail.com>
 */
public class Day2 {

    private static List<String> entries = new ArrayList<>();

    public static void main(String[] args) {
        entries = FileReadHelper.writeLinesToList("Day2Input.txt");
        System.out.println(getChecksum());
        System.out.println(getCommonLetters());
    }

    private static int getChecksum() {
        return getNumberOfBoxesWithRepetitiveLetters(2) * getNumberOfBoxesWithRepetitiveLetters(3);
    }

    private static int getNumberOfBoxesWithRepetitiveLetters(int repeats) {
        int words = 0;
        for(String entry : entries) {
            words += countMatches(entry, repeats);
        }
        return words;
    }

    private static int countMatches(String entry, int repeats) {
        for(char c : entry.toCharArray()) {
            if(StringUtils.countMatches(entry, c) == repeats) {
                return 1;
            }
        }
        return 0;
    }

    private static String getCommonLetters() {

        for(int i = 0; i < entries.size(); i++) {
            for(int j = 0; j < entries.size(); j++) {
                if(i == j) {
                    continue;
                }

                int numberOfDifferences = 0;
                char differentChar = 0;

                char[] firstBoxID = entries.get(i).toCharArray();
                char[] secondBoxID = entries.get(j).toCharArray();

                for(int k = 0; k < firstBoxID.length; k++) {

                    if(firstBoxID[k] != secondBoxID[k]) {
                        numberOfDifferences++;
                        differentChar = secondBoxID[k];
                    }

                }

                if(numberOfDifferences == 1) {
                    return String.valueOf(ArrayUtils.removeElement(secondBoxID, differentChar));
                }

            }
        }

        return "Could find matching boxes.";

    }

}
