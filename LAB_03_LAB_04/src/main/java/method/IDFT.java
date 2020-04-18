package method;

import javafx.util.Pair;
import model.ChartDetails;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

public class IDFT {

    public ChartDetails idft (List<Pair<Double, Double>> dft)
    {
        List<Double> scores = new ArrayList<>();

        for (int n=0; n<dft.size(); n++)
        {
            double sum=0;
            for(int k=0; k<dft.size(); k++){
                double real=dft.get(k).getKey();
                double imaginary=dft.get(k).getValue();
                double ws=2*Math.PI*k*n;
                sum+=cos(ws/dft.size())*real-sin(ws/dft.size())*imaginary;
         }
                scores.add(sum);
        }

        return new ChartDetails("IDFT: ", scores);
    }
}
