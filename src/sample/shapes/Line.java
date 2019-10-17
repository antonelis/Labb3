package sample.shapes;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;

public class Line extends Shape {

    private double height;
    private double width;

    public Line(double xpos, double ypos, Paint paint, double width, double height) {
        super(xpos, ypos, paint);
        this.height = height;
        this.width = width;
    }

    //<editor-fold desc="Getters/Setters">
    @Override
    public double getHeight() {
        return height;
    }

    @Override
    public void setHeight(double height) {
        this.height = height;
    }

    @Override
    public double getWidth() {
        return width;
    }

    @Override
    public void setWidth(double width) {
        this.width = width;
    }
    //</editor-fold>

    @Override
    public boolean intersects(double x, double y) {
        if (x > getXpos() - getWidth() / 2 &&
                x < getXpos() + getWidth() / 2 &&
                y > getYpos() - getHeight() / 2 &&
                y < getYpos() + getHeight() / 2) {
            return true;
        } else {
            return false;
        }
    }


    @Override
    public void draw(GraphicsContext gc) {
        gc.setFill(getPaint());
        gc.fillRect(getXpos() - getWidth() / 2, getYpos() - getHeight() / 2, getWidth(), getHeight());
    }

    @Override
    public String toString() {
        return "<line x=\"" + (getXpos() - getWidth() / 2) + "\" " + "y=\"" + (getYpos() - getHeight() / 2) + "\" " + "width=\"" + getWidth() + "\" " + "height=\"" + getHeight() + "\" " + "fill=\"" + "#" + getPaint().toString().substring(2, 8) + "\"/>";
    }
}

