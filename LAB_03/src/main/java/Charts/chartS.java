package Charts;

import CalculateFunctions.CalculateFunctions;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class chartS extends Application {

    private double start = 0;
    private double stop = 553;
    private int ts =1;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        final CalculateFunctions calculateFunctions = new CalculateFunctions();
        //def axes
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        final NumberAxis xAxisI = new NumberAxis();
        final NumberAxis yAxisI = new NumberAxis();


        //xAxis.setLabel("");
        //yAxis.setLabel("s(t)");

        //create chart
        final LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);
        final LineChart<Number, Number> lineChartIDFT = new LineChart<>(xAxisI, yAxisI);

        lineChart.setTitle("DFT - X(t)");
        lineChartIDFT.setTitle("IDFT - X(t)");

        //def series
        XYChart.Series seriesDFT = new XYChart.Series();
        XYChart.Series seriesIDFT = new XYChart.Series();

        calculateFunctions.calculateS(start, stop, ts, false).forEach(((x, y) -> seriesDFT.getData().add(new XYChart.Data(x, y))));
        calculateFunctions.calculateS(start, stop, ts, true).forEach((x, y) -> seriesIDFT.getData().add(new XYChart.Data(x, y)));

        lineChart.getData().add(seriesDFT);
        lineChart.setCreateSymbols(false);

        lineChartIDFT.getData().add(seriesIDFT);
        lineChartIDFT.setCreateSymbols(false);

        FlowPane root = new FlowPane();
        root.getChildren().addAll(lineChart, lineChartIDFT);

        Scene scene = new Scene(root, 1920, 1080);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}


