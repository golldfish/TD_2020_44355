package com.karasinska.utlil;

import com.karasinska.model.ChartDetails;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class S2BSChart {
    public ChartDetails stringToBinaryStream(String text, Boolean isBigEndian, double sampleCount) {
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
            for (int sample = 0; sample < sampleCount; sample++)
                list.add(Double.parseDouble(String.valueOf(bits.charAt(bit))));
        }
        if (isBigEndian)
            Collections.reverse(list);
        System.err.println(bits.toString());
        return new ChartDetails("m[t]", list, "t[s]", "m[t]");
    }
}
