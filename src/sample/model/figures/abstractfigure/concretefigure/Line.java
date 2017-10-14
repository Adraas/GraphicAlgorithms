package sample.model.figures.abstractfigure.concretefigure;

import sample.model.figures.Drawable;
import sample.model.figures.abstractfigure.Dot;

import java.util.ArrayList;
import java.util.List;

public class Line implements Drawable<List<Dot>, boolean[][]> {

    private List<Dot> dots;
    private Dot startDot;
    private Dot endDot;

    public Line(Dot startDot, Dot endDot) {
        this.dots = new ArrayList<>();
        this.startDot = startDot;
        this.endDot = endDot;
    }

    /*
    @Override
    public void draw() {
        double x = startDot.getX();
        double y = startDot.getY();
        double dx = endDot.getX() - startDot.getX();
        double dy = endDot.getY() - startDot.getY();
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
            dots.add(new Dot(x, y));
            i++;
        }
    }
    */

    @Override
    public void draw(boolean[][] coordinate)
    {
        int x0 = startDot.getX();
        int x1 = endDot.getX();
        int y0 = startDot.getY();
        int y1 = endDot.getY();
        int x = startDot.getX();
        int y = startDot.getY();
        int i = 0;
        double m, e, dx = endDot.getX() - startDot.getX();
        double dy = endDot.getY() - startDot.getY();
        if (dx != 0 && dy != 0)
        {
            m = dy / dx;
            if (Math.abs(x1 - x0) >= Math.abs(y1 - y0))
            {
                e = m - 0.5;
                if (x1 < x0)
                {
                    if (y1 < y0)
                    {
                        while (i < Math.abs(dx))
                        {
                            if (e >= 0)
                            {
                                y--;
                                e = e + m - 1;
                            }
                            else e = e + m;
                            x--;
                            dots.add((new Dot(x, y)));
                            coordinate[x][y] = true;
                            i = i + 1;
                        }
                    }
                    else
                    {
                        while (i < Math.abs(dx))
                        {
                            if (e >= 0)
                            {
                                y = y + 1;
                                e = e + m - 1;
                            }
                            else e = e - m;
                            x--;
                            dots.add((new Dot(x, y)));
                            coordinate[x][y] = true;
                            i = i + 1;
                        }
                    }
                }
                else
                {
                    if (y1 < y0)
                    {
                        m = Math.abs(m);
                        while (i < Math.abs(dx))
                        {
                            if (e >= 0)
                            {
                                y--;
                                e = e + m - 1;
                            }
                            else e = e + m;
                            x++;
                            dots.add((new Dot(x, y)));
                            coordinate[x][y] = true;
                            i = i + 1;
                        }
                    }
                    else
                    {
                        while (i < Math.abs(dx))
                        {
                            if (e >= 0)
                            {
                                y = y + 1;
                                e = e + m - 1;
                            }
                            else e = e + m;
                            x = x + 1;
                            dots.add((new Dot(x, y)));
                            coordinate[x][y] = true;
                            i = i + 1;
                        }
                    }

                }
            }
            else
            {
                m = dx / dy;
                e = m - 0.5;
                if (x1 < x0)
                {
                    if (y1 < y0)
                    {
                        while (i < Math.abs(dy))
                        {
                            if (e >= 0)
                            {
                                x--;
                                e = e + m - 1;
                            }
                            else e = e + m;

                            y = y - 1;
                            dots.add((new Dot(x, y)));
                            coordinate[x][y] = true;
                            i = i + 1;
                        }
                    }
                    else
                    {
                        m = Math.abs(m);
                        while (i < Math.abs(dy))
                        {
                            if (e >= 0)
                            {
                                x = x - 1;
                                e = e + m - 1;
                            }
                            else e = e + m;

                            y = y + 1;
                            dots.add((new Dot(x, y)));
                            coordinate[x][y] = true;
                            i = i + 1;
                        }
                    }
                }
                else
                {
                    if (y1 < y0)
                    {
                        m = Math.abs(m);
                        while (i < Math.abs(dy))
                        {
                            if (e >= 0)
                            {
                                x++;
                                e = e + m - 1;
                            }
                            else e = e + m;
                            y--;
                            dots.add((new Dot(x, y)));
                            coordinate[x][y] = true;
                            i = i + 1;
                        }
                    }
                    else
                    {
                        while (i < Math.abs(dy))
                        {
                            if (e >= 0)
                            {
                                x = x + 1;
                                e = e + m - 1;
                            }
                            else e = e + m;

                            y = y + 1;
                            dots.add((new Dot(x, y)));
                            coordinate[x][y] = true;
                            i = i + 1;
                        }

                    }
                }
            }
        }
        else
        {
            if (dx == 0)
            {
                while (i < Math.abs(dy))
                {
                    dots.add((new Dot(x, y)));
                    coordinate[x][y] = true;
                    i++;
                    if (dy > 0) y++;
                    else y--;
                }
            }
            else
            {
                while (i < Math.abs(dx))
                {
                    dots.add((new Dot(x, y)));
                    coordinate[x][y] = true;
                    i++;
                    if (dx > 0) x++;
                    else x--;
                }
            }
        }
    }

    @Override
    public List<Dot> getDots() {
        return dots;
    }
}
