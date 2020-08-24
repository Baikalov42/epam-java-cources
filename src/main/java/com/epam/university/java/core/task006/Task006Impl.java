package com.epam.university.java.core.task006;

import java.util.Collection;

public class Task006Impl implements Task006 {
    @Override
    public double resistance(Collection<Measurement> measurements) {
        if (measurements == null) {
            throw new IllegalArgumentException();
        }
        double avgVoltage = 0.0;
        double avgAmperage = 0.0;

        for (Measurement element : measurements) {
            avgAmperage += element.getAmperage();
            avgVoltage += element.getVoltage();
        }
        avgAmperage = avgAmperage / measurements.size();
        avgVoltage = avgVoltage / measurements.size();


        double numerator = 0.0;
        double denominator = 0.0;

        for (Measurement m : measurements) {
            numerator += (m.getAmperage() - avgAmperage) * (m.getVoltage() - avgVoltage);
            denominator += Math.pow(m.getAmperage() - avgAmperage, 2);
        }

        return Math.round((numerator / denominator) * 1000) / 1000.0;
    }
}
