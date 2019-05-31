package com.hksy.framework.util;

/*     */
public class Base64 {
    private static final int BASELENGTH = 128;
    private static final int LOOKUPLENGTH = 64;
    private static final int TWENTYFOURBITGROUP = 24;
    private static final int EIGHTBIT = 8;
    private static final int SIXTEENBIT = 16;
    private static final int FOURBYTE = 4;
    private static final int SIGN = -128;
    private static final char PAD = '=';
    private static final boolean fDebug = false;
    /*  13 */   private static final byte[] base64Alphabet = new byte[''];
    /*  14 */   private static final char[] lookUpBase64Alphabet = new char[64];

    /*     */
    static {
        for (int i = 0; i < BASELENGTH; ++i) {
            base64Alphabet[i] = -1;
        }
        for (int i = 'Z'; i >= 'A'; i--) {
            base64Alphabet[i] = (byte) (i - 'A');
        }
        for (int i = 'z'; i >= 'a'; i--) {
            base64Alphabet[i] = (byte) (i - 'a' + 26);
        }

        for (int i = '9'; i >= '0'; i--) {
            base64Alphabet[i] = (byte) (i - '0' + 52);
        }

        base64Alphabet['+'] = 62;
        base64Alphabet['/'] = 63;

        for (int i = 0; i <= 25; i++) {
            lookUpBase64Alphabet[i] = (char) ('A' + i);
        }

        for (int i = 26, j = 0; i <= 51; i++, j++) {
            lookUpBase64Alphabet[i] = (char) ('a' + j);
        }

        for (int i = 52, j = 0; i <= 61; i++, j++) {
            lookUpBase64Alphabet[i] = (char) ('0' + j);
        }
        lookUpBase64Alphabet[62] = (char) '+';
        lookUpBase64Alphabet[63] = (char) '/';

    }

    /*     */
/*     */
/*     */
    private static boolean isWhiteSpace(char octect) {
/*  59 */
        return (octect == ' ') || (octect == '\r') || (octect == '\n') || (octect == '\t');
    }

    /*     */
    private static boolean isPad(char octect) {
/*  64 */
        return octect == '=';
    }

    /*     */
    private static boolean isData(char octect) {
/*  69 */
        return (octect < '') && (base64Alphabet[octect] != -1);
    }

