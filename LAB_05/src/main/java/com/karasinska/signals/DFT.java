package com.karasinska.signals;

import com.karasinska.model.ChartDetails;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.*;

public class DFT {

    public List<Pair<Double, Double>> dft(ChartDetails signal) {
        List<Pair<Double, Double>> scores = new ArrayList<>();
        for (int k = 0; k < signal.getValues().size(); ++k) {
            double real = 0.0;
            double imaginary = 0.0;
            for (int n = 0; n < signal.getValues().size(); ++n) {
                double wn = 2 * Math.PI * n * k;
                real += signal.getValues().get(n) * cos(wn / signal.getValues().size());
                imaginary += signal.getValues().get(n) * sin(wn / signal.getValues().size()) * (-1);
            }
            Pair<Double, Double> pair = new Pair<>(real, imaginary);
            scores.add(pair);
        }
        return scores;
    }

    public ChartDetails makeAmplitude(List<Pair<Double, Double>> dft) {
        List<Double> scores = new ArrayList<>();
        for (int i = 0; i < dft.size() / 2; i++) {
            double real = dft.get(i).getKey();
            double imaginary = dft.get(i).getValue();
            double sum = 0;
            sum = sqrt(real * real + imaginary * imaginary);
            scores.add(sum / 250);
        }
        return new ChartDetails("com.karasinska.signals.DFT - amplitude", scores, "Frequency [Hz]", "Amplitude");
    }


    public ChartDetails decibelScale(ChartDetails amplitude) {
        List<Double> scores = new ArrayList<>();

        for (int i = 0; i < amplitude.getValues().size(); ++i) {
            scores.add(10 * log10(amplitude.getValues().get(i)));
        }

        return new ChartDetails("com.karasinska.signals.DFT spectrum with decibel scale", scores, "Frequency [Hz]", "Amplitude (dB)");
    }
}