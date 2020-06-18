import org.ejml.simple.SimpleMatrix;

public class Hamming {
    static int[][] g = {
            {1, 0, 0, 0, 0, 1, 1},
            {0, 1, 0, 0, 1, 0, 1},
            {0, 0, 1, 0, 1, 1, 0},
            {0, 0, 0, 1, 1, 1, 1}};

    static int[][] h = {
            {0, 1, 1},
            {1, 0, 1},
            {1, 1, 0},
            {1, 1, 1},
            {1, 0, 0},
            {0, 1, 0},
            {0, 0, 1}};

    private S2BS s2BS = new S2BS();

    public static void main(String[] args) {
        String text = "P";
        S2BS s2BS = new S2BS();
        int len = s2BS.stringToBinaryStream(text, false).length();
        Hamming hamming = new Hamming();
        int[][] hCode = hamming.hamming74Encode(text);
        System.out.println("Text:" + s2BS.stringToBinaryStream(text, false));
        System.out.println("Hamming: " + hamming.arrayToString(hCode, len / 4, 7));
        int[][] hNoise = hamming.noise(hCode, 1, 0, len);
        System.out.println("Noise: " + hamming.arrayToString(hNoise, len / 4, 7));
        int[][] hDetected = hamming.hammingDetect(hNoise, len);
        System.out.println("Detected: " + hamming.arrayToString(hDetected, len / 4, 3));
        int[][] hDecoded = hamming.hammingDecode(hDetected, hNoise, len);
        System.out.println("Decoded: " + hamming.arrayToString(hDecoded, 2, 4));
    }

    private SimpleMatrix make4bit(String text, int len) {
        SimpleMatrix array = new SimpleMatrix(len / 4, 4);
        int i = 0;
        int j = 0;
        for (char c : text.toCharArray()) {
            String tmp = s2BS.stringToBinaryStream(String.valueOf(c), false);
            String s1 = tmp.substring(0, (tmp.length() / 2));
            String s2 = tmp.substring(tmp.length() / 2);
            for (char t : s1.toCharArray()) {
                array.set(i, j, Character.getNumericValue(t));
                j++;
            }
            i++;
            j = 0;
            for (char t : s2.toCharArray()) {
                array.set(i, j, Character.getNumericValue(t));
                j++;
            }
            j = 0;
            i++;
        }
        return array;
    }

    public int[][] hamming74Encode(String text) {
        int len = s2BS.stringToBinaryStream(text, false).length();
        SimpleMatrix bit4 = make4bit(text, len);
        SimpleMatrix G = getSimpleMatrix(true, 4, 7);
        int[][] scores = new int[len / 4][7];
        for (int k = 0; k < len / 4; k++) {
            for (int i = 0; i < G.numCols(); i++) {
                double sum = 0;
                for (int j = 0; j < G.numRows(); j++) {
                    sum += bit4.get(k, j) * G.get(j, i);
                    System.out.println(String.format("%d %d %d %f", k, i, j, sum));
                }
                scores[k][i] = ((int) sum % 2);
            }
        }
        return scores;
    }

    //detect if it's a mistake in code-word
    public int[][] hammingDetect(int[][] array, int len) {
        int[][] scores = new int[len / 4][3];

        for (int k = 0; k < len / 4; k++) {
            for (int i = 0; i < 3; i++) {
                double sum = 0;
                for (int j = 0; j < 7; j++) {
                    sum += array[k][j] * h[j][i];
                }
                scores[k][i] = (int) sum % 2;
            }
        }
        return scores;
    }

    public int[][] removeRedundancyBits(int[][] array, int len) {
        int[][] decodedWord = new int[len / 4][4];

        for (int i = 0; i < len / 4; i++) {
            for (int j = 0; j < 4; j++) {
                decodedWord[i][j] = array[i][j];
            }
        }
        return decodedWord;
    }

    public int[][] hammingDecode(int[][] detected, int[][] text, int len) {
        int[][] scores = new int[len / 4][4];
        boolean flag = false;
        int index = 0;
        for (int k = 0; k < len / 4; k++) {
            for (int i = 0; i < 7; i++) {
                if ((detected[k][0] == h[i][0]) && (detected[k][1] == h[i][1]) && (detected[k][2] == h[i][2])) {
                    flag = true;
                    index = i;
                }
                if (flag) {
                    if (text[k][index] == 1) {
                        text[k][index] = 0;
                    } else {
                        text[k][index] = 1;
                    }
                }
                flag = false;
            }
            scores = removeRedundancyBits(text, len);
        }
        return scores;
    }

    private String arrayToString(int[][] array, int row, int col) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                stringBuilder.append(array[i][j]);
            }
        }
        return stringBuilder.toString();
    }

    private SimpleMatrix getSimpleMatrix(boolean isG, int row, int col) {
        SimpleMatrix matrix = new SimpleMatrix(row, col);
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (isG) {
                    matrix.set(i, j, g[i][j]);
                } else {
                    matrix.set(i, j, h[i][j]);
                }
            }
        }
        return matrix;
    }

    private int[][] noise(int[][] array, int row, int col, int len) {
        int[][] tmp = new int[len / 4][7];

        for (int i = 0; i < len / 4; i++) {
            for (int j = 0; j < 7; j++) {
                if (i == row && j == col) {
                    if (array[i][j] == 0) {
                        tmp[i][j] = 1;
                    } else {
                        tmp[i][j] = 0;
                    }
                } else {
                    tmp[i][j] = array[i][j];
                }
            }

        }
        return tmp;
    }
}
