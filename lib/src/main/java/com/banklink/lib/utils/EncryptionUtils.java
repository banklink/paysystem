package com.banklink.lib.utils;


import android.annotation.SuppressLint;

import com.banklink.lib.config.ConfigInfo;

/**
 * Author：FynnJason
 * Describe：Encryption tools
 */

public class EncryptionUtils {

    /**
     * Plaintext encryption processing method, incoming corresponding encryption key and
     * encryption content.
     * @param encryptKey key
     * @param plainText content
     * @return
     */
    public static String encrypt(String encryptKey, String plainText) {
        return Cfb_256crypt.encrypt(encryptKey, plainText);
    }

    /**
     * The decryption method of the encrypted content is passed into the corresponding
     * decryption key and decryption content.
     * @param encryptKey key
     * @param encrypted content
     * @return
     */
    public static String decrypt(String encryptKey,String encrypted) {
        return Cfb_256crypt.decrypt(encryptKey, encrypted);
    }

    /**
     * The method for signing a signature is convenient for the developer to obtain the
     * number after the signature.
     *
     * @param data data
     *
     * @return
     */
    public static String sign(String data) {

        @SuppressLint("DefaultLocale")
        String timestamp = String.format("%010d", TimeUtils.getNowMills() / 1000);

        String c = ConfigInfo.SIGN_APPID + ConfigInfo.APP_ID + ConfigInfo.SIGN_DATA + data + ConfigInfo.SIGN_TS + timestamp +ConfigInfo.APP_KEY;

        return MD5Util.getMD5(Cfb_256crypt.getSHA256StrJava(c)).toLowerCase();
    }


//    public static boolean checkSign(String value) {
//
//    }
}
