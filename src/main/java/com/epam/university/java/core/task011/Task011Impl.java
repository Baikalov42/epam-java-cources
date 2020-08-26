package com.epam.university.java.core.task011;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class Task011Impl implements Task011 {
    @Override
    public String getLastName(String[] collection) {

        if (collection == null || collection.length == 0) {
            throw new IllegalArgumentException();
        }

        int index = 0;
        while (collection.length > 1) {

            collection[index] = "";
            index++;

            if (index == collection.length) {
                index = 1;
            }
            if (index == collection.length - 1) {
                index = 0;
            }
            collection = Arrays.stream(collection)
                    .filter(x -> !x.equals(""))
                    .toArray(String[]::new);
        }
        return collection[0];
    }

    @Override
    public String getLastName(ArrayList<String> collection) {
        if (collection == null || collection.size() == 0) {
            throw new IllegalArgumentException();
        }
        if (collection.size() == 1) {
            return collection.get(0);
        }

        int index = 0;
        while (collection.size() > 1) {

            collection.remove(collection.get(index));
            if (index == collection.size()) {
                index = 1;
            } else if (index == collection.size() - 1) {
                index = 0;
            } else {
                index++;
            }
        }

        return collection.get(0);
    }

    @Override
    public String getLastName(LinkedList<String> collection) {
        if (collection == null || collection.size() == 0) {
            throw new IllegalArgumentException();
        }
        while (collection.size() > 1) {
            collection.removeFirst();
            collection.addLast(collection.pop());
        }
        return collection.peek();
    }
}
