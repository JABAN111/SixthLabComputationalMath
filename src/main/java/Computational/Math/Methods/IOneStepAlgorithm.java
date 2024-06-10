package Computational.Math.Methods;

import java.util.function.BinaryOperator;

public interface IOneStepAlgorithm {
    double applyOneStep(AbstractMethod method, double xn, BinaryOperator<Double> function, double x_0, double y_0, double step, double accuracy);
}