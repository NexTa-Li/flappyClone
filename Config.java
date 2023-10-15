import util.Vector2D;

public interface Config {
    public static final int PANEL_WIDTH = 1600;
    public static final int PANEL_HEIGHT = 1000;

    public static final int FPS = 60;
    public static final Vector2D GRAVITY = new Vector2D(0, 500);
    public static final double timestep = 0.05;
    public static final double BIRD_JUMP = 15;
    public static final double PIPE_SPEED = 10;
}
