package homework.math;

import java.util.Arrays;

public abstract class Method {

    public double[][] calculate(double[] startValues, double kappa, double mu, int stepsCount) {
        double T[][] = new double[stepsCount][startValues.length];
        double dx = 1. / startValues.length;
        double dt = 1. / stepsCount;
        double uu = mu * dt / dx;
        double ae = kappa * dt / (dx * dx);

        T[0] = Arrays.copyOf(startValues, startValues.length);
        for (int time = 1; time < stepsCount; ++time) {
            T[time] = calcNext(T[time - 1], uu, ae);
        }
        return T;
    }

    protected abstract double[] calcNext(double[] prev, double uu, double ae);
}
