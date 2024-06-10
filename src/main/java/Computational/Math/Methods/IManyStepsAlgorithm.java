package Computational.Math.Methods;

import java.util.function.BinaryOperator;
import java.util.function.Function;

public interface IManyStepsAlgorithm {
    double applyManySteps(Function<Double,Double> correctFunction, int m, double b, BinaryOperator<Double> f, double x_0, double y_0, double step, double accuracy);
}
