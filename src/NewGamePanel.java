import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.Random;

public class NewGamePanel extends JPanel implements ActionListener {

    private static final int SCREEN_WIDTH = 600;
    private static final int SCREEN_HEIGHT = 600;
    private static final int UNIT_SIZE = 25;
    private static final int GAME_UNITS = SCREEN_WIDTH * SCREEN_HEIGHT / UNIT_SIZE;
    private static final int DELAY = 75;

    public final int[] x = new int[GAME_UNITS];
    public final int[] y = new int[GAME_UNITS];
    public int bodyParts = 6;
    public int applesEaten = 0;
    public int appleX;
    public int appleY;
    public char direction = 'R';
    public boolean running = false;
    public boolean finished = false;
    public Timer timer;
    public Random random;
    JButton retryButton = new JButton("Retry");
    JButton quitButton = new JButton("Quit");

    public NewGamePanel() {
        random = new Random();
        setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        setBackground(Color.BLACK);
        setFocusable(true);
        addKeyListener(new MyKeyAdapter());
        startGame();
    }

    public void startGame() {
        newApple();
        finished = false;
        running = true;
        timer = new Timer(DELAY, this);
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (running) {
            draw(g);
        } else {
            gameOver(g);
        }
    }

    private void draw(Graphics g) {
        drawApple(g);
        drawSnake(g, 0);
        drawScore(g);
    }

    private void drawApple(Graphics g) {
        g.setColor(Color.RED);
        g.fillOval(appleX, appleY, UNIT_SIZE * 7 / 10, UNIT_SIZE * 7 / 10);
    }

    private void drawSnake(Graphics g, int i) {
        for (i = 0; i < bodyParts; i++) {
            if (i == 0) {
                g.setColor(Color.GREEN);
            } else {
                g.setColor(new Color(9, 153, 40));
            }
            g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
        }
    }

    private void drawScore(Graphics g) {
        g.setColor(Color.ORANGE);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        FontMetrics metrics = getFontMetrics(g.getFont());
        g.drawString("Score: " + applesEaten, (SCREEN_WIDTH - metrics.stringWidth("Score: " + applesEaten)) / 2,
                g.getFont().getSize());
    }

    private void gameOver(Graphics g) {
        g.setColor(Color.RED);
        g.setFont(new Font("Ink Free", Font.BOLD, 75));
        FontMetrics metrics = getFontMetrics(g.getFont());
        g.drawString("Game Over", (SCREEN_WIDTH - metrics.stringWidth("Game Over")) / 2, SCREEN_HEIGHT / 2);

        // displaying score
        drawScore(g);

        // create and add retry button
        retryButton.setFont(new Font("Arial", Font.BOLD, 18)); // sets font to Arial, bold and size 20
        retryButton.setBackground(Color.ORANGE);
        retryButton.setForeground(Color.WHITE);
        retryButton.setBounds((SCREEN_WIDTH - 100) / 2, SCREEN_HEIGHT / 2 + 100, 100, 40);
        add(retryButton);

        // create and add quit button
        quitButton.setFont(new Font("Arial", Font.BOLD, 18)); // sets font to Arial, bold and size 20
        quitButton.setBackground(Color.RED);
        quitButton.setForeground(Color.WHITE);
        quitButton.setBounds((SCREEN_WIDTH - 100) / 2, SCREEN_HEIGHT / 2 + 150, 100, 40);
        add(quitButton);
    }

    private void newApple() {
        appleX = random.nextInt(SCREEN_WIDTH / UNIT_SIZE) * UNIT_SIZE;
        appleY = random.nextInt(SCREEN_HEIGHT / UNIT_SIZE) * UNIT_SIZE;
    }

    private void move(int i) {
        for (i = bodyParts; i > 0; i--) {
            x[i] = x[i - 1];
            y[i] = y[i - 1];
        }
        switch (direction) {
            case 'U':
                y[0] -= UNIT_SIZE;
                break;
            case 'D':
                y[0] += UNIT_SIZE;
                break;
            case 'L':
                x[0] -= UNIT_SIZE;
                break;
            case 'R':
                x[0] += UNIT_SIZE;
                break;
        }
    }

    public void checkApple() {
        if ((x[0] == appleX) && (y[0] == appleY)) {
            bodyParts++;
            applesEaten++;
            newApple();
        }
    }

    public void checkCollisions() {
        // check if head collides with body
        for (int i = bodyParts; i > 0; i--) {
            if ((x[0] == x[i]) && (y[0] == y[i])) {
                running = false;
                finished = true;
            }
        }

        // check if head touches left border
        if (x[0] < 0 || x[0] > SCREEN_WIDTH || y[0] < 0 || y[0] > SCREEN_HEIGHT) {
            // left border || right border || top border || bottom border
            running = false;
            finished = true;
        }

        if (!running) {
            timer.stop();
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (running) {
            move(0);
            checkApple();
            checkCollisions();
        }
        repaint();
    }

    class MyKeyAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    if (direction != 'R') {
                        direction = 'L';
                    }
                    break;
                case KeyEvent.VK_RIGHT:
                    if (direction != 'L') {
                        direction = 'R';
                    }
                    break;
                case KeyEvent.VK_UP:
                    if (direction != 'D') {
                        direction = 'U';
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    if (direction != 'U') {
                        direction = 'D';
                    }
                    break;
            }
        }
    }

}
