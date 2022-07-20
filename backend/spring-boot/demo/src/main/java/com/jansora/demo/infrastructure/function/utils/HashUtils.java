package com.jansora.demo.infrastructure.function.utils;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Date;

/**
 * <Description> Description for HashUtils <br>
 *
 * @author jansora (zhang.yangyuan) <br>
 * @version 1.0 <br>
 * @transId null
 * @CreateDate 2021/9/14 19:05:01 <br>
 * @see com.jansora.app.util <br>
 * @since 1.0 <br>
 */
public class HashUtils {

    private static MessageDigest md = null;

    static {
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (Exception e) {

        }
    }

    public static String hash(String content) {
        // 反复调用update输入数据:

        content = new Date().getTime() + content;

        try {
            md.update(content.getBytes(StandardCharsets.UTF_8));
            return new BigInteger(1, md.digest()).toString(16);
        } catch (Exception e) {
            return String.valueOf(new Date().getTime());
        }
    }


}
