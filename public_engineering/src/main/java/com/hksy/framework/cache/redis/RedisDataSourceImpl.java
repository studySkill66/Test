
package com.hksy.framework.cache.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.*;

@Repository("redisDataSource")
public class RedisDataSourceImpl implements RedisDataSource {
    private static final Logger log = LoggerFactory.getLogger(RedisDataSourceImpl.class);
    @Autowired
    ShardedJedisPool shardedJedisPool;

    public ShardedJedis getRedisClient() {
        try {
            return this.shardedJedisPool.getResource();
        } catch (Exception e) {
            log.error("getRedisClent error", e);
        }
        return null;
    }

    public void returnResource(ShardedJedis shardedJedis) {
        this.shardedJedisPool.returnResource(shardedJedis);
    }

    public void returnResource(ShardedJedis shardedJedis, boolean broken) {
        if (broken) {
            this.shardedJedisPool.returnBrokenResource(shardedJedis);
        } else {
            this.shardedJedisPool.returnResource(shardedJedis);
        }
    }
}


/* Location:              /Users/Edanel/Library/Application Support/com.tencent.xinWeChat/2.0b4.0.9/98cec777e95bfadfeabf56ef33ad26af/Message/MessageTemp/0c9d95d056c532e28592a9c60e764a85/File/yobtc-task-service_1.1.0.jar!/BOOT-INF/lib/yobtc-framework-1.0.6.RELEASE.jar!/com/yobtc/framework/cache/redis/RedisDataSourceImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */