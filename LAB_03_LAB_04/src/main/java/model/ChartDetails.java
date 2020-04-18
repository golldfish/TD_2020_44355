package model;

import java.util.List;

public class ChartDetails {
    private final String title;
    private final List<Double> values;
    private final String xAxisTitle;
    private final String yAxisTitle;

    public ChartDetails(String title, List<Double> values, String xAxisTitle, String yAxisTitle) {
        this.title = title;
        this.values = values;
        this.xAxisTitle = xAxisTitle;
        this.yAxisTitle = yAxisTitle;
    }

    public String getTitle() {
        return title;
    }

    public List<Double> getValues() {
        return values;
    }

    public String getxAxisTitle() {
        return xAxisTitle;
    }

    public String getyAxisTitle() {
        return yAxisTitle;
    }
}
