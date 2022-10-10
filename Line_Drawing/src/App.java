import javax.swing.*;
import java.awt.*;

class App extends JPanel {
    public static void main(String[] args) {
        App m = new App();
        JFrame f = new JFrame();
        f.add(m);
        f.setTitle("Computer Graphics Lab - Line Drawing");
        f.setSize(500, 500);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }

    public void paintComponent(Graphics g) {
        g.setColor(Color.BLACK);
        ddaLine(g, 0, 0, 300,200);
    }

    public void naiveLine(Graphics g, int x1, int y1, int x2, int y2) {
        int dx = x2 - x1;
        int dy = y2 - y1;
        int intercept = y1 - (dy/dx)*x1;
        for(int x = x1; x <= x2; x++) {
            int y = (dy/dx)*x + intercept;
            plot(g, x, y, 1);
        }
    }

    public void ddaLine(Graphics g, int x1, int y1, int x2, int y2) {
        double dx = x2 - x1;
        double dy = y2 - y1;
        double slope = dy / dx;
        double x = x1;
        double y = y1;

        // Slope >= 0 && Slope < 1
        if(slope >= 0 && slope < 1) {
            for(x = x1; x <= x2; x++) {
                plot(g, (int)x, (int)y, 1);
                y += slope;
            }
        }

        // Slope >= 1
        else if(slope >= 1) {
            for(y = y1; y <= y2; y++) {
                plot(g, (int)x, (int)y, 1);
                x += 1/slope;
            }
        }

        // Slope < 0 && Slope > -1
        else if(slope < 0 && slope > -1) {
            for(x = x1; x >= x2; x--) {
                plot(g, (int)x, (int)y, 1);
                y += slope;
            }
        }

        // Slope <= -1 
        else if(slope <= -1) {
            for(y = y1; y >= y2; y--) {
                plot(g, (int)x, (int)y, 1);
                x += 1/slope;
            }
        }
    }

    public void plot(Graphics g, int x, int y, int size) {
        g.fillRect(x, y, size, size);
    }
}