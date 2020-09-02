package com.epam.university.java.core.task017;

import java.util.Locale;

public class Task017Impl implements Task017 {
    @Override
    public String formatString(Object... args) {
        checkForNull(args);
        return String.format("You know %s, %s!", args);
    }

    @Override
    public String formatNumbers(Object... args) {
        checkForNull(args);
        return String.format(new Locale("en", "CA"), "%1.1f, %<1.2f, +%<1.2f, %<a", args);
    }

    @Override
    public String formatDates(Object... args) {
        checkForNull(args);
        return String.format("%tY.%<td.%<tm", args);
    }

    private void checkForNull(Object... args) {
        for (Object o : args) {
            if (o == null) {
                throw new IllegalArgumentException();
            }
        }
    }
}
