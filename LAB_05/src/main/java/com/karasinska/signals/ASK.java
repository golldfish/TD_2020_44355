package com.karasinska.signals;

import com.karasinska.model.ChartDetails;
import com.karasinska.model.Sinus;

import java.util.ArrayList;
import java.util.List;

public class ASK {

    private int bitsRestr = 10;

    public ChartDetails ask(int sampleCount, String bits, boolean restrict) {
        List<Double> scores = new ArrayList<>();
        boolean loop = true;
        double a1 = 0;
        double a2 = 2;
        double f = 11;
        double fi = 0;

        for (int i = 0; i < bits.length() && loop; i++) {
            if (bits.charAt(i) == '0') {
                Sinus.calculateSinus(a1, f, fi, sampleCount, scores);
            } else {
                Sinus.calculateSinus(a2, f, fi, sampleCount, scores);
            }

            if (restrict && i >= bitsRestr) {
                loop = false;
            }
        }

        return new ChartDetails("ASK", scores, "t[s]", "zASK[t]");
    }
}
