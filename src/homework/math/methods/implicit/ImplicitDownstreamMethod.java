package homework.math.methods.implicit;

import homework.math.Method;

public class ImplicitDownstreamMethod extends Method {

    /**
     * Equation:
     * (T[n + 1][k] - T[n][k])
     * + uu * (T[n + 1][k] - T[n + 1][k - 1])
     * - ae * (T[n + 1][k - 1] - 2 * T[n + 1][k] + T[n + 1][k + 1])
     * = 0
     */
    @Override
    protected double[] calcNext(double[] prev, double uu, double ae) {
        return ImplicitCalculator.calculate(prev, -uu - ae, 1 + uu + 2 * ae, -ae);
    }
}
