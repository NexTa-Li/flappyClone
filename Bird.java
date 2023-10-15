import util.Point2D;
import util.Vector2D;

public class Bird {
    Point2D position;
    Point2D oldPosition;
    Vector2D velocity;
    Vector2D force;

    double radius;

    public boolean up = false;

    public Bird(double radius) {
        this.position = new Point2D();
        this.oldPosition = new Point2D();
        this.velocity = new Vector2D();
        this.force = new Vector2D();
        this.radius = radius;
    }

    public void update(double dt) {

        if (up) {
            this.position.add(0, -Config.BIRD_JUMP);
            up = false;
        }

        this.velocity.set(this.position.sub(this.oldPosition));
        this.oldPosition = new Point2D(this.position);

        double dtSq = dt * dt;

        double px = velocity.x + force.x * dtSq;
        double py = velocity.y + force.y * dtSq;

        this.position.add(px, py);
        this.force.set(0, 0);
    }

    public void accelerate(Vector2D v) {
        this.force.add(v);
    }

    /**
     * check if the bird has collided with the ground
     */
    public boolean checkGroundCollision() {
        return position.y > Config.PANEL_HEIGHT - radius;
    }

}