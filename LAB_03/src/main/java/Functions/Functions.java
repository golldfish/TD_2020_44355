package Functions;

import java.util.HashMap;
import java.util.Map;

import static java.lang.Math.*;

public class Functions {
    Map<Double, Double> scores = new HashMap<>();
    private double a = 5;
    private double b = 5;
    private double c = 3;
    private double A = 1.0 / 100; //[V]
    private double f = b / 100; //[Hz]
    private double fi = c * PI / 100; //[rad]

    public Double countS(double t) {
        return A * sin(2 * PI * f * t + fi);
    }

    public Double countX(double t) {
        return a * pow(t, 2) + b * t + c;
    }

    public Double countY(double t) {
        return 2 * pow(countX(t), 2) + 12 * cos(t);
    }

    public Double countZ(double t) {
        return sin(2 * PI * 7 * t) * (countX(t)) - 0.2 * log10(abs(countY(t)) + PI);
    }

    public Double countV(double t) {
        if (t >= 0 && t < 0.22)
            return (1 - 7 * t) * sin((2 * PI * t * 10) / (t + 0.04));
        else if (t >= 0.22 && t < 0.7)
            return 0.63 * t * sin(125 * t);
        else
            return pow(t, -0.662) + 0.77 * sin(8 * t);
    }

    public Double countU(double t) {
        return sqrt(abs(countY(t) * countY(t) * countZ(t))) - 1.8 * sin(0.4 * t * countZ(t) * countX(t));
    }

    public Double countP2(double t) {
        double sum = 0.0;
        for (int n = 1; n <= 2; n++) {
            sum += (cos(12 * t * n * n) + cos(16 * t * n)) / (n * n);
        }
        return sum;
    }

    public Double countP4(double t) {
        double sum = 0.0;
        for (int n = 1; n <= 4; n++) {
            sum += (cos(12 * t * n * n) + cos(16 * t * n)) / (n * n);
        }
        return sum;
    }

    public Double countP55(double t) {
        double sum = 0.0;
        for (int n = 1; n <= 55; n++) {
            sum += (cos(12 * t * n * n) + cos(16 * t * n)) / (n * n);
        }
        return sum;
    }
}


