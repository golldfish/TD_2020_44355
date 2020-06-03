import model.ChartDetails;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class S2BSChart {
    private double ts = 125;
    public ChartDetails stringToBinaryStream(String text, Boolean isBigEndian) {
        byte[] bytes = text.getBytes();
        List<Double> list = new ArrayList<>();
        StringBuilder bits = new StringBuilder();
        System.err.println(text + " contains " + bytes.length + " bytes");
        for (byte b : bytes) {
            int val = b;
            for (int i = 0; i < 8; i++) {
                bits.append((val & 128) == 0 ? 0 : 1);
                val <<= 1;
            }
        }
        for (int bit = 0; bit < bits.length(); bit++) {
            for (int sample = 0; sample < ts; sample++)
                list.add(Double.parseDouble(String.valueOf(bits.charAt(bit))));
        }
        if (isBigEndian)
            Collections.reverse(list);
        System.err.println(bits.toString());
        return new ChartDetails("m[t]", list, "t[s]", "m[t]");
    }
}
