package com.hksy.framework.cache.redis;

import redis.clients.jedis.ShardedJedis;

public abstract interface RedisDataSource
{
  public abstract ShardedJedis getRedisClient();
  
  public abstract void returnResource(ShardedJedis paramShardedJedis);
  
  public abstract void returnResource(ShardedJedis paramShardedJedis, boolean paramBoolean);
}


/* Location:              /Users/Edanel/Library/Application Support/com.tencent.xinWeChat/2.0b4.0.9/98cec777e95bfadfeabf56ef33ad26af/Message/MessageTemp/0c9d95d056c532e28592a9c60e764a85/File/yobtc-task-service_1.1.0.jar!/BOOT-INF/lib/yobtc-framework-1.0.6.RELEASE.jar!/com/yobtc/framework/cache/redis/RedisDataSource.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */