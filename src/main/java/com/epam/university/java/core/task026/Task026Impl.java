package com.epam.university.java.core.task026;

public class Task026Impl implements Task026 {

    @Override
    public String encrypt(String sourceString, int shift) {

        if (sourceString == null) {
            throw new IllegalArgumentException();
        }
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < sourceString.length(); i++) {
            sb.append(cipher(sourceString.charAt(i), shift));
        }
        return sb.toString();

    }

    @Override
    public String decrypt(String encryptedString, int shift) {
        return encrypt(encryptedString, -shift);
    }

    private static char cipher(char c, int k) {

        if (k != 0 && Character.isLetter(c)) {

            final int alphaLength = 26;
            final char asciiShift = Character.isUpperCase(c) ? 'A' : 'a';
            final int cipherShift = k % alphaLength;

            char shifted = (char) (c - asciiShift);

            shifted = (char) ((shifted + cipherShift + alphaLength) % alphaLength);

            return (char) (shifted + asciiShift);
        } else {
            return c;
        }
    }
}
