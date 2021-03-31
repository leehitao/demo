package com.xrt.bzj.web;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BzjWebApplicationTests {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Test
    void contextLoads() {
        logger.info("asdfaf");
        logger.error("asdfaf");
        logger.debug("asdfaf");

    }

}
