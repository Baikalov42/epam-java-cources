package com.epam.university.java.core.task036;

import java.util.function.Function;

public class IntegratorImpl implements Integrator {

    @Override
    public double integrate(double left, double right, Function<Double, Double> function) {

        double result = 0;
        double partsNumber = 1000000;
        double k = (right - left) / partsNumber;

        for (double i = left + k; i <= right; i += k) {
            result += k * function.apply(i);
        }
        return result;
    }
}
