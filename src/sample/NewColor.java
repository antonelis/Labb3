package sample;

import javafx.scene.paint.Paint;
import sample.shapes.Changes;
import sample.shapes.Shape;

public class NewColor implements Changes {

    private Shape shape;
    private final Paint paint;

    public NewColor(Shape figure, Paint oldPaint) {
        shape = figure;
        paint = oldPaint;
    }

    @Override
    public void undo() {
   shape.setPaint(paint);
    }
}
