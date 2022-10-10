import javax.swing.*;
import java.awt.*;

class App extends JPanel {
    public static void main(String[] args) {
        App m = new App();
        JFrame f = new JFrame();
        f.add(m);
        f.setTitle("Computer Graphics Lab - Spline Drawing");
        f.setSize(500, 500);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }

    public void paintComponent(Graphics g) {
        g.setColor(Color.BLACK);
        bezierCurve(g, 10, 10, 50, 50, 80, 50, 100, 10);
    }

    // Bezier Curve.
    public void bezierCurve(Graphics g, int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4) {
        // (x^3 + 3x^2y + 3xy^2 + y^3) 
        // x = 1 - t
        // y = t
        for(int i = 0; i <= 1000; i++) {
            double t = i / 1000.0;

            int x = (int) ((Math.pow(1 - t, 3) * x1) + (3 * Math.pow(1 - t, 2) * t * x2) + (3 * (1 - t) * Math.pow(t, 2) * x3) + (Math.pow(t, 3) * x4));
            int y = (int) ((Math.pow(1 - t, 3) * y1) + (3 * Math.pow(1 - t, 2) * t * y2) + (3 * (1 - t) * Math.pow(t, 2) * y3) + (Math.pow(t, 3) * y4));

            plot(g, x, y, 3);
        }
    }

    public void bsplineCurve() {

    }

    public void plot(Graphics g, int x, int y, int size) {
        g.fillRect(x, y, size, size);
    }
}