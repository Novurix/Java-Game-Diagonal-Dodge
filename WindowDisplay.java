import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.ImageIcon;

import java.awt.Toolkit;

public class WindowDisplay extends JFrame {

    public static int width, height;
    
    Screen screen;
    Launcher launcher;

    boolean Visible;


    ImageIcon icon;

    public WindowDisplay(int width, int height, Launcher launcher, boolean Visible) {

        this.Visible = Visible;

        this.launcher = launcher;

        this.width = width;
        this.height = height;

        setTitle("Diagonal Dodge");
        setSize(this.width,this.height);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setBackground(Color.black);

        icon = new ImageIcon("Logo.jpg");
        setIconImage(icon.getImage());

        setGridLayout();
        draw();
    }

    public void setGridLayout() {
        setLayout(new GridLayout(1,1,0,0));

        screen = new Screen(this);
        add(screen);
        setVisible(Visible);
    }

    public void draw() {
        Graphics graphics = getGraphics();

        graphics.fillRect(0, 0, width, height);
        graphics.setColor(Color.black);
    } 
}