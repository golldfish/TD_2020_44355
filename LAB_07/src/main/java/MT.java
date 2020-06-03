import model.ChartDetails;

import java.util.ArrayList;
import java.util.List;

public class MT {
    public List<Double> xValues = new ArrayList<>();
    private double ts = 125;
    public ChartDetails mt (String text, int stepsValue)
    {
        List<Double> yValues = new ArrayList<>();
        String bits = stringToBinaryStream(text, Boolean.FALSE); //dlugosc = 16
        double step = bits.length()/stepsValue;
        //double bitStep = (1/freqency)/2;  //długość jednego bitu

        for (int i = 0;i<stepsValue;i++)
        {

        }
        return new ChartDetails("CLK", yValues, "", "");
    }

       public String stringToBinaryStream(String text, Boolean isBigEndian) {
            byte[] bytes = text.getBytes();
            StringBuilder bits = new StringBuilder();
            StringBuilder reverseBits = new StringBuilder();
            System.err.println(text + " contains " + bytes.length + " bytes");
            for (byte b : bytes) {
                int val = b;
                for (int i = 0; i < 8; i++) {
                    bits.append((val & 128) == 0 ? 0 : 1);
                    val <<= 1;
                }
            }
            if (isBigEndian){
                for (int i = bits.length()-1; i>0; i--)
                {
                    reverseBits.append(bits.charAt(i));
                }
                return reverseBits.toString();
            }

            System.err.println(bits.toString());
            return bits.toString();
        }
}
