import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Color;


public class EnemyTrail extends Rectangle {
    Enemy enemy;

    int enemyX;
    int enemyY;

    public void GetEnemy(Enemy enemy) {
        this.enemy = enemy;
    }

    public EnemyTrail(int xCor, int yCor) {
        setBounds(xCor,yCor,15,15);
    }

    public void GetPosition() {

        this.x = enemyX;
        this.y = enemyY;
        if (enemy.xDirection == 1) {
            enemyX = enemy.x -2;

            if (enemy.yDirection == 1) {
                enemyY = enemy.y - 2;
            }
            else {
                enemyY = enemy.y + 2;
            }
        }

        else if (enemy.xDirection == -1) {
            enemyX = enemy.x +2;

            if (enemy.yDirection == 1) {
                enemyY = enemy.y - 2;
            }
            else {
                enemyY = enemy.y + 2;
            }
        }
    }

    public void draw(Graphics graphics) {
        graphics.fillRect(this.x, this.y, this.width, this.height);
    }
}