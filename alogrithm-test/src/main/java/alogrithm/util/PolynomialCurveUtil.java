package alogrithm.util;

import org.apache.commons.math3.fitting.PolynomialCurveFitter;
import org.apache.commons.math3.fitting.WeightedObservedPoints;

import java.util.Arrays;

/**
 * @author chenjunhong
 * 创建日期: 2023-12-26  11:41
 */
public class PolynomialCurveUtil {
    public static void main(String[] args) {
        double[] x = new double[] {1, 2, 3, 4, 5};
        double[] y = new double[] {1, 4, 9, 16, 25};

        WeightedObservedPoints weightedObservedPoints = new WeightedObservedPoints();
        weightedObservedPoints.add(1,1);
        weightedObservedPoints.add(2,2);
        weightedObservedPoints.add(3,4);
        weightedObservedPoints.add(4,8);
        weightedObservedPoints.add(5,16);
        weightedObservedPoints.add(6,32);
        weightedObservedPoints.add(7,64);


        int degree = 8;
        PolynomialCurveFitter fitter = PolynomialCurveFitter.create(degree);
        double[] coefficients = fitter.fit(weightedObservedPoints.toList());
        System.out.println(Arrays.toString(coefficients));

        double yResult = 0;
        for (int i = 0; i < coefficients.length; i++) {
            yResult += coefficients[i] * Math.pow(8, i);
        }

        System.out.println(yResult);


    }



}
