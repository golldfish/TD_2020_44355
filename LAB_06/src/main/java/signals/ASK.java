package signals;

import model.ChartDetails;

import java.util.ArrayList;
import java.util.List;

public class ASK {

    double Ts = 100;
    double N = 2;
    double fs = 2000;
    double a1 = 0;
    double a2 = 2;
    double f = 50;
    double fi = 0;
    private int bitsRestr = 10;
    double h = 0.1;

    public ChartDetails ask(String bits, boolean restrict) {
        List<Double> xlist = makeXList(bits);
        List<Double> ylist = new ArrayList<>();
        boolean loop = true;

        for (int i = 0; i < xlist.size() && loop; i++) {
            double tmp = 0;
            if (bits.charAt((int) (i / Ts)) == '0') {
                tmp = a1 * Math.sin(fi + 2 * Math.PI * f * xlist.get(i));
            } else {
                tmp = a2 * Math.sin(fi + 2 * Math.PI * f * xlist.get(i));
            }
            ylist.add(tmp);

            if (restrict && i >= bitsRestr) {
                loop = false;
            }
        }
        return new ChartDetails("ASK", ylist, "t[s]", "zASK[t]");
    }

    public ChartDetails multiplyASK(String bits, List<Double> list) {
        List<Double> xlist = makeXList(bits);
        List<Double> scores = new ArrayList<>();
        boolean loop = true;

        for (int i = 0; i < xlist.size() && loop; i++) {
            double tmp = list.get(i) * a2 * Math.sin(fi + 2 * Math.PI * f * xlist.get(i));
            scores.add(tmp);
        }
        return new ChartDetails("Demodulacja ASK - x(t)", scores, "t[s]", "x[t]");
    }

    public ChartDetails integralASK(String bits, List<Double> list) {
        List<Double> scores = new ArrayList<>();
        List<Double> xlist = makeXList(bits);
        int bitsLength = list.size()/bits.length();
        int bitNumber = 0;
        double sum = 0;
        for (int i = 0; i < list.size(); i++) {
            if (i<xlist.size()-1) {
                sum += list.get(i) * (xlist.get(i + 1) - xlist.get(i));
            }

            if (bitNumber!=i/bitsLength)
            {
             sum = 0;
             bitNumber++;
            }
            scores.add(sum*1000);

        }
        return new ChartDetails("Demodulacja ASK - p(t)", scores, "t[s]", "p[t]");
    }

    public ChartDetails gateASK (List<Double>list)
    {
        List<Double> scores = new ArrayList<>();

        for (Double e: list) {
            if (e<h)
            {
                scores.add(0.0);
            }
            else
            {
                scores.add(1.0);
            }
        }

        return new ChartDetails("Demodulacja ASK - m(t)", scores, "t[s]", "m[t]");
    }

    private List<Double> makeXList(String bits) {
        double count = bits.length();
        List<Double> scores = new ArrayList<>();
        for (int i = 0; i < count * Ts; i++) {
            scores.add(i / (count * Ts));
        }
        return scores;
    }

}
