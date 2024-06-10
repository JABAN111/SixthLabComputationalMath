package Computational.Math.Methods.OneStep;

import Computational.Math.Methods.AbstractMethod;
import org.netirc.library.jtables.JTablesBuilder;
import org.netirc.library.jtables.exception.MalformedTableException;
import org.netirc.library.jtables.table.MonospaceTable;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BinaryOperator;

public class EulerMethod extends AbstractMethod {
    public EulerMethod() {
        super("Метод Эйлера");
    }

    @Override
    public List<Double> apply(double xn, BinaryOperator<Double> function, double x_0, double y_0, double step )  {
//        JTablesBuilder<MonospaceTable> builder = MonospaceTable.build();
//        double finalAnswer = 999;
        List<Double> yValues = new ArrayList<>();
//        builder.columns("i","x_i","y_i","f(x_i,y_i)");
        int iter = 0;double nextY = y_0;
        int n = (int) ((xn-x_0)/step);

        for (int i = 0; i <= n; i++) {
//            builder.row(STR."\{i}",round(x_0),round(y_0),round(function.apply(x_0,y_0)));
            nextY = nextY(x_0,y_0,step,function);
            yValues.add(nextY);
            x_0 += step;
            y_0 = nextY;
        }
//        System.out.println(builder.getTable().toStringHorizontal());
        return yValues;
    }
    private double nextY(double x_0,double y_0, double step, BinaryOperator<Double> f){
        return y_0 + step * f.apply(x_0,y_0);
    }
}
