package com.example.abreak.util;

/**
 * Created by break on 18. 4. 7.
 */
public class DataHeader {
    //헤더는 총 64바이트
    private final  byte STX = 0x02;
    private final static  byte ETX = 0x03;
    private final  byte DATA_HD_SIZE = 65;
    private final  byte DATA_HD_LENGTH = 8;
    private final  byte DATA_HD_TYPE = 1;
    static final byte[] LOGIN_DATA_SIZE = new byte[] {0,0,0,0, 0,0,0,64};
    static final byte[] TEXT_DATA_SIZE  = new byte[] {127, 127, 127, 127, 127, 127, 127, 127};
    //private final byte[] INFO_DATA_SIZE;
    final static byte NVL = 0x00;
    private int arrayIndex;
    private byte[] dataHeader = new byte[DATA_HD_SIZE];

    public DataHeader(byte[] length, byte dataType){
        arrayIndex=0;
        this.dataHeader[arrayIndex++] = STX;

        System.out.println(length[7]);
        for(int i=0; i < DATA_HD_LENGTH; i++) {
            this.dataHeader[arrayIndex++] = length[i];

        }
        System.out.println(dataHeader[8]);
        if(dataType != 77 && dataType != 66){
            System.out.println("유효한 데이터가 아님 ");
        }
        if(arrayIndex == 9) {
            this.dataHeader[arrayIndex++] = dataType;

        }
    	/*
       for(int i =0; i < DATA_HD_SIZE - 8 - 1 -1; i++){
            this.dataHeader[arrayIndex++] = NVL;
        }*/

        for(int i=arrayIndex;i<DATA_HD_SIZE;i++){
            this.dataHeader[i] = NVL;
        }

        //55byte
        for(int i =0; i < DATA_HD_SIZE; i++){
            System.out.print(this.dataHeader[i]);
        }
        System.out.println("데이터 헤더의 성공적 생성");
    } // 생성자

    public byte[] getDataHeader() {
        return dataHeader;
    }

    public byte getSTX() {
        return STX;
    }

    public static byte getETX() {
        return ETX;
    }
}
