package com.example.navtelsmartbt.ntcb_java.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.util.Log;

import androidx.annotation.NonNull;

import java.net.Socket;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ThreadPoolExecutor;

//этим классом будем читать
public class BlueToothReader implements  BTSocketReader.BlueToothReaderListener{
    private String device = ""; //устройство для работы
    private BTSocketReader reader = null;
    private BluetoothSocket socket = null;

    public BlueToothReader(String device) throws Exception {
        this.device = device;
        socket = initSocket(device);
        if (socket!=null){
            reader=new BTSocketReader(socket, this);
        }
        else
            throw new Exception("Bluetooth error!");
    }

    private BluetoothSocket initSocket(String strDevice) {
        BluetoothSocket socket = null;
        try {
            BluetoothAdapter btAdapter = BluetoothAdapter.getDefaultAdapter();
            BluetoothDevice device = btAdapter.getRemoteDevice(strDevice);
            UUID uuid = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
            btAdapter.cancelDiscovery();
            socket = device.createInsecureRfcommSocketToServiceRecord(uuid);
            socket.connect();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                socket.close();
            } catch (Exception ex) {
            }
            socket = null;
        }
        return socket;
    }

    @Override
    public void onReadBytes(byte[] data, int leng) {

    }

    @Override
    public void onReaderStopped(BTSocketReader reader) {

    }

    public void stop(){
        reader.setStop();
        try {
            socket.close();
        }
        catch (Exception ex){}
    }
}
