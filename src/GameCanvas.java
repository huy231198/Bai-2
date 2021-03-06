import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameCanvas extends JPanel {
    private List<Star> stars;
    private BufferedImage playerImage;
    private BufferedImage backBuffered;

    public Player player = new Player();

    private Graphics graphics;
    public Random rd = new Random();

    private int timeIntervalStar = 0;

    public GameCanvas() {
        this.player.x[0] = 200;
        this.player.y[0] = 100;

        this.setupBackBufferd();

        this.setupCharacter();

        this.setVisible(true);
    }

    private void setupBackBufferd() {
        this.backBuffered = new BufferedImage(1024, 600, BufferedImage.TYPE_INT_ARGB);
        this.graphics = this.backBuffered.getGraphics();
    }

    private void setupCharacter() {
        this.setupStar();

    }

    public void renderBackground() {
        this.graphics.setColor(Color.BLACK);
        this.graphics.fillRect(0, 0, 1024, 600);
    }

    private void setupStar() {
        this.stars = new ArrayList<>();


    }

    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(this.backBuffered, 0, 0, null);

    }

    public void renderAll() {
        this.renderBackground();
        this.stars.forEach(star -> star.render(graphics));
        this.player.render(graphics);
        this.repaint();

    }

    public void runAll() {
        this.createStar();
        this.createPlayer();
        this.stars.forEach(star -> star.run());

    }

    private void createPlayer() {
        this.player.x[1] = this.player.x[0] + 20;
        this.player.y[1] = this.player.y[0]+10;

        this.player.x[2] = this.player.x[0] + 20 ;
        this.player.y[2] = this.player.y[0]-10;

        this.player.velocity = 20;

    }

    private void createStar() {
        if (this.timeIntervalStar == 30) {
            Star star = new Star();
            star.x = 900;
            star.y = this.rd.nextInt(600);
            star.image = this.loadImage("resources/images/star.png");
            star.width = 10;
            star.height = 10;
            star.velocityX = this.rd.nextInt(3) + 1;
            star.velocityY = this.rd.nextInt(3) + 1;
            this.stars.add(star);
            this.timeIntervalStar = 0;

        } else {
            this.timeIntervalStar += 1;
        }
    }

    private BufferedImage loadImage(String path) {
        try {
            return ImageIO.read(new File(path));
        } catch (IOException e) {
            return null;
        }
    }
}
