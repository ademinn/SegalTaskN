package homework.gui;

import homework.math.Methods;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Main extends JFrame {
    private static final int WIDTH = 100;
    private static final int SINGLE_PEAK_WIDTH = 20;
    private static final int MULTI_PEAK_COUNT = 4;
    private static final int MULTI_PEAK_WIDTH = 4;
    private static final int MULTI_PEAK_SPACE = 4;

    private static final Map<String, double[]> startValues = new LinkedHashMap<String, double[]>();

    static {
        startValues.put("Один пик", initSinglePeak());
        startValues.put("Несколько пиков", initMultiPeak());
    }

    private List<JRadioButton> methodRadioButtons = new ArrayList<JRadioButton>();
    private JComboBox<String> initStateBox = new JComboBox<String>();

    private IntegerField stepCountField = new IntegerField("Количество шагов", 100);
    private DoubleField kappaField = new DoubleField("Константа \u03f0", 0.0);
    private DoubleField muField = new DoubleField("Константа \u03bc", 0.0);

    private Main() {
        setTitle("Параметры");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        VBoxPanel mainPanel = new VBoxPanel();

        VBoxPanel methodsPanel = new VBoxPanel();
        methodsPanel.setBorder(BorderFactory.createTitledBorder("Схема"));
        ButtonGroup btnGroup = new ButtonGroup();
        for (String s : Methods.getNames()) {
            JRadioButton button = new JRadioButton(s);
            methodRadioButtons.add(button);
            btnGroup.add(button);
            methodsPanel.addComponent(button);
        }
        methodRadioButtons.get(0).setSelected(true);
        mainPanel.addComponent(methodsPanel);

        VBoxPanel initStatePanel = new VBoxPanel();
        initStatePanel.setBorder(BorderFactory.createTitledBorder("Начальные условия"));
        for (String s : startValues.keySet()) {
            initStateBox.addItem(s);
        }
        initStatePanel.addComponent(initStateBox);
        initStatePanel.addComponent(new JLabel("Ширина: " + WIDTH));
        mainPanel.addComponent(initStatePanel);

        VBoxPanel paramPanel = new VBoxPanel();
        paramPanel.setBorder(BorderFactory.createTitledBorder("Параметры"));
        paramPanel.addComponent(stepCountField);
        paramPanel.addComponent(kappaField);
        paramPanel.addComponent(muField);
        mainPanel.addComponent(paramPanel);

        JButton calcButton = new JButton("Вычислить");
        calcButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, calcButton.getPreferredSize().height));
        calcButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculate();
            }
        });
        mainPanel.addComponent(calcButton);

        setContentPane(mainPanel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void calculate() {
        String type = initStateBox.getSelectedItem().toString();
        String method = getSelectedMethod();
        double kappa;
        double mu;
        int stepCount;
        try {
            stepCount = stepCountField.getValue();
            kappa = kappaField.getValue();
            mu = muField.getValue();
        } catch (IllegalArgumentException e) {
            errorMessage("Неверный формат входных данных");
            return;
        }
        double[] start = startValues.get(type);
        double[][] values;
        try {
            values = Methods.getMethod(method).calculate(start, kappa, mu, stepCount);
        } catch (IllegalArgumentException e) {
            errorMessage("Неверные значения параметров");
            return;
        }
        String description = method.toLowerCase() + ", " + type.toLowerCase() + ", " + stepCount + " шагов, k=" + kappa + ", \u03bc=" + mu;
        new GraphFrame(values, description);

    }

    private void errorMessage(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Ошибка", JOptionPane.ERROR_MESSAGE);
    }

    private String getSelectedMethod() {
        for (JRadioButton btn : methodRadioButtons) {
            if (btn.isSelected()) {
                return btn.getText();
            }
        }
        throw new IllegalStateException("Method not selected");
    }

    private static double[] initSinglePeak() {
        double[] ans = new double[WIDTH];
        int start = (WIDTH - SINGLE_PEAK_WIDTH) / 2;
        for (int i = start; i < start + SINGLE_PEAK_WIDTH; ++i) {
            ans[i] = 1.0;
        }
        return ans;
    }

    private static double[] initMultiPeak() {
        double[] ans = new double[WIDTH];
        int start = (WIDTH - (MULTI_PEAK_COUNT * MULTI_PEAK_WIDTH + (MULTI_PEAK_COUNT - 1) * MULTI_PEAK_SPACE)) / 2;
        for (int i = 0; i < MULTI_PEAK_COUNT; ++i) {
            for (int j = 0; j < MULTI_PEAK_WIDTH; ++j) {
                ans[start + j] = 1.0;
            }
            start += MULTI_PEAK_WIDTH + MULTI_PEAK_SPACE;
        }
        return ans;
    }

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                new Main();
            }
        }).start();
    }
}
