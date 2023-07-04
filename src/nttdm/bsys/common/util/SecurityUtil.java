/**
 * @(#)SecurityUtil.java
 * 
 * Billing System
 * 
 * Version 1.00
 * 
 * Created 2011/10/09
 * 
 * Copyright (c) 2011 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.common.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * encrypt and decrypt
 * 
 * @author gplai
 * 
 */
public class SecurityUtil {
    /**
     * resource file path
     */
    private static final String RESOURCE_FILE_PATH = "securityInfo";

    /**
     * resource file path
     */
    private static final String SECURITY_KEY = "security.key";

    /**
     * md5 encrypt
     * 
     * @param str
     *            data
     * @return encrypted data
     */
    public static String md5(String str) {
        String result = "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
            byte b[] = md.digest();
            int i;
            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0) {
                    i += 256;
                }
                if (i < 16) {
                    buf.append("0");
                }
                buf.append(Integer.toHexString(i));
            }
            result = buf.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * string encryption
     * 
     * @param data
     *            string data
     * @return encrypted data
     */
    public static String aesEncrypt(String data) {
        String encryptResultStr = "";
        try{
            // get security key
            String key = getResourceStr(SECURITY_KEY);
            // encrypt data with key
            byte[] encryptResult = encrypt(data, key);
            // parse encrypted result byte to string
            encryptResultStr = parseByte2HexStr(encryptResult);
            // encode string to base64 for storing
            encryptResultStr = ebotongEncrypto(encryptResultStr);
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return encryptResultStr;
    }

    /**
     * string decryption
     * 
     * @param encrypted
     *            the encrypted data
     * @return decrypted data
     */
    public static String aesDecrypt(String encrypted) {
        String decryptResultStr = "";
        try{
            // get security key
            String key = getResourceStr(SECURITY_KEY);
            // decode stored string by base64
            String decrpt = ebotongDecrypto(encrypted);
            // parse encrypted string to byte
            byte[] decryptFrom = parseHexStr2Byte(decrpt);
            // decrypt string
            byte[] decryptResult = decrypt(decryptFrom, key);
            decryptResultStr = new String(decryptResult);
        }catch(Exception e){
            e.printStackTrace();
        }
        return decryptResultStr;
    }

    /**
     * change encoding with BASE64Encoder
     * 
     * @param str
     * @return
     */
    private static String ebotongEncrypto(String str) {
        BASE64Encoder base64encoder = new BASE64Encoder();
        String result = str;
        if (str != null && str.length() > 0) {
            try {
                byte[] encodeByte = str.getBytes("UTF-8");
                result = base64encoder.encode(encodeByte);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result.replaceAll("\r\n", "").replaceAll("\r", "").replaceAll("\n", "");
    }

    /**
     * change to base64 decoder
     * 
     * @param str
     * @return
     */
    private static String ebotongDecrypto(String str) {
        BASE64Decoder base64decoder = new BASE64Decoder();
        try {
            byte[] encodeByte = base64decoder.decodeBuffer(str);
            return new String(encodeByte);
        } catch (IOException e) {
            e.printStackTrace();
            return str;
        }
    }

    /**
     * encryption data
     * 
     * @param content
     * @param strKey
     * @return
     */
    private static byte[] encrypt(String content, String strKey) {
        try {
            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            secureRandom.setSeed(strKey.getBytes());
            kgen.init(128, secureRandom);
            SecretKey secretKey = kgen.generateKey();
            byte[] enCodeFormat = secretKey.getEncoded();
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
            Cipher cipher = Cipher.getInstance("AES");
            byte[] byteContent = content.getBytes("utf-8");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] result = cipher.doFinal(byteContent);
            return result;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
        return new byte[0];
    }

    /**
     * decryption method
     * 
     * @param content
     * @param strKey
     * @return
     */
    private static byte[] decrypt(byte[] content, String strKey) {
        try {
            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            secureRandom.setSeed(strKey.getBytes());
            kgen.init(128, secureRandom);
            SecretKey secretKey = kgen.generateKey();
            byte[] enCodeFormat = secretKey.getEncoded();
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] result = cipher.doFinal(content);
            return result;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
        return new byte[0];
    }

    /**
     * byte to string
     * 
     * @param buf
     * @return
     */
    private static String parseByte2HexStr(byte buf[]) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < buf.length; i++) {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }

    /**
     * string to byte
     * 
     * @param hexStr
     * @return
     */
    private static byte[] parseHexStr2Byte(String hexStr) {
        if (hexStr.length() < 1) {
            return new byte[0];
        }
        byte[] result = new byte[hexStr.length() / 2];
        for (int i = 0; i < hexStr.length() / 2; i++) {
            int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
            result[i] = (byte) (high * 16 + low);
        }
        
        return result;
    }

    /**
     * get value by key
     * 
     * @param key
     *            String key
     * @return value
     */
    private static String getResourceStr(String key) {
        ResourceBundle rb = ResourceBundle.getBundle(RESOURCE_FILE_PATH, Locale.getDefault());
        return rb.getString(key);
    }
}
