package IDFT;

import javafx.util.Pair;

import java.util.HashMap;
import java.util.Map;

import static java.lang.Math.*;

public class IDFT {
    Double sum;

    public Map<Double, Double> reverseDiscreteFourierTransform(Map<Double, Pair<Double, Double>> dft) {
        double size = dft.size();
        Map<Double, Double> scores = new HashMap<>();
        for (int n = 0; n < size; n++) {
            sum = 0.0;
            for (Map.Entry<Double, Pair<Double, Double>> entry : dft.entrySet()) {
                double wn = (2 * PI * entry.getKey() * n) / size;
                double real = entry.getValue().getKey();
                double imaginary = entry.getValue().getValue();
                sum += cos(wn) * real - sin(wn) * real;
            }

            scores.put((double) n, sum / size);
        }
        return scores;
    }
}

