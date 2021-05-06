package com.example.navtelsmartbt.ntcb_java.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import com.example.navtelsmartbt.ntcb_java.packet.MessageJava;
import com.example.navtelsmartbt.ntcb_java.packet.ResponsesJava;
import com.example.navtelsmartbt.ntcb_java.telemetry.Parameter;
import com.example.navtelsmartbt.ntcb_java.telemetry.TelemetryParser;
import java.util.List;
import java.util.UUID;

//этим классом будем читать
public class BlueToothIO implements  BTSocketReader.BlueToothReaderListener,
        InputStreamParser.InputStreamParserListener {
    private BTSocketReader reader = null;
    private BluetoothSocket socket = null;
    private InputStreamParser inputParser=new InputStreamParser(this);
    private BlueToothReaderListener listener = null;
    private BTSocketWriter writer = null;

    //интерфейс листенера состояний
    public interface BlueToothReaderListener {
        void onTelemetryReceived(List<Parameter> listParameters);
        void onIMEIReceived(String imei);
        void onModelReceived(String model);
    }

    public BlueToothIO(String device, BlueToothReaderListener listener) throws Exception {
        this.listener = listener;
        socket = initSocket(device);
        if (socket != null) {
            reader = new BTSocketReader(socket, this);
            writer = new BTSocketWriter(socket);
        } else
            throw new Exception("Bluetooth error!");
        reader.start();
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
    public void onReaderInitialized(BTSocketReader reader) {
        writer.start();
    }

    @Override
    public void onReadBytes(byte[] data, int leng) {
        inputParser.setNewData(data, leng);
    }

    @Override
    public void onReaderStopped(BTSocketReader reader) {
        writer.setStop();
    }

    public void stop(){
        writer.setStop();
        reader.setStop();
        try {
            socket.close();
        }
        catch (Exception ex){}
    }

    @Override
    public void onMessageReceived(MessageJava message) {
        if (message.getCmd().getName().equals(ResponsesJava.TELEMETRY_ALL.getName())){
            List<Parameter> parameterList= TelemetryParser.parseData(message);
            if(parameterList.size()>0)
                listener.onTelemetryReceived(parameterList);
        }
        if (message.getCmd().getName().equals(ResponsesJava.IMEI.getName())){
            listener.onIMEIReceived(new String(message.getData()));
        }
        if (message.getCmd().getName().equals(ResponsesJava.MODEL_VERSION.getName())){
            listener.onModelReceived(new String(message.getData()));
        }
    }
}
