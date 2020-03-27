package Zad_2.Zad_2v;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

public class Chart2v extends Application {

    @Override
    public void start(Stage primaryStage){
        Zad_2v zad_2v=new Zad_2v();
        primaryStage.setTitle("v(t)");

        //def axes
        final NumberAxis xAxis=new NumberAxis();
        final NumberAxis yAxis=new NumberAxis();

        xAxis.setLabel("X");
        yAxis.setLabel("Y");

        //create chart
        final ScatterChart<Number, Number> scatterChart=new ScatterChart<>(xAxis,yAxis);
        scatterChart.setPrefHeight(0.5);

        //def series
        XYChart.Series series=new XYChart.Series();

        zad_2v.countScores(0,1).forEach((x,y)->series.getData().add(new XYChart.Data(x,y)));

        scatterChart.getData().add(series);
        //lineChart.setCreateSymbols(false);
        Scene scene=new Scene(scatterChart, 1920,1080 );
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main (String [] args)
    {
        launch(args);
    }
}
