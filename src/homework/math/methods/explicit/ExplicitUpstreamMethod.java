package homework.math.methods.explicit;

import homework.math.Method;

public class ExplicitUpstreamMethod extends Method {

    /**
     * Equation:
     * (T[n + 1][k] - T[n][k])
     * + uu * (T[n][k + 1] - T[n][k])
     * - ae * (T[n][k - 1] - 2 * T[n][k] + T[n][k + 1])
     * = 0
     */
    @Override
    protected double[] calcNext(double[] prev, double uu, double ae) {
        return ExplicitCalculator.calculate(prev, ae, 1 + uu - 2 * ae, ae - uu);
    }
}
