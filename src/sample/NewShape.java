package sample;

import javafx.collections.ObservableList;
import sample.shapes.Changes;
import sample.shapes.Shape;

public class NewShape implements Changes {

    private final ObservableList<Shape> list;
    private Shape shape;

    public NewShape(Shape figure, ObservableList<Shape> items) {
        shape = figure;
        list = items;
    }

    @Override
    public void undo() {
        list.remove(shape);
    }
}
