package sample.shapes;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;

public class Point extends Shape {
    private double radius;
    private double height;
    private double width;

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

    public Point(double xpos, double ypos, double circD, Paint paint) {
        super(xpos, ypos, paint);
        this.radius = circD/2;
        height = circD;
        width = circD;
    }

    //<editor-fold desc="Getters/Setters">


    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
        this.height = radius * 2;
        this.width = radius * 2;
    }
    //</editor-fold>

    @Override
    public boolean intersects(double x, double y) {
        double r = getRadius();
        double x2 = (x - getXpos());
        double y2 = (y - getYpos());
        if ((x2 * x2 + y2 * y2) < r * r)
            return true;
        return false;
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setFill(getPaint());
        gc.fillOval(getXpos() - getWidth()/2, getYpos() - getHeight()/2, getWidth(), getHeight());
    }
    @Override
    public String toString() {
        return "<circle cx=" + "\"" + getXpos()+ "\"" + " "+"cy="+ "\""+getYpos()+ "\""+" "+ "r="+"\""+getRadius()+"\"" +" "+ "fill="+"\""+ "#" +getPaint().toString().substring(2,8)+"\""+"/>";
    }
}

