import java.awt.Rectangle;
import java.awt.Graphics;
import java.util.Random;


public class Immortal extends Rectangle {


    boolean canPickUp = true;
    
    public Immortal(int width, int height) {

        Random rand = new Random();

        x = rand.nextInt(1000);
        y = rand.nextInt(720);

        setBounds(x,y,width,height);
    }

    public void draw(Graphics graphics) {
        graphics.fillRect(this.x, this.y, this.width, this.height);
    }

    public void tick() {
        if (!canPickUp) {
            x = 1000000;
            y = 1000000;
        }
    }

    public Rectangle bounds() {
        return (new Rectangle(x, y, width, height));
    }
}