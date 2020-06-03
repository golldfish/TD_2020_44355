package signals;
import model.ChartDetails;
import java.util.ArrayList;
import java.util.List;

public class PSK {
    private int bitsRestr = 10;
    double Ts = 100;
    double N = 2;
    double fs = 2000;
    double a = 2;
    double f = 50;
    double fi1 = 0;
    double fi2 = Math.PI;
    double h = 0.1;

    public ChartDetails psk(String bits, boolean restrict) {
        List<Double> xlist = makeXList(bits);
        List<Double> ylist = new ArrayList<>();
        boolean loop = true;

        for (int i = 0; i < xlist.size() && loop; i++) {
            double tmp = 0;
            if (bits.charAt((int) (i / Ts)) == '0') {
                tmp = a * Math.sin(fi1 + 2 * Math.PI * f * xlist.get(i));
            } else {
                tmp = a * Math.sin(fi2 + 2 * Math.PI * f * xlist.get(i));
            }
            ylist.add(tmp);

            if (restrict && i >= bitsRestr) {
                loop = false;
            }
        }

        return new ChartDetails("PSK", ylist, "t[s]", "zPSK[t]");
    }


    public ChartDetails multiplyPSK(String bits, List<Double> list) {
        List<Double> xlist = makeXList(bits);
        List<Double> scores = new ArrayList<>();
        boolean loop = true;

        for (int i = 0; i < xlist.size() && loop; i++) {
            double tmp = list.get(i) * a * Math.sin(fi2 + 2 * Math.PI * f * xlist.get(i));
            scores.add(tmp);
        }
        return new ChartDetails("ASK", scores, "t[s]", "zASK[t]");
    }

    public ChartDetails integralPSK(String bits, List<Double> list) {
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
        return new ChartDetails("ASK", scores, "t[s]", "zASK[t]");
    }

    public ChartDetails gatePSK (List<Double>list)
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

        return new ChartDetails("ASK", scores, "t[s]", "zASK[t]");
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
