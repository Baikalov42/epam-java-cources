package com.epam.university.java.core.task013;

import java.util.ArrayList;
import java.util.Collection;

public class FigureImpl implements Figure {

    private final ArrayList<Vertex> vertexArray = new ArrayList<>();

    public final int countOfVertex;

    public FigureImpl(int countOfVertex) {
        this.countOfVertex = countOfVertex;
    }

    @Override
    public void addVertex(Vertex vertex) {
        if (countOfVertex != vertexArray.size()) {
            vertexArray.add(vertex);
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public Collection<Vertex> getVertexes() {
        return vertexArray;
    }


}