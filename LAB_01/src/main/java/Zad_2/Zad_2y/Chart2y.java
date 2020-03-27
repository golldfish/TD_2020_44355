package Zad_2.Zad_2y;

import Zad_1.ZeroOfFunction;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

public class Chart2y extends Application {

    @Override
    public void start(Stage primaryStage){
        Zad_2y zad_2y=new Zad_2y();
        primaryStage.setTitle("y(t)=2*x(t)^2+12*cos(t)");

        //def axes
        final NumberAxis xAxis=new NumberAxis();
        final NumberAxis yAxis=new NumberAxis();

        xAxis.setLabel("X");
        yAxis.setLabel("Y");

        //create chart
        final LineChart<Number, Number> lineChart=new LineChart<Number, Number>(xAxis,yAxis);

        //def series
        XYChart.Series series=new XYChart.Series();

        zad_2y.countScores(0,1).forEach((x,y)->series.getData().add(new XYChart.Data(x,y)));

        lineChart.getData().add(series);
        lineChart.setCreateSymbols(false);
        Scene scene=new Scene(lineChart, 1920,1080 );
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main (String [] args)
    {
        launch(args);
    }
}
