package com.heima.data_platform.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author ：wt
 * @date ：Created in 2021-04-23 10:40
 * @description：发送http请求
 * @modified By：wt
 */
@Configuration
public class Configs {

    /**
     * 注入restTemplate,用于进行http请求
     * @return RestTemplate对象
     */
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
