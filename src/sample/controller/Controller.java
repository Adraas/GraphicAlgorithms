package sample.controller;

import com.sun.javafx.robot.impl.BaseFXRobot;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import sample.model.AlgorithmRunner;
import sample.model.figures.abstractfigure.Dot;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
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
    private AlgorithmRunner runner;
    private List<Dot> dots;
    private boolean[][] coordinate;
    private int color;
    private BaseFXRobot robot;

    public Controller() {
        runner = new AlgorithmRunner();
        dots = new ArrayList<>();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        graphicsContext = canvas.getGraphicsContext2D();
        robot = new BaseFXRobot(canvas.getScene());
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
        color();
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
        fillLine(Color.GRAY, new Dot(1, 300), new Dot(80, 70));
        fillLine(Color.GRAY, new Dot(80, 70), new Dot(120, 70));
        fillLine(Color.GRAY, new Dot(80, 70), new Dot(110, 1));
        fillLine(Color.GRAY, new Dot(110, 1), new Dot(120, 20));
        fillLine(Color.GRAY, new Dot(120, 20), new Dot(126, 13));
        fillLine(Color.GRAY, new Dot(126, 13), new Dot(150, 90));
        fillLine(Color.GRAY, new Dot(120, 70), new Dot(150, 90));
        fillLine(Color.GRAY, new Dot(150, 90), new Dot(250, 300));
        fillLine(Color.GRAY, new Dot(0, 300), new Dot((int) canvas.getWidth(), 300));
    }

    private void drawSun() {
        fillCircle(Color.ORANGE, new Dot(300, 100), 25);
    }

    private void drawLake() {
        fillCurveBezier(Color.DARKBLUE, new Dot[]{});
    }


    private void fillLine(Paint paint, Dot startDot, Dot endDot) {
        dots = runner.fillLine(coordinate, startDot, endDot);
        drawByCoordinate(paint);
    }

    private void fillCircle(Paint paint, Dot startDot, int radius) {
        dots = runner.fillCircle(coordinate, startDot, radius);
        drawByCoordinate(paint);
    }

    private void fillCurveBezier(Paint paint, Dot[] controlDots) {
        dots = runner.fillCurveBezier(coordinate, controlDots);
        drawByCoordinate(paint);
    }

    private void drawByCoordinate(Paint paint) {
        for (Dot dot : dots) {
            int x = dot.getX();
            int y = dot.getY();
            graphicsContext.getPixelWriter().setColor(x, y, (Color) paint);
        }
    }

    private void color() {
        //TODO: coloring by coordinates
    }
}
