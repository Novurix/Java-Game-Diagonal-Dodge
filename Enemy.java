import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Color;
import java.util.Random;

public class Enemy extends Rectangle {

    int speed = 1;
    int xDirection, yDirection;


    Screen screen;

    Rectangle[] enemies = new Rectangle[5];
    int borderX, borderY;

    boolean isAlive = true, canCreate = true;

    EnemyTrail enemyTrail = new EnemyTrail(x,y);

    int[] previousX = new int[2];
    int[] previousY = new int[2];

    public Enemy(Screen screen) {

        this.screen = screen;

            Random rand = new Random(), dirx = new Random(),diry = new Random(), randSpeed = new Random();
            x = rand.nextInt(1000);
            y = rand.nextInt(720);

            setBounds(x,y,15,15);

            speed = 2;

            xDirection = dirx.nextInt(2);
            yDirection = diry.nextInt(2);

            if (xDirection == 2) {
                xDirection = -1;
            }
            if (xDirection == 0) {
                xDirection = dirx.nextInt(1);
                if (xDirection == 0) {
                    xDirection = -1;
                }
            }
            if (yDirection == 0) {
                yDirection = -1;
            }
            if (yDirection == 2) {
                yDirection = -1;
            }
    }

    public void tick() {

        if (isAlive == true) {
            this.x += xDirection * speed;
            this.y += yDirection * speed;

            if (this.x < -10) {
                xDirection = 1;
            }

            if (this.x > 995) {
                xDirection = -1;
            }

            if (this.y > 710) {


                yDirection = -1;
            }

            if (this.y <-28) {

                yDirection = 1;
            }
            if (enemyTrail.enemy == null) {
                enemyTrail.GetEnemy(this);
            }
        }
    }

    public void draw(Graphics graphics) {
        graphics.fillRect(this.x, this.y, this.width, this.height);
        enemyTrail.GetPosition();

        if (!isAlive) {
            System.out.println("Not alive");
            if (canCreate == true) {
                canCreate = false;
                System.out.println("Fired");
                CreateNewEnemy();
            }
        }
    }   

    public void setxDirection(int newDirX) {
        xDirection = newDirX;
    }

    public void setyDirection(int newDirY) {
        yDirection = newDirY;
    }

    public Rectangle bounds() {
        return (new Rectangle(x,y,width,height));
    }

    public void CreateNewEnemy() {
        Random randEnemy = new Random();
        int random = randEnemy.nextInt(99);

        if (random+1 <= 20) {
            screen.createNewEnemy(2, this);
        }
        else {
            screen.createNewEnemy(1, this);
        }
    }
}