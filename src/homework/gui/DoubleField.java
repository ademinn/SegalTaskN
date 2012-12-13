package homework.gui;

public class DoubleField extends ValueField<Double> {

    public DoubleField(String labelText, Double startValue) {
        super(labelText, startValue);
    }

    @Override
    protected Double parseString(String s) {
        return Double.parseDouble(s);
    }
}
