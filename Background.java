import java.awt.Rectangle;
import java.awt.Graphics;


public class Background extends Rectangle {

    int width, height;

    public Background() {
        setBounds(0,0,width,height);

        this.width = 1000;
        this.height = 1000/12*9;
    }

    public void draw(Graphics graphics) {
        graphics.fillRect(0, 0, width, height);
    }
}