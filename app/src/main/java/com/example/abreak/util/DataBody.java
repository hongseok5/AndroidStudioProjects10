package com.example.abreak.util;

import java.io.UnsupportedEncodingException;
import java.util.List;

public class DataBody {

        private final byte ETX = 0x03;
        private int dataSize = 0;
        protected byte[] dataBody;
        private final static byte PARAM_SIZE = 32;

        public DataBody(List<String> params,byte bodyType) throws UnsupportedEncodingException {
            //assume that all parameter size is below 32 Bytes except main stt data.

            this.dataSize = params.size() * 32 + 1;
            System.out.println("DataBody : " + dataSize);
            dataBody = new byte[dataSize];
            dataBody[dataSize - 1] = ETX;
            fillWithNVL(params);
        }

        public void fillWithNVL(List<String> params) throws UnsupportedEncodingException {
            // fill 32byte of id and pw
            int index = 0;
            //int paramNumbers = params.size();


            for (String param : params) {
                byte[] tempFilledData = param.getBytes("UTF-8");
                int gap = PARAM_SIZE - tempFilledData.length;
                for (int i = 0; i < tempFilledData.length; i++) {
                    dataBody[index++] = tempFilledData[i];
                }
                for (int i = 0; i < gap; i++) {
                    dataBody[index++] = 0x00;
                }
            }
        }

        public byte[] getDataBody() {
            return dataBody;
        }

        public int getDataSize() {
            return this.dataSize;
        }


}