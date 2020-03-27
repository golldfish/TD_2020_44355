package Zad_2.Zad_2p;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.FlowPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.List;
import java.util.Map;

public class Chart2p extends Application {


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        //create chart
        Zad_2p zad_2p = new Zad_2p();
        //def axes
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();

        xAxis.setLabel("X");
        yAxis.setLabel("Y");

        final LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);

        lineChart.setTitle("p(t)");

        //def series
        XYChart.Series series1 = new XYChart.Series();
        XYChart.Series series2 = new XYChart.Series();
        XYChart.Series series3 = new XYChart.Series();

        series1.setName("n=2");
        series2.setName("n=4");
        series3.setName("n=55");

        zad_2p.countScores(0, 1,2).forEach((x,y)->series1.getData().add(new XYChart.Data(x,y)));
        zad_2p.countScores(0,1,4).forEach((x,y)->series2.getData().add(new XYChart.Data(x,y)));
        zad_2p.countScores(0,1,55).forEach((x,y)->series3.getData().add(new XYChart.Data(x,y)));

        lineChart.getData().addAll(series1,series2,series3);
        lineChart.setCreateSymbols(false);


       // FlowPane root=new FlowPane();
        //root.getChildren().addAll(lineChart1,lineChart2,lineChart3);
        Scene scene = new Scene(lineChart, 1920, 1080);
        scene.getStylesheets().add("style.css");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    private LineChart <Number, Number> createLineChart (double start, double stop, double n)
    {
        Zad_2p zad_2p = new Zad_2p();
        //def axes
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();

        xAxis.setLabel("X");
        yAxis.setLabel("Y");

        final LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);

        lineChart.setTitle("p(t) for n = " +n);

        //def series
        XYChart.Series series = new XYChart.Series();

        zad_2p.countScores(start, stop, n).forEach((x,y)->series.getData().add(new XYChart.Data(x,y)));

        lineChart.getData().add(series);
        lineChart.setCreateSymbols(false);


        return lineChart;
    }
}

