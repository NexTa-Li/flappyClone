
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Main extends JPanel implements Config, Runnable {
    Bird bird;

    Thread thread;

    public Main() {
        this.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));

        addKeyListener(new listener());
        setFocusable(true);
        requestFocus();

        thread = new Thread(this);
        thread.start();
    }

    private void idle() {
        bird.update(1.0 / FPS);
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

        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.add(new Main());
        frame.setVisible(true);
    }

    private class listener implements KeyListener {
        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                bird.up = true;
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {

        }
    }
}
