package org.kevin.utility;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class MD5Utility {
    public static final String Encryption(String plainText) {
        try {
            MessageDigest md = MessageDigest.getInstance("md5");

            byte[] bytes = md.digest(plainText.getBytes("UTF-8"));

            String result = Base64.getEncoder().encodeToString(bytes);
            return result;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
