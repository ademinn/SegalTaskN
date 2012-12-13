package homework.gui;

import javax.swing.*;
import java.awt.*;

public abstract class ValueField<T> extends JPanel {
    private JTextField field;

    public ValueField(String labelText, T startValue) {
        JLabel label = new JLabel(labelText);
        field = new JTextField(String.valueOf(startValue));

        setLayout(new BorderLayout());
        add(label, BorderLayout.NORTH);
        add(field, BorderLayout.CENTER);

    }

    public T getValue() throws IllegalArgumentException {
        return parseString(field.getText());
    }

    protected abstract T parseString(String s);

}
