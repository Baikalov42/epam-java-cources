package com.epam.university.java.core.task007;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class Task007Impl implements Task007 {
    @Override
    public Collection<Integer> multiplyPolynomial(Collection<Integer> first,
                                                  Collection<Integer> second) {
        if (first == null || second == null) {
            throw new IllegalArgumentException();
        }
        Integer[] firstArray = first.toArray(new Integer[0]);
        Integer[] secondArray = second.toArray(new Integer[0]);

        int[] resultArray = new int[first.size() + second.size()];

        for (int i = 0; i < firstArray.length; i++) {
            for (int k = 0; k < secondArray.length; k++) {
                int index = i + k;
                int multiplyResult = firstArray[i] * secondArray[k];

                if (resultArray[index] != 0) {
                    resultArray[index] += multiplyResult;
                } else {
                    resultArray[index] = multiplyResult;
                }
            }
        }

        List<Integer> result = Arrays.stream(resultArray).boxed().collect(Collectors.toList());

        if (result.lastIndexOf(0) == result.size() - 1) {
            result.remove(result.size() - 1);
        }
        return result;
    }
}
