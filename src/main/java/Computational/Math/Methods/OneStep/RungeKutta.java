package Computational.Math.Methods.OneStep;

import Computational.Math.Methods.AbstractMethod;
import org.netirc.library.jtables.JTablesBuilder;
import org.netirc.library.jtables.exception.MalformedTableException;
import org.netirc.library.jtables.table.MonospaceTable;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.Function;

public class RungeKutta extends AbstractMethod {
    public RungeKutta() {
        super("Метод Рунге-Кутта 4-го порядка");
    }

    @Override
    public List<Double> apply( double xn, BinaryOperator<Double> function, double x_0, double y_0, double step)
    {
        var n = (int) ((xn - x_0)/step);
//        JTablesBuilder<MonospaceTable> builder = MonospaceTable.build();
        double k1,k2,k3,k4;
        List<Double> yFinal = new ArrayList<>();
        yFinal.add(y_0);
//        builder.columns("i","xi","k1","k2","k3","k4","y");
        for (int i = 0; i < n; i++) {

            k1 = step*function.apply(x_0,y_0);
            k2 = step*function.apply(x_0+step/2,y_0+k1/2);
            k3 = step * function.apply(x_0+step/2,y_0+k2/2);
            k4 = step * function.apply(x_0+step,y_0+k3);

            double yNext =  y_0 + (k1 + (2*k2) + (2*k3) + k4)/6;

            y_0 = yNext;
            yFinal.add(yNext);
            x_0 += step;
//            builder.row(STR."\{i+1}",round(x_0),round(k1),round(k2),round(k3),round(k4),round(yNext));
        }
//        System.out.println(builder.getTable().toStringHorizontal());
        return yFinal;
    }

}
