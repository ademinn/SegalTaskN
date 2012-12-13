package homework.gui;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class GraphSlicePanel extends JPanel {
    private JSlider slider;
    private Graph2DPanel panel;

    public GraphSlicePanel(double[][] values) {
        setLayout(new BorderLayout());
        panel = new Graph2DPanel(values);
        add(panel, BorderLayout.CENTER);
        slider = new JSlider(0, values.length - 1, JSlider.HORIZONTAL);
        slider.setMajorTickSpacing(10);
        slider.setPaintLabels(true);
        slider.setMinorTickSpacing(1);
        slider.setPaintTicks(true);
        add(slider, BorderLayout.SOUTH);
        slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                panel.setCurrent(slider.getValue());
//                e.
                //To change body of implemented methods use File | Settings | File Templates.
            }
        });
    }
}
