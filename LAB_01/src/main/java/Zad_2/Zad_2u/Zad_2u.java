package Zad_2.Zad_2u;

import java.util.HashMap;
import java.util.Map;

import static java.lang.Math.*;

public class Zad_2u {
    private static double step = 0.00004535;
    private Map<Double,Double> scores = new HashMap<>();
    private double a = 5;
    private double b = 5;
    private double c = 3;

    private Double countX(double t)
    {
        return a*pow(t,2)+b*t+c;
    }
    private Double countY(double t) {return 2*pow(countX(t),2)+12*cos(t); }
    private Double countZ(double t) {return sin(2*PI*7*t)*(countX(t))-0.2*log10(abs(countY(t))+PI);}

    Map<Double, Double> countScores(int start, int stop) {
        for (double t = start; t <= stop; t += step) {
            double tmp = sqrt(abs(countY(t)*countY(t)*countZ(t)))-1.8*sin(0.4*t*countZ(t)*countX(t));
            scores.put(t, tmp);
        }

        return scores;
    }
}

