package com.epam.university.java.core.task002;

public class Task002Impl implements Task002 {
    @Override
    public boolean isEquals(String firstString, String secondString) {
        checkForNull(firstString, secondString);

        return firstString.equals(secondString);
    }

    @Override
    public String left(String sourceString, int number) {
        checkForNull(sourceString, number);

        if (sourceString.length() < number) {
            return sourceString;
        } else {
            return sourceString.substring(0, number);
        }
    }

    @Override
    public String left(String sourceString, String separator) {
        checkForNull(sourceString, separator);

        int index = sourceString.indexOf(separator);
        return sourceString.substring(0, index);
    }

    @Override
    public String right(String sourceString, int number) {
        checkForNull(sourceString, number);

        if (number >= sourceString.length()) {
            return sourceString;
        } else {
            return sourceString.substring(sourceString.length() - number);
        }
    }

    @Override
    public String right(String sourceString, String separator) {
        checkForNull(sourceString, separator);

        if (!sourceString.contains(separator)) {
            return sourceString;
        }
        int index = sourceString.length() - sourceString.lastIndexOf(separator);

        return sourceString.substring(index);
    }

    @Override
    public String[] split(String sourceString, String split) {
        checkForNull(sourceString, split);
        return sourceString.split(split);
    }

    @Override
    public String join(String[] sourceCollection, String glue) {

        checkForNull(sourceCollection, glue);

        if (sourceCollection.length == 0) {
            throw new IllegalArgumentException();
        }
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < sourceCollection.length; i++) {
            result.append(sourceCollection[i]);

            if (i != sourceCollection.length - 1) {
                result.append(glue);
            }
        }
        return result.toString();
    }

    private void checkForNull(String first, String second) {
        if (first == null || second == null) {
            throw new IllegalArgumentException();
        }
    }

    private void checkForNull(String string, int number) {
        if (string == null || number < 0) {
            throw new IllegalArgumentException();
        }
    }

    private void checkForNull(String[] strings, String string) {
        if (strings == null || string == null) {
            throw new IllegalArgumentException();
        }
        for (String element : strings) {
            if (element == null) {
                throw new IllegalArgumentException();
            }
        }
    }
}
