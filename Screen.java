import java.awt.Graphics;
import java.awt.event.*;
import java.util.Random;
import java.awt.Color;

import javax.swing.*;

import java.awt.Rectangle;

public class Screen extends JPanel implements ActionListener, KeyListener, MouseListener {

    /* ------------------------------------------------------------------------------------------- */

    /* VARIABLES */

    /* ------------------------------------------------------------------------------------------- */


    private static final long serialVersionUID = 1L;
    boolean isCollided, canCollide = true, createEnemy = false;
    Timer timer = new Timer(10, this);

    Background background;

    int playerWidth = 20;
    int playerHeight = 20;
    WindowDisplay window;

    int borderX = WindowDisplay.width/2;
    int borderY = WindowDisplay.height/2;

    int xPos = borderX - playerWidth/2;
    int yPos = borderY - playerHeight/2;
    int startImmortalIndex, immortalPowerUpIndexTimer;

    Player player = new Player(xPos,yPos,playerWidth,playerHeight,0,0,borderX,borderY);
    Bullet bullet;

    Bullet[] bullets = new Bullet[100000];
    Immortal[] immortalPowerUps = new Immortal[450000];
    Speed[] speedPowerUps = new Speed[450000];

    Immortal immortal;
    Speed speed;

    Enemy enemy;
    Enemy[] enemies = new Enemy[100000];
    int enemyStartQuantity = 5;

    public Screen(WindowDisplay window) {
        this.window = window;

        background = new Background();

        for (int i = 0; i < enemyStartQuantity; i++) {
            enemies[i] = new Enemy(this);
        }

        addKeyListener(this);
        addMouseListener(this);
        setFocusable(true);
        setBackground(Color.black);

        timer.start();
    }

    /* ------------------------------------------------------------------------------------------- */

    /* UPDATING ALL ELEMENTS */

    /* ------------------------------------------------------------------------------------------- */


    @Override
    public void actionPerformed(ActionEvent e) {

        for (int i = 0; i < enemies.length; i++) {
            if (enemies[i] != null) {
                enemies[i].tick();
            }
        }
        player.tick();

        for (int i = 0; i < bullets.length; i++) {
            if (bullets[i] != null) {
                bullets[i].tick();
            }
        }

        for (int i = 0; i < immortalPowerUps.length; i++) {
            if (immortalPowerUps[i] != null) {
                immortalPowerUps[i].tick();
            }
        }

        for (int i = 0; i < immortalPowerUps.length; i++) {
            if (speedPowerUps[i] != null) {
                speedPowerUps[i].tick();
            }
        }

        player.GetWindow(window);
        repaint();

        collision();
        bulletCollision();
        powerUpCollision();

        /* --------------------------------------------- */

        /* SUB AREA --- SPAWNING */

        /* --------------------------------------------- */

        startImmortalIndex++;

        if (startImmortalIndex >= 500) {
            player.immortal = false;
        }

        immortalPowerUpIndexTimer++;

        if (immortalPowerUpIndexTimer >= 4000) {

            Random randomAsset = new Random();
            int random = randomAsset.nextInt(8);

            if (random > 2) {
                speed = new Speed(15, 15);

                for (int i = 0; i < speedPowerUps.length; i++) {
                    if (speedPowerUps[i] == null) {
                        speedPowerUps[i] = speed;
                        break;
                    }
                }
            }
            else {
                immortal = new Immortal(15, 15);

                for (int i = 0; i < immortalPowerUps.length; i++) {
                    if (immortalPowerUps[i] == null) {
                        immortalPowerUps[i] = immortal;
                        break;
                    }
                }
            }

            immortalPowerUpIndexTimer = 0;
        }
    }

    /* ------------------------------------------------------------------------------------------- */

    /* RENDERING */

    /* ------------------------------------------------------------------------------------------- */

