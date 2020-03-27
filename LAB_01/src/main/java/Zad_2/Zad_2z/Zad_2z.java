package Zad_2.Zad_2z;

import java.util.HashMap;
import java.util.Map;

import static java.lang.Math.*;

public class Zad_2z {
    private static double step = 0.00004535;

    private Map<Double,Double> scores = new HashMap<>();
    private double a = 5;
    private double b = 5;
    private double c = 3;

    Double countX (double t)
    {
        return a*pow(t,2)+b*t+c;
    }
    Double countY(double t) {return 2*pow(countX(t),2)+12*cos(t); }

    Map<Double, Double> countScores(int start, int stop) {
        for (double t = start; t <= stop; t += step) {
            double tmp = sin(2*PI*7*t)*(countX(t))-0.2*log10(abs(countY(t))+PI);
            scores.put(t, tmp);
        }

        return scores;
    }


}

