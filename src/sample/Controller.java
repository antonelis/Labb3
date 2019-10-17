package sample;

import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Slider;
import javafx.scene.input.*;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import sample.shapes.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Stack;

import static sample.ShapeType.*;


public class Controller {

    @FXML
    Button clear;
    @FXML
    Canvas canvas;
    @FXML
    ColorPicker colorPicker;
    @FXML
    Slider slider;
    @FXML
    Button circleButton;
    @FXML
    Button squareButton;
    @FXML
    Button lineButton;
    @FXML
    Button pointButton;

    Model model;
    Stage stage;
    GraphicsContext gc;
    Color color;
    ShapeType shape;
    private Stack<Changes> lastChange = new Stack<>();

    public Controller(Model model) {
        this.model = model;
    }

    public void initialize() {
        gc = canvas.getGraphicsContext2D();
        canvas.widthProperty().addListener(observable -> drawShapes());
        canvas.heightProperty().addListener(observable -> drawShapes());
        model.getItems().addListener(this::onListChange);
    }

    private void onListChange(Observable observable) {

    }

    public void circleButtonAction(ActionEvent actionEvent) {
        shape = CIRCLE;
    }

    public void squareButtonAction(ActionEvent actionEvent) {
        shape = SQUARE;
    }

    public void lineButtonAction(ActionEvent actionEvent) {
        shape = LINE;
    }

    public void pointButtonAction(ActionEvent actionEvent) {
        shape = POINT;
    }

    public void drawShapes() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.WHEAT);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        gc.setFill(color);
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void changeColor(ActionEvent actionEvent) {
        color = colorPicker.getValue();
        gc.setFill(color);
        gc.setStroke(color);
    }

    public void init(Scene scene) {
        scene.addEventFilter(KeyEvent.KEY_PRESSED,
                new EventHandler<KeyEvent>() {
                    final KeyCombination ctrlZ = new KeyCodeCombination(KeyCode.Z,
                            KeyCombination.CONTROL_DOWN);

                    public void handle(KeyEvent ke) {
                        if (ctrlZ.match(ke)) {
                            System.out.println(model.getItems().size());
                            if (!lastChange.empty())
                                lastChange.pop().undo();
                            redrawCanvas();
                            System.out.println("Undo");
                            ke.consume(); // <-- stops passing the event to next node
                        }
                    }
                });
    }

    public void canvasOnMouseClicked(MouseEvent mouseEvent) {
        double size = slider.getValue();
        Shape figure;
        boolean newColor = mouseEvent.isControlDown();
        boolean newSize = mouseEvent.isShiftDown();
        if (newSize) {
            for (Shape shape : model.getItems()) {
                if (shape.intersects(mouseEvent.getX(), mouseEvent.getY())) {
                    lastChange.push(new NewSize(shape, shape.getHeight()));
                    shape.setWidth(slider.getValue());
                    shape.setHeight(slider.getValue());
                    redrawCanvas();
                }
            }
        } else if (newColor) {
            for (Shape shape : model.getItems()) {
                if (shape.intersects(mouseEvent.getX(), mouseEvent.getY())) {
                    lastChange.push(new NewColor(shape, shape.getPaint()));
                    shape.setPaint(colorPicker.getValue());
                    redrawCanvas();
                }
            }

        } else {
            switch (shape) {
                case CIRCLE:
                    figure = new Circle(mouseEvent.getX(), mouseEvent.getY(), size, colorPicker.getValue());
                    lastChange.push(new NewShape(figure, model.getItems()));
                    model.getItems().add(figure);
                    redrawCanvas();
                    break;
                case SQUARE:
                    figure = new Square(mouseEvent.getX(), mouseEvent.getY(), colorPicker.getValue(), size, size);
                    lastChange.push(new NewShape(figure, model.getItems()));
                    model.getItems().add(figure);
                    redrawCanvas();
                    break;
                case LINE:
                    figure = new Line(mouseEvent.getX(), mouseEvent.getY(), colorPicker.getValue(), size, 5);
                    lastChange.push(new NewShape(figure, model.getItems()));
                    model.getItems().add(figure);
                    redrawCanvas();
                    break;

                case POINT:
                    figure = new Point(mouseEvent.getX(), mouseEvent.getY(), 5, colorPicker.getValue());
                    lastChange.push(new NewShape(figure, model.getItems()));
                    model.getItems().add(figure);
                    redrawCanvas();
                    break;
            }
        }
        redrawCanvas();
    }

    private void redrawCanvas() {
        clearCanvas();
        for (var shape : model.getItems()) {
            shape.draw(gc);
        }
    }

    public void clearCanvas() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.rgb(244, 244, 244));
        gc.fillRect(0, 0, 600, 600);
    }

    public void clearList(ActionEvent actionEvent) {
        model.getItems().clear();
        redrawCanvas();
    }

    public void saveFileButton(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save File");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.dir")));
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter(".svg", "*.svg"));
        File path = fileChooser.showSaveDialog(stage);
        File filePath = new File(String.valueOf(path));
        try (FileWriter out = new FileWriter(filePath)) {
            out.write("<?xml version=\"1.0\" standalone=\"no\"?>\n" +
                    "<svg width=\"373.0\" height=\"336.0\" version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\">" + "\n");
            for (Drawable shape : model.getItems()) {
                out.write(shape + "\n");
            }
            out.write("\n" + "</svg>");
        }
    }
}
