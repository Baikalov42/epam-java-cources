package com.epam.university.java.core.task005;

public class Task005Impl implements Task005 {

    @Override
    public PiHolder findPi(int digits) {

        if (digits <= 0 || digits > 10) {
            throw new IllegalArgumentException();
        }

        int first = 0;
        int second = 0;

        int startNumber = (int) (Math.pow(10, digits - 1));
        int finishNumber = (int) Math.pow(10, digits) - 1;

        double result;
        double min = 1;

        for (int i = (int) (startNumber * Math.PI); i <= finishNumber; i++) {
            for (int k = startNumber; k < i; k++) {
                double a = i;
                double b = k;
                result = Math.abs((a / b) - Math.PI);
                if (result < min) {
                    min = result;
                    first = i;
                    second = k;
                }
                if (k > i / 3) {
                    break;
                }
            }
        }
        return new PiHolderImpl(first, second);
    }
}
