package homework.math.methods.explicit;

public class ExplicitCalculator {
    public static double[] calculate(double[] prev, double left, double center, double right) {
        int n = prev.length;
        double[] ans = new double[n];
        for (int i = 1; i < n - 1; ++i) {
            ans[i] = left * prev[i - 1] + center * prev[i] + right * prev[i + 1];
        }
        ans[0] = center * prev[0] + right * prev[1];
        ans[n - 1] = left * prev[n - 2] + center * prev[n - 1];
        return ans;
    }
}
