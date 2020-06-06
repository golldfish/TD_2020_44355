import model.ChartDetails;

import java.util.ArrayList;
import java.util.List;

public class CLK {

    public List<Double> xValues = new ArrayList<>();
    public ChartDetails clk (double freqency, double start, double stop, int stepsValue)
    {
        List<Double> yValues = new ArrayList<>();
        double size = start-stop;
        if (size ==0)
        {
            size = 1;
        }
        double step = size/stepsValue;
        double bitStep = (1/freqency)/2;  //długość jednego bitu

        for (int i = 0;i<stepsValue;i++)
        {
            xValues.add(i*step);
            if (((int)(i*step/bitStep))%2==0)
            {
                yValues.add(1.0);
            }
            else
            {
                yValues.add(0.0);
            }
        }
        return new ChartDetails("CLK", yValues, "", "");
    }
}
