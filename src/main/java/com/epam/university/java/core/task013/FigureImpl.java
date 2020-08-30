package com.epam.university.java.core.task013;

import java.util.ArrayList;
import java.util.Collection;

public class FigureImpl implements Figure {

    private final ArrayList<Vertex> vertexArray = new ArrayList<>();
    private final int countOfVertex;

    public FigureImpl(int countOfVertex) {
        this.countOfVertex = countOfVertex;
    }

    @Override
    public void addVertex(Vertex vertex) {
        vertexArray.add(vertex);
    }

    @Override
    public Collection<Vertex> getVertexes() {
        return vertexArray;
    }
}