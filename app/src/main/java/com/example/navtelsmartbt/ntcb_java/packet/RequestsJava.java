package com.example.navtelsmartbt.ntcb_java.packet;

import com.example.navtelsmartbt.ntcb_java.packet.ConstantNameJava;

//команды для запросов в устройство
public class RequestsJava {
    public static final ConstantNameJava MODEL_VERSION = new ConstantNameJava("*?V");   //запрос модели и версии устройства
    public static final ConstantNameJava IMEI = new ConstantNameJava("*?S");              //запрос Уникального идентификатора устройства
    public static final ConstantNameJava ENTER_PASSWORD = new ConstantNameJava("*>PASS:");//ввод пароля
    public static final ConstantNameJava TELEMETRY_ALL = new ConstantNameJava("*?A");     //Запрос текущего состояния устройства (всей телеметрии)
    public static final ConstantNameJava TELEMETRY_INDEX = new ConstantNameJava("*?I");   //Запрос записи телеметрии по ее индексу

    public static ConstantNameJava[] asArray(){
        return new ConstantNameJava[] {
                MODEL_VERSION, IMEI, ENTER_PASSWORD, TELEMETRY_ALL, TELEMETRY_INDEX
        };
    }

}