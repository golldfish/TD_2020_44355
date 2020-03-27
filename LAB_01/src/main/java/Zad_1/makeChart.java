package Zad_1;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

public class makeChart extends Application {

    @Override
    public void start(Stage primaryStage){
        ZeroOfFunction zeroOfFunction=new ZeroOfFunction();
        primaryStage.setTitle("x(t)=A*t^2+B*t+C");

        //def axes
        final NumberAxis xAxis=new NumberAxis();
        final NumberAxis yAxis=new NumberAxis();

        xAxis.setLabel("X");
        yAxis.setLabel("Y");

        //create chart
        final LineChart<Number, Number> lineChart=new LineChart<Number, Number>(xAxis,yAxis);

        //def series
        XYChart.Series series=new XYChart.Series();

        zeroOfFunction.countScores(-10,10).forEach((x,y)->series.getData().add(new XYChart.Data(x,y)));

        lineChart.getData().add(series);
        lineChart.setCreateSymbols(false);
        Scene scene=new Scene(lineChart, 1920 , 1080 );
        primaryStage.setScene(scene);
        primaryStage.show();
        zeroOfFunction.countZerosOfFunction();
    }

    public static void main (String [] args)
    {
        launch(args);
    }
}
