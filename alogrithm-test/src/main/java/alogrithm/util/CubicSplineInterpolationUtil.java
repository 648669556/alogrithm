package alogrithm.util;

import org.apache.commons.math3.analysis.interpolation.SplineInterpolator;
import org.apache.commons.math3.analysis.polynomials.PolynomialSplineFunction;

/**
 * @author chenjunhong
 * 创建日期: 2023-12-26  14:05
 */
public class CubicSplineInterpolationUtil {
    public static void main(String[] args) {
        double[] x = {1.0, 2.0, 3.0, 4.0, 5.0};
        double[] y = {1, 2, 4, 8, 16};
        double x0 = 5;
        double y0 = interpolate(x, y, x0);
        System.out.println("The interpolated value at x = " + x0 + " is " + y0);
    }

    public static double interpolate(double[] x, double[] y, double x0) {
        SplineInterpolator interpolator = new SplineInterpolator();
        PolynomialSplineFunction function = interpolator.interpolate(x, y);
        double y0 = function.value(x0);
        return y0;
    }
}
