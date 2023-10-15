import java.awt.geom.Line2D;
import java.util.List;

import util.Point2D;
import util.Vector2D;

public class Bird {
    Point2D position;
    Point2D oldPosition;
    public Vector2D velocity;
    Vector2D force;

    double radius;

    public boolean up = false;

    public Bird(double x, double y, double radius) {
        this.position = new Point2D(x, y);
        this.oldPosition = new Point2D(x, y);
        this.velocity = new Vector2D();
        this.force = new Vector2D();
        this.radius = radius;
    }

    public void update(double dt) {

        this.velocity.set(this.position.sub(this.oldPosition));

        this.oldPosition = new Point2D(this.position);

        double dtSq = dt * dt;

        double py = velocity.y + force.y * dtSq;

        if (up) {
            py = -Config.BIRD_JUMP;
            up = false;
        }

        this.position.add(0, py);
        this.force.set(0, 0);
    }

    public void accelerate(Vector2D v) {
        this.force.add(v);
    }

    /**
     * check if the bird has collided with the ground
     */
    public boolean checkGroundCollision() {
        return position.y + radius > Config.PANEL_HEIGHT;
    }

    /**
     * check if the bird has collided with any of the pipes
     */
    public boolean checkPipeCollision(List<Pipe> pipes) {

        for (Pipe pipe : pipes) {
            // find closest point on pipe to bird
            double[] xArr = pipe.top.getXArr();
            double[] yArr = pipe.top.getYArr();

            double[] xArr2 = pipe.bottom.getXArr();
            double[] yArr2 = pipe.bottom.getYArr();

            for (int i = 0; i < 4; i++) {// 4 sides of a pipe
                Point2D p1 = new Point2D(xArr[i], yArr[i]);
                Point2D p2 = new Point2D(xArr[(i + 1) % 4], yArr[(i + 1) % 4]);

                Point2D p3 = new Point2D(xArr2[i], yArr2[i]);
                Point2D p4 = new Point2D(xArr2[(i + 1) % 4], yArr2[(i + 1) % 4]);

                if (Line2D.ptSegDist(p1.x, p1.y, p2.x, p2.y, position.x, position.y) < radius
                        || Line2D.ptSegDist(p3.x, p3.y, p4.x, p4.y, position.x, position.y) < radius)
                    return true;

            }

        }

        return false;

    }

    public static Point2D closestPointOnLineSegment(Point2D p, Point2D a, Point2D b) {
        double x0 = p.getX();
        double y0 = p.getY();
        double lx1 = a.getX();
        double ly1 = a.getY();
        double lx2 = b.getX();
        double ly2 = b.getY();

        double dx = lx2 - lx1;
        double dy = ly2 - ly1;

        // Calculate the squared length of the line segment
        double segmentLengthSquared = dx * dx + dy * dy;

        // Handle degenerate cases where the segment has zero length
        if (segmentLengthSquared == 0) {
            return new Point2D(lx1, ly1);
        }

        // Calculate the vector from the start of the segment to the point
        double vectorX = x0 - lx1;
        double vectorY = y0 - ly1;

        // Calculate the dot product between the vector and the direction
        double dotProduct = vectorX * dx + vectorY * dy;

        // Calculate the closest point on the line segment
        double t = dotProduct / segmentLengthSquared;
        t = Math.max(0, Math.min(1, t)); // Clamp t to the range [0, 1]
        double closestX = lx1 + t * dx;
        double closestY = ly1 + t * dy;

        return new Point2D(closestX, closestY);
    }
}