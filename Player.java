import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.event.MouseInputListener;

public class Player extends Rectangle {

    int speed = 2, health = 100;
    int xDirection, yDirection;

    Gun gun;

    WindowDisplay window;

    boolean canControl = true, immortal = true, launchGame = true;
    PlayerTrail trail;

    int borderX, borderY;
    int speedIndex = 0;

    public Player(int Cx, int Cy, int width, int height, int xDir, int yDir, int borderX, int borderY) {

        setBounds(Cx, Cy, width, height);
        xDirection = xDir;
        yDirection = yDir;

        this.borderX = borderX;
        this.borderY = borderY;

        trail = new PlayerTrail(Cx, Cy);
        gun = new Gun(this);
        System.out.println("Player made");
    }

    public void Click() {
        System.out.println("Shoot");
        if (canControl) {
            gun.Shoot();
        }
    }

    public void tick() {

        speedIndex++;

        if (speedIndex >= 500) {
            speed = 2;
        }

        this.x += xDirection * speed;
        this.y += yDirection * speed;

        if (this.x < -25) {
            this.x = 1020;
        }

        if (this.x > 1025) {
            this.x = -20;
        }

        if (this.y > 725) {
            this.y = -30;
        }

        if (this.y < -35) {
            this.y = 720;
        }

        if (!canControl) {
            xDirection = 0;
            yDirection = 0;
        }

        if (health <= 0) {
            canControl = false;
        }

        if (trail.player == null) {
            trail.GetPlayer(this);
        }
    }

    public void draw(Graphics graphics) {
        graphics.fillRect(this.x, this.y, this.width, this.height);
        graphics.setColor(new Color(100, 100, 100));

        trail.GetPosition();
    }

    public void setxDirection(int newDirX) {
        if (canControl) {
            xDirection = newDirX;
        } else {
            xDirection = 0;
        }
    }

    public void setyDirection(int newDirY) {
        if (canControl) {
            yDirection = newDirY;
        } else {
            yDirection = 0;
        }
    }

    public Rectangle bounds() {
        return (new Rectangle(x, y, width, height));
    }

    public void Damage() {
        if (!immortal) {
            health -= 10; 
        }
    }

    public void Kill() {
        health = 0;
    }

    public void GetWindow(WindowDisplay window) {
        this.window = window;
    }
}

class PlayerTrail extends Rectangle {

    int playerX, playerY;
    Player player;

    public PlayerTrail(int xCor, int yCor) {
        setBounds(xCor, yCor, 20, 20);

        playerX = xCor;
        playerY = yCor;
    }

    public void GetPlayer(Player player) {
        this.player = player;
    }

    public void GetPosition() {

        this.x = playerX;
        this.y = playerY;

        if (player.xDirection == 1) {
            playerX = player.x - 2;

            if (player.yDirection == 1) {
                playerY = player.y - 2;
            } else if (player.yDirection == -1) {
                playerY = player.y + 2;
            } else {
                playerY = player.y;
            }
        }

        else if (player.xDirection == -1) {
            playerX = player.x + 2;

            if (player.yDirection == 1) {
                playerY = player.y - 2;
            } else if (player.yDirection == -1) {
                playerY = player.y + 2;
            } else {
                playerY = player.y;
            }
        }

        else if (player.yDirection == -1) {
            if (player.xDirection == 0) {
                playerY = player.y + 2;
                playerX = player.x;
            }
        }

        else if (player.yDirection == 1) {
            if (player.xDirection == 0) {
                playerY = player.y - 2;
                playerX = player.x;
            }
        }
    }

    public void draw(Graphics graphics) {
        graphics.fillRect(this.x, this.y, this.width, this.height);
    }
}

class Gun extends Rectangle {

    Player player;
    private static final long serialVersionUID = 1L;

    public Gun(Player player) {
        this.player = player;
    }

    public void Shoot() {
        new Bullet(player.x,player.y,5,15, player);
    }
}