package com.epam.university.java.core.task033;

public class LessExceptionImpl extends RuntimeException implements LessException, BaseException {

    LessExceptionImpl(String message) {
        super(message, new BaseExceptionImpl());
    }
}
