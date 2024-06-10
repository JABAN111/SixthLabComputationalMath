package Computational.Math.Methods;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BinaryOperator;

public class AdamsMethod {
    private String methodName = "Метод Адамса";


    public List<Double> apply(int m, double x_n, BinaryOperator<Double> f, double x_0, double y_0, double step) {
        int n = (int) ((x_n - x_0) / step);
        System.out.println(n);
        Double[] yArr = new Double[n + 1];
        Double[] xArr = new Double[n + 1];
        for (int i = 0; i <= n; i++) {
            xArr[i] = x_0 + i * step;
        }
        yArr[0] = y_0;
        for (int i = 0; i < m; i++) {
            yArr[i + 1] = yArr[i] + step * f.apply(xArr[i], yArr[i]);
        }
        for (int i = m; i < n; i++) {
            yArr[i + 1] = yArr[i] + step * (
                    55 * f.apply(xArr[i], yArr[i]) -
                            59 * f.apply(xArr[i - 1], yArr[i - 1]) +
                            37 * f.apply(xArr[i - 2], yArr[i - 2]) -
                            9 * f.apply(xArr[i - 3], yArr[i - 3])
            ) / 24.0;
        }
        System.out.println("Последнее значение массива x: " + new ArrayList<Double>(List.of(xArr)));
        return new ArrayList<Double>(List.of(yArr));
    }
}