import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Pair;
import model.ChartDetails;
import signalsFunctions.AmplitudeModulation;
import signalsFunctions.InformSignal;
import signalsFunctions.PhaseModulation;

import java.util.ArrayList;
import java.util.List;

public class Chart extends Application {
    public static void main(final String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        InformSignal is = new InformSignal();
        AmplitudeModulation am = new AmplitudeModulation();
        PhaseModulation pm = new PhaseModulation();
        DFT fourier = new DFT();
        SignalLength signalLength = new SignalLength();
        final double start = 0;
        final double stop = 0.5;
        final double step = 1000;
        final double amplitude = 1;
        final double frequencyM = 10;
        final double frequencyN = 150;

        //for  1>kA>0 ; kP <2
        //final double kA = 0.99;
        //final double kP = 1.9;

        //for 12>kA>2; Pi > kP >0
        //final double kA=5;
        //final  double kP=Math.PI/2;

        //for kA>55 ; kP >55 //fM 1; fN 15
        final double kA=55; //44
        final  double kP=12;

        final ChartDetails informSignal = is.makeInformSignal(start, stop, step, amplitude, frequencyM);
        final ChartDetails amplitudeModulation = am.makeAmplitudeModulation(start, stop, step, kA, frequencyN, informSignal.getValues());
        final ChartDetails phaseModulation = pm.phaseModulation(start, stop, step, kP, frequencyN, informSignal.getValues());


        //for am
        final List<Pair<Double, Double>> dftAM = fourier.dft(amplitudeModulation);
        final ChartDetails amplitudeMapAM = fourier.makeAmplitude(dftAM);
        final ChartDetails decibelAM = fourier.decibelScale(amplitudeMapAM);

        //for pm
        final List<Pair<Double, Double>> dftPM = fourier.dft(phaseModulation);
        final ChartDetails amplitudeMapPM = fourier.makeAmplitude(dftPM);
        final ChartDetails decibelPM = fourier.decibelScale(amplitudeMapPM);

        double AM = signalLength.signalLength(decibelAM);
        double PM = signalLength.signalLength(decibelPM);
        //Szerokość pasma
        // 1) AM = 10 //
        // 1) PM = 20 //
        // 2) AM = 10 //
        // 2) PM = 10 //
        // 3) AM = 10 //
        // 3) PM = 120 //

        makeChart(primaryStage, informSignal, amplitudeModulation, phaseModulation, amplitudeMapAM, decibelAM, amplitudeMapPM, decibelPM);

    }

    private void makeChart(final Stage stage, final ChartDetails... chartDetails) {
        final List<Pair<NumberAxis, NumberAxis>> axises = createAxises(chartDetails);
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

    private List<Pair<NumberAxis, NumberAxis>> createAxises(ChartDetails[] chartDetails) {
        List<Pair<NumberAxis, NumberAxis>> pairs = new ArrayList<>();
        for (ChartDetails chartDetail : chartDetails) {
            //Defining the x axis
            NumberAxis xAxis;
            if (chartDetail.getMinX() != null && chartDetail.getMaxX() != null) {
                xAxis = new NumberAxis(chartDetail.getMinX(), chartDetail.getMaxX(), (double) (1 / chartDetail.getValues().size()));
            } else {
                xAxis = new NumberAxis();
            }
            xAxis.setLabel(chartDetail.getxAxisTitle());

            //Defining the y axis
            NumberAxis yAxis;
            if (chartDetail.getMinY() != null && chartDetail.getMaxY() != null) {
                yAxis = new NumberAxis(chartDetail.getMinY(), chartDetail.getMaxX(), (double) (1 / chartDetail.getValues().size()));
            } else {
                yAxis = new NumberAxis();
            }

            yAxis.setLabel(chartDetail.getyAxisTitle());

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