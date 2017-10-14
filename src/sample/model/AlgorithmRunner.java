package sample.model;

import sample.model.figures.Drawable;
import sample.model.figures.abstractfigure.Dot;
import sample.model.figures.abstractfigure.concretefigure.Circle;
import sample.model.figures.abstractfigure.concretefigure.CurveBezier;
import sample.model.figures.abstractfigure.concretefigure.Line;

import java.util.List;

public class AlgorithmRunner {

    private Drawable<List<Dot>, boolean[][]> line;
    private Drawable<List<Dot>, boolean[][]> circle;
    private Drawable<List<Dot>, boolean[][]> curveBezier;

    public List<Dot> fillLine(boolean[][] coordinate, Dot startDot, Dot endDot) {
        line = new Line(startDot, endDot);
        line.draw(coordinate);
        return line.getDots();
    }

    public List<Dot> fillCircle(boolean[][] coordinate, Dot startDot, int radius) {
        circle = new Circle(startDot, radius);
        circle.draw(coordinate);
        return circle.getDots();
    }

    public List<Dot> fillCurveBezier(boolean[][] coordinate, Dot[] controlDots) {
        curveBezier = new CurveBezier(controlDots);
        curveBezier.draw(coordinate);
        return curveBezier.getDots();
    }
}
