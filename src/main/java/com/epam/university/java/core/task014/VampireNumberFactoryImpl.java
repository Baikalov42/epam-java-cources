package com.epam.university.java.core.task014;

public class VampireNumberFactoryImpl implements VampireNumberFactory {

    @Override
    public VampireNumber newInstance(int multiplication, int first, int second) {
        VampireNumberImpl vn = new VampireNumberImpl();

        vn.setMultiplication(multiplication);
        vn.setFirst(first);
        vn.setSecond(second);

        return vn;
    }
}
