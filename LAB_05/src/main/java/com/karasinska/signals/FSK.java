package com.karasinska.signals;

import com.karasinska.model.ChartDetails;
import com.karasinska.model.Sinus;

import java.util.ArrayList;
import java.util.List;

public class FSK {
    double N = 300;
    double tb = 0.5;
    private int bitsRestr = 10;

    public ChartDetails fsk(int sample_count, String bits, boolean restrict) {
        List<Double> scores = new ArrayList<>();
        boolean loop = true;
        double a = 2;
        double f1 = 1;
        double f2 = 2;
        double fi = 0;

        for (int i = 0; i < bits.length() && loop; ++i) {
            if (bits.charAt(i) == '0') {
                Sinus.calculateSinus(a, f1, fi, sample_count, scores);
            } else {
                Sinus.calculateSinus(a, f2, fi, sample_count, scores);
            }
            if (restrict && i >= bitsRestr) {
                loop = false;
            }
        }

        return new ChartDetails("FSK", scores, "s[t]", "zFSK[t]");
    }
}
