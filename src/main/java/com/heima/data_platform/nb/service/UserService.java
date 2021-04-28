package com.heima.data_platform.nb.service;

import cn.hutool.core.util.IdUtil;
import com.heima.data_platform.nb.dao.UserMapper;
import com.heima.data_platform.nb.common.User;
import com.heima.data_platform.util.FormatUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.concurrent.TimeUnit;

/**
 * @author ：wt
 * @date ：Created in 2021-04-07 9:27
 * @description：
 * @modified By：wt
 */
@Service
@Slf4j
public class UserService {

    private final UserMapper userMapper;
    private final RedisTemplate redisTemplate;

    @Autowired
    public UserService(UserMapper userMapper, RedisTemplate redisTemplate) {
        this.userMapper = userMapper;
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
     * 用户登录
     * @param username 用户名
     * @param password 密码
     * @return
     */
    public User getUser(String username, String password){
        //已经登录
        /*try {
            boolean flag = (Boolean) redisTemplate.boundValueOps(username).get();
            if (flag){
                return new User();
            }
        }catch (Exception e){
            log.warn("没有用户在线缓存信息>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        }*/
        //没有登录
        User user = userMapper.getUserInfo(username, password);
        if (user !=null){
            String key = username;
            String token = IdUtil.simpleUUID();
            user.setToken(token);
            user.setPassword("");
            //格式化module列表
            try {
                user.setModuleList(FormatUtil.string2Int(user.getModule(),","));
            }catch (Exception e){
                log.info(this.getClass()+">>>>>格式转换异常");
                e.printStackTrace();
            }
            redisTemplate.boundValueOps(token).set(user,60*24, TimeUnit.MINUTES);
            redisTemplate.boundValueOps(username).set(true,60*24, TimeUnit.MINUTES);

        }
        return user;
    }

    /**
     * 用户退出
     * @param token 用户信息
     */
    public void logout(String token) {
        try {
            User user = (User) redisTemplate.boundValueOps(token).get();

            redisTemplate.boundValueOps(token).set(null,1, TimeUnit.SECONDS);
            redisTemplate.boundValueOps(user.getUsername()).set(null,1, TimeUnit.SECONDS);
        }catch (Exception e){
            log.warn(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>无用户在线退出");
        }


    }
}
