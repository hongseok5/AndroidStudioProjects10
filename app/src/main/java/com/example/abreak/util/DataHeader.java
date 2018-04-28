package com.example.abreak.util;

import java.util.List;

public class DataHeader {
    //헤더는 총 64바이트
    private final  byte STX = 0x02;
    private final  byte DATA_HD_SIZE = 65;
    private final static byte NVL = 0x00;
    private byte[] dataHeader = new byte[DATA_HD_SIZE];
    private int arrayIndex = 0;
    //private final byte[] DATA_TYPE = new byte[] {68, 73, 76, 83, 84, 85};

    public DataHeader(List<String> params, byte dataType) {

        System.out.println("DataHeader : " + params.size());
        int paramsSize = params.size() * 32;
        System.out.println("DataHeader : " + paramsSize);

        for(String param:params) {
            if(param.getBytes().length > 32) {
                System.out.println("DataHeader : over 32 bytes");
            }
        }

        this.dataHeader[arrayIndex++] = STX;
        setDataSize(paramsSize);
        this.dataHeader[arrayIndex++] = dataType;
        for(int i = arrayIndex; i < DATA_HD_SIZE; i++) {
            this.dataHeader[i] = NVL;
        }

        System.out.print("DataHeader : constructor 2 created obj : ");
        for(int i = 0; i < DATA_HD_SIZE; i++) {
            System.out.print(this.dataHeader[i]);
        }
    }

    public void setDataSize(int paramNumber) {
        byte[] byteArray = new byte[8];
        byteArray[0] = (byte) (paramNumber / 10000000);
        paramNumber -= byteArray[0] * 10000000;
        byteArray[1] = (byte) (paramNumber / 1000000);
        paramNumber -= byteArray[1] * 1000000;
        byteArray[2] = (byte) (paramNumber / 100000);
        paramNumber -= byteArray[2] * 100000;
        byteArray[3] = (byte) (paramNumber / 10000);
        paramNumber -= byteArray[3] * 10000;
        byteArray[4] = (byte) (paramNumber / 1000);
        paramNumber -= byteArray[4] * 1000;
        byteArray[5] = (byte) (paramNumber / 100);
        paramNumber -= byteArray[5] * 100;
        byteArray[6] = (byte) (paramNumber / 10);
        paramNumber -= byteArray[6] * 10;
        byteArray[7] = (byte) paramNumber;
        for(int i = 0 ; i < byteArray.length; i++) {
            dataHeader[arrayIndex++] = byteArray[i];
        }
    }

    public byte[] getDataHeader() {
        return dataHeader;
    }
}