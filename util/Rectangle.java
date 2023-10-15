package util;

public class Rectangle {
    public double x, y, width, height;

    public Rectangle(double x, double y, double width, double height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void translate(double dx, double dy) {
        this.x += dx;
        this.y += dy;
    }

    public boolean contains(Point2D p) {
        return p.getX() >= x && p.getX() <= x + width && p.getY() >= y && p.getY() <= y + height;

    }
}
