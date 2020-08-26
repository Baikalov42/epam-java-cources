package com.epam.university.java.core.task008;

import java.util.LinkedList;
import java.util.NoSuchElementException;

public class Task008Impl implements Task008 {

    @Override
    public boolean isValid(String sourceString) {

        char[] source = sourceString.toCharArray();
        LinkedList<Character> bracers = new LinkedList<>();

        for (char symbol : source) {
            switch (symbol) {
                case '(':
                case '[':
                case '{':
                    bracers.add(symbol);
                    break;
                case ')':
                    try {
                        if (bracers.getLast() == '(') {
                            bracers.removeLast();
                            break;
                        } else {
                            return false;
                        }
                    } catch (NoSuchElementException e) {
                        return false;
                    }
                case ']':
                    try {
                        if (bracers.getLast() == '[') {
                            bracers.removeLast();
                            break;
                        } else {
                            return false;
                        }
                    } catch (NoSuchElementException e) {
                        return false;
                    }
                case '}':
                    try {
                        if (bracers.getLast() == '{') {
                            bracers.removeLast();
                            break;
                        } else {
                            return false;
                        }
                    } catch (NoSuchElementException e) {
                        return false;
                    }
                default:
                    break;
            }
        }
        return bracers.isEmpty();
    }
}
