package com.example.navtelsmartbt.ntcb_java.telemetry;

//описание одного параметра протокола FLEX
public class ParameterDescriptor {
    private int index;
    private String name;
    private int lengBytes;

    public ParameterDescriptor(int index, String name, int lengBytes) {
        this.index = index;
        this.name = name;
        this.lengBytes = lengBytes;
    }

    public int getIndex() {
        return index;
    }

    public String getName() {
        return name;
    }

    public int getLengBytes() {
        return lengBytes;
    }
}
