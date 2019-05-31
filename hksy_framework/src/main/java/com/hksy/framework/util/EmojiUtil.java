 package com.hksy.framework.util;

 import java.util.regex.Matcher;
 import java.util.regex.Pattern;

 public class EmojiUtil
 {
   public static boolean containsEmoji(String source)
   {
/* 10 */     int len = source.length();
/* 11 */     boolean isEmoji = false;
/* 12 */     for (int i = 0; i < len; i++) {
/* 13 */       char hs = source.charAt(i);
/* 14 */       if ((55296 <= hs) && (hs <= 56319)) {
/* 15 */         if (source.length() > 1) {
/* 16 */           char ls = source.charAt(i + 1);
/* 17 */           int uc = (hs - 55296) * 1024 + (ls - 56320) + 65536;
/* 18 */           if ((118784 <= uc) && (uc <= 128895)) {
/* 19 */             return true;
           }
         }
       }
       else {
/* 24 */         if (('℀' <= hs) && (hs <= '⟿') && (hs != '☻'))
/* 25 */           return true;
/* 26 */         if (('⬅' <= hs) && (hs <= '⬇'))
/* 27 */           return true;
/* 28 */         if (('⤴' <= hs) && (hs <= '⤵'))
/* 29 */           return true;
/* 30 */         if (('㊗' <= hs) && (hs <= '㊙'))
/* 31 */           return true;
/* 32 */         if ((hs == '©') || (hs == '®') || (hs == '〽') || (hs == '〰') || (hs == '⭕') || (hs == '⬜') || (hs == '⬛') || (hs == '⭐') || (hs == '⌚'))
         {

/* 35 */           return true;
         }
/* 37 */         if ((!isEmoji) && (source.length() > 1) && (i < source.length() - 1)) {
/* 38 */           char ls = source.charAt(i + 1);
/* 39 */           if (ls == '⃣') {
/* 40 */             return true;
           }
         }
       }
     }
/* 45 */     return isEmoji;
   }

   private static boolean isEmojiCharacter(char codePoint)
   {
/* 50 */     return (codePoint == 0) || (codePoint == '\t') || (codePoint == '\n') || (codePoint == '\r') || ((codePoint >= ' ') && (codePoint <= 55295)) || ((codePoint >= 57344) && (codePoint <= 65533)) || ((codePoint >= 65536) && (codePoint <= 1114111));
   }











   public static String filterEmoji(String source)
   {
/* 65 */     if (org.apache.commons.lang3.StringUtils.isBlank(source)) {
/* 66 */       return source;
     }
/* 68 */     StringBuilder buf = null;
/* 69 */     int len = source.length();
/* 70 */     for (int i = 0; i < len; i++) {
/* 71 */       char codePoint = source.charAt(i);
/* 72 */       if (isEmojiCharacter(codePoint)) {
/* 73 */         if (buf == null) {
/* 74 */           buf = new StringBuilder(source.length());
         }
/* 76 */         buf.append(codePoint);
       }
     }
/* 79 */     if (buf == null) {
/* 80 */       return source;
     }
/* 82 */     if (buf.length() == len) {
/* 83 */       buf = null;
/* 84 */       return source;
     }
/* 86 */     return buf.toString();
   }


   public static void main(String[] args)
   {
/* 92 */     String regEx = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
/* 93 */     Pattern p = Pattern.compile(regEx);
/* 94 */     Matcher m = p.matcher("12");
/* 95 */     System.out.println(m.find());
   }
 }


/* Location:              /Users/Edanel/Library/Application Support/com.tencent.xinWeChat/2.0b4.0.9/98cec777e95bfadfeabf56ef33ad26af/Message/MessageTemp/0c9d95d056c532e28592a9c60e764a85/File/yobtc-task-service_1.1.0.jar!/BOOT-INF/lib/yobtc-framework-1.0.6.RELEASE.jar!/com/yobtc/framework/util/EmojiUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */