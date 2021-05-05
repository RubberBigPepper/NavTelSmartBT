package com.example.navtelsmartbt.ntcb_java.telemetry;

import com.example.navtelsmartbt.ntcb_java.packet.MessageJava;
import com.example.navtelsmartbt.ntcb_java.packet.ResponsesJava;
import com.example.navtelsmartbt.ntcb_java.telemetry.DescriptorsList;
import com.example.navtelsmartbt.ntcb_java.telemetry.Parameter;
import com.example.navtelsmartbt.ntcb_java.telemetry.ParameterDescriptor;

import java.util.ArrayList;
import java.util.List;

//будет парсить данные телеметрии (если в CMD пришел Responses.TELEMETRY_ALL)
public class TelemetryParser {
    private static byte TYPE_VALUE = 0xB; //тип данных - основная телеметрическая запись
    private static int TYPE_LENG = 1; //длина поля тип данных
    private static byte FLEX_VERSION_10 = 0xa; //версия FLEX 1.0
    private static byte FLEX_VERSION_20 = 0x14; //версия FLEX 2.0
    private static byte FLEX_VERSION_30 = 0x1e; //версия FLEX 3.0
    private static int VERSION_LENG = 1; //длина поля версии FLEX
    private static int BITFIELD_LENG = 32; //длина битового поля
    private static int FLEX_HEADER_LENG = TYPE_LENG + VERSION_LENG + BITFIELD_LENG;
    //возможно, стоит прописать и константы ошибок разбора?

    //парсинг битового поля - карты параметров, которые представлены в посылке
    private static List<ParameterDescriptor> parseBitFieldData(byte[] data, int startFrom) {
        List<ParameterDescriptor> res = new ArrayList<>();
        if (data.length < (startFrom + BITFIELD_LENG))//маловат размер пришедшего массива
            return res;
        int index = 0;//индекс параметра из ParameterList
        byte testFlag = -128;//0b10000000 - установленный старший бит
        int endIndex = startFrom + BITFIELD_LENG;
        for (int byteIndex = startFrom; byteIndex < endIndex; byteIndex++) {
            byte temp = data[byteIndex];
            for (int n = 0; n < 8; n++) {//смотрим побитово, какие биты установлены, причем порядок у навтелекома перепутан - 8й бит соотвествует первому параметру!
                if (index >= DescriptorsList.flexList.length)//проблема в том, что битовое поле теоретически может иметь 256 параметров, а у нас их только 255
                    break;
                if ((temp & testFlag) != 0) {//старший бит установлен, добавляем соответствующий дескриптор параметра из таблицы - параметр есть в посылке
                    res.add(DescriptorsList.flexList[index]);
                }
                index++;
                temp = (byte) (temp << 1);
            }
        }
        return res;
    }

    private static int findBitFieldStart(MessageJava message){
        byte[] data = message.getData();
        if (data.length < FLEX_HEADER_LENG)
            return -1; //длины записей не хватает для работы - ошибка чтения
        int index = 0;
        byte type = data[index++]; //пока не используем, но для контроля правильности чтения
        byte version = data[index++];//может пригодиться
        return index;
    }

    private static int getAllBytesLeng(List<ParameterDescriptor> parameterDescriptors) {
        int res = 0;
        for (ParameterDescriptor param : parameterDescriptors) {
            res += param.getLengBytes();
        }
        return res;
    }

    public static List<Parameter> parseData(MessageJava message){
        if (!message.getCmd().equals(ResponsesJava.TELEMETRY_ALL)) //посылка неправильного типа
            return null;
        int bitFieldStart = findBitFieldStart(message);
        if(bitFieldStart<0)
            return null;//не нашли битовое поле
        List<ParameterDescriptor> listParamDescr = parseBitFieldData(message.getData(), bitFieldStart);
        int allLengParams = getAllBytesLeng(listParamDescr);//сколько займут все параметры в байтах
        if (message.getData().length < (allLengParams + FLEX_HEADER_LENG))//прочитанных байт меньше, чем нужно для чтения параметров - ошибка чтения
            return null;
        List<Parameter> res = new ArrayList<>();
        int startFrom = FLEX_HEADER_LENG;//с какого индекса в массиве данных начинаются непосредственно данные параметра
        for (ParameterDescriptor descriptor : listParamDescr) {//берем параметр из карты параметров и читаем их по одному
            int endIndex = startFrom + descriptor.getLengBytes();
            byte[] data = new byte[descriptor.getLengBytes()];
            System.arraycopy(message.getData(), startFrom, data, 0, data.length);
            res.add(new Parameter(descriptor, data));
            startFrom = endIndex;
        }
        return res;
    }
}