package com.epam.university.java.core.task027;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Task027Impl implements Task027 {

    @Override
    public Collection<Integer> extract(String sourceString) {

        if (sourceString == null) {
            throw new IllegalArgumentException();
        }

        if (sourceString.charAt(0) == '0') {
            return Collections.emptyList();
        }

        List<Integer> numbers = new ArrayList<>();

        for (int i = 1; i < sourceString.length() / 2; i++) {

            String substring = sourceString.substring(0, i);
            Integer number = Integer.parseInt(substring);
            numbers.add(number);

            while (substring.length() < sourceString.length()) {
                number++;
                substring = substring + number;
                numbers.add(number);
                if (substring.length() > sourceString.length()
                        || !substring.equals(sourceString.substring(0, substring.length()))) {
                    break;
                }
            }
            if (substring.equals(sourceString)) {
                return numbers;
            }
            numbers.clear();
        }
        return Collections.emptyList();
    }
}
