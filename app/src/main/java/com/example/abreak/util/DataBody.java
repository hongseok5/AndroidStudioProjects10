package com.example.abreak.util;

public class DataBody {

    private final static byte SIZE = 32;
    private final byte BODY_SIZE = 65;
    /*
    public byte[] fillId(String id) {
        byte[] filledId = null;

        return filledId;
    }
    */
    public static byte[] fillPw(String pw) {

        System.out.println("파라미터는 : " + pw);
        byte[] tempFilledPw = null;
        tempFilledPw = pw.getBytes();
        // 위에서 사이즈를 정해주더라도 getByte 이후에는 사이즈가 받은 바이트 수 만큼으로 재정의 된다.
        if(tempFilledPw.length > SIZE) {
            System.out.println("data is too big");
            return null;
        }else if(tempFilledPw.length == SIZE) {
            System.out.println("데이터 사이즈가 같다");
            return tempFilledPw;
        }else{
            int gap = SIZE - tempFilledPw.length;
            byte[] filledPw = new byte[32];
            System.out.println("gap의 크기는 " + gap);

            for(int i=0; i < tempFilledPw.length; i++) {
                filledPw[i] = tempFilledPw[i];
                System.out.println(filledPw[i] + "  " + tempFilledPw[i]);
            }


            for(int i = 0; i < gap ; i++) {
                System.out.println(tempFilledPw.length + i );
                filledPw[tempFilledPw.length + i] = DataHeader.NVL;
//				System.out.println(filledPw[filledPw.length + i]);

            }
            System.out.println("성공적으로 채움");
            return filledPw;

        }
    }
}
