package sample.model.figures.abstractfigure.concretefigure;

import sample.model.figures.Drawable;
import sample.model.figures.abstractfigure.Dot;

import java.util.ArrayList;
import java.util.List;

public class Circle implements Drawable<List<Dot>, boolean[][]> {

    private List<Dot> dots;
    private Dot startDot;
    private int radius;

    public Circle(Dot startDot, int radius) {
        this.dots = new ArrayList<>();
        this.startDot = startDot;
        this.radius = radius;
    }

    @Override
    public void draw(boolean[][] coordinate) {
        int x0 = startDot.getX();
        int y0 = startDot.getY();
        int x = 0;
        int y = radius;
        int d = 3 - 2 * radius;
        while (y >= x) {
            dots.addAll(get8Pixels(coordinate, x0, x, y0, y));
            if (d < 0) {
                d = d + 4 * x + 6;
            }
            else {
                d = d + 4 * (x - y) + 10;
                y--;
            }
            x++;
        }
    }

    private List<Dot> get8Pixels(boolean[][] coordinate, int x0, int x, int y0, int y) {
        dots.add(new Dot(x + x0, y + y0));
        coordinate[x + x0][y + y0] = true;
        dots.add(new Dot(x + x0, -y + y0));
        coordinate[x + x0][-y + y0] = true;
        dots.add(new Dot(-x + x0, y + y0));
        coordinate[-x + x0][y + y0] = true;
        dots.add(new Dot(-x + x0, -y + y0));
        coordinate[-x + x0][-y + y0] = true;
        dots.add(new Dot(y + x0, x + y0));
        coordinate[y + x0][x + y0] = true;
        dots.add(new Dot(y + x0, -x + y0));
        coordinate[y + x0][-x + y0] = true;
        dots.add(new Dot(-y + x0, x + y0));
        coordinate[-y + x0][x + y0] = true;
        dots.add(new Dot(-y + x0, -x + y0));
        coordinate[-y + x0][-x + y0] = true;
        return dots;
    }

    @Override
    public List<Dot> getDots() {
        return dots;
    }
}
