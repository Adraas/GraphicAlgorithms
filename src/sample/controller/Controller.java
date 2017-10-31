package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;
import sample.controller.clone.BooleanArray2DCloner;
import sample.model.abstractfigure.Figure;
import sample.model.Pixel;
import sample.model.abstractfigure.concretefigure.Circle;
import sample.model.abstractfigure.concretefigure.CurveBezier;
import sample.model.abstractfigure.concretefigure.Line;
import sample.model.coloring.Painter;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private AnchorPane parent;
    @FXML
    private Canvas canvas;
    @FXML
    private Button drawing;
    @FXML
    private Button coloring;
    @FXML
    private Button deleting;
    private GraphicsContext graphicsContext;
    private boolean[][] coordinate;
    private Painter painter;
    private Figure<boolean[][], Canvas> line;
    private Figure<boolean[][], Canvas> circle;
    private Figure<boolean[][], Canvas> curveBezier;

    public Controller() {
        painter = new Painter();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        graphicsContext = canvas.getGraphicsContext2D();
        coordinate = new boolean[(int) canvas.getWidth()][(int) canvas.getHeight()];
    }

    @FXML
    public void onActionDrawing(ActionEvent actionEvent) {
        draw();
        drawing.setVisible(false);
        coloring.setVisible(true);
        deleting.setVisible(true);
    }

    @FXML
    public void onActionColoring(ActionEvent actionEvent) {
        coloring();
        coloring.setVisible(false);
    }

    @FXML
    public void onActionDeleting(ActionEvent actionEvent) {
        graphicsContext.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        drawing.setVisible(true);
        coloring.setVisible(false);
        deleting.setVisible(false);
    }

    private void draw() {
        drawMountain();
        drawSun();
        drawLake();
    }

    private void drawMountain() {
        fillLine(Color.GRAY, new Pixel(1, 300), new Pixel(80, 70));
        fillLine(Color.GRAY, new Pixel(80, 70), new Pixel(120, 70));
        fillLine(Color.GRAY, new Pixel(80, 70), new Pixel(110, 1));
        fillLine(Color.GRAY, new Pixel(110, 1), new Pixel(120, 20));
        fillLine(Color.GRAY, new Pixel(120, 20), new Pixel(126, 13));
        fillLine(Color.GRAY, new Pixel(126, 13), new Pixel(150, 90));
        fillLine(Color.GRAY, new Pixel(120, 70), new Pixel(150, 90));
        fillLine(Color.GRAY, new Pixel(150, 90), new Pixel(250, 300));
        fillLine(Color.GRAY, new Pixel(0, 300), new Pixel((int) canvas.getWidth(), 300));
    }

    private void drawSun() {
        fillCircle(Color.ORANGE, new Pixel(300, 100), 30);
    }

    private void drawLake() {
        fillCurveBezier(Color.DARKBLUE, new Pixel[]{new Pixel(260, 300), new Pixel(420, 500), new Pixel(400, 300)});
    }


    private void fillLine(Color color, Pixel startPixel, Pixel endPixel) {
        line = new Line(startPixel, endPixel, color);
        line.draw(coordinate, canvas);
    }

    private void fillCircle(Color color, Pixel startPixel, int radius) {
        circle = new Circle(startPixel, radius, color);
        circle.draw(coordinate, canvas);
    }

    private void fillCurveBezier(Color color, Pixel[] controlDots) {
        curveBezier = new CurveBezier(controlDots, color);
        curveBezier.draw(coordinate, canvas);
    }

    private void coloring() {
        painter.coloring(
                new Pixel(300, 110),
                Color.YELLOW,
                (boolean[][]) new BooleanArray2DCloner<>(coordinate).clone().getArray(),
                canvas.getGraphicsContext2D().getPixelWriter());
        painter.coloringModifier(
                new Pixel(5, 295),
                Color.GRAY,
                (boolean[][]) new BooleanArray2DCloner<>(coordinate).clone().getArray(),
                canvas.getGraphicsContext2D().getPixelWriter());
        painter.coloringModifier(
                new Pixel(1, 1),
                Color.LIGHTBLUE,
                (boolean[][]) new BooleanArray2DCloner<>(coordinate).clone().getArray(),
                canvas.getGraphicsContext2D().getPixelWriter());
        painter.coloringModifier(
                new Pixel(400, 5),
                Color.LIGHTBLUE,
                (boolean[][]) new BooleanArray2DCloner<>(coordinate).clone().getArray(),
                canvas.getGraphicsContext2D().getPixelWriter());
        painter.coloringModifier(
                new Pixel(270, 310),
                Color.AQUA,
                (boolean[][]) new BooleanArray2DCloner<>(coordinate).clone().getArray(),
                canvas.getGraphicsContext2D().getPixelWriter());
        painter.coloringModifier(
                new Pixel(5, 310),
                Color.LIGHTGREEN,
                (boolean[][]) new BooleanArray2DCloner<>(coordinate).clone().getArray(),
                canvas.getGraphicsContext2D().getPixelWriter());
        byte[][] image = new byte[][] {
                {0, 1, 0, 0, 1, 0},
                {1, 0, 1, 1, 0, 1},
                {1, 0, 0, 0, 0, 1},
                {1, 0, 1, 1, 0, 1},
                {0, 1, 0, 0, 1, 0}
        };
        painter.coloringWithImage(
                new Pixel(90, 67),
                Color.BLACK,
                Color.WHITE,
                (boolean[][]) new BooleanArray2DCloner<>(coordinate).clone().getArray(),
                (byte[][]) new BooleanArray2DCloner<>(image).clone().getArray(),
                canvas.getGraphicsContext2D().getPixelWriter());
    }
}
