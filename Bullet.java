import java.awt.Rectangle;
import java.awt.Graphics;

public class Bullet extends Rectangle {
    public Bullet(int xCor, int yCor, int width, int height, Player player) {

        int modifiedXCor = xCor + player.width/2 - 2;
        setBounds(modifiedXCor,yCor,width,height);

        player.window.screen.getBullet(this);
    }

    public void tick() {
        y += -4;
    }

    public void draw(Graphics graphics) {
        graphics.fillRect(this.x, this.y, this.width, this.height);
    }
}