package mgame;


import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class SnakeGameBackground extends JPanel {

    // Random object for generating random flower positions
    private Random random = new Random();
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawBackground(g);
    }

    private void drawBackground(Graphics g) {
        // Draw grass
        g.setColor(new Color(34, 139, 34)); // Grass green
        g.fillRect(0, 0, getWidth(), getHeight());

        // Draw flowers randomly
        for (int i = 0; i < 10; i++) { // Random number of flowers
            int x = random.nextInt(getWidth() - 50); // X position
            int y = random.nextInt(getHeight() - 50); // Y position
            drawFlower(g, x, y);
        }
    }

    private void drawFlower(Graphics g, int x, int y) {
        // Flower petals
        g.setColor(new Color(255, 105, 180)); // Light pink for flower petals
        g.fillOval(x, y, 20, 20); // Draw flower center
        g.fillOval(x - 10, y + 5, 20, 20);
        g.fillOval(x + 5, y - 10, 20, 20);
        g.fillOval(x + 5, y + 20, 20, 20);
        g.fillOval(x - 15, y - 10, 20, 20);

        // Flower stem
        g.setColor(new Color(34, 139, 34)); // Grass green for the stem
        g.fillRect(x + 5, y + 20, 5, 30); // Draw flower stem
    }
}