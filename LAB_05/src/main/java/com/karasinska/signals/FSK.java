package com.karasinska.signals;

import com.karasinska.model.ChartDetails;

import java.util.ArrayList;
import java.util.List;

public class FSK {
    private int bitsRestr = 10;
    public ChartDetails fsk(List<Double> list, double amplitude, double f0, double f1, double phi, int sampleCount, boolean restrict) {
        List<Double> scores = new ArrayList<>();
        double s = 1;
        boolean loop = true;
        for (int i = 0; i < list.size()&&loop; i++) {
            double sa;
            if (list.get(i) == 0) {
                sa = amplitude * Math.sin(Math.toRadians(2 * Math.PI * f0 * i*s/sampleCount + phi));
            } else {
                sa = amplitude * Math.sin(Math.toRadians(2 * Math.PI * f1 * i*s/sampleCount + phi));
            }
            scores.add(sa);
            if (restrict && i >= sampleCount * bitsRestr) {
                loop = false;
            }

        }
        return new ChartDetails("FSK", scores, "s[t]", "zFSK[t]");
    }
}
