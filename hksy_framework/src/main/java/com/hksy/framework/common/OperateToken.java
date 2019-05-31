 package com.hksy.framework.common;

 import com.hksy.framework.cache.redis.RedisCache;
 import java.util.UUID;
 import javax.annotation.Resource;
 import org.springframework.stereotype.Component;




 @Component
 public class OperateToken
 {
   @Resource
 RedisCache cache;

   public String createToken()
   {
     String uuid = UUID.randomUUID().toString();
     String result = this.cache.set(uuid, "0", 30);
     if ("OK".equalsIgnoreCase(result)) {
       return uuid;
     }
     return null;
   }






   public String createToken(int expire)
   {
/* 34 */     String uuid = UUID.randomUUID().toString();
/* 35 */     String result = this.cache.set(uuid, "0", expire);
/* 36 */     if ("OK".equalsIgnoreCase(result)) {
/* 37 */       return uuid;
     }
/* 39 */     return null;
   }






   public boolean checkToken(String token)
   {
/* 49 */     Long result = this.cache.del(token);
/* 50 */     result = Long.valueOf(result == null ? 0L : result.longValue());

/* 52 */     if (result.longValue() > 0L) {
/* 53 */       return true;
     }
/* 55 */     return false;
   }
 }


/* Location:              /Users/Edanel/Library/Application Support/com.tencent.xinWeChat/2.0b4.0.9/98cec777e95bfadfeabf56ef33ad26af/Message/MessageTemp/0c9d95d056c532e28592a9c60e764a85/File/yobtc-task-service_1.1.0.jar!/BOOT-INF/lib/yobtc-framework-1.0.6.RELEASE.jar!/com/yobtc/framework/common/OperateToken.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */