 package com.hksy.framework.util;

 import java.math.BigDecimal;


 public class MathUtil
 {
   public static BigDecimal add(BigDecimal v1, BigDecimal v2)
   {
/* 10 */     return v1.add(v2);
   }



   public static BigDecimal subtract(BigDecimal v1, BigDecimal v2)
   {
/* 17 */     return v1.subtract(v2);
   }



   public static BigDecimal multiply(BigDecimal v1, BigDecimal v2)
   {
/* 24 */     return v1.multiply(v2);
   }




   public static BigDecimal divide(BigDecimal v1, BigDecimal v2)
   {
/* 32 */     int DEF_DIV_SCALE = 10;
/* 33 */     return v1.divide(v2, DEF_DIV_SCALE, 5);
   }


   public static BigDecimal divideBig(BigDecimal v1, BigDecimal v2)
   {
/* 41 */     int DEF_DIV_SCALE = 10;
/* 42 */     return v1.divide(v2, DEF_DIV_SCALE, 5);
   }



   public static BigDecimal accuracyRoundHalfUp(BigDecimal v, int scale)
   {
/* 49 */     if (scale < 0) {
/* 50 */       throw new IllegalArgumentException("The scale must be a positive integer or zero");
     }
/* 52 */     BigDecimal one = new BigDecimal("1");
/* 53 */     return v.divide(one, scale, 4);
   }





            /**
             * v以{@scale}为精度取值<br/>
             * @param v 待优化值
             * @param scale 小数位数
             * @return
             */
   public static BigDecimal accuracyRoundDown(BigDecimal v, int scale)
   {
     if (scale < 0) {
       throw new IllegalArgumentException("The scale must be a positive integer or zero");
     }
     if(v.compareTo(new BigDecimal(0))==0){
         return v;
     }
     BigDecimal one = new BigDecimal("1");
     return v.divide(one, scale, 1);
   }
 }
