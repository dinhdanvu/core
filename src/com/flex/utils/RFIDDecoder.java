package com.flex.utils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * RFIDDecoder to decode RFID to trailer imei.
 * Created by Tran Gia Minh on 6/30/2017.
 */
public class RFIDDecoder {
    /**
     * Map: character to code (byte)
     */
    private static final Map<Character, Byte> MAP_BASE_64 = new HashMap<Character, Byte>();

    static {
        String BASE64 = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz!/";
        int len = BASE64.length();
        for (int i = 0; i < len; i++) {
            MAP_BASE_64.put(BASE64.charAt(i), new Byte((byte) i));
        }
    }

    /**
     * Convert string input to base64 code array.
     *
     * @param input
     * @return base64 code array.
     */
    private static byte[] convertInputToBase64Code(String input) {
        int length = input.length();
        byte[] result = new byte[length];
        for (int i = 0; i < length; i++) {
            result[i] = MAP_BASE_64.get(input.charAt(i));
        }
        return result;
    }

    /**
     * Convert 4-byte array to 3-byte array
     *
     * @param input
     * @return 3-byte array
     */
    private static byte[] convert4BytesTo3Bytes(byte[] input) {
        int nGroup = input.length / 4;
        byte[] result = new byte[nGroup * 3];

        for (int idxGroup = 0; idxGroup < nGroup; idxGroup++) {
//            byte[] arrayTemp = new byte[4];
//            // copy to arrayTemp
//            for (int col = 0; col < arrayTemp.length; col++) {
//                int idxInput = idxGroup * 4 + col;
//                if (idxInput < input.length) {
//                    arrayTemp[col] = input[idxInput];
//                }
//            }
//
//            // remove 7th & 6th bit and merge to 3 bytes
//            int b0 = ((arrayTemp[0] & 0b00111111) << 2) | ((arrayTemp[1] & 0b00110000) >> 4);
//            int b1 = ((arrayTemp[1] & 0b00001111) << 4) | ((arrayTemp[2] & 0b00111100) >> 2);
//            int b2 = ((arrayTemp[2] & 0b00000011) << 6) | (arrayTemp[3] & 0b00111111);


            int idxInput = idxGroup * 4;
            // remove 7th & 6th bit and merge to 3 bytes
            int b0 = ((input[idxInput + 0] & 0b00111111) << 2) | ((input[idxInput + 1] & 0b00110000) >> 4);
            int b1 = ((input[idxInput + 1] & 0b00001111) << 4) | ((input[idxInput + 2] & 0b00111100) >> 2);
            int b2 = ((input[idxInput + 2] & 0b00000011) << 6) | (input[idxInput + 3] & 0b00111111);

            result[idxGroup * 3] = (byte) b0;
            result[idxGroup * 3 + 1] = (byte) b1;
            result[idxGroup * 3 + 2] = (byte) b2;
        }
        return result;
    }

    /**
     * Decode input string to int value.
     *
     * @param input
     * @return int
     */
    public static String decode(String input) {
        if (input == null || input.isEmpty()) {
            return null;
        }

        try {
            // Tra các mã đồ họa, lấy dữ liệu từ bảng Base64.
            byte[] base64Code = convertInputToBase64Code(input.replace("\"", ""));
            // bỏ 2 bit đầu, biến mảng 4 byte thành mảng 3 byte.
            byte[] bytesData = convert4BytesTo3Bytes(base64Code);
            String prefix =Lib.hexBytes2String(new String(bytesData, 1, 3).getBytes());
            // bỏ 4 byte đầu, lấy 7 byte sau.
            return prefix + Integer.parseInt(new String(bytesData, 4, 7), 16);
        } catch (Exception ex) {
            return null;
        }
    }

    public static void main(String[] args) {
        int n = 10;
        System.out.println(RFIDDecoder.decode(("0ZP4C4L3DM4vHKOD2WC")));
        Date start = new Date();
        for (int i = 0; i < n; i++) {
            RFIDDecoder.decode("0ZP4C4L3DZ4vHKOD2WC");
        }
        System.out.println((new Date().getTime() - start.getTime() + " ms"));
    }
}