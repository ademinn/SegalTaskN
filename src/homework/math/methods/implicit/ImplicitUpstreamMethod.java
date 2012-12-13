package homework.math.methods.implicit;

import homework.math.Method;

public class ImplicitUpstreamMethod extends Method {

    /**
     * Equation:
     * (T[n + 1][k] - T[n][k])
     * + uu * (T[n + 1][k + 1] - T[n + 1][k])
     * - ae * (T[n + 1][k - 1] - 2 * T[n + 1][k] + T[n + 1][k + 1])
     */
    @Override
    protected double[] calcNext(double[] prev, double uu, double ae) {
        return ImplicitCalculator.calculate(prev, -ae, 1 - uu + 2 * ae, uu - ae);
    }
}
