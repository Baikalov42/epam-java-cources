package com.epam.university.java.core.task050;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

public class Task050Impl implements Task050 {

    @Override
    public double calculate(int size, Map<Double, Double> items) {

        validate(size, items);

        if (items.size() == 1) {
            return getResultFromSingleElement(items);
        }

        TreeMap<Double, Map.Entry<Double, Double>> sorted = new TreeMap<>();

        for (Map.Entry<Double, Double> entry : items.entrySet()) {
            sorted.put(entry.getKey() / entry.getValue(), entry);
        }

        double result = 0;
        while (size > 0) {
            Map.Entry<Double, Map.Entry<Double, Double>> entries
                    = sorted.pollLastEntry();

            if (entries.getValue().getValue() < size) {
                result += entries.getValue().getKey();
                size -= entries.getValue().getValue();

            } else {
                result += size * entries.getKey();
                break;
            }
        }

        return formatDouble(result);
    }

    private double formatDouble(double result) {
        DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols(Locale.US);
        String pattern = "#0.000";
        DecimalFormat decimalFormat = new DecimalFormat(pattern, otherSymbols);

        return Double.parseDouble(decimalFormat.format(result));

    }

    private double getResultFromSingleElement(Map<Double, Double> items) {
        return items.keySet()
                .stream()
                .mapToDouble(x -> x)
                .findFirst()
                .getAsDouble();
    }

    private void validate(int size, Map<Double, Double> items) {
        if (size == 0 || items == null) {
            throw new IllegalArgumentException();
        }
    }
}
