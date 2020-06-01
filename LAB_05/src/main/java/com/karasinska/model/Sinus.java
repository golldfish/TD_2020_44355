package com.karasinska.model;

import java.util.List;

public class Sinus {

    public static List<Double> calculateSinus(double amplitude, double phase, double phi, int sample_count, List<Double> list) {
        for (int i = 0; i < sample_count; ++i) {
            double tmp = amplitude * Math.sin(phi + 2 * Math.PI * phase * (double) i / (double) sample_count);
            list.add(tmp);
        }
        return list;
    }
}
