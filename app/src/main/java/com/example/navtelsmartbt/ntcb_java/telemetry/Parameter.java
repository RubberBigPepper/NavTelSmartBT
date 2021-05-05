package com.example.navtelsmartbt.ntcb_java.telemetry;

//параметр с данными телеметрической информации
public class Parameter{
    private ParameterDescriptor descriptor;
    private byte[] data;

    public Parameter(ParameterDescriptor descriptor, byte[] data) {
        this.descriptor = descriptor;
        this.data = data;
    }

    public ParameterDescriptor getDescriptor() {
        return descriptor;
    }

    public byte[] getData() {
        return data;
    }


    /*val asUByte: UByte? = when (data.size) {
        1 -> data[0].toUByte()
        else -> null
    }

    val asUShort: UShort? = when (data.size) {
        2 -> (data[0].toUByte() + 256u * data[1].toUByte()).toUShort()
        else -> null
    }

    val asUInt: UInt? = when (data.size) {
        4 -> data[0].toUByte() + 256u * data[1].toUByte() + 65536u * data[2].toUByte() +
                65536u * 256u * data[3].toUByte()
        else -> null
    }

    val asULong: ULong? = when (data.size) {
        //8 -> data.foldRight(0) { sum, element -> (sum*256uL).toULong().plus(element)}.toULong() // на агрегаторе почему то ругался - хочет Int вместо ULong
        8 -> data[0].toUByte() + 256uL * data[1].toUByte() + 65536uL * data[2].toUByte() +
                65536uL * 256uL * data[3].toUByte() + 65536uL * 65536uL * data[4].toUByte() +
                65536uL * 65536uL * 256uL * data[5].toUByte() +
                65536uL * 65536uL * 65536uL * data[6].toUByte() +
                65536uL * 65536uL * 65536uL * 256uL * data[7].toUByte()
        else -> null
    }

    val asText: String? = when (descriptor.lengBytes) {
        1 -> asUByte?.toString()
        2 -> asUShort?.toString()
        4 -> asUInt?.toString()
        8 -> asULong?.toString()
        else -> ByteUtils.Bytes2String(data)
    }*/
}