    /*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
    public static String encode(byte[] binaryData) {
/*  82 */
        if (binaryData == null) {
/*  83 */
            return null;
        }
/*     */
/*     */
/*  87 */
        int lengthDataBits = binaryData.length * 8;
/*  88 */
        if (lengthDataBits == 0) {
/*  89 */
            return "";
        }
/*     */
/*     */
/*  93 */
        int fewerThan24bits = lengthDataBits % 24;
/*  94 */
        int numberTriplets = lengthDataBits / 24;
/*  95 */
        int numberQuartet = fewerThan24bits != 0 ? numberTriplets + 1 : numberTriplets;
/*  96 */
        char[] encodedData = null;
/*     */
/*     */
/*  99 */
        encodedData = new char[numberQuartet * 4];
/*     */
/*     */
/* 102 */
        byte k = 0;
        byte l = 0;
        byte b1 = 0;
        byte b2 = 0;
        byte b3 = 0;
/*     */
/*     */
/* 105 */
        int encodedIndex = 0;
/* 106 */
        int dataIndex = 0;
/*     */
/*     */
/*     */
/*     */
/*     */
/* 112 */
        for (int i = 0; i < numberTriplets; i++) {
/* 113 */
            b1 = binaryData[(dataIndex++)];
/* 114 */
            b2 = binaryData[(dataIndex++)];
/* 115 */
            b3 = binaryData[(dataIndex++)];
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/* 123 */
            l = (byte) (b2 & 0xF);
/* 124 */
            k = (byte) (b1 & 0x3);
/*     */
/*     */
/* 127 */
            byte val1 = (b1 & 0xFFFFFF80) == 0 ? (byte) (b1 >> 2) : (byte) (b1 >> 2 ^ 0xC0);
/* 128 */
            byte val2 = (b2 & 0xFFFFFF80) == 0 ? (byte) (b2 >> 4) : (byte) (b2 >> 4 ^ 0xF0);
/* 129 */
            byte val3 = (b3 & 0xFFFFFF80) == 0 ? (byte) (b3 >> 6) : (byte) (b3 >> 6 ^ 0xFC);
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/* 139 */
            encodedData[(encodedIndex++)] = lookUpBase64Alphabet[val1];
/* 140 */
            encodedData[(encodedIndex++)] = lookUpBase64Alphabet[(val2 | k << 4)];
/* 141 */
            encodedData[(encodedIndex++)] = lookUpBase64Alphabet[(l << 2 | val3)];
/* 142 */
            encodedData[(encodedIndex++)] = lookUpBase64Alphabet[(b3 & 0x3F)];
        }
/*     */
/*     */
/*     */
/* 147 */
        if (fewerThan24bits == 8) {
/* 148 */
            b1 = binaryData[dataIndex];
/* 149 */
            k = (byte) (b1 & 0x3);
/*     */
/*     */
/*     */
/*     */
/* 154 */
            byte val1 = (b1 & 0xFFFFFF80) == 0 ? (byte) (b1 >> 2) : (byte) (b1 >> 2 ^ 0xC0);
/* 155 */
            encodedData[(encodedIndex++)] = lookUpBase64Alphabet[val1];
/* 156 */
            encodedData[(encodedIndex++)] = lookUpBase64Alphabet[(k << 4)];
/* 157 */
            encodedData[(encodedIndex++)] = '=';
/* 158 */
            encodedData[(encodedIndex++)] = '=';
/* 159 */
        } else if (fewerThan24bits == 16) {
/* 160 */
            b1 = binaryData[dataIndex];
/* 161 */
            b2 = binaryData[(dataIndex + 1)];
/* 162 */
            l = (byte) (b2 & 0xF);
/* 163 */
            k = (byte) (b1 & 0x3);
/*     */
/*     */
/* 166 */
            byte val1 = (b1 & 0xFFFFFF80) == 0 ? (byte) (b1 >> 2) : (byte) (b1 >> 2 ^ 0xC0);
/* 167 */
            byte val2 = (b2 & 0xFFFFFF80) == 0 ? (byte) (b2 >> 4) : (byte) (b2 >> 4 ^ 0xF0);
/*     */
/*     */
/* 170 */
            encodedData[(encodedIndex++)] = lookUpBase64Alphabet[val1];
/* 171 */
            encodedData[(encodedIndex++)] = lookUpBase64Alphabet[(val2 | k << 4)];
/* 172 */
            encodedData[(encodedIndex++)] = lookUpBase64Alphabet[(l << 2)];
/* 173 */
            encodedData[(encodedIndex++)] = '=';
        }
/*     */
/*     */
/* 177 */
        return new String(encodedData);
    }

    /*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
    public static byte[] decode(String encoded) {
/* 190 */
        if (encoded == null) {
/* 191 */
            return null;
        }
/*     */
/*     */
/* 195 */
        char[] base64Data = encoded.toCharArray();
/*     */
/* 197 */
        int len = removeWhiteSpace(base64Data);
/*     */
/*     */
/* 200 */
        if (len % 4 != 0) {
/* 201 */
            return null;
        }
/*     */
/*     */
/* 205 */
        int numberQuadruple = len / 4;
/*     */
/*     */
/* 208 */
        if (numberQuadruple == 0) {
/* 209 */
            return new byte[0];
        }
/*     */
/*     */
/* 213 */
        byte[] decodedData = null;
/* 214 */
        byte b1 = 0;
        byte b2 = 0;
        byte b3 = 0;
        byte b4 = 0;
/* 215 */
        char d1 = '\000';
        char d2 = '\000';
        char d3 = '\000';
        char d4 = '\000';
/*     */
/*     */
/* 218 */
        int i = 0;
/* 219 */
        int encodedIndex = 0;
/* 220 */
        int dataIndex = 0;
