package sample.model.abstractfigure.concretefigure;

import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;
import sample.model.abstractfigure.Figure;
import sample.model.Pixel;

public class Line extends Figure<boolean[][], Canvas> {

    private Pixel startPixel;
    private Pixel endPixel;
    private Color color;

    public Line(Pixel startPixel, Pixel endPixel, Color color) {
        this.startPixel = startPixel;
        this.endPixel = endPixel;
        this.color = color;
    }

    @Override
    public void draw(boolean[][] coordinate, Canvas canvas) {
        int x0 = startPixel.getX();
        int y0 = startPixel.getY();
        int x1 = endPixel.getX();
        int y1 = endPixel.getY();
        int dy, dx, sign;
        dy = y1 - y0;
        dx = x0 - x1;
        if (Math.abs(dy) > Math.abs(dx)) {
            sign = 1;
        } else
            sign = -1;
        int signDy, signDx;
        if (dy < 0) {
            signDy = -1;
        } else
            signDy = 1;
        if (dx < 0) {
            signDx = -1;
        } else
            signDx = 1;
        int f = 0;
        coordinate[x0][y0] = true;
        canvas.getGraphicsContext2D().getPixelWriter().setColor(x0, y0, color);
        int x = x0, y = y0;
        if (sign == -1) {
            do {
                f += dy * signDy;
                if (f > 0) {
                    f -= dx * signDx;
                    y += signDy;
                }
                x -= signDx;
                coordinate[x - 1][y - 1] = true;
                canvas.getGraphicsContext2D().getPixelWriter().setColor(x, y, color);
            } while (x != x1 || y != y1);
        } else {
            do {
                f += dx * signDx;
                if (f > 0) {
                    f -= dy * signDy;
                    x -= signDx;
                }
                y += signDy;
                coordinate[x - 1][y - 1] = true;
                canvas.getGraphicsContext2D().getPixelWriter().setColor(x, y, color);
            } while (x != x1 || y != y1);
        }
    }
}
