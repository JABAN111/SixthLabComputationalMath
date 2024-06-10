package MethodsTest;

import Computational.Math.EquationSolve;
import org.junit.Test;

public class SolverTest {
    @Test
    public void testcase(){
        EquationSolve equationSolve = new EquationSolve();
        System.out.println(equationSolve.solveEquation(x -> x + 1000));
    }
}
