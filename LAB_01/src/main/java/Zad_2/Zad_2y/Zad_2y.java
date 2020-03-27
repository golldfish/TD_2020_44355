package Zad_2.Zad_2y;

import java.util.HashMap;
import java.util.Map;

import static java.lang.Math.cos;
import static java.lang.Math.pow;

public class Zad_2y {
    private static double step = 0.00004535;
    Double countX (double t)
    {
        return a*pow(t,2)+b*t+c;
    }
    private Map<Double,Double> scores = new HashMap<>();
    private double a= 5;
    private double b = 5;
    private double c = 3;


    Map<Double, Double> countScores(int start, int stop) {
        for (double t = start; t <= stop; t += step) {
            double tmp = 2*pow(countX(t),2)+12*cos(t);
            scores.put(t, tmp);
        }

        return scores;
    }


}

