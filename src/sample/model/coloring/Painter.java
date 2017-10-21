package sample.model.coloring;

import javafx.scene.image.PixelWriter;
import javafx.scene.paint.Color;
import sample.model.Pixel;

public class Painter {

    public void coloring(Pixel pixel, Color color, boolean[][] coordinate, PixelWriter pixelWriter) {
        if (!coordinate[pixel.getX()][pixel.getY()]) {
            coordinate[pixel.getX()][pixel.getY()] = true;
            pixelWriter.setColor(pixel.getX(), pixel.getY(), color);
            coloring(new Pixel(pixel.getX() + 1, pixel.getY()), color, coordinate, pixelWriter);
            coloring(new Pixel(pixel.getX(), pixel.getY() + 1), color, coordinate, pixelWriter);
            coloring(new Pixel(pixel.getX() - 1, pixel.getY()), color, coordinate, pixelWriter);
            coloring(new Pixel(pixel.getX(), pixel.getY() - 1), color, coordinate, pixelWriter);
        }
    }

    public void coloringModifier(Pixel pixel, Color color, boolean[][] coordinate, PixelWriter pixelWriter) {
        int xLeft = pixel.getX();
        int xRight = pixel.getX() + 1;
        int x = pixel.getX();
        int y = pixel.getY();
        int width = coordinate.length;
        int height = coordinate[0].length;
        if (!coordinate[x][y]) {
            while (!coordinate[xLeft][y] && xLeft > 0) {
                pixelWriter.setColor(xLeft, y, color);
                coordinate[xLeft][y] = true;
                xLeft--;
            }
            xLeft++;
            while (!coordinate[xRight][y] && xRight < width - 1) {
                pixelWriter.setColor(xRight, y, color);
                coordinate[xRight][y] = true;
                xRight++;
            }
            xRight--;
            for (int i = xLeft; i < xRight; i++) {
                if (i > 0 && i < width - 1) {
                    if (y + 1 < height && !coordinate[i][y + 1])
                        coloringModifier(new Pixel(i, y + 1), color, coordinate, pixelWriter);
                    if (y - 1 > 0 && !coordinate[i][y - 1]) {
                        coloringModifier(new Pixel(i, y - 1), color, coordinate, pixelWriter);
                    }
                }
            }
        }
    }

    public void coloringWithImage(Pixel pixel, Color color, Color colorBack, boolean[][] coordinate, byte[][] image, PixelWriter pixelWriter) {
        int xLeft = pixel.getX();
        int xRight = pixel.getX() + 1;
        int x = pixel.getX();
        int y = pixel.getY();
        int width = coordinate.length;
        int height = coordinate[0].length;
        if (!coordinate[x][y]) {
            while (!coordinate[xLeft][y] && xLeft > 0) {
                drawImage(image, coordinate, xLeft, y, color, colorBack, pixelWriter);
                xLeft--;
            }
            xLeft++;
            while (!coordinate[xRight][y] && xRight < width - 1) {
                drawImage(image, coordinate, xRight, y, color, colorBack, pixelWriter);
                xRight++;
            }
            xRight--;
            for (int i = xLeft; i < xRight; i++) {
                if (i > 0 && i < width - 1) {
                    if (y + 1 < height && !coordinate[i][y + 1])
                        coloringWithImage(new Pixel(i, y + 1), color, colorBack, coordinate, image, pixelWriter);
                    if (y - 1 > 0 && !coordinate[i][y - 1])
                        coloringWithImage(new Pixel(i, y - 1), color, colorBack, coordinate, image, pixelWriter);
                }
            }
        }
    }

    private void drawImage(byte[][] image, boolean[][] coordinate, int x, int y, Color color, Color colorBack, PixelWriter pixelWriter) {
        if (image[x % image.length][y % image[0].length] == 1) {
            pixelWriter.setColor(x, y, color);
        } else {
            pixelWriter.setColor(x, y, colorBack);
        }
        coordinate[x][y] = true;
    }
}
