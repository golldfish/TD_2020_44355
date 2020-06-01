package com.karasinska.signals;

import com.karasinska.model.ChartDetails;
import com.karasinska.model.Sinus;

import java.util.ArrayList;
import java.util.List;

public class PSK {
    private int bitsRestr = 10;

    public ChartDetails psk(int sample_count, String bits, boolean restrict) {
        List<Double> scores = new ArrayList<>();

        boolean loop = true;
        double a = 2;
        double f = 11;
        double fi1 = 0;
        double fi2 = Math.PI;

        for (int i = 0; i < bits.length() && loop; ++i) {
            if (bits.charAt(i) == '0') {
                Sinus.calculateSinus(a, f, fi1, sample_count, scores);
            } else {
                Sinus.calculateSinus(a, f, fi2, sample_count, scores);
            }
            if (restrict && i >= bitsRestr) {
                loop = false;
            }
        }

        return new ChartDetails("PSK", scores, "t[s]", "zPSK[t]");
    }
}
