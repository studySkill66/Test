 package com.hksy.framework.util;

 import java.nio.charset.Charset;
 import java.security.MessageDigest;
 import java.security.NoSuchAlgorithmException;

 public class MD5Util {
     public static String md5(String string) {
         if (string == null) {
             return null;
         }
         try {
             MessageDigest messageDigest = MessageDigest.getInstance("MD5");
             messageDigest.reset();
             messageDigest.update(string.getBytes(Charset.forName("UTF8")));
             byte[] resultByte = messageDigest.digest();
             return new String(org.apache.commons.codec.binary.Hex.encodeHex(resultByte));
         } catch (NoSuchAlgorithmException e) {
             e.printStackTrace();
         }
         return null;
     }
 }