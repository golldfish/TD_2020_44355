package com.karasinska.signals;

import com.karasinska.model.ChartDetails;

import java.util.ArrayList;
import java.util.List;

public class ASK {
    private int bitsRestr=10;
    public ChartDetails ask(List<Double> list, double amplitude1, double amplitude2, double f, double phi, int sampleCount, boolean restrict) {
        int tmp;
        boolean loop=true;
        List<Double> scores = new ArrayList<>();
        double s = 10;
        for (int i = 0; i < list.size()&&loop; i++) {
            double sin = Math.sin(Math.toRadians(2 * Math.PI * f * i*s  + phi));
            if (list.get(i) == 0) {
                scores.add(amplitude1 * sin);
            } else {
                scores.add(amplitude2 * sin);
            }
            if (restrict && i >= sampleCount * bitsRestr) {
                loop = false;
            }

        }
        return new ChartDetails("ASK", scores, "t[s]", "zASK[t]");
    }
}