/* 221 */
        decodedData = new byte[numberQuadruple * 3];
/* 224 */
        for (;
/*     */
/* 224 */         i < numberQuadruple - 1; i++) {
/*     */
/* 227 */
            if ((!isData(d1 = base64Data[(dataIndex++)])) || (!isData(d2 = base64Data[(dataIndex++)])) ||
/* 228 */         (!isData(d3 = base64Data[(dataIndex++)])) ||
/* 229 */         (!isData(d4 = base64Data[(dataIndex++)]))) {
/* 230 */
                return null;
            }
/*     */
/*     */
/* 234 */
            b1 = base64Alphabet[d1];
/* 235 */
            b2 = base64Alphabet[d2];
/* 236 */
            b3 = base64Alphabet[d3];
/* 237 */
            b4 = base64Alphabet[d4];
/*     */
/*     */
/* 240 */
            decodedData[(encodedIndex++)] = ((byte) (b1 << 2 | b2 >> 4));
/* 241 */
            decodedData[(encodedIndex++)] = ((byte) ((b2 & 0xF) << 4 | b3 >> 2 & 0xF));
/* 242 */
            decodedData[(encodedIndex++)] = ((byte) (b3 << 6 | b4));
        }
/*     */
/*     */
/* 246 */
        if ((!isData(d1 = base64Data[(dataIndex++)])) || (!isData(d2 = base64Data[(dataIndex++)]))) {
/* 247 */
            return null;
        }
        b1 = base64Alphabet[d1];
        b2 = base64Alphabet[d2];
        d3 = base64Data[(dataIndex++)];
        d4 = base64Data[(dataIndex++)];
        if ((!isData(d3)) || (!isData(d4))) {
            if ((isPad(d3)) && (isPad(d4))) {
                if ((b2 & 0xF) != 0) {
                    return null;
                }
                byte[] tmp = new byte[i * 3 + 1];
                System.arraycopy(decodedData, 0, tmp, 0, i * 3);
                tmp[encodedIndex] = ((byte) (b1 << 2 | b2 >> 4));
                return tmp;
            }
            if ((!isPad(d3)) && (isPad(d4))) {
                b3 = base64Alphabet[d3];
                if ((b3 & 0x3) != 0) {
                    return null;
                }
                byte[] tmp = new byte[i * 3 + 2];
                System.arraycopy(decodedData, 0, tmp, 0, i * 3);
                tmp[(encodedIndex++)] = ((byte) (b1 << 2 | b2 >> 4));
                tmp[encodedIndex] = ((byte) ((b2 & 0xF) << 4 | b3 >> 2 & 0xF));
                return tmp;
            }
            return null;
        }
        b3 = base64Alphabet[d3];
        b4 = base64Alphabet[d4];
        decodedData[(encodedIndex++)] = ((byte) (b1 << 2 | b2 >> 4));
        decodedData[(encodedIndex++)] = ((byte) ((b2 & 0xF) << 4 | b3 >> 2 & 0xF));
        decodedData[(encodedIndex++)] = ((byte) (b3 << 6 | b4));

        return decodedData;
    }


    private static int removeWhiteSpace(char[] data) {
/* 303 */
        if (data == null) {
/* 304 */
            return 0;
        }
/*     */
/*     */
/*     */
/* 309 */
        int newSize = 0;
/* 310 */
        int len = data.length;
/* 311 */
        for (int i = 0; i < len; i++) {
/* 312 */
            if (!isWhiteSpace(data[i])) {
/* 313 */
                data[(newSize++)] = data[i];
            }
        }
/* 316 */
        return newSize;
    }
}


/* Location:              /Users/Edanel/Library/Application Support/com.tencent.xinWeChat/2.0b4.0.9/98cec777e95bfadfeabf56ef33ad26af/Message/MessageTemp/0c9d95d056c532e28592a9c60e764a85/File/yobtc-task-service_1.1.0.jar!/BOOT-INF/lib/yobtc-framework-1.0.6.RELEASE.jar!/com/yobtc/framework/util/Base64.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */