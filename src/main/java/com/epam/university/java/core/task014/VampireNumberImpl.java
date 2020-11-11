package com.epam.university.java.core.task014;

public class VampireNumberImpl implements VampireNumber {
    private int multiplication;
    private int first;
    private int second;

    @Override
    public int getMultiplication() {
        return multiplication;
    }

    @Override
    public int getFirst() {
        return first;
    }

    @Override
    public int getSecond() {
        return second;
    }

    public void setFirst(int first) {
        this.first = first;
    }

    public void setSecond(int second) {
        this.second = second;
    }

    public void setMultiplication(int multiplication) {
        this.multiplication = multiplication;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof VampireNumber)) {
            return false;
        }
        VampireNumber o = (VampireNumber) obj;
        if (this.multiplication != o.getMultiplication()) {
            return false;
        }
        return (this.first == o.getFirst() && this.second == o.getSecond())
                || (this.second == o.getFirst() && this.first == o.getSecond());
    }
}
