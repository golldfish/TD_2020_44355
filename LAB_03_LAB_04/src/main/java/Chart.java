import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Pair;
import method.DFT;
import method.IDFT;
import method.Sampling;
import model.ChartDetails;

import java.util.ArrayList;
import java.util.List;

public class Chart extends Application {
    public static void main(final String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        //for S(t)
        /*final int start = 0;
        final int stop = 553;
        final double step = 7;*/

        //X(t)
        final int start = -10;
        final int stop = 10;
        final double step = 100;

        //for others
        /*final int start = 0;
        final int stop = 1;
        final double step = 500;*/

        DFT fourier = new DFT();
        IDFT f1 = new IDFT();


        //for S(t)
        //final ChartDetails sample = Sampling.makeSampling(start, stop, step);

        //for X(t)
        final ChartDetails sample = Sampling.makeX(start, stop, step);

        //for Y(t)
        //final ChartDetails sample = Sampling.makeY(start, stop, step);

        //for Z(t)
       // final ChartDetails sample = Sampling.makeZ(start, stop, step);

        //for U(t)
       //final ChartDetails sample = Sampling.makeU(start, stop, step);

        //for V(t)
        //final ChartDetails sample = Sampling.makeV(start, stop, step);

        //for P2(t)
        //final ChartDetails sample = Sampling.makeP2(start, stop, step);

        //for P4(t)
        //final ChartDetails sample = Sampling.makeP4(start, stop, step);

        //for P55(t)
        //final ChartDetails sample = Sampling.makeP55(start, stop, step);


        final List<Pair<Double, Double>> dft = fourier.dft(sample);
        final ChartDetails amplitudeMap = fourier.amplituda(dft);
        final ChartDetails phaseMap = fourier.faza(dft);
        final ChartDetails decibel = fourier.decibelScale(amplitudeMap);
        final ChartDetails idft = f1.idft(dft);

        makeChart(primaryStage, sample, amplitudeMap, phaseMap, decibel, idft);

    }

    private void makeChart(final Stage stage, final ChartDetails... chartDetails) {
        final List<Pair<NumberAxis, NumberAxis>> axises = createAxises(chartDetails.length);
        final GridPane root = new GridPane();
        makeChartProperties(root);
        setCharts(axises, root, chartDetails);

        //Creating a scene object
        Scene scene = new Scene(root, 1920, 1080);

        //Setting title to the Stage
        stage.setTitle("Line Chart");

        //Adding scene to the stage
        stage.setScene(scene);
        stage.setMaximized(true);

        //Displaying the contents of the stage
        stage.show();
    }

    private void setCharts(List<Pair<NumberAxis, NumberAxis>> axises, GridPane root, ChartDetails[] charts) {
        int row = 0;
        for (int i = 0; i < charts.length; i++) {
            //Creating the line chart
            boolean flag = false;
            if (charts[i].getTitle().equals("decibel")) {
                flag = true;
            }
            LineChart lineChart = getLinechart(charts[i], axises.get(i), flag);

            //Creating a Group object
            if (i != 0 && i % 2 == 0) {
                ++row;
            }
            root.add(lineChart, i % 2, row);
        }
    }

    private void makeChartProperties(GridPane root) {
        ColumnConstraints column1 = new ColumnConstraints();
        column1.setPercentWidth(50);
        ColumnConstraints column2 = new ColumnConstraints();
        column2.setPercentWidth(50);
        root.getColumnConstraints().addAll(column1, column2);
    }

    private List<Pair<NumberAxis, NumberAxis>> createAxises(int length) {
        List<Pair<NumberAxis, NumberAxis>> pairs = new ArrayList<>();
        for (int i = 0; i < length; ++i) {
            //Defining the x axis
            NumberAxis xAxis = new NumberAxis();
            xAxis.setLabel("X");

            //Defining the y axis
            NumberAxis yAxis = new NumberAxis();
            yAxis.setLabel("Y");

            pairs.add(new Pair<>(xAxis, yAxis));
        }
        return pairs;
    }

    private LineChart<NumberAxis, NumberAxis> getLinechart(ChartDetails chartDetails, Pair<NumberAxis, NumberAxis> pairXYAxis, boolean useDec) {
        LineChart<NumberAxis, NumberAxis> lineChart = new LineChart(pairXYAxis.getKey(), pairXYAxis.getValue());
        XYChart.Series<NumberAxis, NumberAxis> dftSeries = new XYChart.Series<>();
        //chartDetails.getValues().forEach((y) -> dftSeries.getData().add(new XYChart.Data(y)));
        int x = 0;
        for (Double value : chartDetails.getValues()) {
            if (useDec) {
                double x1 = value * (7 / chartDetails.getValues().size());
                dftSeries.getData().add(new XYChart.Data(x1, value));
            } else {
                dftSeries.getData().add(new XYChart.Data(x, value));
                ++x;
            }
        }

        lineChart.getData().add(dftSeries);
        lineChart.setCreateSymbols(false);
        lineChart.setTitle(chartDetails.getTitle());

        return lineChart;
    }


}
