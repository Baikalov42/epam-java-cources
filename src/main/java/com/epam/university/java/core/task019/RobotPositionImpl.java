package com.epam.university.java.core.task019;

public class RobotPositionImpl implements RobotPosition {

    private int pointX;
    private int pointY;

    @Override
    public int getX() {
        return pointX;
    }

    @Override
    public int getY() {
        return pointY;
    }

    @Override
    public void setX(int pointX) {
        this.pointX = pointX;
    }

    @Override
    public void setY(int pointY) {
        this.pointY = pointY;
    }
}
