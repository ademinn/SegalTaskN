package homework.gui;

import javax.swing.*;
import java.awt.*;

public class Graph2DPanel extends JPanel {
    private static final Color BACKGROUND_COLOR = Color.WHITE;
    private static final Color LINE_COLOR = Color.BLACK;
    private static final Color LIMIT_COLOR = Color.LIGHT_GRAY;
    private double[][] values;
    private int width;
    private int current;

    public Graph2DPanel(double[][] values) {
        this.values = values;
        width = values[0].length;
        current = 0;
    }

    public void setCurrent(int index) {
        current = index;
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(BACKGROUND_COLOR);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(LIMIT_COLOR);
        g.drawLine(0, getY(0.0), getWidth(), getY(0.0));
        g.drawLine(0, getY(1.0), getWidth(), getY(1.0));
        g.setColor(LINE_COLOR);
        double[] row = values[current];
        int prev = getY(row[0]);
        int cur;
        g.drawLine(getX(0), prev,  getX(1), prev);
        for (int i = 1; i < width; ++i) {
            cur = getY(row[i]);
            g.drawLine(getX(i), prev, getX(i), cur);
            g.drawLine(getX(i), cur, getX(i + 1), cur);
            prev = cur;
        }
    }

    private int getX(int index) {
        return getWidth() * index / width;
    }

    private int getY(double y) {
        double scale = getHeight() / 3.0;
        double zero = 2 * scale;
        return (int) Math.round(zero - scale * y);
    }
}
