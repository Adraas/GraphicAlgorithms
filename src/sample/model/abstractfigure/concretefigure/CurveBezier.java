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
            coordinate[x][y] = true;
        }
    }

    /*
    public void DrawBezier(Pixel[] pixels, Bitmap Pixel, Color Color)
    {
        int length = pixels.length;
        int countPoints = length << 6;
        Pixel[] tempPixels = new Pixel[length];
        Pixel[] drawingPoints = new Pixel[countPoints + 1];
        float dt = 1f / countPoints;
        float t = 0f;
        for (int i = 0; i <= countPoints; i++)
        {
            System.arraycopy(pixels, 0, tempPixels, 0, length);
            for (int j = length - 1; j > 0; j--)
            {
                for (int k = 0; k < j; k++)
                {
                    tempPixels[k].setX((int) (tempPixels[k].getX() + t * (tempPixels[k + 1].getX() - tempPixels[k].getX())));
                    tempPixels[k].setY((int) (tempPixels[k].getY() + t * (tempPixels[k + 1].getY() - tempPixels[k].getY())));
                }
            }
            drawingPoints[i] = tempPixels[0];
            t += dt;
        }
        for (int i = 1; i < countPoints + 1; i++)
        {
            DrawLine((int)drawingPoints[i - 1].getX(), (int)drawingPoints[i - 1].getY(), (int)drawingPoints[i].getX(), (int)drawingPoints[i].getY(), Pixel, Color);
        }
    }
    */
}
