package sample.model.abstractfigure.concretefigure;

import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;
import sample.model.abstractfigure.Figure;
import sample.model.Pixel;

public class Circle extends Figure<boolean[][], Canvas> {

    private Pixel startDot;
    private int radius;
    private Color color;

    public Circle(Pixel startDot, int radius, Color color) {
        this.startDot = startDot;
        this.radius = radius;
        this.color = color;
    }

    @Override
    public void draw(boolean[][] coordinate, Canvas canvas) {
        int x0 = startDot.getX();
        int y0 = startDot.getY();
        int x = 0;
        int y = radius;
        int d = 3 - 2 * radius;
        while (y >= x) {
            draw8Pixels(coordinate, canvas, x0, x, y0, y);
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

    private void draw8Pixels(boolean[][] coordinate, Canvas canvas, int x0, int x, int y0, int y) {
        canvas.getGraphicsContext2D().getPixelWriter().setColor(x + x0, y + y0, color);
        coordinate[x + x0][y + y0] = true;
        canvas.getGraphicsContext2D().getPixelWriter().setColor(x + x0, -y + y0, color);
        coordinate[x + x0][-y + y0] = true;
        canvas.getGraphicsContext2D().getPixelWriter().setColor(-x + x0, y + y0, color);
        coordinate[-x + x0][y + y0] = true;
        canvas.getGraphicsContext2D().getPixelWriter().setColor(-x + x0, -y + y0, color);
        coordinate[-x + x0][-y + y0] = true;
        canvas.getGraphicsContext2D().getPixelWriter().setColor(y + x0, x + y0, color);
        coordinate[y + x0][x + y0] = true;
        canvas.getGraphicsContext2D().getPixelWriter().setColor(y + x0, -x + y0, color);
        coordinate[y + x0][-x + y0] = true;
        canvas.getGraphicsContext2D().getPixelWriter().setColor(-y + x0, x + y0, color);
        coordinate[-y + x0][x + y0] = true;
        canvas.getGraphicsContext2D().getPixelWriter().setColor(-y + x0, -x + y0, color);
        coordinate[-y + x0][-x + y0] = true;
    }
}
