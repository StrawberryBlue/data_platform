package com.heima.data_platform.util;

import com.heima.data_platform.nb.common.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.TimeUnit;

/**
 * @author ：wt
 * @date ：Created in 2021-04-07 12:34
 * @description：校验
 * @modified By：wt
 */
@Component
public class CheckUtil {

    private final RedisTemplate redisTemplate;
    @Autowired
    public CheckUtil(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * 解决rdm乱码问题
     */
    @PostConstruct
    private void init() {
        redisTemplate.setKeySerializer(RedisSerializer.string());
        redisTemplate.setHashKeySerializer(RedisSerializer.string());
        redisTemplate.setValueSerializer(RedisSerializer.json());
    }


    /**
     * 校验登录token是否存在
     * @param token
     * @return
     */
    public boolean checkToken(String token){
        User user =(User) redisTemplate.boundValueOps(token).get();
        if (user !=null&&token.equals(user.getToken())){
            redisTemplate.boundValueOps(token).set(user,60*24, TimeUnit.MINUTES);
            return true;
        }
        return false;
    }

    /**
     * 获取缓存中的用户信息
     * @param token
     * @return
     */
    public User getRedisUser(String token){
        return (User) redisTemplate.boundValueOps(token).get();
    }
}
