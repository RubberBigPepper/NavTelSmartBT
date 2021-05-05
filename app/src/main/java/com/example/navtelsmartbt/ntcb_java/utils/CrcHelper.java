package com.example.navtelsmartbt.ntcb_java.utils;

//работа с CRC
public class CrcHelper {

    public static byte calcCRC8bit(byte[] data) {
        if (data == null || data.length == 0)
            return 0;
        byte x = data[0];
        for (int n = 1; n < data.length; n++)
            x ^= data[n];
        return x;
    }

    public static byte calcCRC8bit(byte[] data, int startFrom, int toIndex) {
        if (data == null || startFrom >= data.length || data.length == 0 ||
                startFrom > toIndex)
            return 0;
        byte x = data[startFrom];
        int end = Math.min(toIndex, data.length - 1);
        for (int n = startFrom + 1; n <= end; n++)
            x ^= data[n];
        return x;
    }
}