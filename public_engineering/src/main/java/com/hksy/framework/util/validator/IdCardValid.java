 package com.hksy.framework.util.validator;

 import org.apache.commons.lang3.StringUtils;




 public class IdCardValid
 {
   public static boolean idCardNoValid(String idCardNo)
   {
/* 12 */     if (StringUtils.isBlank(idCardNo)) {
/* 13 */       return false;
     }
/* 15 */     if (idCardNo.length() != 18) {
/* 16 */       return false;
     }


/* 20 */     char[] c = idCardNo.toCharArray();
/* 21 */     int[] coefficient = { 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2 };
/* 22 */     char[] valiCode = { '1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2' };

/* 24 */     int sum = 0;

/* 26 */     for (int i = 0; i < coefficient.length; i++) {
/* 27 */       sum += Integer.valueOf(c[i] + "").intValue() * coefficient[i];
     }


/* 31 */     int result = sum % 11;

/* 33 */     char code = valiCode[result];

/* 35 */     if ((c[(c.length - 1)] == 'x' ? 'X' : c[(c.length - 1)]) == code) {
/* 36 */       return true;
     }
/* 38 */     return false;
   }
 }


/* Location:              /Users/Edanel/Library/Application Support/com.tencent.xinWeChat/2.0b4.0.9/98cec777e95bfadfeabf56ef33ad26af/Message/MessageTemp/0c9d95d056c532e28592a9c60e764a85/File/yobtc-task-service_1.1.0.jar!/BOOT-INF/lib/yobtc-framework-1.0.6.RELEASE.jar!/com/yobtc/framework/util/validator/IdCardValid.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */