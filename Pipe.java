
import util.*;

public class Pipe {
    public Rectangle top, bottom; // width is fixed
    public double gap;

    public Pipe(double x, double y, double width, double height, double gap) {
        this.top = new Rectangle(x, y, width, height);
        double height2 = Config.PANEL_HEIGHT - height - gap;
        this.bottom = new Rectangle(x, y + height + gap, width, height2); // gap is fixed

        this.gap = gap;
    }

    public void move(double dx, double dy) {
        this.top.translate(dx, dy);
        this.bottom.translate(dx, dy);
    }

    public void checkCollision() {

    }
}
