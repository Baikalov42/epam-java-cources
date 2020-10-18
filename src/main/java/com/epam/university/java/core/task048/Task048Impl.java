package com.epam.university.java.core.task048;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Task048Impl implements Task048 {
    @Override
    public Collection<Integer> getArmstrongNumbers(Integer from, Integer to) {
        if (from == null || to == null) {
            throw new IllegalArgumentException();
        }

        if (from < 1 || to < 1) {
            throw new IllegalArgumentException();
        }
        List<Integer> resultCollection = new ArrayList<>();

        for (int i = from; i < to; ++i) {

            int digits = 0;
            int result = 0;
            int originalNumber = i;

            while (originalNumber != 0) {
                originalNumber = originalNumber / 10;
                digits++;
            }

            originalNumber = i;

            while (originalNumber != 0) {
                int remainder = originalNumber % 10;

                result = result + (int) Math.pow(remainder, digits);
                originalNumber = originalNumber / 10;
            }

            if (result == i) {
                resultCollection.add(i);
            }
        }
        return resultCollection;
    }
}
