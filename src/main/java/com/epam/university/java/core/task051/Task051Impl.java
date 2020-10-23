package com.epam.university.java.core.task051;

public class Task051Impl implements Task051 {

    private static final String A = "a   ";
    private static final String AN = "an  ";

    @Override
    public String replace(String source) {

        if (source == null || source.isEmpty()) {
            throw new IllegalArgumentException();
        }

        if (!source.toLowerCase().equals(source) || source.length() < 4) {
            throw new IllegalArgumentException();
        }

        StringBuilder result = new StringBuilder(source);

        for (int i = 0; i < source.length() - 5; i++) {
            String substring = source.substring(i, i + 5);

            if (substring.matches("(the[\\s].)")) {

                if (substring.matches("(the[\\s][aeiouy])")) {
                    result.replace(i, i + 4, AN);
                } else {
                    result.replace(i, i + 4, A);
                }
            }
        }

        return result.toString().replaceAll("( )+", " ");
    }
}
