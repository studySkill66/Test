 package com.hksy.framework.util.jwt;

 import com.hksy.framework.util.Base64;
 import io.jsonwebtoken.Claims;
 import io.jsonwebtoken.Jws;
 import io.jsonwebtoken.JwtBuilder;
 import io.jsonwebtoken.JwtParser;
 import io.jsonwebtoken.Jwts;
 import io.jsonwebtoken.SignatureAlgorithm;
 import java.util.Date;
 import javax.crypto.SecretKey;
 import javax.crypto.spec.SecretKeySpec;





 public class JwtHelper
 {
   private static SecretKey generalKey()
   {
/* 22 */     String stringKey = "Mpk4ZjZim2Q0Nj0xZDMpM2NhZlU0ZTgzMrYyN2IpZjY";
/* 23 */     byte[] encodedKey = Base64.encode(stringKey.getBytes()).getBytes();
/* 24 */     SecretKey key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
/* 25 */     return key;
   }

   public static Claims parseJWT(String jsonWebToken) {
     try {
/* 30 */       SecretKey key = generalKey();
/* 31 */       return (Claims)Jwts.parser().setSigningKey(key).parseClaimsJws(jsonWebToken).getBody();
     }
     catch (Exception ex) {}
/* 34 */     return null;
   }

   public static String createJWT(String name, String userId)
   {
/* 39 */     SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

/* 41 */     long nowMillis = System.currentTimeMillis();
/* 42 */     Date now = new Date(nowMillis);


/* 45 */     SecretKey signingKey = generalKey();




/* 50 */     JwtBuilder builder = Jwts.builder().setHeaderParam("typ", "JWT").claim("unique_name", name).claim("userid", userId).setIssuer("pc.api.yobtc.com").setAudience("098f6bcd4621d373cade4e832627b4f6").setIssuedAt(now).signWith(signatureAlgorithm, signingKey);

/* 52 */     if (JwtConstant.JWT_REFRESH_TTL >= 0L) {
/* 53 */       long expMillis = nowMillis + JwtConstant.JWT_REFRESH_TTL;
/* 54 */       Date exp = new Date(expMillis);
/* 55 */       builder.setExpiration(exp).setNotBefore(now);
     }


/* 59 */     return builder.compact();
   }
 }


/* Location:              /Users/Edanel/Library/Application Support/com.tencent.xinWeChat/2.0b4.0.9/98cec777e95bfadfeabf56ef33ad26af/Message/MessageTemp/0c9d95d056c532e28592a9c60e764a85/File/yobtc-task-service_1.1.0.jar!/BOOT-INF/lib/yobtc-framework-1.0.6.RELEASE.jar!/com/yobtc/framework/util/jwt/JwtHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */