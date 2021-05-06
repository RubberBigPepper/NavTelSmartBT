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
    private final long WRITE_DELAY = 100;

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

    private boolean waitAndContinue(){
        synchronized (mutex) {
            if (isStop)//сигнал завершения
                return false;
            try {
                mutex.wait(WRITE_DELAY);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return true;
    }

    private void sendMessage(ConstantNameJava message){
        byte[] packet= MessageJava.buildMessageToDevice(message);
        if(message!=null) {
            try {
                outputStream.write(packet);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void run() {
        mutex = new Object();
        sendMessage(RequestsJava.MODEL_VERSION);
        if (!waitAndContinue())
            return;
        sendMessage(RequestsJava.IMEI);
        while (waitAndContinue()) {
            sendMessage(RequestsJava.TELEMETRY_ALL);
        }
    }
}
