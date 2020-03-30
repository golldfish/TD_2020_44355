package Zad_2;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

public class makeChartZad2 extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Zad_2 zad_2 = new Zad_2();

        //def axes
        final NumberAxis xAxis = new NumberAxis(0, 0.2, 0.02);
        //final NumberAxis xAxis=new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();

        xAxis.setLabel("t");
        yAxis.setLabel("s(t)");

        //create chart
        final LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setTitle("Quantization - q = 16");

        //def series
        XYChart.Series series = new XYChart.Series();

        zad_2.quantization(0, 5, 2000).forEach((x, y) -> series.getData().add(new XYChart.Data(x, y)));

        lineChart.getData().add(series);
        lineChart.setCreateSymbols(false);
        Scene scene = new Scene(lineChart, 1920, 1080);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
 