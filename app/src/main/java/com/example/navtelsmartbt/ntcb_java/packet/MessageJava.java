package com.example.navtelsmartbt.ntcb_java.packet;

import com.example.navtelsmartbt.ntcb_java.utils.ByteUtils;
import com.example.navtelsmartbt.ntcb_java.utils.CrcHelper;

import java.io.ByteArrayOutputStream;

public class MessageJava {

    private ConstantNameJava cmd;
    private byte[] data;
    private long timeStamp = System.currentTimeMillis(); //дата создания мессаги

    static byte[] PREAMBLE = "@NTC".getBytes(ByteUtils.CHARSET);//строковое представление преамбулы @NTC
    static byte[] IDr = new byte[] {0x0, 0x0, 0x0, 0x0};           //адрес получателя (четыре ноля, если получатель - устройство)
    static byte[] IDs = new byte[] {0x01, 0x0, 0x0, 0x0};          //адрес отправителя (единица и три ноля, если отправитель - андроид, комп, и т.д., то есть мастер)
    static byte[] SERVER_TO_DEVICE = ByteUtils.concatenateByteArrays(PREAMBLE, IDr, IDs);                //заголовок для отправки устройству
    static byte[] DEVICE_TO_SERVER = ByteUtils.concatenateByteArrays(PREAMBLE, IDs, IDr);                //заголовок для отправки серверу (андроиду)

    public MessageJava(ConstantNameJava cmd, byte[] data) {
        this.cmd = cmd;
        this.data = data;
    }

    //формирование посылки для отправки устройству
    static byte[] buildMessageToDevice(ConstantNameJava request) {
        return buildMessageToDevice(request, "");
    }

    //формирование посылки для отправки устройству
    static byte[] buildMessageToDevice(ConstantNameJava request, String additionalText) {
        byte[] data = null;
        if (request.equals(RequestsJava.IMEI) || request.equals(RequestsJava.TELEMETRY_ALL) ||
                request.equals(RequestsJava.MODEL_VERSION)) {//запросы без тела, игнорируем additionalText
            data = request.getSignature();
        } else//иначе - присобачиваем
            data = ByteUtils.concatenateByteArrays(request.getSignature(), additionalText.getBytes(ByteUtils.CHARSET));
        //заголовок посылки состоит из: неизменяемой части (преамбула + направление) + 2 байта длина данных + CRC данных + CRC заголовка
        //CRC данных вычисляется по данным, затем полученное записывается в заголовок и вычисляется CRC всего заголовка
        byte dataCrc = CrcHelper.calcCRC8bit(data);//для данных нужен отдельный CRC
        //составляем бОльшую часть заголовка, возможно, лучше переделать на коллекции
        ByteArrayOutputStream res = new ByteArrayOutputStream();
        try {
            res.write(SERVER_TO_DEVICE);
            res.write(ByteUtils.short2ByteArray((short) data.length));
            res.write(dataCrc);
            //добавляем CRC полученного заголовка
            res.write(CrcHelper.calcCRC8bit(res.toByteArray()));
            //добавляем данные
            res.write(data);
            return res.toByteArray();
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static MessageJava parseMessage(byte[] array) {
        byte[] findPrehead = DEVICE_TO_SERVER;
        int starts = ByteUtils.findFirst(array, findPrehead);
        if (starts == -1) {//попробуем парсинг от сервера к устройству
            findPrehead = SERVER_TO_DEVICE;
            starts = ByteUtils.findFirst(array, findPrehead);
        }
        if (starts >= 0) {//нашли начало посылки. парсим
            try {
                int curIndex = starts + findPrehead.length;
                int leng = ((array[curIndex] & 0xFF) | ((array[++curIndex] & 0xff) << 8));//длина данных
                byte crcData = array[++curIndex];//ипользовать для контроля правильности посылки
                byte crcRealHead = CrcHelper.calcCRC8bit(array, 0, curIndex);
                byte crcHead = array[++curIndex];//нам пока CRC не нужны, но можно попробовать их
                if (crcRealHead != crcHead)
                    return null;
                curIndex++;
                int endIndex = curIndex + leng;
                MessageJava message = null;
                for (ConstantNameJava cmd : ResponsesJava.asArray()) {
                    if (ByteUtils.findFirst(array, cmd.getSignature(), curIndex) == curIndex) {
                        int startIndex = curIndex + cmd.getSize();
                        byte[] data = new byte[endIndex - startIndex];
                        System.arraycopy(array, startIndex, data, 0, data.length);
                        message = new MessageJava(cmd, data);
                        break;
                    }
                }
                if (message == null) {
                    //Repsonses не удалось найти - видимо, это Requests - пробуем найти их
                    for (ConstantNameJava cmd : RequestsJava.asArray()) {
                        if (ByteUtils.findFirst(array, cmd.getSignature(), curIndex) == curIndex) {
                            int startIndex = curIndex + cmd.getSize();
                            byte[] data = new byte[endIndex - startIndex];
                            System.arraycopy(array, startIndex, data, 0, data.length);
                            message = new MessageJava(cmd, data);
                            break;
                        }
                    }
                }
                if (message != null) {
                    byte crcRealData = CrcHelper.calcCRC8bit(array, curIndex, endIndex);
                    if (crcData != crcRealData)
                        return null;
                }
                return message;
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }

    public ConstantNameJava getCmd() {
        return cmd;
    }

    public byte[] getData() {
        return data;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

}