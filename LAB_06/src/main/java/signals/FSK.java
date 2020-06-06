package signals;

import model.ChartDetails;


import java.util.ArrayList;
import java.util.List;

public class FSK {
    private int bitsRestr = 10;
    double Ts = 100;
    double N = 2;
    double fs = 2000;
    double a = 2;
    double f1 = 50;
    double f2 = 100;
    double fi = 0;
    double h = 2;

    public ChartDetails fsk(String bits, boolean restrict) {
        List<Double> xlist = makeXList(bits);
        List<Double> ylist = new ArrayList<>();
        boolean loop = true;

        for (int i = 0; i < xlist.size() && loop; i++) {
            double tmp = 0;
            if (bits.charAt((int) (i / Ts)) == '0') {
                tmp = a * Math.sin(fi + 2 * Math.PI * f1 * xlist.get(i));
            } else {
                tmp = a * Math.sin(fi + 2 * Math.PI * f2 * xlist.get(i));
            }
            ylist.add(tmp);

            if (restrict && i >= bitsRestr) {
                loop = false;
            }
        }
        return new ChartDetails("FSK", ylist, "t[s]", "zFSK[t]");
    }

    public ChartDetails multiplyFSK1(String bits, List<Double> list) {
        List<Double> xlist = makeXList(bits);
        List<Double> scores = new ArrayList<>();
        boolean loop = true;

        for (int i = 0; i < xlist.size() && loop; i++) {
            double tmp = list.get(i) * a * Math.sin(fi + 2 * Math.PI * f1 * xlist.get(i));
            scores.add(tmp);
        }
        return new ChartDetails("Demodulacja FSK - x1(t)", scores, "t[s]", "x1[t]");
    }

    public ChartDetails multiplyFSK2(String bits, List<Double> list) {
        List<Double> xlist = makeXList(bits);
        List<Double> scores = new ArrayList<>();
        boolean loop = true;

        for (int i = 0; i < xlist.size() && loop; i++) {
            double tmp = list.get(i) * a * Math.sin(fi + 2 * Math.PI * f2 * xlist.get(i));
            scores.add(tmp);
        }
        return new ChartDetails("Demodulacja FSK - x2(t)", scores, "t[s]", "x2[t]");
    }

    public ChartDetails integralFSK1(String bits, List<Double> list) {
        List<Double> scores = new ArrayList<>();
        List<Double> xlist = makeXList(bits);
        int bitsLength = list.size() / bits.length();
        int bitNumber = 0;
        double sum = 0;
        for (int i = 0; i < list.size(); i++) {
            if (i < xlist.size() - 1) {
                sum += list.get(i) * (xlist.get(i + 1) - xlist.get(i));
            }

            if (bitNumber != i / bitsLength) {
                sum = 0;
                bitNumber++;
            }
            scores.add(sum * 1000);

        }
        return new ChartDetails("Demodulacja FSK - p1(t)", scores, "t[s]", "p1[t]");
    }

    public ChartDetails integralFSK2(String bits, List<Double> list) {
        List<Double> scores = new ArrayList<>();
        List<Double> xlist = makeXList(bits);
        int bitsLength = list.size() / bits.length();
        int bitNumber = 0;
        double sum = 0;
        for (int i = 0; i < list.size(); i++) {
            if (i < xlist.size() - 1) {
                sum += list.get(i) * (xlist.get(i + 1) - xlist.get(i));
            }

            if (bitNumber != i / bitsLength) {
                sum = 0;
                bitNumber++;
            }
            scores.add(sum * 1000);

        }
        return new ChartDetails("Demodulacja FSK - p2(t)", scores, "t[s]", "p2[t]");
    }


    public ChartDetails result(List<Double> list1, List<Double> list2) {
        List<Double> scores = new ArrayList<>();
        for (int i = 0; i < list1.size() && i < list2.size(); i++) {
            scores.add(list1.get(i) - list2.get(i));
        }
        return new ChartDetails("Demodulacja FSK - p(t)", scores, "t[s]", "p[t]");
    }

    public ChartDetails gateFSK(List<Double> list) {
        List<Double> scores = new ArrayList<>();

        for (Double e : list) {
            if (e < h) {
                scores.add(0.0);
            } else {
                scores.add(1.0);
            }
        }

        return new ChartDetails("Demodulacja FSK - m(t)", scores, "t[s]", "m[t]");
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
