package com.karasinska.utlil;

public class S2BS {
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
