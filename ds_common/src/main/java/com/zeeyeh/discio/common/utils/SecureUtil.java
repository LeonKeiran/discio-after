package com.zeeyeh.discio.common.utils;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.util.HexUtil;
import cn.hutool.crypto.Mode;
import cn.hutool.crypto.Padding;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;
import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.Digester;
import cn.hutool.crypto.symmetric.AES;

import java.security.SecureRandom;

/**
 * @author Leon_Keiran
 * @description 安全工具类
 * @date 2024/12/24/周二 21:06:14
 * @github <a href="https://github.com/LeonKeiran">https://github.com/LeonKeiran</a>
 */
public class SecureUtil {

    /**
     * RSA公钥加密
     * @author Leon_Keiran
     * @date 2024/12/24/周二 21:06:59
     * @github <a href="https://github.com/LeonKeiran">https://github.com/LeonKeiran</a>
     * @param content 加密内容
     * @param publicKey 公钥
     * @return 加密后的内容
     */
    public static String rsaPublicEncrypt(String content, String publicKey) {
        RSA rsa = new RSA(null, publicKey);
        return rsa.encryptHex(content, KeyType.PublicKey);
    }

    /**
     * RSA公钥解密
     * @author Leon_Keiran
     * @date 2024/12/24/周二 21:07:06
     * @github <a href="https://github.com/LeonKeiran">https://github.com/LeonKeiran</a>
     * @param content 加密后的内容
     * @param publicKey 公钥
     * @return 解密后的内容
     */
    public static String rsaPublicDecrypt(String content, String publicKey) {
        RSA rsa = new RSA(null, publicKey);
        return rsa.decryptStr(content, KeyType.PublicKey);
    }

    /**
     * RSA私钥加密
     * @author Leon_Keiran
     * @date 2024/12/24/周二 21:07:14
     * @github <a href="https://github.com/LeonKeiran">https://github.com/LeonKeiran</a>
     * @param content 加密内容
     * @param privateKey 私钥
     * @return 加密后的内容
     */
    public static String rsaPrivateEncrypt(String content, String privateKey) {
        RSA rsa = new RSA(privateKey, null);
        return rsa.encryptHex(content, KeyType.PrivateKey);
    }

    /**
     * RSA私钥解密
     * @author Leon_Keiran
     * @date 2024/12/24/周二 21:07:28
     * @github <a href="https://github.com/LeonKeiran">https://github.com/LeonKeiran</a>
     * @param content 加密后的内容
     * @param privateKey 私钥
     * @return 解密后的内容
     */
    public static String rsaPrivateDecrypt(String content, String privateKey) {
        RSA rsa = new RSA(privateKey, null);
        return rsa.decryptStr(content, KeyType.PrivateKey);
    }

    /**
     * AES加密
     * @author Leon_Keiran
     * @date 2024/12/24/周二 21:07:39
     * @github <a href="https://github.com/LeonKeiran">https://github.com/LeonKeiran</a>
     * @param content 加密内容
     * @param key 密钥
     * @param iv 向量
     * @return 加密后的内容
     */
    public static String aesEncrypt(String content, String key, String iv) {
        return aesEncrypt(content, key, iv, Mode.OFB, Padding.ZeroPadding);
    }

    /**
     * AES加密
     * @author Leon_Keiran
     * @date 2024/12/24/周二 21:07:53
     * @github <a href="https://github.com/LeonKeiran">https://github.com/LeonKeiran</a>
     * @param content 加密内容
     * @param key 密钥
     * @param iv 向量
     * @param mode 加密模式
     * @param padding 填充方式
     * @return 加密后的内容
     */
    public static String aesEncrypt(String content, String key, String iv, Mode mode, Padding padding) {
        AES aes = new AES(mode, padding, Base64.decode(key), Base64.decode(iv));
        return aes.encryptHex(content);
    }

    /**
     * AES解密
     * @author Leon_Keiran
     * @date 2024/12/24/周二 21:08:25
     * @github <a href="https://github.com/LeonKeiran">https://github.com/LeonKeiran</a>
     * @param content 加密后的内容
     * @param key 密钥
     * @param iv 向量
     * @return 解密后的内容
     */
    public static String aesDecrypt(String content, String key, String iv) {
        return aesDecrypt(content, key, iv, Mode.OFB, Padding.ZeroPadding);
    }

    /**
     * AES解密
     * @author Leon_Keiran
     * @date 2024/12/24/周二 21:08:35
     * @github <a href="https://github.com/LeonKeiran">https://github.com/LeonKeiran</a>
     * @param content 加密后的内容
     * @param key 密钥
     * @param iv 向量
     * @param mode 加密模式
     * @param padding 填充方式
     * @return 解密后的内容
     */
    public static String aesDecrypt(String content, String key, String iv, Mode mode, Padding padding) {
        AES aes = new AES(mode, padding, Base64.decode(key), Base64.decode(iv));
        return aes.decryptStr(content);
    }

