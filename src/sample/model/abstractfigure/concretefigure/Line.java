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

    /*
    @Override
    public void draw(boolean[][] coordinate, Canvas canvas) {
        double x = startPixel.getX();
        double y = startPixel.getY();
        double dx = endPixel.getX() - startPixel.getX();
        double dy = endPixel.getY() - startPixel.getY();
        double m = dy / dx;
        double e = m - 1/2;
        int i = 0;
        while (i < dx) {
            if (e >= 0) {
                y++;
                e = e + m - 1;
            }
            else {
                e = e + m;
            }
            x++;
            canvas.getGraphicsContext2D().getPixelWriter().setColor(x, y, color);
            i++;
        }
    }
    */

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

    /*
    @Override
    public void draw(boolean[][] coordinate, Canvas canvas) {
        int x0 = startPixel.getX();
        int x1 = endPixel.getX();
        int y0 = startPixel.getY();
        int y1 = endPixel.getY();
        int x = startPixel.getX();
        int y = startPixel.getY();
        int i = 0;
        double m, e, dx = endPixel.getX() - startPixel.getX();
        double dy = endPixel.getY() - startPixel.getY();
        if (dx != 0 && dy != 0) {
            m = dy / dx;
            if (Math.abs(x1 - x0) >= Math.abs(y1 - y0)) {
                e = m - 0.5;
                if (x1 < x0) {
                    if (y1 < y0) {
                        while (i < Math.abs(dx)) {
                            if (e >= 0) {
                                y--;
                                e = e + m - 1;
                            } else e = e + m;
                            x--;
                            canvas.getGraphicsContext2D().getPixelWriter().setColor(x, y, color);
                            coordinate[x][y] = true;
                            i = i + 1;
                        }
                    } else {
                        while (i < Math.abs(dx)) {
                            if (e >= 0) {
                                y = y + 1;
                                e = e + m - 1;
                            } else e = e - m;
                            x--;
                            canvas.getGraphicsContext2D().getPixelWriter().setColor(x, y, color);
                            coordinate[x][y] = true;
                            i = i + 1;
                        }
                    }
                } else {
                    if (y1 < y0) {
                        m = Math.abs(m);
                        while (i < Math.abs(dx)) {
                            if (e >= 0) {
                                y--;
                                e = e + m - 1;
                            } else e = e + m;
                            x++;
                            canvas.getGraphicsContext2D().getPixelWriter().setColor(x, y, color);
                            coordinate[x][y] = true;
                            i = i + 1;
                        }
                    } else {
                        while (i < Math.abs(dx)) {
                            if (e >= 0) {
                                y = y + 1;
                                e = e + m - 1;
                            } else e = e + m;
                            x = x + 1;
                            canvas.getGraphicsContext2D().getPixelWriter().setColor(x, y, color);
                            coordinate[x][y] = true;
                            i = i + 1;
                        }
                    }

                }
            } else {
                m = dx / dy;
                e = m - 0.5;
                if (x1 < x0) {
                    if (y1 < y0) {
                        while (i < Math.abs(dy)) {
                            if (e >= 0) {
                                x--;
                                e = e + m - 1;
                            } else e = e + m;

                            y = y - 1;
                            canvas.getGraphicsContext2D().getPixelWriter().setColor(x, y, color);
                            coordinate[x][y] = true;
                            i = i + 1;
                        }
                    } else {
                        m = Math.abs(m);
                        while (i < Math.abs(dy)) {
                            if (e >= 0) {
                                x = x - 1;
                                e = e + m - 1;
                            } else e = e + m;

                            y = y + 1;
                            canvas.getGraphicsContext2D().getPixelWriter().setColor(x, y, color);
                            coordinate[x][y] = true;
                            i = i + 1;
                        }
                    }
                } else {
                    if (y1 < y0) {
                        m = Math.abs(m);
                        while (i < Math.abs(dy)) {
                            if (e >= 0) {
                                x++;
                                e = e + m - 1;
                            } else e = e + m;
                            y--;
                            canvas.getGraphicsContext2D().getPixelWriter().setColor(x, y, color);
                            coordinate[x][y] = true;
                            i = i + 1;
                        }
                    } else {
                        while (i < Math.abs(dy)) {
                            if (e >= 0) {
                                x = x + 1;
                                e = e + m - 1;
                            } else e = e + m;

                            y = y + 1;
                            canvas.getGraphicsContext2D().getPixelWriter().setColor(x, y, color);
                            coordinate[x][y] = true;
                            i = i + 1;
                        }

                    }
                }
            }
        } else {
            if (dx == 0) {
                while (i < Math.abs(dy)) {
                    canvas.getGraphicsContext2D().getPixelWriter().setColor(x, y, color);
                    coordinate[x][y] = true;
                    i++;
                    if (dy > 0) y++;
                    else y--;
                }
            } else {
                while (i < Math.abs(dx)) {
                    canvas.getGraphicsContext2D().getPixelWriter().setColor(x, y, color);
                    coordinate[x][y] = true;
                    i++;
                    if (dx > 0) x++;
                    else x--;
                }
            }
        }
    }
    */
}
