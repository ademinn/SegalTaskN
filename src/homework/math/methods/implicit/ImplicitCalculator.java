package homework.math.methods.implicit;

public class ImplicitCalculator {
    private static final double EPS = 1e-10;

    public static double[] calculate(double[] prev, double left, double center, double right) {
        if (center < EPS) {
            throw new IllegalArgumentException("Center should be non-zero");
        }
        int n = prev.length;
        double ans[] = new double[n];
        double alpha[] = new double[n];
        double beta[] = new double[n];
        alpha[1] = -right / center;
        beta[1] = prev[0] / center;
        for (int i = 2; i < n; ++i) {
            alpha[i] = -right / (left * alpha[i - 1] + center);
            beta[i] = (prev[i - 1] - left * beta[i - 1]) / (left * alpha[i - 1] + center);
        }
        ans[n - 1] = (prev[n - 1] - left * beta[n - 1]) / (center + left * alpha[n - 1]);
        for (int i = n - 2; i >= 0; --i) {
            ans[i] = alpha[i + 1] * ans[i + 1] + beta[i + 1];
        }
        return ans;
    }
}
