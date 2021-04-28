package com.heima.data_platform;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * @author ：wt
 * @date ：Created in 2021-04-19 9:46
 * @description：
 * @modified By：wt
 */
public class ServletInitializer extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(DataPlatformApplication.class);
    }
}
