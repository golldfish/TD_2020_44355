package Zad_1;

import java.util.HashMap;
import java.util.Map;

import static java.lang.Math.*;

public class Zad_1 {
    Map<Double, Double> scores = new HashMap<Double, Double>();
    private double a = 5;
    private double b = 5;
    private double c = 3;

    Map<Double, Double> sampling (double start, double stop, double ts) {
        double A = 1.0; //[V]
        double f = b; //[Hz]
        double fi = c * PI; //[rad]
        double fs = 1/ts;

        double s;
        for (double t = start; t <= stop; t += fs) {
            s = A * sin(2 * PI * f * t + fi);
            scores.put(t, s);
        }
        return scores;
    }
}