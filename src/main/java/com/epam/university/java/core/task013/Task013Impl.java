package com.epam.university.java.core.task013;

import java.util.Collection;

public class Task013Impl implements Task013 {

    @Override
    public Figure invokeActions(Figure figure, Collection<FigureAction> actions) {
        if (figure == null || actions == null || actions.size() == 0) {
            throw new IllegalArgumentException();
        }

        for (FigureAction action : actions) {
            action.run(figure);
        }
        return figure;
    }

    @Override
    public boolean isConvexPolygon(Figure figure) {
        if (figure == null) {
            throw new IllegalArgumentException();
        }

        int lines = 0;
        for (Vertex start : figure.getVertexes()) {
            for (Vertex end : figure.getVertexes()) {

                boolean negative = false;
                boolean positive = false;

                int pointA = start.getY() - end.getY();
                int pointB = end.getX() - start.getX();
                int pointC = (start.getX() * end.getY()) - (end.getX() * start.getY());

                for (Vertex vertex : figure.getVertexes()) {

                    if (pointA * vertex.getX() + pointB * vertex.getY() + pointC < 0) {
                        negative = true;
                    } else if (pointA * vertex.getX() + pointB * vertex.getY() + pointC > 0) {
                        positive = true;
                    }
                }
                if (negative ^ positive) {
                    lines++;
                    break;
                }
            }
        }
        return lines == figure.getVertexes().size();
    }
}
