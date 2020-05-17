package com.karasinska.signals;

import com.karasinska.model.ChartDetails;

import java.util.ArrayList;
import java.util.List;

public class PSK {
    private int bitsRestr=10;
    public ChartDetails psk(List<Double> list, double amplitude, double f, double phi0, double phi1, int sampleCount, boolean restrict) {
        List<Double> scores = new ArrayList<>();
        double s =1;
        boolean loop=true;
        int t = 0;
        for (int i=0; i<list.size()&&loop; i++) {
            if (list.get(i) == 0) {
                double sa = amplitude * Math.sin(Math.toRadians(2 * Math.PI * f * i + phi0));
                scores.add(sa);
            } else {
                double sa = amplitude * Math.sin(Math.toRadians(2 * Math.PI * f * i + phi1));
                scores.add(sa);
            }
            t++;

            if (restrict && i >= sampleCount * bitsRestr) {
                loop = false;
            }

        }
        return new ChartDetails("PSK", scores, "t[s]", "zPSK[t]");
    }
}
