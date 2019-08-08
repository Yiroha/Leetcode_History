package com.per.iroha.util;

import com.per.iroha.controller.LoginController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

public class StringUtils {

    private static Logger logger = LoggerFactory.getLogger(StringUtils.class);

    /**
     * md5加密(ITS)
     *
     * @param str
     * @param charSet
     * @return
     */
    public synchronized static String getMD5Str(String str, String charSet) { //md5加密
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            if (charSet == null) {
                messageDigest.update(str.getBytes());
            } else {
                messageDigest.update(str.getBytes(charSet));
            }
        } catch (NoSuchAlgorithmException e) {
            logger.error("md5 error:" + e.getMessage(), e);
        } catch (UnsupportedEncodingException e) {
            logger.error("md5 error:" + e.getMessage(), e);
        }

        byte[] byteArray = messageDigest.digest();
        StringBuffer md5StrBuff = new StringBuffer();
        for (int i = 0; i < byteArray.length; i++) {
            if (Integer.toHexString(0xFF & byteArray[i]).length() == 1)
                md5StrBuff.append("0").append(Integer.toHexString(0xFF & byteArray[i]));
            else
                md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
        }
        return md5StrBuff.toString();
    }

}
