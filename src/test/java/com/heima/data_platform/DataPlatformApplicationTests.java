package com.heima.data_platform;

import com.heima.data_platform.emp.dao.SystemMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DataPlatformApplicationTests {
    @Autowired
    SystemMapper systemMapper;

    @Test
    void contextLoads() {

    }

}
