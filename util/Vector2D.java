package util;

import java.util.Objects;

public class Vector2D {
    public double x, y;

    public Vector2D() {
        this.x = 0;
        this.y = 0;
    }

    public Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vector2D(Vector2D v) {
        this.x = v.x;
        this.y = v.y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void set(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void set(Vector2D v) {
        this.x = v.x;
        this.y = v.y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double distance(double vx, double vy) {
        vx -= x;
        vy -= y;
        return Math.sqrt(vx * vx + vy * vy);
    }

    public double distance(Vector2D v) {
        double vx = v.x - this.x;
        double vy = v.x - this.y;
        return Math.sqrt(vx * vx + vy * vy);
    }

    public double getLength() {
        return Math.sqrt(x * x + y * y);
    }

    public void normalize() {
        double magnitude = getLength();
        if (magnitude == 0) {
            return;
        }

        x /= magnitude;
        y /= magnitude;
    }

    public Vector2D getNormalized() {
        double magnitude = getLength();
        return new Vector2D(x / magnitude, y / magnitude);
    }

    public Vector2D add(Vector2D v) {
        this.x += v.x;
        this.y += v.y;
        return this;
    }

    public void add(double vx, double vy) {
        this.x += vx;
        this.y += vy;
    }

    public Vector2D addX(double vx) {
        this.x += vx;
        return this;
    }

    public Vector2D addY(double vy) {
        this.y += vy;
        return this;
    }

    public Vector2D subtract(Vector2D v) {
        this.x -= v.x;
        this.y -= v.y;
        return this;
    }

    public Vector2D getSubtracted(Vector2D v) {
        this.x -= v.x;
        this.y -= v.y;
        return this;
    }

    public void subtract(double vx, double vy) {
        this.x -= vx;
        this.y -= vy;
    }

    public Vector2D subtractX(double vx) {
        this.x -= vx;
        return this;
    }

    public Vector2D subtractY(double vy) {
        this.y -= vy;
        return this;
    }

    public Vector2D multiply(Vector2D o) {
        this.x *= o.x;
        this.y *= o.y;
        return this;
    }

    public Vector2D multiply(double scalar) {
        this.x *= scalar;
        this.y *= scalar;
        return this;
    }

    public Vector2D multiplyX(double scalar) {
        this.x *= scalar;
        return this;
    }

    public Vector2D multiplyY(double scalar) {
        this.y *= scalar;
        return this;
    }

    public void divide(Vector2D v) {
        this.x /= v.x;
        this.y /= v.y;
    }

    public void divide(double scalar) {
        this.x /= scalar;
        this.y /= scalar;
    }

    public double dot(Vector2D v) {
        return this.x * v.x + this.y * v.y;
    }

    public double cross(Vector2D v) {
        return this.x * v.y - this.y * v.x;
    }

    @Override
    public boolean equals(Object o) {

        if (o == null)
            return false;

        if (!(o instanceof Vector2D)) {
            return false;
        }

        Vector2D v = (Vector2D) o;

        return (Double.doubleToLongBits(v.getX()) == Double.doubleToLongBits(this.x)
                && Double.doubleToLongBits(v.getY()) == Double.doubleToLongBits(this.y));
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

}
