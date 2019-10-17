package sample;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;

public interface Drawable {

    void setRadius(double radius);
    double getRadius();

    void setXpos(double xpos);

    void setYpos(double ypos);

    double getXpos();

    void setWidth(double width);

    void setHeight(double height);

    double getWidth();

    double getHeight();

    DoubleProperty xposProperty();

    double getYpos();

    DoubleProperty yposProperty();

    void draw(GraphicsContext gc);

    Paint getPaint();

    ObjectProperty<Paint> paintProperty();

    void setPaint(Paint paint);

    boolean intersects(double x, double y);
}
