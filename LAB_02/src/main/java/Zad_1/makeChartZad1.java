package Zad_1;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

public class makeChartZad1 extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Zad_1 zad_1 = new Zad_1();

        //def axes
        final NumberAxis xAxis = new NumberAxis(0, 0.2, 0.01);
        final NumberAxis yAxis = new NumberAxis();

        xAxis.setLabel("X");
        yAxis.setLabel("Y");

        //create chart
        final LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setTitle("Simple tone");

        //def series
        XYChart.Series series = new XYChart.Series();

        zad_1.sampling(0, 5, 2000).forEach((x, y) -> series.getData().add(new XYChart.Data(x, y)));

        lineChart.getData().add(series);
        lineChart.setCreateSymbols(false);
        Scene scene = new Scene(lineChart, 1920, 1080);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
 