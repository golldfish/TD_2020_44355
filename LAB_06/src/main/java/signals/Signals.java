package signals;

import java.util.ArrayList;
import java.util.List;

public class Signals {
    private double Ts;
    private double N;
    private final double fs;
    private String text;
    private List<Double> listX = makeXList();
    private final double A1;
    private final double A2;
    private final double length = text.length();
    private final double Tb = Ts/length;
    private final double fn = N*(1/Tb);
    private final double fn1 = (N+1)/Tb;
    private final double fn2 = (N+2)/Tb;

    public Signals(double ts, double n, double fs, String text, double a1, double a2) {
        Ts = ts;
        N = n;
        this.fs = fs;
        this.text = text;
        A1 = a1;
        A2 = a2;
    }


    private List<Double> makeXList ()
    {
        List <Double> scores = new ArrayList<>();
        for (int i = 0; i<fs*Ts;i++)
        {
            scores.add(i/fs-1/fs);
        }
        return scores;
    }

}
