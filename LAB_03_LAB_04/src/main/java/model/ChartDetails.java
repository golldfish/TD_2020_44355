package model;

import java.util.List;
import java.util.Map;

public class ChartDetails {
    private final String title;
    private final List<Double> values;

    public ChartDetails(String title, List<Double> values) {
        this.title = title;
        this.values = values;
    }

    public String getTitle() {
        return title;
    }

    public List<Double> getValues() {
        return values;
    }
}
