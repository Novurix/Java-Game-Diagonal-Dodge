public class Launcher {

    public Launcher() {}

    static int width = 1000, height = width/12 * 9;

    static WindowDisplay window;
    public static void main(String[] args) {
        window = new WindowDisplay(width,height,new Launcher(),true);
    }

    public static void LaunchGame() {
    }
}