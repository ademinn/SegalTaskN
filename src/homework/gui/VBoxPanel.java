package homework.gui;

import javax.swing.*;
import java.awt.*;

public class VBoxPanel extends JPanel {
    private static final int STRUT = 5;

    public VBoxPanel() {
//        setLayout(new FlowLayout());
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }

//    @Override
//    public Component add(Component c) {
//        if (this.getComponentCount() > 0) {
//            super.add(Box.createVerticalStrut(STRUT));
//        }
//        //        setMinimumSize(new Dimension(Integer.MAX_VALUE, getPreferredSize().height));
////        setMaximumSize(new Dimension(500, getPreferredSize().height));
////        setSize(new Dimension(Integer.MAX_VALUE, getPreferredSize().height));
//        return super.add(c);
//    }

    public void addComponent(JComponent c) {
        if (this.getComponentCount() > 0) {
            super.add(Box.createVerticalStrut(STRUT));
        }
        c.setAlignmentX(JComponent.LEFT_ALIGNMENT);
        super.add(c);
        setMaximumSize(new Dimension(Integer.MAX_VALUE, getPreferredSize().height));
    }
}
