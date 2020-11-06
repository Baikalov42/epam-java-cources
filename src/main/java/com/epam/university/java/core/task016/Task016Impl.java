package com.epam.university.java.core.task016;


import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;


public class Task016Impl implements Task016 {

    @Override
    public Collection<Coordinate> getSquaresInsideCircle(int radius) {
        if (radius <= 0) {
            throw new IllegalArgumentException();
        }
        CoordinateFactory factory = new CoordinateFactoryImpl();
        Set<Coordinate> result = new HashSet<>();

        for (double i = 0.5; i < radius; i = i + 0.5) {
            for (double k = 0.5; k < radius; k = k + 0.5) {

                if (Math.sqrt(i * i + k * k) < radius) {

                    result.add(factory.newInstance((int) (i / 0.5), (int) (k / 0.5)));
                    result.add(factory.newInstance((int) (-i / 0.5), (int) (k / 0.5)));
                    result.add(factory.newInstance((int) (i / 0.5), (int) (-k / 0.5)));
                    result.add(factory.newInstance((int) (-i / 0.5), (int) (-k / 0.5)));
                }
            }
        }
        return new ArrayList<>(result);
    }
}
