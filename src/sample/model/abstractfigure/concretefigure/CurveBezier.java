package sample.model.abstractfigure.concretefigure;

import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;
import sample.model.abstractfigure.Figure;
import sample.model.Pixel;

public class CurveBezier extends Figure<boolean[][], Canvas> {

    private Pixel[] controlPixels;
    private Color color;

    public CurveBezier(Pixel[] controlPixels, Color color) {
        this.controlPixels = controlPixels;
        this.color = color;
    }

    @Override
    public void draw(boolean[][] coordinate, Canvas canvas) {
        int x, y;
        for (double t = 0; t <= 1; t = t + 0.0001) {
            x = (int) (Math.pow(1 - t, 2) * controlPixels[0].getX() + 2 * t * (1 - t) * controlPixels[1].getX() + t * t * controlPixels[2].getX());
            y = (int) (Math.pow(1 - t, 2) * controlPixels[0].getY() + 2 * t * (1 - t) * controlPixels[1].getY() + t * t * controlPixels[2].getY());
            canvas.getGraphicsContext2D().getPixelWriter().setColor(x, y, color);
        }
    }
}
