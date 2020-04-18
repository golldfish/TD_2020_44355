package method;

import javafx.util.Pair;
import model.ChartDetails;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.*;

public class DFT {

    public List<Pair<Double, Double>> dft(ChartDetails signal) {
        List<Pair<Double, Double>> scores = new ArrayList<>();
        for (int k = 0; k < signal.getValues().size(); ++k) {
            double real = 0.0;
            double imaginary = 0.0;
            for (int n = 0; n < signal.getValues().size(); ++n) {
                double wn = 2 * Math.PI * n * k;
                real += signal.getValues().get(n) * cos(wn / signal.getValues().size());
                imaginary += signal.getValues().get(n) * sin(wn / signal.getValues().size()) * (-1);
            }
            Pair<Double, Double> pair = new Pair<>(real, imaginary);
            scores.add(pair);
        }
        return scores;
    }

    public ChartDetails amplituda(List<Pair<Double, Double>> dft) {
        List<Double> scores = new ArrayList<>();
        for (int i = 0; i < dft.size(); ++i) {
            double real = dft.get(i).getKey();
            double imaginary = dft.get(i).getValue();
            double sum = 0;
            sum = sqrt(real * real + imaginary * imaginary);
            scores.add(sum);
        }
        return new ChartDetails ("Amplituda:", scores);
    }

    public ChartDetails faza(List<Pair<Double, Double>> dft) {
        List<Double> scores = new ArrayList<>();
        for (int i = 0; i < dft.size(); ++i) {
            double real = dft.get(i).getKey();
            double imaginary = dft.get(i).getValue();
            double sum = 0;

            if (real != 0) {
                sum = atan(imaginary / real);
                scores.add(sum);
            }
        }
        return new ChartDetails("Phase: ", scores);
    }

    public ChartDetails decibelScale (ChartDetails amplituda)
    {
        List<Double> scores = new ArrayList<>();

        for (int i=0; i<amplituda.getValues().size();++i)
        {
            scores.add(10*log10(amplituda.getValues().get(i)));
        }

        return new ChartDetails("Decybelowe: ", scores);
    }
}
