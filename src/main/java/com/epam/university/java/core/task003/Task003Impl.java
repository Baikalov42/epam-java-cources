package com.epam.university.java.core.task003;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Comparator;

public class Task003Impl implements Task003 {
    @Override
    public String[] invert(String[] source) {
        validate(source);
        if (source.length == 0) {
            return source;
        }
        List<String> list = Arrays.asList(source);
        Collections.reverse(list);
        return list.stream().toArray(String[]::new);
    }

    @Override
    public String[] join(String[] first, String[] second) {
        validate(first);
        validate(second);

        if (first.length == 0 && second.length != 0) {
            return second;
        } else if (second.length == 0 && first.length != 0) {
            return first;
        } else if (second.length == 0 && first.length == 0) {
            return first;
        }

        String[] result = new String[first.length + second.length];
        int index = 0;
        for (String string : first) {
            result[index] = string;
            index++;
        }
        for (String string : second) {
            result[index] = string;
            index++;
        }
        return result;
    }

    @Override
    public int findMax(int[] source) {
        if (source == null || source.length == 0) {
            throw new IllegalArgumentException();
        }

        int result = Integer.MIN_VALUE;
        for (int i : source) {
            if (i > result) {
                result = i;
            }
        }
        return result;
    }

    @Override
    public String[] filter(String[] source, FilteringCondition condition) {
        if (source == null || condition == null) {
            throw new IllegalArgumentException();
        }
        if (source.length == 0) {
            return source;
        }
        List<String> list = new ArrayList<>();

        for (String string : source) {
            if (condition.isValid(string)) {
                list.add(string);
            }
        }
        return list.toArray(new String[0]);
    }

    @Override
    public String[] removeElements(String[] source, String[] toRemote) {
        if (source == null || toRemote == null) {
            throw new IllegalArgumentException();
        }
        if (source.length == 0 || toRemote.length == 0) {
            return source;
        }
        List<String> sourceList = Arrays.asList(source);
        List<String> toRemoteList = Arrays.asList(toRemote);
        List<String> result = new ArrayList<>();

        for (String string : sourceList) {
            if (!toRemoteList.contains(string)) {
                result.add(string);
            }
        }
        return result.toArray(new String[0]);
    }

    @Override
    public String[] map(String[] source, MappingOperation operation) {
        if (source == null || operation == null) {
            throw new IllegalArgumentException();
        }
        if (source.length == 0) {
            return source;
        }
        String[] result = new String[source.length];
        for (int i = 0; i < source.length; i++) {
            result[i] = operation.map(source[i]);
        }
        return result;
    }

    @Override
    public String[] flatMap(String[] source, FlatMappingOperation operation) {
        if (source == null || operation == null) {
            throw new IllegalArgumentException();
        }
        if (source.length == 0) {
            return source;
        }
        List<String> list = new ArrayList<>();

        for (String string : source) {
            list.addAll(Arrays.asList(operation.flatMap(string)));
        }
        return list.stream()
                .map(String::trim)
                .distinct()
                .map(Integer::parseInt)
                .sorted(Comparator.reverseOrder())
                .map(Object::toString)
                .toArray(String[]::new);
    }

    private void validate(String[] array) {
        if (array == null) {
            throw new IllegalArgumentException();
        }
    }
}
