package com.epam.university.java.core.task013;

public class VertexImpl implements Vertex {
    private int pointX;
    private int pointY;

    public VertexImpl(int x, int y) {
        this.pointX = x;
        this.pointY = y;
    }

    @Override
    public int getX() {
        return pointX;
    }

    @Override
    public void setX(int value) {
        pointX = value;
    }

    @Override
    public int getY() {
        return pointY;
    }

    @Override
    public void setY(int value) {
        pointY = value;
    }
}
