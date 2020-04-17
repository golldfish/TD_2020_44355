package DFT;

import javafx.util.Pair;

import java.util.HashMap;
import java.util.Map;

import static java.lang.Math.*;

public class DFT {
    Double real;
    Double imaginary;

    public Map<Double, Pair<Double, Double>> discreteFourierTransform (Map<Double, Double>signal)
    {
      double size=signal.size();
        Map<Double, Pair<Double,Double>> scores=new HashMap<>();
      for (int k=0; k<size;k++)
      {
          real=0.0;
          imaginary=0.0;
          for (Map.Entry<Double, Double> entry : signal.entrySet()) {
              double n = entry.getKey();
              double wn = (2 * PI * (k * n)) / size;
              real += cos(wn) * entry.getValue();
              imaginary += -sin(wn) * entry.getValue();
          }
          Pair<Double, Double> pair=new Pair<>(real, imaginary);
          scores.put((double)k, pair);
      }
        return scores;
    }

}
