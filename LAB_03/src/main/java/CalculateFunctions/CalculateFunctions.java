package CalculateFunctions;

import DFT.DFT;
import Functions.Functions;
import IDFT.IDFT;
import javafx.util.Pair;

import java.util.HashMap;
import java.util.Map;

import static java.lang.Math.*;

public class CalculateFunctions {
    Functions functions = new Functions();
    DFT dft = new DFT();
    IDFT idft = new IDFT();
    Map<Double, Double> scores = new HashMap<>();
    Double real;
    Double imaginary;

    public Map<Double, Double> calculateS(double start, double stop, int step, boolean choice) {
        double fs = 1 / step;

        Map<Double, Double> scoresS = new HashMap<>();
        Map<Double, Pair<Double, Double>> dftSScores;
        Map<Double, Double> spectrumScores = new HashMap<>();

        for (double k = start; k <= stop; k += fs) {
            scoresS.put(k, functions.countS(k));
        }

        dftSScores = dft.discreteFourierTransform(scoresS);


        for (Map.Entry<Double, Pair<Double, Double>> entry : dftSScores.entrySet()) {
            real = entry.getValue().getKey();
            imaginary = entry.getValue().getValue();

            double sum = sqrt(pow(real, 2) + pow(imaginary, 2));
            double y = 10 * log10(sum);
            double x = entry.getKey() * (fs / dftSScores.size());
            spectrumScores.put(x, y);
        }

        //choice -> reverse if !choice = dft; else -> reverse
        if (choice) {
            Map<Double, Double> idftScores = idft.reverseDiscreteFourierTransform(dftSScores);
            return idftScores;
        } else {
            return spectrumScores;
        }

    }

    public Map<Double, Double> calculateX(double start, double stop, double step, boolean choice) {
        double fs = 1 / step;

        Map<Double, Double> scoresS = new HashMap<>();
        Map<Double, Pair<Double, Double>> dftSScores;
        Map<Double, Double> spectrumScores = new HashMap<>();

        for (double k = start; k <= stop; k += step) {
            scoresS.put(k, functions.countX(k));
        }

        dftSScores = dft.discreteFourierTransform(scoresS);


        for (Map.Entry<Double, Pair<Double, Double>> entry : dftSScores.entrySet()) {
            real = entry.getValue().getKey();
            imaginary = entry.getValue().getValue();

            double sum = sqrt(pow(real, 2) + pow(imaginary, 2));
            double y = 10 * log10(sum);
            double x = entry.getKey() * (fs / step);
            spectrumScores.put(x, y);
        }

        //choice -> reverse if !choice = dft; else -> reverse
        if (choice) {
            Map<Double, Double> idftScores = idft.reverseDiscreteFourierTransform(dftSScores);
            return idftScores;
        } else {
            return spectrumScores;
        }

    }
}