    public void paint(Graphics graphics) {

        graphics.clearRect(0, 0, getWidth(), getHeight());

        if (player.canControl) {
            Graphics backgroundGraphics = graphics;
            backgroundGraphics.setColor(Color.black);
            background.draw(graphics);
        }
        else {
            Graphics backgroundGraphics = graphics;
            backgroundGraphics.setColor(new Color(20, 4, 0));
            background.draw(graphics);
        }   


        for (int i = 0; i < enemies.length; i++) {
            if (enemies[i] != null) {
                if (enemies[i].isAlive == false) {
                    Graphics enemyTrailGraphics = graphics;
                    enemyTrailGraphics.setColor(new Color(12, 12, 12));
                    enemies[i].enemyTrail.draw(enemyTrailGraphics);


                    Graphics enemyGraphics = graphics;
                    enemyGraphics.setColor(new Color(20, 20, 20));
                    enemies[i].draw(enemyGraphics);
                }
            }
        }


        if (!player.canControl) {
            Graphics playerTrailGraphics = graphics;

            if (player.trail != null) {
                playerTrailGraphics.setColor(new Color(20, 20, 20));
                player.trail.draw(playerTrailGraphics);
            }

            Graphics playerGraphics = graphics;
            playerGraphics.setColor(new Color(20, 25, 28));
            player.draw(playerGraphics);
        }

        for (int i = 0; i < enemies.length; i++) {
            if (enemies[i] != null) {
                if (enemies[i].isAlive == true) {
                    Graphics enemyTrailGraphics = graphics;
                    enemyTrailGraphics.setColor(new Color(181, 64, 38));
                    enemies[i].enemyTrail.draw(enemyTrailGraphics);


                    Graphics enemyGraphics = graphics;
                    enemyGraphics.setColor(new Color(230, 85, 53));
                    enemies[i].draw(enemyGraphics);
                }
            }
        }

        Graphics immortalGraphics = graphics;

        immortalGraphics.setColor(new Color(227, 154, 27));
        for (int i = 0; i < immortalPowerUps.length; i++) {
            if (immortalPowerUps[i] != null) {
                immortalPowerUps[i].draw(immortalGraphics);
            }
        }

        Graphics speedGraphics = graphics;

        speedGraphics.setColor(new Color(130, 255, 20));
        for (int i = 0; i < speedPowerUps.length; i++) {
            if (speedPowerUps[i] != null) {
                speedPowerUps[i].draw(speedGraphics);
            }
        }
            
        if (player.canControl == true) {
            Graphics playerTrailGraphics = graphics;
            if (player.immortal) {

                if (player.trail != null) {
                    playerTrailGraphics.setColor(new Color(143, 104, 40));
                    player.trail.draw(playerTrailGraphics);
                }
    
                Graphics playerGraphics = graphics;
                playerGraphics.setColor(new Color(227, 154, 27));
                player.draw(playerGraphics);

                Graphics bulletGraphics = graphics;

                bulletGraphics.setColor(new Color(227, 154, 27));
                for (int i = 0; i < bullets.length; i++) {
                    if (bullets[i] != null) {
                        bullets[i].draw(bulletGraphics);
                    }
                }
            }
            else {
                if (player.trail != null) {
                    playerTrailGraphics.setColor(new Color(79, 142, 171));
                    player.trail.draw(playerTrailGraphics);
                }
    
                Graphics playerGraphics = graphics;
                playerGraphics.setColor(new Color(104, 184, 222));
                player.draw(playerGraphics);

                Graphics bulletGraphics = graphics;

                bulletGraphics.setColor(new Color(104, 184, 222));
                for (int i = 0; i < bullets.length; i++) {
                    if (bullets[i] != null) {
                        bullets[i].draw(bulletGraphics);
                    }
                }
            }
        }
    }

    public void getBullet(Bullet bullet) {
        for (int i = 0; i < bullets.length; i++) {
            if (bullets[i] == null) {
                bullets[i] = bullet;
                break;
            }
        }
    }

    /* ------------------------------------------------------------------------------------------- */

    /* KEYBOARD */

    /* ------------------------------------------------------------------------------------------- */

    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {

        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                player.setyDirection(-1);
                break;

            case KeyEvent.VK_A:
                player.setxDirection(-1);
                break;

            case KeyEvent.VK_S:
                player.setyDirection(1);
                break;

            case KeyEvent.VK_D:
                player.setxDirection(1);
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                player.setyDirection(0);
                break;

            case KeyEvent.VK_A:
                player.setxDirection(0);
                break;

            case KeyEvent.VK_S:
            player.setyDirection(0);
                break;

            case KeyEvent.VK_D:
            player.setxDirection(0);
                break;
        }
    }

    /* ------------------------------------------------------------------------------------------- */

    /* COLLISIONS */

    /* ------------------------------------------------------------------------------------------- */

    public void Collide() {
        if (isCollided == true) {
            isCollided = false;
            player.Damage();
        }
    }

    public void collision() {
        Rectangle obj1 = player.bounds();

        for (int i = 0; i < enemies.length; i++) {
            if (enemies[i] != null && enemies[i].isAlive == true) {
                if (enemies[i].bounds().intersects(obj1)) {
                    Collide();
                }

                else {
                    isCollided = true;
                }
            }
        }
    }

    public void bulletCollision() {
        for (int i = 0; i < enemies.length; i++) {
            if (enemies[i] != null) {
                for (int e = 0; e < bullets.length; e++) {
                    if (bullets[e] != null) {
                        if (bullets[e].intersects(enemies[i])) {
                            createEnemy = true;

                            enemies[i].isAlive = false;
                        }
                    }
                }
            }
        }
    }

    public void powerUpCollision() {
        Rectangle playerBounds = player.bounds();

        for (int i = 0; i < immortalPowerUps.length; i++) {
            if (immortalPowerUps[i] != null) {
                if (playerBounds.intersects(immortalPowerUps[i].bounds())) {
                    if (immortalPowerUps[i].canPickUp == true) {
                        player.immortal = true;
                        startImmortalIndex = 0;
                        immortalPowerUps[i].canPickUp = false;
                    }
                }
            }
        }

        for (int i = 0; i < speedPowerUps.length; i++) {
            if (speedPowerUps[i] != null) {
                if (playerBounds.intersects(speedPowerUps[i].bounds())) {
                    if (speedPowerUps[i].canPickUp == true) {
                        player.speedIndex = 0;
                        player.speed = 4;
                        speedPowerUps[i].canPickUp = false;
                    }
                }
            }
        }
    }

    /* ------------------------------------------------------------------------------------------- */

    /* MOUSE LISTENER */

    /* ------------------------------------------------------------------------------------------- */

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        player.Click();
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    /* ------------------------------------------------------------------------------------------- */

    /* NEW ENEMIES */

    /* ------------------------------------------------------------------------------------------- */

    public void createNewEnemy(int newEnemyQuantity, Enemy deadEnemy) {
        for (int i = 0; i < newEnemyQuantity; i++) {
            for (int j = 0; j < enemies.length; j++) {
                if (enemies[j] == null) {
                    enemies[j] = new Enemy(this);
                    break;
                }
            }
        }

        for (int i = 0; i < enemies.length; i++) {
            if (enemies[i] == deadEnemy) enemies[i] = null;
        }
    }
}