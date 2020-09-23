package com.epam.university.java.core.task025;

public class Task025Impl implements Task025 {

    @Override
    public int getAmountOfAlteredLetters(String sourceMessage) {
        if (sourceMessage == null) {
            throw new IllegalArgumentException();
        }

        if ("".equals(sourceMessage)) {
            return 0;
        }

        final String pattern = "SOS";
        int count = 0;

        for (int i = 0; i < sourceMessage.length(); i++) {

            if (sourceMessage.charAt(i) != pattern.charAt(i % pattern.length())) {
                count++;
            }
        }
        return count;
    }
}
