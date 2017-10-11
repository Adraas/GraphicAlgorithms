package sample.model.figures.concretefigure;

import sample.model.figures.IDrawing;
import sample.model.figures.abstractfigure.Dot;

import java.util.List;

public class Ellipse implements IDrawing<List<Dot>> {

    @Override
    public void draw() {
        //
    }

    @Override
    public List<Dot> getDots() {
        return null;
    }
}
