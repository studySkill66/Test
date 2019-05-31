 package com.hksy.framework.common;

 public class BusinessException
   extends RuntimeException
 {
   public BusinessException() {}

   public BusinessException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace)
   {
/* 10 */     super(message, cause, enableSuppression, writableStackTrace);
   }

   public BusinessException(String message, Throwable cause)
   {
/* 15 */     super(message, cause);
   }

   public BusinessException(String message)
   {
/* 20 */     super(message);
   }

   public BusinessException(Throwable cause)
   {
/* 25 */     super(cause);
   }
 }


/* Location:              /Users/Edanel/Library/Application Support/com.tencent.xinWeChat/2.0b4.0.9/98cec777e95bfadfeabf56ef33ad26af/Message/MessageTemp/0c9d95d056c532e28592a9c60e764a85/File/yobtc-task-service_1.1.0.jar!/BOOT-INF/lib/yobtc-framework-1.0.6.RELEASE.jar!/com/yobtc/framework/common/BusinessException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */