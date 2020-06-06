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
import signals.ASK;
import signals.FSK;
import signals.PSK;
import signals.S2BS;

import java.util.ArrayList;
import java.util.List;

public class Chart extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) throws Exception {

        //S2BSChart s2BSChart = new S2BSChart();
        ASK ask = new ASK();
        FSK fsk = new FSK();
        PSK psk = new PSK();
        S2BS s2BS = new S2BS();
        String text = "Panda";


        String bits = s2BS.stringToBinaryStream(text.trim(), Boolean.FALSE);
        //ChartDetails zASK = FileUtility.getDoubleListFromFileASK(Objects.requireNonNull(getClass().getClassLoader().getResource("klucz_ASK.csv")));

        ChartDetails zASK = ask.ask(bits, false);
        ChartDetails mASK = ask.multiplyASK(bits, zASK.getValues());
        ChartDetails iASK = ask.integralASK(bits, mASK.getValues());
        ChartDetails gASK = ask.gateASK(iASK.getValues());
        //final List<Pair<Double, Double>> dftAM = fourier.dft(zASK);
        //final ChartDetails askAM = fourier.makeAmplitude(dftAM);
        //final ChartDetails dASK = fourier.decibelScale(askAM);

        ChartDetails zFSK = fsk.fsk(bits, false);
        ChartDetails mFSK1 = fsk.multiplyFSK1(bits, zFSK.getValues());
        ChartDetails mFSK2 = fsk.multiplyFSK2(bits, zFSK.getValues());
        ChartDetails iFSK1 = fsk.integralFSK1(bits, mFSK1.getValues());
        ChartDetails iFSK2 = fsk.integralFSK2(bits, mFSK2.getValues());
        ChartDetails rFSK = fsk.result(iFSK2.getValues(), iFSK1.getValues());
        ChartDetails gFSK = fsk.gateFSK(rFSK.getValues());

        //final List<Pair<Double, Double>> dftFSK = fourier.dft(zFSK);
        //final ChartDetails fskAM = fourier.makeAmplitude(dftFSK);
        //final ChartDetails dFSK = fourier.decibelScale(fskAM);

        ChartDetails zPSK = psk.psk(bits, false);
        ChartDetails mPSK = psk.multiplyPSK(bits, zPSK.getValues());
        ChartDetails iPSK = psk.integralPSK(bits, mPSK.getValues());
        ChartDetails gPSK = psk.gatePSK(iPSK.getValues());
        //final List<Pair<Double, Double>> dftPSK = fourier.dft(zPSK);
        //final ChartDetails pskAM = fourier.makeAmplitude(dftPSK);
        //final ChartDetails dPSK = fourier.decibelScale(pskAM);

        //makeChart(primaryStage, zASK, mASK, iASK, gASK);
        //makeChart(primaryStage, zPSK, mPSK, iPSK, gPSK);
        makeChart(primaryStage, zFSK, mFSK1, mFSK2, iFSK1, iFSK2, rFSK, gFSK);

    }

    private void makeChart(Stage stage, ChartDetails... chartDetails) {
        List<Pair<NumberAxis, NumberAxis>> axises = createAxises(chartDetails);
        GridPane root = new GridPane();
        makeChartProperties(root);
        setCharts(axises, root, chartDetails);
        Scene scene = new Scene(root, 1920.0D, 1080.0D);
        stage.setTitle("Line com.karasinska.Chart");
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }

    private void setCharts(List<Pair<NumberAxis, NumberAxis>> axises, GridPane root, ChartDetails[] charts) {
        int row = 0;
        for (int i = 0; i < charts.length; i++) {
            boolean flag = false;
            if (charts[i].getTitle().equals("decibel"))
                flag = true;
            LineChart<NumberAxis, NumberAxis> lineChart = getLinechart(charts[i], axises.get(i), flag);
            if (i != 0 && i % 2 == 0)
                row++;
            root.add(lineChart, i % 2, row);
        }
    }

    private void makeChartProperties(GridPane root) {
        ColumnConstraints column1 = new ColumnConstraints();
        column1.setPercentWidth(50.0D);
        ColumnConstraints column2 = new ColumnConstraints();
        column2.setPercentWidth(50.0D);
        root.getColumnConstraints().addAll(column1, column2);
    }

    private List<Pair<NumberAxis, NumberAxis>> createAxises(ChartDetails[] chartDetails) {
        List<Pair<NumberAxis, NumberAxis>> pairs = new ArrayList<>();
        for (ChartDetails chartDetail : chartDetails) {
            NumberAxis xAxis, yAxis;
            if (chartDetail.getMinX() != null && chartDetail.getMaxX() != null) {
                xAxis = new NumberAxis(chartDetail.getMinX(), chartDetail.getMaxX(), (1 / chartDetail.getValues().size()));
            } else {
                xAxis = new NumberAxis();
            }
            xAxis.setLabel(chartDetail.getxAxisTitle());
            if (chartDetail.getMinY() != null && chartDetail.getMaxY() != null) {
                yAxis = new NumberAxis(chartDetail.getMinY(), chartDetail.getMaxX(), (1 / chartDetail.getValues().size()));
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
        int x = 0;
        for (Double value : chartDetails.getValues()) {
            if (useDec) {
                double x1 = value * (7 / chartDetails.getValues().size());
                dftSeries.getData().add(new XYChart.Data(x1, value));
                continue;
            }
            dftSeries.getData().add(new XYChart.Data(x, value));
            x++;
        }
        lineChart.getData().add(dftSeries);
        lineChart.setCreateSymbols(false);
        lineChart.setTitle(chartDetails.getTitle());
        return lineChart;
    }
}
