package sample.model;

import sample.model.figures.IDrawing;
import sample.model.figures.abstractfigure.Dot;
import sample.model.figures.concretefigure.BezierCurve;
import sample.model.figures.concretefigure.Ellipse;
import sample.model.figures.concretefigure.Line;

import java.util.List;

public class AlgorithmRunner {

    private IDrawing<List<Dot>> line;
    private IDrawing<List<Dot>> ellipse;
    private IDrawing<List<Dot>> curveBezier;

    public AlgorithmRunner() {
        ellipse = new Ellipse();
        curveBezier = new BezierCurve();
    }

    public List<Dot> fillLine(Dot startDot, Dot endDot) {
        line = new Line(startDot, endDot);
        line.draw();
        return line.getDots();
    }
}
