package homework.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class GraphFrame extends JFrame {
    private GraphColoredPanel panel;
    private StatusBar statusBar;

    public GraphFrame(double[][] values, String title) {
        setTitle(title);

        setLayout(new BorderLayout());
        JPanel colorPanel = new JPanel();
        colorPanel.setLayout(new BorderLayout());
        panel = new GraphColoredPanel(values);
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
        statusBar = new StatusBar();
        colorPanel.add(panel, BorderLayout.CENTER);
        colorPanel.add(statusBar, BorderLayout.SOUTH);

        GraphSlicePanel slicePanel = new GraphSlicePanel(values);
//        add(slicePanel, BorderLayout.CENTER);

        JTabbedPane mainPane = new JTabbedPane();
        mainPane.addTab("Colored", colorPanel);
        mainPane.addTab("Slice", slicePanel);

        add(mainPane);

        setSize(values[0].length * 5 / 2, values.length * 5 / 2);
        setVisible(true);
    }
}
