package com.karasinska.model;

import java.util.List;

public class ChartDetails {
    private final String title;

    private final List<Double> values;

    private final String xAxisTitle;

    private final String yAxisTitle;

    private final Integer minX;

    private final Integer maxX;

    private final Integer minY;

    private final Integer maxY;

    public ChartDetails(String title, List<Double> values, String xAxisTitle, String yAxisTitle) {
        this(title, values, xAxisTitle, yAxisTitle, null, null);
    }

    public ChartDetails(String title, List<Double> values, String xAxisTitle, String yAxisTitle, Integer minX, Integer maxX) {
        this(title, values, xAxisTitle, yAxisTitle, minX, maxX, null, null);
    }

    public ChartDetails(String title, List<Double> values, String xAxisTitle, String yAxisTitle, Integer minX, Integer maxX, Integer minY, Integer maxY) {
        this.title = title;
        this.values = values;
        this.xAxisTitle = xAxisTitle;
        this.yAxisTitle = yAxisTitle;
        this.minX = minX;
        this.maxX = maxX;
        this.minY = minY;
        this.maxY = maxY;
    }

    public String getTitle() {
        return this.title;
    }

    public List<Double> getValues() {
        return this.values;
    }

    public String getxAxisTitle() {
        return this.xAxisTitle;
    }

    public String getyAxisTitle() {
        return this.yAxisTitle;
    }

    public Integer getMinX() {
        return this.minX;
    }

    public Integer getMaxX() {
        return this.maxX;
    }

    public Integer getMinY() {
        return this.minY;
    }

    public Integer getMaxY() {
        return this.maxY;
    }
}
