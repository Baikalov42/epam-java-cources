package com.epam.university.java.core.task012;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class GraphImpl implements Graph {

    private final Map<Integer, Set<Integer>> vertexConnections;
    private final int countOfVertexes;

    /**
     * GraphFactory's constructor.
     *
     * @param countOfVertexes - count of vertex.
     */
    public GraphImpl(int countOfVertexes) {
        this.countOfVertexes = countOfVertexes;
        this.vertexConnections = new HashMap<>();

        for (int i = 1; i <= countOfVertexes; i++) {
            vertexConnections.put(i, new HashSet<>());
        }
    }

    @Override
    public void createEdge(int from, int to) {
        if (from > countOfVertexes || to > countOfVertexes) {
            throw new IllegalArgumentException();
        }
        vertexConnections.get(from).add(to);
        vertexConnections.get(to).add(from);
    }

    @Override
    public boolean edgeExists(int from, int to) {
        return vertexConnections.get(from).contains(to);
    }

    @Override
    public void removeEdge(int from, int to) {
        if (from > countOfVertexes || to > countOfVertexes) {
            throw new IllegalArgumentException();
        }
        vertexConnections.get(from).remove(to);
        vertexConnections.get(to).remove(from);
    }

    @Override
    public Collection<Integer> getAdjacent(int from) {
        if (from > countOfVertexes) {
            throw new IllegalArgumentException();
        }
        Set<Integer> adjacentSet = new HashSet<>();
        findConnect(adjacentSet, from);

        return adjacentSet;
    }

    private void findConnect(Collection<Integer> adjacentSet, int from) {
        for (Integer i : vertexConnections.keySet()) {
            if (vertexConnections.get(i).contains(from)) {
                if (!adjacentSet.contains(i)) {
                    adjacentSet.add(i);
                    findConnect(adjacentSet, i);
                }
            }
        }
    }
}

