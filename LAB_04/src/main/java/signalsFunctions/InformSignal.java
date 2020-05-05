package signalsFunctions;

import model.ChartDetails;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.Math.sin;

/**
 * The type Inform signal.
 */
public class InformSignal {
    /**
     * Make inform signal function that counts scores and puts them into a map.
     * It has simple tone form.
     *
     * @param start      the start
     * @param stop       the stop
     * @param fs         is the frequency of sampling -> uses to generate values
     * @param amplitude  the amplitude
     * @param frequencyM the frequency_m of inform signal
     * @return the map
     */
    public ChartDetails makeInformSignal (double start, double stop, double fs, double amplitude, double frequencyM){
        List<Double> scores= new ArrayList<>();
        double m=0;
        for (double t=start; t<stop;t+=(1/fs))
        {
            m=amplitude*sin(2*Math.PI*frequencyM*t);
            scores.add(m);
        }

        return new ChartDetails("Inform Signal", scores, "t[s]", "m(t)");
    }

}
