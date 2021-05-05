package com.example.navtelsmartbt.ntcb_java.utils;

import java.nio.charset.Charset;

public class ByteUtils {
    public static Charset CHARSET = Charset.forName("ASCII");//чарсет для перекодирования байтов в строки и обратно

    public static byte[] short2ByteArray(short value) {
        return new byte[] {getLowestByte(value), getLowestByte(value>>8)};
    }

    //поиск последовательности байтов в массиве, начиная с начала
    public static int findFirst(byte[] arrayWhere, byte[] sequenceWhat) {
        return findFirst(arrayWhere, sequenceWhat, 0);
    }

    //поиск последовательности байтов в массиве, начиная с указанного
    public static int findFirst(byte[] arrayWhere, byte[] sequenceWhat, int startFrom) {
        if (arrayWhere==null || arrayWhere.length==0)
            throw new IllegalArgumentException("non-empty byte array is required");
        if (sequenceWhat==null || sequenceWhat.length==0)
            throw new IllegalArgumentException("non-empty byte sequence is required");
        if (startFrom < 0)
            throw new IllegalArgumentException("startFrom must be non-negative");
        int matchOffset = 0;
        int start = startFrom;
        int offset = startFrom;
        while (offset < arrayWhere.length) {
            if (arrayWhere[offset] == sequenceWhat[matchOffset]) {
                if (matchOffset++ == 0) start = offset;
                if (matchOffset == sequenceWhat.length) return start;
            } else
                matchOffset = 0;
            offset++;
        }
        return -1;
    }

    //берем только младшие 8 байт числа
    public static byte getLowestByte(int value) {
        return (byte) (value & 0xFF);
    }

    public static byte getLowestByte(short value) {
        return (byte) (value & 0xFF);
    }

    // эти функции тоже бы надо переделать как расширения для ByteArray
    public static String Bytes2String(byte[] byArData) {
        StringBuilder cBuilder = new StringBuilder();
        for (int n = 0; n < byArData.length; n++) {
            if (n > 0) cBuilder.append(", ");
            cBuilder.append(String.format("0x%02x", byArData[n]));
        }
        return cBuilder.toString();
    }

    public static byte[] concatenateByteArrays(byte[] a, byte[] b) {
        byte[] result = new byte[a.length + b.length];
        System.arraycopy(a, 0, result, 0, a.length);
        System.arraycopy(b, 0, result, a.length, b.length);
        return result;
    }

    public static byte[] concatenateByteArrays(byte[] a, byte[] b, byte[] c) {
        byte[] result = new byte[a.length + b.length + c.length];
        System.arraycopy(a, 0, result, 0, a.length);
        System.arraycopy(b, 0, result, a.length, b.length);
        System.arraycopy(c, 0, result, a.length + b.length, c.length);
        return result;
    }
}