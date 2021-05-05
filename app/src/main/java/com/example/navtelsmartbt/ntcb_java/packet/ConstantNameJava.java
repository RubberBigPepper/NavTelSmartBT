package com.example.navtelsmartbt.ntcb_java.packet;

import com.example.navtelsmartbt.ntcb_java.utils.ByteUtils;

import java.nio.charset.Charset;
import java.util.Arrays;

public class ConstantNameJava {

    private String name = "";
    private byte[] signature = null;

    public ConstantNameJava(String name) {
        this.name = name;
        this.signature = name.getBytes(ByteUtils.CHARSET);
    }

    public boolean equals(Object other) {
        if (other instanceof ConstantNameJava)
            return Arrays.equals(this. signature, ((ConstantNameJava) other).signature);
        return false;
    }

    public int hashCode() {
        return name.hashCode();
    }

    public int getSize() {
        if (signature == null)
            return 0;
        return signature.length;
    }

    public byte[] getSignature() {
        return signature;
    }

    public String getName() {
        return name;
    }

}