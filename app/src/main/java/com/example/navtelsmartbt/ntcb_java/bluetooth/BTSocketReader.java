package com.example.navtelsmartbt.ntcb_java.bluetooth;

import android.bluetooth.BluetoothGattService;
import android.bluetooth.BluetoothSocket;
import android.util.Log;

import androidx.annotation.NonNull;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class BTSocketReader extends Thread {
    private volatile boolean isStop = false;
    private Object mutex = null;
    private InputStream inputStream = null;
    private static final String TAG = "BlueToothReader";
    private BlueToothReaderListener listener = null;
    private final long READ_DELAY = 100;

    public interface BlueToothReaderListener {
        void onReadBytes(byte[] data, int leng);

        void onReaderStopped(BTSocketReader reader);
    }

    ;

    public BTSocketReader(BluetoothSocket socket, @NonNull BlueToothReaderListener listener) throws IOException {
        inputStream = socket.getInputStream();
        this.listener = listener;
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
        byte[] bufferRead = new byte[1024];//буфер чтения
        while (!isStop) {
            try {
                int leng = inputStream.read(bufferRead, 0, bufferRead.length);
                if (leng > 0) {//что то считали
                    listener.onReadBytes(bufferRead, leng);
                }
            } catch (Exception ex) {
            }
            synchronized (mutex) {
                if (isStop)//сигнал завершения
                    break;
                try {
                    mutex.wait(READ_DELAY);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
        listener.onReaderStopped(this);
    }
}
