
import javax.swing.*;

import util.Vector2D;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;
import java.util.ArrayList;

public class Main extends JPanel implements Config, Runnable {
    Bird bird;

    List<Pipe> pipes = new ArrayList<Pipe>();
    double pipeSpace = 500;
    double gap = 275;

    Thread thread;
    boolean running = false;

    public Main() {

        bird = new Bird(PANEL_WIDTH / 2, PANEL_HEIGHT / 2, 25);
        this.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        createPipes();
        addKeyListener(new listener());
        setFocusable(true);
        requestFocus();

        thread = new Thread(this);
        // thread.start();
    }

    void createPipes() {
        for (int i = 0; i < 2; i++) {
            double x = PANEL_WIDTH;
            double y = 0;
            double width = 100;
            double height = 200;
            createPipe(x + i * (width + pipeSpace), y, width, height, gap);
        }
    }

    void createPipe(double x, double y, double width, double height, double gap) {

        Pipe pipe = new Pipe(x, y, width, height, gap);
        pipes.add(pipe);
    }

    private void idle() {
        bird.accelerate(GRAVITY);
        bird.update(timestep);
        if (bird.checkGroundCollision() || bird.checkPipeCollision(pipes)) {//
            System.out.println("Game Over");
            System.exit(0);
        }

        // translate pipes
        for (int i = 0; i < pipes.size(); i++) {

            Pipe pipe = pipes.get(i);
            pipe.move(-PIPE_SPEED, 0);

            if (pipe.top.x + pipe.top.width < 0) {
                pipes.remove(pipe);

                // createPipe(PANEL_WIDTH, 0, 100, 200, 225);
            }
        }

        if (pipes.get(pipes.size() - 1).top.x + pipes.get(pipes.size() - 1).top.width < PANEL_WIDTH - pipeSpace) {

            double height = Math.random() * (PANEL_HEIGHT - 3 * gap) + gap;
            createPipe(PANEL_WIDTH, 0, 100, height, gap);

        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.BLACK);
        g.fillRect(0, 0, PANEL_WIDTH, PANEL_HEIGHT);

        g.setColor(Color.WHITE);
        g.fillOval((int) (bird.position.x - bird.radius), (int) (bird.position.y - bird.radius), (int) bird.radius * 2,
                (int) bird.radius * 2);

        for (Pipe pipe : pipes) {
            g.setColor(Color.GREEN);
            g.fillRect((int) pipe.top.x, (int) pipe.top.y, (int) pipe.top.width, (int) pipe.top.height);
            g.fillRect((int) pipe.bottom.x, (int) pipe.bottom.y, (int) pipe.bottom.width, (int) pipe.bottom.height);
        }
    }

    @Override
    public void run() {
        double drawInterval = 1000000000.0 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        long timer = 0;
        int drawCount = 0;

        while (thread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            // Update the game logic when enough time has passed for a frame
            if (delta >= 1.0) {
                idle();
                revalidate();
                repaint();

                delta--;
                drawCount++;
            }

            // Calculate the time to sleep in order to achieve the desired frame rate
            long sleepTime = (long) (drawInterval - (System.nanoTime() - currentTime));

            if (sleepTime > 0) {
                try {
                    Thread.sleep(sleepTime / 1000000); // Convert nanoseconds to milliseconds
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            if (timer >= 1000000000) {
                System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            }

        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Flappy Bird");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // frame.setLocationRelativeTo(null );
        frame.setResizable(false);
        frame.add(new Main());
        frame.pack();
        frame.setVisible(true);
    }

    private class listener extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                if (running == false) {
                    running = true;
                    thread.start();
                }
                bird.up = true;

            }
        }
    }
}
