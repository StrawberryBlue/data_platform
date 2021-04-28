package com.heima.data_platform.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author ：wt
 * @date ：Created in 2021-04-07 9:15
 * @description：解决跨域问题配置类
 * @modified By：wt
 */
@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                //设置允许访问来源url，为*时使用allowedOriginPatterns，有详细url时使用addAllowedOrigin("http://www.aimaonline.cn/")
                .allowedOriginPatterns("*")
                //为true时允许cookie
                .allowCredentials(true)
                .allowedMethods("GET", "POST", "DELETE", "PUT")
                .maxAge(3600);
    }
}
