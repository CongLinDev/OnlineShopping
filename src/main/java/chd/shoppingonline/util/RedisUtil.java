package chd.shoppingonline.util;
/*
 * @ClassName RedisUtil
 * @Author 从林
 * @Date 2019-03-30 20:10
 * @Description Redis工具类
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class RedisUtil {
    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    /**
     * 存键值对
     * @param key
     * @param value
     */
    public void set(String key,String value){
        ValueOperations<String,String> valueOperations = redisTemplate.opsForValue();
        valueOperations.set(key,value);
    }

    public long getExpireTime(String key){
        return redisTemplate.getExpire(key);
    }

    /**
     * 存储键值对，并且设置有效期
     * @param key
     * @param value
     * @param time
     */
    public void setAndTime(String key,String value,long time){
        ValueOperations<String,String> valueOperations = redisTemplate.opsForValue();
        valueOperations.set(key,value);
        redisTemplate.expire(key,time, TimeUnit.SECONDS);
    }

    /**
     * 读取redis
     * @param key
     * @return
     */
    public String get( String key) {
        ValueOperations<String, String> operations = redisTemplate.opsForValue();
        return operations.get(key);
    }
}
