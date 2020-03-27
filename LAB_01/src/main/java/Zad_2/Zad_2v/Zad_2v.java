package Zad_2.Zad_2v;

import java.util.HashMap;
import java.util.Map;

import static java.lang.Math.*;

public class Zad_2v {
    private static double step = 0.00004535;

    private Map<Double,Double> scores = new HashMap<>();

    Map<Double, Double> countScores(int start, int stop) {
        double tmp;
        for (double t = start; t <= stop; t += step) {
           if (t>=0 && t<0.22)
           {
               tmp=(1-7*t)*sin((2*PI*t*10)/(t+0.04));
               scores.put(t,tmp);
           }
            else if (t>=0.22 && t<0.7)
           {
                tmp=0.63*t*sin(125*t);
                scores.put(t, tmp);
           }
            else if (t<=1 && t>=0.7)
           {
               tmp=pow(t, -0.662)+0.77*sin(8*t);
               scores.put(t, tmp);
           }
        }

        return scores;
    }
}

