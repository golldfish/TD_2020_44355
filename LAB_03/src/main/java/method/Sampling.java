package method;

import model.ChartDetails;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.*;

public class Sampling {
    private static double a = 5;
    private static double b = 5;
    private static double c = 3;

   private static Double countX(double t) {
        return a * pow(t, 2) + b * t + c;
    }

    private static Double countY(double t) {
        return 2 * pow(countX(t), 2) + 12 * cos(t);
    }

    private static Double countZ(double t) {
        return sin(2 * PI * 7 * t) * (countX(t)) - 0.2 * log10(abs(countY(t)) + PI);
    }

    public static ChartDetails makeX(double start, double stop, double step) {

        List<Double> scores = new ArrayList<>();
        for (double t = start; t <= stop; t += (1 / step)) {
            double tmp = a * pow(t, 2) + b * t + c;
            scores.add(tmp);
        }
        return new ChartDetails("X(t)", scores, "t[s]", "X(t)");
    }

    public static ChartDetails makeY(double start, double stop, double step) {

        List<Double> scores = new ArrayList<>();
        for (double t = start; t <= stop; t += (1 / step)) {
            double tmp = 2 * pow(countX(t), 2) + 12 * cos(t);
            scores.add(tmp);
        }
        return new ChartDetails("Y(t)", scores, "t[s]", "Y(t)");
    }

    public static ChartDetails makeZ(double start, double stop, double step) {

        List<Double> scores = new ArrayList<>();
        for (double t = start; t <= stop; t += (1 / step)) {
            double tmp = sin(2 * PI * 7 * t) * (countX(t)) - 0.2 * log10(abs(countY(t)) + PI);
            scores.add(tmp);
        }
        return new ChartDetails("Z(t)", scores, "t[s]", "Z(t)");
    }

    public static ChartDetails makeP2(double start, double stop, double step) {

        List<Double> scores = new ArrayList<>();
        for (double t = start; t <= stop; t += (1 / step)) {
            double tmp = 0;
            for (int n = 1; n <= 2; n++) {
                tmp += (cos(12 * t * n * n) + cos(16 * t * n)) / (n * n);
            }

            scores.add(tmp);
        }
        return new ChartDetails("P(t) dla N=2", scores, "t[s]", "P(t)");
    }

    public static ChartDetails makeP4(double start, double stop, double step) {

        List<Double> scores = new ArrayList<>();
        for (double t = start; t <= stop; t += (1 / step)) {
            double tmp = 0;
            for (int n = 1; n <= 4; n++) {
                tmp += (cos(12 * t * n * n) + cos(16 * t * n)) / (n * n);
            }

            scores.add(tmp);
        }
        return new ChartDetails("P(t) dla N=4", scores, "t[s]", "P(t)");
    }

    public static ChartDetails makeP55(double start, double stop, double step) {

        List<Double> scores = new ArrayList<>();
        for (double t = start; t <= stop; t += (1 / step)) {
            double tmp = 0;
            for (int n = 1; n <= 55; n++) {
                tmp += (cos(12 * t * n * n) + cos(16 * t * n)) / (n * n);
            }

            scores.add(tmp);
        }
        return new ChartDetails("P(t) dla N=55", scores, "t[s]", "P(t)");
    }

    public static ChartDetails makeU(double start, double stop, double step) {

        List<Double> scores = new ArrayList<>();
        for (double t = start; t <= stop; t += (1 / step)) {
            double tmp = sqrt(abs(countY(t) * countY(t) * countZ(t))) - 1.8 * sin(0.4 * t * countZ(t) * countX(t));
            scores.add(tmp);
        }
        return new ChartDetails("U(t)", scores, "t[s]", "U(t)");
    }

    public static ChartDetails makeV(double start, double stop, double step) {

        List<Double> scores = new ArrayList<>();
        double tmp;
        for (double t = start; t <= stop; t += (1 / step)) {
            if (t >= 0 && t < 0.22) {
                tmp = (1 - 7 * t) * sin((2 * PI * t * 10) / (t + 0.04));
                scores.add(tmp);
            } else if (t >= 0.22 && t < 0.7) {
                tmp = 0.63 * t * sin(125 * t);
                scores.add(tmp);
            } else if (t <= 1 && t >= 0.7) {
                tmp = pow(t, -0.662) + 0.77 * sin(8 * t);
                scores.add(tmp);
            }
        }
        return new ChartDetails("V(t)", scores, "t[s]", "V(t)");
    }

    public static ChartDetails makeSampling(double start, double stop, double fs) {
        final List<Double> scores = new ArrayList<>();
        double A = 1.0; //[V]
        double f = b; //[Hz]
        double fi = c * PI; //[rad]
        double s;
        for (double t = start; t <= stop; t += (1 / fs)) {
            s = A * sin(2 * PI * f * t + fi);
            scores.add(s);
        }
        return new ChartDetails("Simple tone's signal", scores, "t", "A");
    }
}
