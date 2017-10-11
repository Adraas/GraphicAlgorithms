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
    private int color;
    private BaseFXRobot robot;

    public Controller() {
        runner = new AlgorithmRunner();
        dots = new ArrayList<>();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        graphicsContext = canvas.getGraphicsContext2D();
        graphicsContext.setFill(Color.BLACK);
        robot = new BaseFXRobot(canvas.getScene());
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
        // TODO: to draw other elements
    }

    private void drawMountain() {
        // It's checking of the coordinate system
        drawLine(new Dot(1, 300), new Dot(100, 1));
        drawLine(new Dot(100, 1), new Dot(120, 20));
        drawLine(new Dot(120, 20), new Dot(126, 13));
        drawLine(new Dot(126, 13), new Dot(150, 295));
    }

    private void drawLine(Dot startDot, Dot endDot) {
        dots = runner.fillLine(startDot, endDot);
        for (Dot dot : dots) {
            double x = dot.getX();
            double y = dot.getY();
            graphicsContext.getPixelWriter().setColor((int) x, (int) y, Color.BLACK);

            /*
            It will being stay at other place. It's just checking.
            I need to write the algorithm for coloring the figure.
             */
            //color = robot.getPixelColor((int) dot.getX(), (int) dot.getY());
        }
    }

    private void color() {
        //TODO: coloring by coordinates
    }
}
