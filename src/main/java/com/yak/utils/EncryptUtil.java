package com.yak.utils;

import org.apache.commons.lang3.StringUtils;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Date;

public class EncryptUtil {
    private static final String SYMBOL_EQUAL = "=";
    private static final String SALT = "YAK123$%^poi<OK>?yhn6tfWSD";
    private static final String KEY_ALGORITHM = "DES";
    private static final String KEY_MD5 = "MD5";
    private static final String CIPHER_ALGORITHM = "DES/ECB/NoPadding";

    private static SecretKey keyGenerator() throws Exception {
        byte input[] = HexString2Bytes();
        DESKeySpec desKey = new DESKeySpec(input);
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(KEY_ALGORITHM);
        return keyFactory.generateSecret(desKey);
    }

    @org.jetbrains.annotations.Contract(pure = true)
    private static int parse(char c) {
        if (c >= 'a') {
            return (c - 'a' + 10) & 0x0f;
        }
        if (c >= 'A') {
            return (c - 'A' + 10) & 0x0f;
        }
        return (c - '0') & 0x0f;
    }

    private static byte[] HexString2Bytes() {
        byte[] b = new byte[EncryptUtil.SALT.length() / 2];
        int j = 0;
        for (int i = 0; i < b.length; i++) {
            char c0 = EncryptUtil.SALT.charAt(j++);
            char c1 = EncryptUtil.SALT.charAt(j++);
            b[i] = (byte) ((parse(c0) << 4) | parse(c1));
        }
        return b;
    }

    /**
     * 加密
     */
    public static String encrypt(String data) throws Exception {
        Key desKey = keyGenerator();
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
        SecureRandom random = new SecureRandom();
        cipher.init(Cipher.ENCRYPT_MODE, desKey, random);
        byte[] results = cipher.doFinal(data.getBytes());
        String encryptData = Base64.getEncoder().encodeToString(results);
        return toMd5(encryptData);
    }

    /**
     * 解密
     * @param data 要解密的数据
     * @return 解密后的字符
     */
    public static String decrypt(String data) throws Exception {
        Key desKey = keyGenerator();
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, desKey);
        return new String(cipher.doFinal(Base64.getDecoder().decode(deMd5(data))));
    }

    private static String deMd5(String encryptData) {
        if (StringUtils.isBlank(encryptData) || encryptData.length() < 32) {
            return SYMBOL_EQUAL;
        }
        int len = encryptData.length();
        StringBuilder bf = new StringBuilder();
        int count = 0;
        for (int i = 0; i < len && count < len - 32; i++) {
            if (count < 32) {
                if (i % 2 == 1) {
                    count++;
                    bf.append(encryptData, i, i + 1);
                }
            }
            else {
                bf.append(encryptData, i, i + 1);
            }
        }
        bf.append(SYMBOL_EQUAL);
        return bf.toString();
    }

    private static String toMd5(String encryptData) {
        try {
            String salt = new Date().getTime() + "";
            MessageDigest m = MessageDigest.getInstance(KEY_MD5);
            m.update(salt.getBytes(StandardCharsets.UTF_8));
            byte bytes[] = m.digest();
            StringBuilder sbf = new StringBuilder();
            for (byte b : bytes) {
                sbf.append(Integer.toHexString((0x000000FF & b) | 0xFFFFFF00).substring(6));
            }
            encryptData = encryptData.substring(0, encryptData.length() - 1);
            StringBuilder bf = new StringBuilder();
            for (int i = 0; i < sbf.length(); i++) {
                bf.append(sbf.substring(i, i + 1));
                if (encryptData.length() > i) {
                    bf.append(encryptData, i, i + 1);
                }
            }
            return bf.toString();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }


    public static void main(String[] args) throws Exception {
        String source = "20200601";
        System.out.println("原文: " + source);

        String encryptData = encrypt(source);
        System.out.println("加密后: " + encryptData);

        String decryptData = decrypt(encryptData);
        System.out.println("解密后: " + decryptData);
    }
}
