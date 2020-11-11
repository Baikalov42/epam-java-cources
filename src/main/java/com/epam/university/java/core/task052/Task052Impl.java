package com.epam.university.java.core.task052;

import java.util.ArrayList;
import java.util.List;

public class Task052Impl implements Task052 {

    @Override
    public boolean validateCard(long number) {

        if (number < 1) {
            throw new IllegalArgumentException();
        }

        String nums = String.valueOf(number);
        int checkDigit = getDigit(nums.charAt(nums.length() - 1));

        StringBuilder reversed = new StringBuilder(nums.substring(0, nums.length() - 1));
        reversed.reverse();

        List<Integer> numbers = new ArrayList<>();

        for (int i = 0; i < reversed.length(); i++) {
            int current = getDigit(reversed.charAt(i));

            if (isOdd(i + 1)) {
                int result = current * 2;

                if (!isOneDigit(result)) {
                    result = getDigitsSum(result);
                }
                numbers.add(result);
            } else {
                numbers.add(current);
            }
        }
        int sum = numbers
                .stream()
                .mapToInt(x -> x)
                .sum();

        int finalDigit = getDigit(String.valueOf(sum).charAt(String.valueOf(sum).length() - 1));

        return (10 - finalDigit) == checkDigit;
    }

    private int getDigitsSum(int number) {

        int first = number / 10;
        int second = number % 10;

        return first + second;
    }

    private boolean isOneDigit(int number) {
        return number / 10 == 0;
    }

    private int getDigit(char ch) {
        String string = Character.toString(ch);
        return Integer.parseInt(string);
    }

    private boolean isOdd(int digit) {
        return digit % 2 != 0;
    }
}
