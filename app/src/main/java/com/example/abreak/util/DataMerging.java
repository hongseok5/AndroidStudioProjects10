package com.example.abreak.util;

/**
 * Created by break on 18. 4. 27.
 */

public class DataMerging {

    private byte[] mergedData;
    private final int HEADER_SIZE = 65;
    private int dataSize;

    public DataMerging(DataHeader dataHeader, DataBody dataBody) {

        dataSize = dataBody.getDataSize();
        dataSize += HEADER_SIZE;
        mergedData = new byte[dataSize];
        System.out.println("DataMerging : " + mergedData.length );
        System.out.println("DataMerging : " + dataBody.getDataSize() );
        int index = 0;

        for(int i = 0; i < HEADER_SIZE; i++) {
            mergedData[index++] = dataHeader.getDataHeader()[i];
        }

        for(int i = 0; i < dataBody.getDataSize() ; i++){
            mergedData[index++] = dataBody.getDataBody()[i];
        }

    }

    public byte[] getMergedData() {
        return this.mergedData;
    }
}
