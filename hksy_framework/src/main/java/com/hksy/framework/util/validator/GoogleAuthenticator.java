//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.hksy.framework.util.validator;

import com.hksy.framework.common.BusinessException;
import org.apache.commons.codec.binary.Base32;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class GoogleAuthenticator {
    public static final int SECRET_SIZE = 10;
    public static final String SEED = "g8GjEvTbW5oVSV7avL47357438reyhreyuryetredLDVKs2m0QN7vxRs2im5MDaNCWGmcD2rvcZx";
    public static final String RANDOM_NUMBER_ALGORITHM = "SHA1PRNG";
    int window_size = 3;

    public GoogleAuthenticator() {
    }

    public void setWindowSize(int s) {
        if (s >= 1 && s <= 17) {
            this.window_size = s;
        }

    }

    public static String generateSecreKey() {
        SecureRandom sr = null;

        try {
            sr = SecureRandom.getInstance("SHA1PRNG");
            sr.setSeed(Base64.decodeBase64("g8GjEvTbW5oVSV7avL47357438reyhreyuryetredLDVKs2m0QN7vxRs2im5MDaNCWGmcD2rvcZx"));
            byte[] buffer = sr.generateSeed(10);
            Base32 codec = new Base32();
            byte[] bEncodedKey = codec.encode(buffer);
            String encodedKey = new String(bEncodedKey);
            return encodedKey;
        } catch (NoSuchAlgorithmException var5) {
            return null;
        }
    }

    public static String getQRBarcodeURL(String user, String host, String secret) {
        String format = "http://www.google.com/chart?chs=200x200&chld=M%%7C0&cht=qr&chl=otpauth://totp/%s@%s?secret=%s";
        return String.format(format, user, host, secret);
    }

    public static String getQRBarcode(String user, String secret, String title) {
        title = StringUtils.isBlank(title) ? "HKSY.COM(HKSY)" : title;
        String format = "otpauth://totp/%s?secret=%s";
        return String.format(format, user, secret) + "&issuer=" + title;
    }

    public boolean check_code(String secret, long code, long timeMsec) {
        Base32 codec = new Base32();
        byte[] decodedKey = codec.decode(secret);
        long t = timeMsec / 1000L / 30L;

        for(int i = -this.window_size; i <= this.window_size; ++i) {
            long hash;
            try {
                hash = (long)verify_code(decodedKey, t + (long)i);
            } catch (Exception var14) {
                var14.printStackTrace();
                throw new BusinessException(var14);
            }

            if (hash == code) {
                return true;
            }
        }

        return false;
    }

    private static int verify_code(byte[] key, long t) throws NoSuchAlgorithmException, InvalidKeyException {
        byte[] data = new byte[8];
        long value = t;

        for(int i = 8; i-- > 0; value >>>= 8) {
            data[i] = (byte)((int)value);
        }

        SecretKeySpec signKey = new SecretKeySpec(key, "HmacSHA1");
        Mac mac = Mac.getInstance("HmacSHA1");
        mac.init(signKey);
        byte[] hash = mac.doFinal(data);
        int offset = hash[19] & 15;
        long truncatedHash = 0L;

        for(int i = 0; i < 4; ++i) {
            truncatedHash <<= 8;
            truncatedHash |= (long)(hash[offset + i] & 255);
        }

        truncatedHash &= 2147483647L;
        truncatedHash %= 1000000L;
        return (int)truncatedHash;
    }
}
