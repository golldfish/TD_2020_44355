package Zad_2.Zad_2p;

import java.util.HashMap;
import java.util.Map;

import static java.lang.Math.cos;

public class Zad_2p {
    private static double step = 0.00004535;

    private Map<Double, Double> scores = new HashMap<>();


    Map<Double, Double> countScores(double start, double stop, double nValue) {
        for (double t = start; t <= stop; t += step) {
            double tmp = 0;
            for (int n = 1; n <= nValue; n++) {
                tmp += (cos(12 * t * n * n) + cos(16 * t * n)) / (n * n);
            }
            scores.put(t, tmp);
        }
        return scores;
    }
}

