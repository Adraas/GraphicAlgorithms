package sample.model.figures.abstractfigure.concretefigure;

import sample.model.figures.Drawable;
import sample.model.figures.abstractfigure.Dot;

import java.util.ArrayList;
import java.util.List;

public class CurveBezier implements Drawable<List<Dot>, boolean[][]> {

    private List<Dot> dots;
    private Dot[] controlDots;

    public CurveBezier(Dot[] controlDots) {
        this.dots = new ArrayList<>();
        this.controlDots = controlDots;
    }

    @Override
    public void draw(boolean[][] coordinate) {
        //
    }

    @Override
    public List<Dot> getDots() {
        return dots;
    }
}
