package Zad_1;

import java.util.HashMap;
import java.util.Map;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class ZeroOfFunction {

    private static double step = 0.01;

    private Map<Double,Double> scores = new HashMap<>();
    private double a = 5;
    private double b = 5;
    private double c = 3;

    private double countDelta() {
        return pow(b, 2) - 4 * a * c;
    }

    Map<Double, Double> countScores(int start, int stop) {
        for (double t = start; t <= stop; t += step) {
            double tmp = a * pow(t, 2) + b * t + c;
            scores.put(t, tmp);
        }

        return scores;
    }

    void countZerosOfFunction() {
        double delta = countDelta();

        if (delta < 0) {
            System.out.println("There's no zeros of function.");
        } else if (delta == 0) {
            double x0 = -(b / 2 * a);
            System.out.println("There's one zero of function - x0 = " + x0);
        } else {
            double x1 = ((-b + sqrt(delta)) / (2 * a));
            double x2 = ((-b - sqrt(delta)) / (2 * a));
            System.out.println("There's two zeros of function - x1 = " + x1 + "and x2 = " + x2);
        }
    }
}



