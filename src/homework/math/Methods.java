package homework.math;

import homework.math.methods.explicit.ExplicitDownstreamMethod;
import homework.math.methods.explicit.ExplicitUpstreamMethod;
import homework.math.methods.implicit.ImplicitDownstreamMethod;
import homework.math.methods.implicit.ImplicitUpstreamMethod;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public enum Methods {
    EXPLICIT_UPSTREAM(new ExplicitUpstreamMethod()),
    EXPLICIT_DOWNSTREAM(new ExplicitDownstreamMethod()),
    IMPLICIT_UPSTREAM(new ImplicitUpstreamMethod()),
    IMPLICIT_DOWNSTREAM(new ImplicitDownstreamMethod());

    private Method method;
    private static Map<String, Methods> nameMap = new LinkedHashMap<String, Methods>();
    static {
        nameMap.put("Явная схема по потоку", EXPLICIT_UPSTREAM);
        nameMap.put("Явная схема против потока", EXPLICIT_DOWNSTREAM);
        nameMap.put("Неявная схема по потоку", IMPLICIT_UPSTREAM);
        nameMap.put("Неявная схема против потока", IMPLICIT_DOWNSTREAM);
    }


    private Methods(Method method) {
        this.method = method;
    }

    public static Set<String> getNames() {
        return nameMap.keySet();
    }

    public static Methods getMethod(String name) {
        return nameMap.get(name);
    }

    public double[][] calculate(double[] startValues, double kappa, double mu, int stepsCount) {
        return method.calculate(startValues, kappa, mu, stepsCount);
    }
}
