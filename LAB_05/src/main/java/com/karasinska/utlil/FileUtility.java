package com.karasinska.utlil;

import com.karasinska.model.ChartDetails;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

public class FileUtility {
    public static ChartDetails getDoubleListFromFileASK(final URL fileName) {
        final List<Double> result = new LinkedList<>();
        try (Stream<String> stream = Files.lines(Paths.get(fileName.toURI()))) {
            stream.forEach(line -> result.add(Double.parseDouble(line)));
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
        return new ChartDetails("ASK", result, "t[s]", "zASK[t]");
    }

    public static ChartDetails getDoubleListFromFileFSK(final URL fileName) {
        final List<Double> result = new LinkedList<>();
        try (Stream<String> stream = Files.lines(Paths.get(fileName.toURI()))) {
            stream.forEach(line -> result.add(Double.parseDouble(line)));
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
        return new ChartDetails("FSK", result, "s[t]", "zFSK[t]");
    }

    public static ChartDetails getDoubleListFromFilePSK(final URL fileName) {
        final List<Double> result = new LinkedList<>();
        try (Stream<String> stream = Files.lines(Paths.get(fileName.toURI()))) {
            stream.forEach(line -> result.add(Double.parseDouble(line)));
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
        return new ChartDetails("PSK", result, "t[s]", "zPSK[t]");
    }
}
