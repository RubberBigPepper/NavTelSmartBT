package com.example.navtelsmartbt.ntcb_java.bluetooth;

import android.bluetooth.BluetoothSocket;
import android.graphics.Color;

import androidx.annotation.NonNull;

import com.example.navtelsmartbt.ntcb_java.packet.ConstantNameJava;
import com.example.navtelsmartbt.ntcb_java.packet.MessageJava;
import com.example.navtelsmartbt.ntcb_java.packet.RequestsJava;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

//этот класс будет просто писать в сокет для чтения телеметрии
public class BTSocketWriter extends Thread {
    private volatile boolean isStop = false;
    private Object mutex = null;
    private OutputStream outputStream = null;
    private static final String TAG = "BlueToothWriter";
    private final long READ_DELAY = 500;

    public BTSocketWriter(BluetoothSocket socket) throws IOException {
        outputStream = socket.getOutputStream();
    }

    //намерение на прекращение потока
    public void setStop() {
        if (mutex != null) {
            synchronized (mutex) {
                isStop = true;
                try {
                    mutex.notify();
                } catch (Exception ex) {
                }
            }
        } else
            isStop = true;
    }

    @Override
    public void run() {
        mutex = new Object();
        byte[] message= MessageJava.buildMessageToDevice(RequestsJava.MODEL_VERSION);
        if(message!=null) {
            try {
                outputStream.write(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        synchronized (mutex) {
            if (isStop)//сигнал завершения
                return;
            try {
                mutex.wait(READ_DELAY);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        message= MessageJava.buildMessageToDevice(RequestsJava.IMEI);
        if(message!=null) {
            try {
                outputStream.write(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        while (!isStop) {
            synchronized (mutex) {
                if (isStop)//сигнал завершения
                    break;
                try {
                    mutex.wait(READ_DELAY);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            message= MessageJava.buildMessageToDevice(RequestsJava.TELEMETRY_ALL);
            if(message!=null) {
                try {
                    outputStream.write(message);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
