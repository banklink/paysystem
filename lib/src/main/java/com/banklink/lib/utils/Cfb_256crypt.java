package com.banklink.lib.utils;

/*
* Author beikelin @Zac 20170828
* java ase-256-cfb 加密解密 通信方法
*/

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class Cfb_256crypt {
    public static byte[] byteMerger(byte[] byte_1, byte[] byte_2) {
        byte[] byte_3 = new byte[byte_1.length + byte_2.length];
        System.arraycopy(byte_1, 0, byte_3, 0, byte_1.length);
        System.arraycopy(byte_2, 0, byte_3, byte_1.length, byte_2.length);
        return byte_3;
    }

    public static String encrypt(String key, String plainText) {
        try {
            //SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

            Cipher cipher = Cipher.getInstance("AES/CFB/NoPadding");
            byte[][] key_iv = EVP_BytesToKey(32, 16, null, key.getBytes("UTF-8"), 0);
            System.out.println("key_iv[0]: " + key_iv[0].length);
            System.out.println("key_iv[1]: " + key_iv[1].length);


            SecretKeySpec skeySpec = new SecretKeySpec(key_iv[0], "AES");
            //这里我不使用 key_iv[1] 做iv ，只用 EVP_BytesToKey 来完善key
            //IvParameterSpec iv = new IvParameterSpec(key_iv[1]);
            //cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec);

            byte[] ivb = cipher.getIV();
            byte[] encrypted = byteMerger(ivb, cipher.doFinal(plainText.getBytes("UTF-8")));

            System.out.println("iv: " + byte2hex(ivb));
            System.out.println("key length:" + key.getBytes("UTF-8").length);
            System.out.println("iv length: " + ivb.length);
            System.out.println("encrypted string: " + byte2hex(cipher.doFinal(plainText.getBytes("UTF-8"))));


            return byte2hex(encrypted);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static String decrypt(String key, String encrypted) {
        try {
            //解密的时候 iv 附加在密文的头部。直接读取分割即可
            byte[] encrypt_data = hex2byte(encrypted);
            byte[] iv_byte = Arrays.copyOfRange(encrypt_data, 0, 16);
            byte[] real_crypt_data = Arrays.copyOfRange(encrypt_data, 16, encrypt_data.length);

            //SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");
            byte[][] key_iv = EVP_BytesToKey(32, 16, null, key.getBytes("UTF-8"), 0);
            IvParameterSpec iv = new IvParameterSpec(iv_byte);
            //IvParameterSpec iv = new IvParameterSpec(key_iv[1]);
            SecretKeySpec skeySpec = new SecretKeySpec(key_iv[0], "AES");

            Cipher cipher = Cipher.getInstance("AES/CFB/NoPadding");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
            byte[] original = cipher.doFinal(real_crypt_data);

            return new String(original, "UTF-8");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    private static byte[] hex2byte(String strhex) {
        if (strhex == null)
            return null;

        int l = strhex.length();
        if (l % 2 == 1)
            return null;

        byte[] b = new byte[l / 2];
        for (int i = 0; i != l / 2; ++i)
            b[i] = (byte) Integer.parseInt(strhex.substring(i * 2, i * 2 + 2), 16);

        return b;
    }

    private static String byte2hex(byte[] b) {
        String hs = "";
        String stmp = "";
        for (int n = 0; n < b.length; ++n) {
            stmp = Integer.toHexString(b[n] & 0xFF);
            if (stmp.length() == 1)
                hs = hs + "0" + stmp;
            else
                hs = hs + stmp;
        }

        return hs.toUpperCase();
    }

    /**
     * Java version of OpenSSL EVP_BytesToKey. Derives key and IV from
     * password and salt.
     * <p>
     * https://www.openssl.org/docs/crypto/EVP_BytesToKey.html
     * <p>
     * Source: https://olabini.com/blog/tag/evp_bytestokey/
     *
     * @param key_len
     * @param iv_len
     * @param salt
     * @param data
     * @param count
     * @return derived Key and IV
     */
    public static byte[][] EVP_BytesToKey(int key_len, int iv_len, byte[] salt,
                                          byte[] data, int count) throws Exception {

        final MessageDigest md = MessageDigest.getInstance("md5");

        byte[][] both = new byte[2][];
        byte[] key = new byte[key_len];
        int key_ix = 0;
        byte[] iv = new byte[iv_len];
        int iv_ix = 0;
        both[0] = key;
        both[1] = iv;
        byte[] md_buf = null;
        int nkey = key_len;
        int niv = iv_len;
        int i = 0;
        if (data == null) {
            return both;
        }
        int addmd = 0;
        for (; ; ) {
            md.reset();
            if (addmd++ > 0) {
                md.update(md_buf);
            }
            md.update(data);
            if (null != salt) {
                md.update(salt, 0, 8);
            }
            md_buf = md.digest();
            for (i = 1; i < count; i++) {
                md.reset();
                md.update(md_buf);
                md_buf = md.digest();
            }
            i = 0;
            if (nkey > 0) {
                for (; ; ) {
                    if (nkey == 0)
                        break;
                    if (i == md_buf.length)
                        break;
                    key[key_ix++] = md_buf[i];
                    nkey--;
                    i++;
                }
            }
            if (niv > 0 && i != md_buf.length) {
                for (; ; ) {
                    if (niv == 0)
                        break;
                    if (i == md_buf.length)
                        break;
                    iv[iv_ix++] = md_buf[i];
                    niv--;
                    i++;
                }
            }
            if (nkey == 0 && niv == 0) {
                break;
            }
        }
        for (i = 0; i < md_buf.length; i++) {
            md_buf[i] = 0;
        }
        return both;
    }

    public static void main(String[] args) {
        //128，192，256
        String key = "11111"; // 128 bit or 256
        System.out.println("aes-256-cfb encrypted:" + encrypt(key, "碎片时间，练就一口流利英语！"));
        System.out.println("aes-256-cfb decrypted:" + decrypt(key, "7267127905F97220F1E43ACB135FEA75BBCBA88FD58193D1277174FA47ECBA9D3598567EF6D8CAD3E2A64C38BB3C30345D66EDAF514D8A719001"));
    }

    /**
     * 利用java原生的摘要实现SHA256加密
     *
     * @param str 加密后的报文
     * @return
     */
    public static String getSHA256StrJava(String str) {
        MessageDigest messageDigest;
        String encodeStr = "";
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(str.getBytes("UTF-8"));
            encodeStr = byte2Hex(messageDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return encodeStr;
    }

    /**
     * 将byte转为16进制
     *
     * @param bytes
     * @return
     */
    private static String byte2Hex(byte[] bytes) {
        StringBuffer stringBuffer = new StringBuffer();
        String temp = null;
        for (int i = 0; i < bytes.length; i++) {
            temp = Integer.toHexString(bytes[i] & 0xFF);
            if (temp.length() == 1) {
                //1得到一位的进行补0操作
                stringBuffer.append("0");
            }
            stringBuffer.append(temp);
        }
        return stringBuffer.toString();
    }
}