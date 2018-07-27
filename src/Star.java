import java.awt.*;
import java.awt.image.BufferedImage;

public class Star {
    public BufferedImage image;

    public int x;
    public int y;

    public int width;
    public int height;

    public int velocityX;
    public int velocityY;


    public void run() {
        this.x -= this.velocityX;
        this.y -= this.velocityY;
        if(this.x<0 || this.x>900) {this.velocityX =- this.velocityX;}
        if(this.y<0 || this.y>500) {this.velocityY =- this.velocityY;}

    }

    public void render(Graphics graphics) {
        graphics.drawImage(this.image, this.x, this.y, this.width, this.height, null);
    }
}
