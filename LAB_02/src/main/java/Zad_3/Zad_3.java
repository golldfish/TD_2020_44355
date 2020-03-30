package Zad_3;

import java.util.HashMap;
import java.util.Map;

import static java.lang.Math.*;

public class Zad_3 {
    Map<Double, Double> scores = new HashMap<Double, Double>();
    private double a = 5;
    private double b = 5;
    private double c = 3;

    Map<Double, Double> quantization(double start, double stop, double ts) {
        double A = 1.0; //[V]
        double f = b; //[Hz]
        double fi = c * PI; //[rad]
        double fs = 1/ts;
        double q=6;
        double tmp=(2*A)/(pow(2,q));
        double values=0;

        double s;
        for (double t = start; t <= stop; t += fs/2) {
            s = A * sin(2 * PI * f * t + fi);
            values=s/tmp;
            double score=round(values)*tmp;
            scores.put(t, score);
        }
        return scores;
    }
}