import model.ChartDetails;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static java.lang.Math.cos;

public class SignalLength {

    double signalLength(ChartDetails chartDetails) {
        double max = chartDetails.getValues().stream().mapToDouble(Double::doubleValue).max().getAsDouble();
        Map<Double, Integer> map = new LinkedHashMap<>();
        List<Double> list = new ArrayList<>();
        int n = 0;
        for (Double ds : chartDetails.getValues()) {
                double tmp = ds - max;
                list.add(tmp);
            }

        for (Double aDouble : list) {
            if (aDouble > -3.1) {
                map.put(aDouble, n);
            }
            n++;
        }
        return (int) map.values().toArray()[map.size()-1] - (int) map.values().toArray()[0];
    }
}