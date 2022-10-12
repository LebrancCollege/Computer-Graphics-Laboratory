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
        
        triangle(g, 100, 100, 300, 300, 300, 100);
        triangle(g, 500, 0, 100, 200, 300, 200);
    }

    // Naive Line Drawing Algorithm. 
    public void naiveLine(Graphics g, int x1, int y1, int x2, int y2) {
        int dx = x2 - x1;
        int dy = y2 - y1;
        int intercept = y1 - (dy/dx)*x1;
        for(int x = x1; x <= x2; x++) {
            int y = (dy/dx)*x + intercept;
            plot(g, x, y, 3);
        }
    }

    // Digital Differential Analyzer Line Drawing Algorithm. 
    public void ddaLine(Graphics g, int x1, int y1, int x2, int y2) {
        double dx = x2 - x1;
        double dy = y2 - y1;
        double slope = dy / dx;
        double x = x1;
        double y = y1;

        // Slope >= 0 && Slope < 1
        if(slope >= 0 && slope < 1) {
            for(x = x1; x <= x2; x++) {
                plot(g, (int)x, (int)y, 3);
                y += slope;
            }
        }

        // Slope >= 1
        else if(slope >= 1) {
            for(y = y1; y <= y2; y++) {
                plot(g, (int)x, (int)y, 3);
                x += 1/slope;
            }
        }

        // Slope < 0 && Slope > -1
        else if(slope < 0 && slope > -1) {
            for(x = x1; x >= x2; x--) {
                plot(g, (int)x, (int)y, 3);
                y += slope;
            }
        }

        // Slope <= -1 
        else if(slope <= -1) {
            for(y = y1; y >= y2; y--) {
                plot(g, (int)x, (int)y, 3);
                x += 1/slope;
            }
        }
    }

    // Bresenham Line Drawing Algorithm. 
    public void bresenhamLine(Graphics g, int x1, int y1, int x2, int y2) {
        int dx = x2 - x1;
        int dy = y2 - y1;
        int d = 2*dy - dx;
        int y = y1;

        for(int x = x1; x <= x2; x++) {
            plot(g, x, y, 3);
            
            if(d >= 0) {
                y++;
                d = d - (2 * dx);
            }

            d = d + (2 * dy);
        }
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

    public void triangle(Graphics g, int x1, int y1, int x2, int y2, int x3, int y3) {
        Polygon poly = new Polygon();
        poly.addPoint(x1, y1);
        poly.addPoint(x2, y2);
        poly.addPoint(x3, y3);
        g.drawPolygon(poly);
        poly.reset(); 
    }

    public void plot(Graphics g, int x, int y, int size) {
        g.fillRect(x, y, size, size);
    }
}