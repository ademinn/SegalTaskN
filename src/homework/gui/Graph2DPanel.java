package homework.gui;

import javax.swing.*;
import java.awt.*;

public class Graph2DPanel extends JPanel {
    private double[][] values;
    private int width;
    private int stepCount;

    public Graph2DPanel(double[][] values) {
        this.values = values;
        width = values[0].length;
        stepCount = values.length;
    }

    public double getValue(int oldX, int oldY) {
        return values[getY(oldY)][getX(oldX)];
    }

    public int getX(int oldX) {
        return oldX * width / getWidth();
    }

    public int getY(int oldY) {
        return (getHeight() - oldY - 1) * stepCount / getHeight();
    }

    @Override
    public void paint(Graphics g) {
        for (int x = 0; x < getWidth(); x++) {
            for (int y = 0; y < getHeight(); y++) {
                g.setColor(getColorByValue(getValue(x, y)));
                g.fillRect(x, y, 1, 1);
            }
        }
    }

    private static Color getColorByValue(double value) {
        if (value < 0) {
            return new Color(0, 0, 255);
        } else if (value > 1) {
            return new Color(0, 255, 0);
        } else {
            return new Color((float) value, 0, 0);
        }
    }

}