    /**
     * SHA256加密
     * @author Leon_Keiran
     * @date 2024/12/24/周二 21:09:37
     * @github <a href="https://github.com/LeonKeiran">https://github.com/LeonKeiran</a>
     * @param content 加密内容
     * @return 加密后的内容
     */
    public static String hashSHA256(String content) {
        return new Digester(DigestAlgorithm.SHA256).digestHex(content);
    }

    /**
     * 加密密码
     * @author Leon_Keiran
     * @date 2024/12/24/周二 21:09:50
     * @github <a href="https://github.com/LeonKeiran">https://github.com/LeonKeiran</a>
     * @param password 密码
     * @param key 密钥
     * @param iv 向量
     * @return 加密后的密码
     */
    public static String encryptPassword(String password, String key, String iv) {
        return hashSHA256(aesDecrypt(password, key, iv));
    }

    /**
     * 生成密钥
     * @author Leon_Keiran
     * @date 2024/12/24/周二 21:10:06
     * @github <a href="https://github.com/LeonKeiran">https://github.com/LeonKeiran</a>
     * @return 密钥
     */
    public static String generateKey() {
        return generateKey(256);
    }

    /**
     * 生成密钥
     * @author Leon_Keiran
     * @date 2024/12/24/周二 21:10:13
     * @github <a href="https://github.com/LeonKeiran">https://github.com/LeonKeiran</a>
     * @param keySize 密钥长度
     * @return 密钥
     */
    public static String generateKey(int keySize) {
        byte[] encoded = cn.hutool.crypto.SecureUtil.generateKey(
                        "AES/OFB/ZeroPadding",
                        keySize)
                .getEncoded();
        return Base64.encode(encoded);
    }

    /**
     * 生成随机向量
     * @author Leon_Keiran
     * @date 2024/12/24/周二 21:10:22
     * @github <a href="https://github.com/LeonKeiran">https://github.com/LeonKeiran</a>
     * @return 随机向量
     */
    public static String generateIv() {
        SecureRandom random = new SecureRandom();
        byte[] iv = new byte[16];
        random.nextBytes(iv);
        return Base64.encode(iv);
    }

    /**
     * 加密响应体
     * @author Leon_Keiran
     * @date 2024/12/24/周二 21:10:38
     * @github <a href="https://github.com/LeonKeiran">https://github.com/LeonKeiran</a>
     * @param body 响应体
     * @param privateKey 私钥
     * @return 加密后的响应体
     */
    public static String encryptResponse(String body, String privateKey) {
        String ciphertextContent = rsaPrivateEncrypt(body, privateKey);
        String aesKey = generateKey();
        String aesIv = generateIv();
        ciphertextContent = aesEncrypt(ciphertextContent, aesKey, aesIv);
        ciphertextContent = aesKey.length() +
                ":" +
                aesIv.length() +
                ":" +
                aesKey +
                aesIv +
                ciphertextContent;
        return HexUtil.encodeHexStr(ciphertextContent.getBytes(), false);
    }

    /**
     * 解密请求体
     * @author Leon_Keiran
     * @date 2024/12/24/周二 21:10:55
     * @github <a href="https://github.com/LeonKeiran">https://github.com/LeonKeiran</a>
     * @param body 请求体
     * @param privateKey 私钥
     * @return 解密后的请求体
     */
    public static String decryptRequest(String body, String privateKey) {
        String plaintextContent = HexUtil.decodeHexStr(body);
        String[] plaintextBlocks = plaintextContent.split(":");
        int aesKeyLength = Integer.parseInt(plaintextBlocks[0]);
        int aesIvLength = Integer.parseInt(plaintextBlocks[1]);
        String ciphertextContent = plaintextBlocks[2];
        plaintextContent = plaintextContent.substring(String.valueOf(aesKeyLength).length() + String.valueOf(aesIvLength).length() + 2);
        String aesKey = plaintextContent.substring(0, aesKeyLength);
        String aesIv = ciphertextContent.substring(aesKeyLength, aesKeyLength + aesIvLength);
        ciphertextContent = ciphertextContent.substring(aesKeyLength + aesIvLength);
        plaintextContent = aesDecrypt(ciphertextContent, aesKey, aesIv);
        // plaintextContent = rsaPublicDecrypt(plaintextContent, privateKey);
        plaintextContent = rsaPrivateDecrypt(plaintextContent, privateKey);
        return plaintextContent;
    }
}
