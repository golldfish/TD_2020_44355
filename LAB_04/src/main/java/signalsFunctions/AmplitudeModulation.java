package signalsFunctions;

import model.ChartDetails;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.cos;

public class AmplitudeModulation {

public ChartDetails makeAmplitudeModulation (double start, double stop, double fs, double kA, double frequencyN, List<Double> list)
{
    List <Double> scores= new ArrayList<>();
    int n=0;
    for (double t=start; t<stop; t+=1/fs)
    {
       double zA=0;
       zA=(kA*list.get(n)+1)*cos(2*Math.PI*frequencyN*t);
       scores.add(zA);
        n++;
    }

    return new ChartDetails("Amplitude modulation", scores, "t[s]", "zA(t)" );
}

}
