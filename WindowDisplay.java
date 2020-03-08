import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Graphics;
import javax.swing.JFrame;

public class WindowDisplay extends JFrame {

    public static int width, height;
    Screen screen;

    public WindowDisplay(int width, int height) {

        this.width = width;
        this.height = height;

        setTitle("Diagonal Dodge");
        setSize(this.width,this.height);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setBackground(Color.black);


        setGridLayout();
        draw();
    }

    public void setGridLayout() {
        setLayout(new GridLayout(1,1,0,0));

        screen = new Screen(this);
        add(screen);
        setVisible(true);
    }

    public void draw() {
        Graphics graphics = getGraphics();

        graphics.fillRect(0, 0, width, height);
        graphics.setColor(Color.black);
    } 
}