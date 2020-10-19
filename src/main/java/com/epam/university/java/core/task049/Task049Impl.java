package com.epam.university.java.core.task049;

public class Task049Impl implements Task049 {

    @Override
    public String getResultList(String first, String second) {
        if (first == null || second == null) {
            throw new IllegalArgumentException();
        }
        if (first.isEmpty() || second.isEmpty()) {
            throw new IllegalArgumentException();
        }
        String smaller;
        String bigger;

        if (first.length() < second.length()) {
            smaller = first;
            bigger = second;
        } else {
            smaller = second;
            bigger = first;
        }


        int startIndex = 0;
        int endIndex = 1;
        String result = "";

        while (startIndex != smaller.length()) {
            String sequence = smaller.substring(startIndex, endIndex);

            if (bigger.contains(sequence) && sequence.length() > result.length()) {
                result = sequence;
            }
            if (endIndex != smaller.length()) {
                endIndex++;
            } else {
                startIndex++;
                endIndex = startIndex;
            }
        }
        return result;
    }
}
