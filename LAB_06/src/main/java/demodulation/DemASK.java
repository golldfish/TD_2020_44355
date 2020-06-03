package demodulation;

import model.ChartDetails;

import java.util.ArrayList;
import java.util.List;

public class DemASK {
    double amplitude = 2;
    double frequency = 11;
    double phi = 0;

    public ChartDetails multiplyASK(List<Double> list) {
        List<Double> scores = new ArrayList<>();
        int t = 0;
        for (Double i : list) {
            double tmp = amplitude * Math.sin(Math.PI * 2 * frequency * i * (double)t/(double)list.size());
            scores.add(tmp);
            t++;
        }



        return new ChartDetails("multiply ASK", scores, "", "");
    }

}
