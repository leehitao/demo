package com.xrt.bzj.web;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
class BzjWebApplicationTests {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    RedisTemplate redisTemplate;

    @Test
    void contextLoads() {
        logger.info("asdfaf");
        logger.error("asdfaf");
        logger.debug("asdfaf");
    }

    @Test
    void fun(){
        redisTemplate.opsForValue().set("aaaa", "你好水1234");
        String aaaa = (String)redisTemplate.opsForValue().get("aaaa");
        System.out.println(String.valueOf(aaaa));

    }


}
