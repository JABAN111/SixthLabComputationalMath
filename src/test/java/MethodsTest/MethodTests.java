package MethodsTest;

import Computational.Math.Main;
import Computational.Math.Methods.AdamsMethod;
import Computational.Math.Methods.OneStep.EulerMethod;
import Computational.Math.Methods.OneStep.RungeKutta;
import Computational.Math.Processing;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BinaryOperator;

import static org.junit.Assert.assertTrue;

public class MethodTests {
    EulerMethod method = new EulerMethod();
    RungeKutta rungeKutta = new RungeKutta();

    @Test
    public void firstCase() {
        BinaryOperator<Double> function = (x, y) -> y + (1 + x) * (y * y);
        var x_0 = 1d;
        var y_0 = -1d;
        var step = 0.1d;
        var a = 1d;
        var b = 1.5d;
//            System.out.println("Euler: " + method.apply(b, function, x_0, y_0, step, 0.000001));
        StringBuilder sb = new StringBuilder();
//            for (int i = 0; i < 250; i++) {
//                sb.append("*");
//            }
//            System.out.println("runge: " + rungeKutta.apply(b, function, x_0, y_0, step, 0.000001));
    }

    @Test
    public void markingTest() {
//        BinaryOperator<Double> function = (x,y) -> y+(1+x)*(y*y);
        BinaryOperator<Double> function = (x, y) -> Math.pow(x, 3) + y;
        var x_0 = 1d;

        var y_0 = -1d;
        var step = 0.1d;
        var a = 1d;
        var b = 1.5d;
        Processing processing = new Processing();
        System.out.println("точность эйлера: " + processing.applyOneStep(new EulerMethod(), b, function, x_0, y_0, step, 0.1));
        System.out.println("точность Рунге Кутта: " + processing.applyOneStep(new RungeKutta(), b, function, x_0, y_0, step, 0.1));

    }

    @Test
    public void AdamTest() {
        var x_0 = 1d;

        var y_0 = -1d;
        var step = 0.1d;
        var a = 1d;
        var x_n = 1.5d;
        AdamsMethod adamsMethod = new AdamsMethod();
//        System.out.println(new RungeKutta().apply(x_n,chosenFunction,x_0,y_0,step));
        List<Double> correctYList = new ArrayList<>();
        Main main = new Main();
        var correctFunction = main.getCorrectFunction(1, x_0, y_0);
        BinaryOperator<Double> function = main.getFunction(1);
        for (double tmp = x_0; tmp < x_n ; tmp += step) {
            correctYList.add(correctFunction.apply(tmp));
        }
        System.out.println(STR."Точные значения: \{correctYList}");
        System.out.println(STR."Значения адама \{adamsMethod.apply(4, x_n, function, x_0, y_0, step)}");
        Processing processing = new Processing();
        processing.applyManySteps(correctFunction,4,x_n,function,x_0,y_0,step,0.1);
    }
}
