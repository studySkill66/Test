 package com.hksy.framework.util.jwt;

 public class AccessToken {
   private String access_token;
   private String token_type;
   private long expires_in;

   public String getAccess_token() {
/*  9 */     return this.access_token;
   }

   public void setAccess_token(String access_token) {
/* 13 */     this.access_token = access_token;
   }

   public String getToken_type() {
/* 17 */     return this.token_type;
   }

   public void setToken_type(String token_type) {
/* 21 */     this.token_type = token_type;
   }

   public long getExpires_in() {
/* 25 */     return this.expires_in;
   }

   public void setExpires_in(long expires_in) {
/* 29 */     this.expires_in = expires_in;
   }
 }


/* Location:              /Users/Edanel/Library/Application Support/com.tencent.xinWeChat/2.0b4.0.9/98cec777e95bfadfeabf56ef33ad26af/Message/MessageTemp/0c9d95d056c532e28592a9c60e764a85/File/yobtc-task-service_1.1.0.jar!/BOOT-INF/lib/yobtc-framework-1.0.6.RELEASE.jar!/com/yobtc/framework/util/jwt/AccessToken.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */