package com.epam.university.java.core.task004;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class Task004Impl implements Task004 {
    @Override
    public String[] intersection(String[] first, String[] second) {
        if (first == null || second == null) {
            throw new IllegalArgumentException();
        }
        if (first.length == 0 || second.length == 0) {
            return new String[0];
        }

        List<String> result = new ArrayList<>();
        List<String> bigger;
        List<String> smaller;

        if (first.length > second.length) {
            bigger = Arrays.asList(first);
            smaller = Arrays.asList(second);
        } else {
            bigger = Arrays.asList(second);
            smaller = Arrays.asList(first);
        }
        for (String element : bigger) {
            if (smaller.contains(element)) {
                result.add(element);
            }
        }

        return result.toArray(new String[0]);
    }

    @Override
    public String[] union(String[] first, String[] second) {
        if (first == null || second == null) {
            throw new IllegalArgumentException();
        }
        Set<String> result = new LinkedHashSet<>();
        result.addAll(Arrays.asList(first));
        result.addAll(Arrays.asList(second));

        return result.toArray(new String[0]);
    }
}
