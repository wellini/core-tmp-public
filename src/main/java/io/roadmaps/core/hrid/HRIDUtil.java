package io.roadmaps.core.hrid;

public class HRIDUtil {

    private static final char[] ALPHABET = {'1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'D', 'E', 'F', 'G', 'H', 'J', 'K', 'L', 'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
    private static final int SIZE_OF_CHARACTER_IN_ALPHABET = 5;
    private static final int COUNT_OF_BYTES = (int) Math.ceil(Long.SIZE / ((double) SIZE_OF_CHARACTER_IN_ALPHABET));
    private static final int SIZE_OF_SERIES = 3;
    private static final int COUNT_OF_SERIES = (int) Math.ceil(COUNT_OF_BYTES / ((double) SIZE_OF_SERIES));
    private static final long LAST_QUINTET_MASK = 0x0000001F;
    private static final char DIVIDER = '-';

    public static String serialize(long rawId) {
        byte[] byteArray = new byte[COUNT_OF_BYTES];

        for (int i = 0; i < COUNT_OF_BYTES; i++) {
            byteArray[i] = (byte) ((rawId >>> i * SIZE_OF_CHARACTER_IN_ALPHABET) & LAST_QUINTET_MASK);
        }

        byte[][] series = new byte[COUNT_OF_SERIES][SIZE_OF_SERIES];

        for (int i = 0; i < byteArray.length; i++) {
            series[i / SIZE_OF_SERIES][i % SIZE_OF_SERIES] = byteArray[i];
        }

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = COUNT_OF_SERIES - 1; i >= 0; i--) {

            boolean seriesAreEmpty = true;
            for (int j = SIZE_OF_SERIES - 1; j >= 0; j--) {
                seriesAreEmpty = seriesAreEmpty && series[i][j] == 0;
            }
            if(!seriesAreEmpty) {
                if (i != COUNT_OF_SERIES - 1) {
                    for (int j = SIZE_OF_SERIES - 1; j >= 0; j--) {
                        stringBuilder.append(ALPHABET[series[i][j]]);
                    }
                } else {
                    boolean leadingZerosAreSkipped = false;
                    for (int j = SIZE_OF_SERIES - 1; j >= 0; j--) {
                        leadingZerosAreSkipped = leadingZerosAreSkipped || series[i][j] != 0;
                        if (leadingZerosAreSkipped) {
                            stringBuilder.append(ALPHABET[series[i][j]]);
                        }
                    }
                }
            }
        }
        String rawResult = stringBuilder.reverse().toString();
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < rawResult.length(); i++) {
            result.append(rawResult.charAt(i));
            if((i + 1) % SIZE_OF_SERIES == 0 && i != rawResult.length() - 1) result.append('-');
        }

        return result.reverse().toString();
    }

    public static long deserialize(String hdid) {
        StringBuilder stringBuilder = new StringBuilder();
        for (char c : hdid.toCharArray()) {
            if (c != DIVIDER) stringBuilder.append(c);
        }
        StringBuilder reversedId = stringBuilder.reverse();
        char[] chars = reversedId.toString().toCharArray();
        long result = 0x00000000;
        for (int i = 0; i < chars.length; i++) {
            byte value = getIndexOf(chars[i]);
            result |= ((long) value) << i * SIZE_OF_CHARACTER_IN_ALPHABET;
        }

        return result;
    }

    private static byte getIndexOf(char c) {
        for (byte i = 0; i < ALPHABET.length; i++) {
            if (ALPHABET[i] == c) return i;
        }
        throw new UnresolvableHRIDException();
    }
}
