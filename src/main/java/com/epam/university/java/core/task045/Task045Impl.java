package com.epam.university.java.core.task045;

public class Task045Impl implements Task045 {

    private static final String DELIMITER = " ";
    private static final int MIN_SIZE_FOR_ANAGRAM = 2;
    private static final char CHAR_DELIMITER = ' ';

    @Override
    public String doAnagram(String input) {

        if (input == null) {
            throw new IllegalArgumentException("invalid value, replace null with string");
        } else if (input.length() < MIN_SIZE_FOR_ANAGRAM) {
            return input;
        } else {
            return trimSpaces(input);
        }
    }

    private String trimSpaces(String inputString) {
        StringBuilder spacesAfter = new StringBuilder();
        StringBuilder withoutEndSpaces = new StringBuilder(inputString);

        for (int i = inputString.length() - 1; i > -1; i--) {
            if (withoutEndSpaces.charAt(i) == CHAR_DELIMITER) {
                spacesAfter.append(DELIMITER);
                withoutEndSpaces.deleteCharAt(i);
            } else {
                break;
            }
        }
        String resultAnagram = reversePhrase(withoutEndSpaces.toString());
        return resultAnagram + spacesAfter.toString();
    }

    private String reversePhrase(String inputString) {

        StringBuilder finalResult = new StringBuilder();
        String[] wordsArray = inputString.split(DELIMITER);

        for (String word : wordsArray) {
            finalResult.append(reverseLettersWord(word)).append(DELIMITER);
        }
        return finalResult.deleteCharAt(finalResult.length() - 1).toString();
    }

    private String reverseLettersWord(String inputWord) {
        StringBuilder wordResult = new StringBuilder();

        for (int i = inputWord.length() - 1; i >= 0; i--) {
            if (Character.isLetter(inputWord.charAt(i))) {
                wordResult.append(inputWord.charAt(i));
            }
        }
        for (int i = 0; i < inputWord.length(); i++) {
            if (!Character.isLetter(inputWord.charAt(i))) {
                wordResult.insert(i, inputWord.charAt(i));
            }
        }
        return wordResult.toString();
    }
}
