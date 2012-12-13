package homework.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class GraphFrame extends JFrame {
    private Graph2DPanel panel;
    private StatusBar statusBar;

    public GraphFrame(double[][] values, String title) {
        setTitle(title);

        setLayout(new BorderLayout());
        panel = new Graph2DPanel(values);
        panel.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                Point p = e.getPoint();
                int x = panel.getX(p.x);
                int y = panel.getY(p.y);
                double value = panel.getValue(p.x, p.y);
                statusBar.setText("x = " + x + ", t = " + y + ", value = " + value);
            }
        });
        add(panel, BorderLayout.CENTER);

        statusBar = new StatusBar();
        add(statusBar, BorderLayout.SOUTH);

        setSize(values[0].length * 5 / 2, values.length * 5 / 2);
        setVisible(true);
    }
}
