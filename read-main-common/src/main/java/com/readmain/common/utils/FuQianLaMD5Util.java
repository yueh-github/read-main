package com.readmain.common.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class FuQianLaMD5Util {
//    private static final String hexDigits[] = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};
//
//    /**
//     * 对字符串签名
//     *
//     * @param signStr
//     * @return
//     */
//    public static String md5Sign(String signStr) {
//
//        byte[] signInfo = null;
//        try {
//            MessageDigest md = MessageDigest.getInstance("MD5");
//            signInfo = md.digest(signStr.getBytes("UTF-8"));
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//        return byteArrayToHexString(signInfo);
//    }
//
//    private static String byteArrayToHexString(byte b[]) {
//        StringBuffer resultSb = new StringBuffer();
//        for (int i = 0; i < b.length; i++) {
//            resultSb.append(byteToHexString(b[i]));
//        }
//
//        return resultSb.toString();
//    }
//
//    private static String byteToHexString(byte b) {
//        int n = b;
//        if (n < 0) {
//            n += 256;
//        }
//        int d1 = n / 16;
//        int d2 = n % 16;
//        return hexDigits[d1] + hexDigits[d2];
//    }

    private static final String hexDigits[] = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d","e", "f" };

    /**
     * 对字符串签名
     *
     * @param signStr
     * @return
     */
    public static String md5Sign(String signStr) {

        byte[] signInfo = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            signInfo = md.digest(signStr.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return byteArrayToHexString(signInfo);

    }

    private static String byteArrayToHexString(byte b[]) {
        StringBuffer resultSb = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            resultSb.append(byteToHexString(b[i]));
        }

        return resultSb.toString();
    }

    private static String byteToHexString(byte b) {
        int n = b;
        if (n < 0) {
            n += 256;
        }
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }

    public static void main(String[] args) {
        System.out.println(md5Sign("accountFlag=01&accountName=任培伟&accountNo=6215590200002908432&accountType=0&acqId=01&amount=0.50&bankId=0102&bizId=831246359855105&bizTp=18&channelId=00000000003&city=110001&identNo=622223198805234111&identType=111&merchId=0080072&mobileNum=18600391314&remark=代付测试&paymentTp=04&pmtChnTp=THIRD&province=110000&sendTime=20170206200207&versionNo=1.04&hxvgzhdakdfo3y90gryp1rn3k89fzfj7jwy4xf60epuvn57u76hjd146pl2wunru"));
        // 9d665bcb6559b5f750bb259a66a6e15c
    }

}
