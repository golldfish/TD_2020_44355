package signalsFunctions;

import model.ChartDetails;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.cos;

public class PhaseModulation {

    public ChartDetails phaseModulation (double start, double stop, double fs, double kP, double fn, List<Double> list)
    {
        List <Double> scores = new ArrayList<>();
        int n=0;
        double zP;
        for (double t=start; t<stop; t+=1/fs)
        {
            zP=cos(2*Math.PI*fn*t +kP*list.get(n));
            scores.add(zP);
            n++;
        }
        return new ChartDetails("Phase Modulation", scores, "t[s]", "zP(t)");
    }
}
