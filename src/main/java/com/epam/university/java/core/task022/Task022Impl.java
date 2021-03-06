package com.epam.university.java.core.task022;

import java.util.Collection;

public class Task022Impl implements Task022 {
    @Override
    public int maxSum(Collection<Integer> numbers) {
        validate(numbers);

        int min = numbers.stream()
                .min(Integer::compareTo)
                .get();

        int result = numbers.stream()
                .reduce(Integer::sum)
                .get();

        return result - min;
    }

    @Override
    public int minSum(Collection<Integer> numbers) {
        validate(numbers);

        int max = numbers.stream()
                .max(Integer::compareTo)
                .get();

        int result = numbers.stream()
                .reduce(Integer::sum)
                .get();

        return result - max;
    }

    private void validate(Collection<Integer> numbers) {
        if (numbers == null) {
            throw new IllegalArgumentException();
        }
        if (numbers.isEmpty()) {
            throw new IllegalArgumentException();
        }
    }
}
