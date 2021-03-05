package conlon.cloud.common.utils;

import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;

/**
 * @program: conlon-cloud Signature.java
 * @description: ${description}
 * @author: Mr conlon
 * @create: 2021-03-05 18:59
 */
@Slf4j
public class Signature {

    /**
     * 将map对象的key 按 ASCII 码进行排序
     */
    public static String mapToAsciiString(Map<String, String> map) {
        String[] sortedKeys = map.keySet().toArray(new String[]{});
        Arrays.sort(sortedKeys);// 排序请求参数
        StringBuilder s2 = new StringBuilder();
        for (String key : sortedKeys) {
            s2.append(key).append("=").append(map.get(key)).append("&");
        }
        s2.deleteCharAt(s2.length() - 1);
        return s2.toString();
    }

    /**
     * 利用java原生的类实现SHA256加密
     *
     * @param str 加密后的报文
     */
    public static String getSHA256(String str) {
        MessageDigest messageDigest;
        String encodestr = "";
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(str.getBytes("UTF-8"));
            encodestr = byte2Hex(messageDigest.digest());
        } catch (Exception e) {
            log.info("Signature-getSHA256-Exception : {} ", e);
        }
        return encodestr;
    }

    /**
     * 将byte转为16进制
     */
    private static String byte2Hex(byte[] bytes) {
        StringBuilder stringBuffer = new StringBuilder();
        String temp;
        for (int i = 0; i < bytes.length; i++) {
            temp = Integer.toHexString(bytes[i] & 0xFF);
            if (temp.length() == 1) {
                // 1得到一位的进行补0操作
                stringBuffer.append("0");
            }
            stringBuffer.append(temp);
        }
        return stringBuffer.toString();
    }
}
