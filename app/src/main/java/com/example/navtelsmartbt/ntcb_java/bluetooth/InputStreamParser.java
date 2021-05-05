package com.example.navtelsmartbt.ntcb_java.bluetooth;

import androidx.annotation.NonNull;

import com.example.navtelsmartbt.ntcb_java.packet.MessageJava;
import com.example.navtelsmartbt.ntcb_java.utils.ByteUtils;

import java.io.ByteArrayOutputStream;

public class InputStreamParser {
    private InputStreamParserListener listener = null;
    private final ByteArrayOutputStream dataCache = new ByteArrayOutputStream();//сюда будем складывать пришедшие данные
    private int messageStart = -1; //индекс в данных, где начало посылки
    private int messageEnd = -1; //индекс в данных, где конец посылки
    private final byte[] START_SIGNATURE = MessageJava.SERVER_TO_DEVICE; //сигнатура начала посылки, т.к. от версии прошивки
    private final byte[] START_SIGNATURE_ALT = MessageJava.DEVICE_TO_SERVER;//зависит, то еще есть альтернативный вариант
    private final int MIN_LENG_FOR_START = START_SIGNATURE.length + 7;//начало + 2 байта длина данных + 21 байта на CRC + 3 байта на команду

    interface InputStreamParserListener {
        void onMessageReceived(MessageJava message);
    }

    public InputStreamParser(@NonNull InputStreamParserListener listener) {
        this.listener = listener;
    }

    public void setNewData(byte[] data, int leng) {
        dataCache.write(data, 0, leng);
        parseDataArray(dataCache.toByteArray());
    }

    private int findOneOfSignatures(byte[] signature, byte[] signatureAlt, byte[] array) {
        return findOneOfSignatures(signature, signatureAlt, array, 0);
    }

    private int findOneOfSignatures(byte[] signature, byte[] signatureAlt,
                                    byte[] array, int startFrom) {
        int res = ByteUtils.findFirst(array, signature, startFrom);
        if (res >= 0)
            return res;
        //не нашли, поищем альтернативный вариант
        return ByteUtils.findFirst(array, signatureAlt, startFrom);
    }

    private void findStart(byte[] array) {
        if (messageStart == -1 && dataCache.size() >= MIN_LENG_FOR_START) { //ищем начало посылки
            messageStart = findOneOfSignatures(START_SIGNATURE, START_SIGNATURE_ALT, array);
        }
    }

    private void findEnd(byte[] array) {
        if (messageStart >= 0 && messageEnd == -1 && dataCache.size() >= MIN_LENG_FOR_START) { //ищем начало посылки
            messageEnd = findOneOfSignatures(START_SIGNATURE, START_SIGNATURE_ALT, array,
                    messageStart + 1);
        }
    }

    private void parseDataArray(byte[] array) {
        findStart(array);
        findEnd(array);
        if (messageStart != -1) { //нашли в потоке начало, попробуем сформировать посылку
            if (messageEnd != -1) {//в потоке есть и конец - формируем по старому
                byte[] messageArray = new byte[messageEnd - messageStart];
                System.arraycopy(array, messageStart, messageArray, 0, messageArray.length);
                MessageJava message = MessageJava.parseMessage(messageArray);
                if (message != null)
                    listener.onMessageReceived(message);
                try {//остатки не выбрасываем, формируем новый датакэш
                    dataCache.reset();
                    dataCache.write(array, messageEnd + 1, array.length - messageEnd - 1);
                    messageStart = -1;
                    messageEnd = -1;
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                parseDataArray(dataCache.toByteArray()); //в остатке потока мог спрятаться еще один пакет данных, парсим его сразу
            } else {//иначе-попробуем весь остаток байтов скормить парсеру пакета
                byte[] messageArray = new byte[messageEnd - messageStart];
                System.arraycopy(array, messageStart, messageArray, 0, messageArray.length);
                MessageJava message = MessageJava.parseMessage(messageArray);
                if (message != null) {
                    listener.onMessageReceived(message);
                    dataCache.reset();
                    messageStart = -1;
                    messageEnd = -1;
                }
            }
        }
    }
}

