package com.epam.university.java.core.task008;

import java.util.ArrayList;
import java.util.List;

public class Task008Impl implements Task008 {

    @Override
    public boolean isValid(String sourceString) {

        char[] source = sourceString.toCharArray();
        List<Character> bracers = new ArrayList<>();

        for (char symbol : source) {
            switch (symbol) {
                case '(':
                case '[':
                case '{':
                    bracers.add(symbol);
                    break;
                case ')':
                    try {
                        if (bracers.get(getLastIndex(bracers)) == '(') {
                            bracers.remove(getLastIndex(bracers));
                            break;
                        } else {
                            return false;
                        }
                    } catch (IndexOutOfBoundsException e) {
                        return false;
                    }
                case ']':
                    try {
                        if (bracers.get(getLastIndex(bracers)) == '[') {
                            bracers.remove(getLastIndex(bracers));
                            break;
                        } else {
                            return false;
                        }
                    } catch (IndexOutOfBoundsException e) {
                        return false;
                    }
                case '}':
                    try {
                        if (bracers.get(getLastIndex(bracers)) == '{') {
                            bracers.remove(getLastIndex(bracers));
                            break;
                        } else {
                            return false;
                        }
                    } catch (IndexOutOfBoundsException e) {
                        return false;
                    }
                default:
                    break;
            }
        }
        return bracers.isEmpty();
    }

    private int getLastIndex(List<Character> list) {
        return list.size() - 1;
    }
}
