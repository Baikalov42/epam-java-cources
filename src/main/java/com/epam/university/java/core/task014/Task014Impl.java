package com.epam.university.java.core.task014;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Task014Impl implements Task014 {

    @Override
    public Collection<VampireNumber> getVampireNumbers() {
        VampireNumberFactory factory = new VampireNumberFactoryImpl();
        Set<VampireNumber> vampireNumbers = new HashSet<>();
        Set<Integer> multiplication = new HashSet<>();

        for (int i = 11; i < 100; i++) {
            for (int k = 11; k < 100; k++) {
                if (i % 10 == 0 && k % 10 == 0) {
                    continue;
                }
                if (isVampire(i * k, i, k)) {
                    if (!multiplication.contains(i * k)) {
                        vampireNumbers.add(factory.newInstance(i * k, i, k));
                        multiplication.add(i * k);
                    }
                }
            }
        }
        return vampireNumbers;
    }

    /** checks if combination of two numbers is vampire.
     *
     * @param multiplication multiplication of first and second.
     * @param first first part of vampire.
     * @param second second part of vampire.
     * @return if number is vampire.
     */
    public boolean isVampire(int multiplication, int first, int second) {
        List<Integer> m = new ArrayList<>();
        List<Integer> f = new ArrayList<>();
        String ms = Integer.toString(multiplication);

        for (int i = 0; i < ms.length(); i++) {
            m.add(Integer.parseInt(String.valueOf(ms.charAt(i))));
        }
        if (m.size() != 4) {
            return false;
        }
        f.add(first / 10);
        f.add(first % 10);
        f.add(second / 10);
        f.add(second % 10);

        for (Integer i : f) {
            m.remove(i);
        }
        return m.isEmpty();
    }
}
