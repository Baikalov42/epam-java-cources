package com.epam.university.java.core.task028;

public class Task028Impl implements Task028 {

    @Override
    public int getWays(int value, int power) {
        return getWays(value, power, 1);
    }

    private int getWays(int value, int power, int current) {
        int different = value - (int) Math.pow(current, power);
        if (different == 0) {
            return 1;
        }
        if (different < 0) {
            return 0;
        }
        return getWays(different, power, current + 1)
                + getWays(value, power, current + 1);
    }
}
