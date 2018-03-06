package com.banklink.lib.utils;


import android.annotation.SuppressLint;
import android.text.TextUtils;

import com.banklink.lib.config.ConfigInfo;
import com.banklink.lib.config.SignInfo;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

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

    /**
     * The method for signing a signature is convenient for the developer to obtain the
     * number after the signature.
     *
     * @param data data
     * @param ts ts
     * @return
     */
    public static String sign(String data,String ts) {

        String c = ConfigInfo.SIGN_APPID + ConfigInfo.APP_ID + ConfigInfo.SIGN_DATA + data + ConfigInfo.SIGN_TS + ts +ConfigInfo.APP_KEY;

        return MD5Util.getMD5(Cfb_256crypt.getSHA256StrJava(c)).toLowerCase();
    }

    /**
     * Verify the correctness and integrity of the signature and ensure the security of the data.
     * @param sign sign
     * @return
     */
    public static boolean checkSign(String sign) {
        if (TextUtils.isEmpty(sign) || !sign.contains(ConfigInfo.SIGN_TAG)) {
            return false;
        } else {
            try {
                SignInfo i = new Gson().fromJson(sign, SignInfo.class);
                return sign(i.getData(), String.valueOf(i.getTimestamp())).equals(i.getSign());
            } catch (JsonSyntaxException e) {
                return false;
            }

        }

    }
}
