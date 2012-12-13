package homework.math.methods.explicit;

import homework.math.Method;

public class ExplicitDownstreamMethod extends Method {

    /**
     * Equation:
     * (T[n + 1][k] - T[n][k])
     * + uu * (T[n][k] - T[n][k - 1])
     * - ae * (T[n][k - 1] - 2 * T[n][k] + T[n][k + 1])
     * = 0
     */
    @Override
    protected double[] calcNext(double[] prev, double uu, double ae) {
        return ExplicitCalculator.calculate(prev, uu + ae, 1 - uu - 2 * ae, ae);
    }
}
