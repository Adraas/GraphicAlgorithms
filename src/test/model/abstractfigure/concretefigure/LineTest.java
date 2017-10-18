package test.model.abstractfigure.concretefigure;

import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;
import org.junit.jupiter.api.Test;
import sample.model.Pixel;
import sample.model.abstractfigure.Figure;
import sample.model.abstractfigure.concretefigure.Line;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LineTest {

    @Test
    public void testDrawLine() {
        Figure<boolean[][], Canvas> line = new Line(new Pixel(0, 0), new Pixel(4, 4), Color.BLACK);
        boolean[][] coordinateExpected = new boolean[5][5];
        boolean[][] coordinateActual = new boolean[5][5];
        coordinateActual[0][0] = true;
        coordinateActual[1][1] = true;
        coordinateActual[2][2] = true;
        coordinateActual[3][3] = true;
        coordinateActual[4][4] = true;
        line.draw(coordinateExpected, new Canvas());
        assertEquals(Arrays.asList(
                coordinateExpected[0][0],
                coordinateExpected[1][1],
                coordinateExpected[2][2],
                coordinateExpected[3][3],
                coordinateExpected[4][4],
                coordinateExpected[3][1]),
                Arrays.asList(
                        coordinateActual[0][0],
                        coordinateActual[1][1],
                        coordinateActual[2][2],
                        coordinateActual[3][3],
                        coordinateActual[4][4],
                        coordinateActual[3][1]));
    }
}
