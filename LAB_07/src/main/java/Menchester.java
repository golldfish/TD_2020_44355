import model.ChartDetails;

import java.util.ArrayList;
import java.util.List;

public class Menchester {

    public ChartDetails menchester(List<Double> clock, String bits) {
        List<Double> scores = new ArrayList<>();
        double tmp = 0;
        boolean up = false;
        boolean down = false;
        char prevBit = '0';
        double prevClock = 1.0;
        double value = 0;

        for (int i = 0, j = 0; i < clock.size(); i++) {
            if (j < bits.length()) {
                if (prevClock == 0 && clock.get(i) == 1) {
                    up = true;
                    j++;
                }
                if (prevClock == 1 && clock.get(i) == 0) {
                    down = true;
                }

                if (down) {
                    if (bits.charAt(j) == '0') {
                        value = 1.0;
                    } else {
                        value = -1.0;
                    }
                }

                if (up && j < bits.length()) {
                    if (j != 0 && bits.charAt(j) == prevBit) { //if value = 1 -> -1 else 1
                        value = (value == 1) ? -1 : 1;
                    }
                    prevBit = bits.charAt(j);
                }

                up = false;
                down = false;
                prevClock = clock.get(i);
                scores.add(value);
            }
        }
        return new ChartDetails("Menchester", scores, "", "");
    }

    public StringBuilder decMenchester(List<Double> menchester, int size) {
        StringBuilder bits = new StringBuilder();
        int bitsLength = menchester.size() / (size * 2);
        int start = bitsLength / 2;
        double prevBit = 0;
        double tmp = 0;

        for (int i = start, j = 0; i < menchester.size(); i += bitsLength, j++) {
            if (j % 2 != 0) {
                if (prevBit == 1 && menchester.get(i) == -1) {
                    bits.append(1);
                } else {
                    bits.append(0);
                }
            }
            prevBit = menchester.get(i);
        }
        return bits;
    }

}
