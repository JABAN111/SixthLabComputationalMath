package Computational.Math;

import Computational.Math.Methods.AbstractMethod;
import Computational.Math.Methods.AdamsMethod;
import Computational.Math.Methods.IManyStepsAlgorithm;
import Computational.Math.Methods.IOneStepAlgorithm;
import Computational.Math.Methods.OneStep.EulerMethod;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.Function;

import static java.lang.Math.abs;

public class Processing implements IOneStepAlgorithm, IManyStepsAlgorithm {
    private final double pEuler = 1d;
    private final double pRungeKutta = 4d;

    @Override
    public double applyOneStep(AbstractMethod method, double xn, BinaryOperator<Double> function, double x_0, double y_0, double step, double accuracy) {
        var currentAccuracy = checkMethod(method,xn, function, x_0, y_0, step);
        while (currentAccuracy > accuracy){
            currentAccuracy = checkMethod(method,xn, function, x_0, y_0, step/2);
            step /= 2;
        }
        return currentAccuracy;
    }
    

    private double checkMethod(AbstractMethod method, double xn, BinaryOperator<Double> function, double x_0, double y_0, double step){
        double p = method.getClass() == EulerMethod.class ? pEuler : pRungeKutta;
        ArrayList<Double> firstInput = (ArrayList<Double>) method.apply(xn, function, x_0, y_0, step);
        ArrayList<Double> secondInput = (ArrayList<Double>) method.apply(xn, function, x_0, y_0, step/2);
        var divisible = abs(firstInput.getLast() - secondInput.getLast());
        var divider = Math.pow(2,p)-1;
        return divisible/divider;
    }


    @Override
    public double applyManySteps(Function<Double,Double> correctFunction, int m, double x_n, BinaryOperator<Double> f, double x_0, double y_0, double step, double accuracy) {
        AdamsMethod adamsMethod = new AdamsMethod();
        double diff = 999999999999999d;

        ArrayList<Double> correctYList = new ArrayList<>();
        for (double tmp = x_0; tmp < x_n; tmp+=step) {
            correctYList.add(correctFunction.apply(tmp));
        }
        System.out.println(STR."Точный массив: \{correctYList}");
        var iterMax = 0;
        step*=2;
        while(diff > accuracy){
            step/=2;
            iterMax++;
            ArrayList<Double> yAdams = (ArrayList<Double>) adamsMethod.apply(4, x_n, f, x_0, y_0, step);
            for (int i = 0; i < Math.min(yAdams.size(),correctYList.size()); i++) {
                diff = 0;
                if(abs(yAdams.get(i) - correctYList.get(i)) > diff){
                    diff = abs(yAdams.get(i) - correctYList.get(i));
                }
            }
            if(iterMax == 20){
                System.out.println(STR."Текущая точность: \{diff}");
                System.out.println(STR."Массив адама: " + yAdams.getLast());
                System.out.println(STR."Точный массив: " + correctYList.getLast());
                System.exit(-1);
            }
        }
        return diff;
    }
}
