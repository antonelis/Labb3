package sample.shapes;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;
import sample.Drawable;

public abstract class Shape implements Drawable {

    private DoubleProperty xpos = new SimpleDoubleProperty();
    private DoubleProperty ypos = new SimpleDoubleProperty();
    private ObjectProperty<Paint> paint = new SimpleObjectProperty<>();

    public Shape(double xpos, double ypos, Paint paint) {
        setXpos(xpos);
        setYpos(ypos);
        this.paint.setValue(paint);
    }

    @Override
    public void setRadius(double radius) {

    }

    @Override
    public double getRadius() {
        return 0;
    }

    @Override
    public void setXpos(double xpos) {
        this.xpos.set(xpos);
    }

    @Override
    public void setYpos(double ypos) {
        this.ypos.set(ypos);
    }

    @Override
    public double getXpos() {
        return xpos.get();
    }

    @Override
    public void setWidth(double width) {

    }

    @Override
    public void setHeight(double height) {

    }

    @Override
    public double getWidth() {
        return 0;
    }

    @Override
    public double getHeight() {
        return 0;
    }

    @Override
    public DoubleProperty xposProperty() {
        return xpos;
    }

    @Override
    public double getYpos() {
        return ypos.get();
    }

    @Override
    public DoubleProperty yposProperty() {
        return ypos;
    }


    @Override
    public Paint getPaint() {
        return paint.get();
    }

    @Override
    public ObjectProperty<Paint> paintProperty() {
        return paint;
    }

    @Override
    public void setPaint(Paint paint) {
        this.paint.set(paint);
    }

    @Override
    public boolean intersects(double x, double y) {
        return false;
    }
@Override
    public  abstract void draw(GraphicsContext gc);

}