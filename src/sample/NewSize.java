package sample;

import javafx.css.Size;
import sample.shapes.Changes;
import sample.shapes.Shape;

public class NewSize implements Changes {

    private Shape shape;
    private final double size;

    public NewSize(Shape figure, double size) {
        shape = figure;
        this.size = size;
        ;
    }

    @Override
    public void undo() {
        shape.setWidth(size);
        shape.setHeight(size);

    }
}
