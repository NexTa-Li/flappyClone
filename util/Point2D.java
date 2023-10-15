package util;

import java.util.Objects;

public class Point2D {
    public double x, y;

    public Point2D() {
        this.x = 0.0;
        this.y = 0.0;
    }

    public Point2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Point2D(Point2D p) {
        this.x = p.x;
        this.y = p.y;
    }

    public void add(Point2D p) {
        add(p.x, p.y);
    }

    public void add(Vector2D v) {
        add(v.getX(), v.getY());
    }

    public void add(double x, double y) {
        this.x += x;
        this.y += y;
    }

    public void subtract(Point2D p) {
        subtract(p.x, p.y);
    }

    public void subtract(Vector2D v) {
        subtract(v.getX(), v.getY());
    }

    public void subtract(double x, double y) {
        this.x -= x;
        this.y -= y;
    }

    public Vector2D sub(Point2D p) {
        return new Vector2D(this.x - p.x, this.y - p.y);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void addX(double x) {
        this.x += x;
    }

    public void addY(double y) {
        this.y += y;
    }

    public double distanceSq(double x, double y) {
        double dx = x - getX();
        double dy = y - getY();
        return (dx * dx + dy * dy);
    }

    public double distance(double px, double py) {
        double dx = px - getX();
        double dy = py - getY();
        return Math.sqrt(dx * dx + dy * dy);
    }

    public double distance(Point2D p) {
        return distance(p.getX(), p.getY());
    }

    public void setLocation(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void translate(double x, double y) {
        this.x += x;
        this.y += y;
    }

    @Override
    public boolean equals(Object o) {

        if (o == null)
            return false;

        if (!(o instanceof Point2D)) {
            return false;
        }

        Point2D p = (Point2D) o;

        return (Double.doubleToLongBits(p.getX()) == Double.doubleToLongBits(this.x)
                && Double.doubleToLongBits(p.getY()) == Double.doubleToLongBits(this.y));
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "(" + String.format("%,.10f", x) + ", " + String.format("%,.10f", y) + ")";
    }
}
