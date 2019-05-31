 package com.hksy.framework.cache.redis.config;

 import java.util.ArrayList;
 import java.util.List;
 import org.apache.commons.lang3.StringUtils;
 import org.springframework.beans.factory.annotation.Value;
 import org.springframework.cache.annotation.CachingConfigurerSupport;
 import org.springframework.cache.annotation.EnableCaching;
 import org.springframework.context.annotation.Bean;
 import org.springframework.context.annotation.Configuration;
 import redis.clients.jedis.JedisPoolConfig;
 import redis.clients.jedis.JedisShardInfo;
 import redis.clients.jedis.ShardedJedisPool;

 @Configuration
 @EnableCaching
 public class RedisConfig extends CachingConfigurerSupport
 {
   @Value("${redis.host}")
   private String host;
   @Value("${redis.port}")
   private Integer port;
   @Value("${redis.pwd}")
   private String pwd;
   @Value("${redis.maxIdle}")
   private Integer maxIdle;
   @Value("${redis.maxTotal}")
   private Integer maxTotal;
   @Value("${redis.maxWaitMillis}")
   private Long maxWaitMillis;
   @Value("${redis.testOnBorrow}")
   private Boolean testOnBorrow;
   @Value("${redis.testOnReturn}")
   private Boolean testOnReturn;

   @Bean
   public ShardedJedisPool redisPoolFactory()
   {
/* 39 */     JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
/* 40 */     jedisPoolConfig.setMaxIdle(this.maxIdle.intValue());
/* 41 */     jedisPoolConfig.setMaxTotal(this.maxTotal.intValue());
/* 42 */     jedisPoolConfig.setMaxWaitMillis(this.maxWaitMillis.longValue());
/* 43 */     jedisPoolConfig.setTestOnBorrow(this.testOnBorrow.booleanValue());
/* 44 */     jedisPoolConfig.setTestOnReturn(this.testOnReturn.booleanValue());

/* 46 */     List<JedisShardInfo> list = new ArrayList();
/* 47 */     JedisShardInfo jedisShardInfo = new JedisShardInfo(this.host, this.port.intValue());
/* 48 */     if (StringUtils.isNotBlank(this.pwd)) {
/* 49 */       jedisShardInfo.setPassword(this.pwd);
     }
/* 51 */     list.add(jedisShardInfo);

/* 53 */     ShardedJedisPool sjp = new ShardedJedisPool(jedisPoolConfig, list);

/* 55 */     return sjp;
   }
 }


/* Location:              /Users/Edanel/Library/Application Support/com.tencent.xinWeChat/2.0b4.0.9/98cec777e95bfadfeabf56ef33ad26af/Message/MessageTemp/0c9d95d056c532e28592a9c60e764a85/File/yobtc-task-service_1.1.0.jar!/BOOT-INF/lib/yobtc-framework-1.0.6.RELEASE.jar!/com/yobtc/framework/cache/redis/config/RedisConfig.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */