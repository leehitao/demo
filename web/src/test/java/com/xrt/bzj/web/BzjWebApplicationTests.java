package com.xrt.bzj.web;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

@SpringBootTest
public class BzjWebApplicationTests {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    RedisTemplate redisTemplate;

    @Test
    public void contextLoads() {
        logger.info("asdfaf");
        logger.error("asdfaf");
        logger.debug("asdfaf");
    }

    @Test
    public void fun() {
        redisTemplate.opsForValue().set("Hi", "girl");
        String aaaa = (String)redisTemplate.opsForValue().get("Hi");
        System.out.println(String.valueOf(aaaa));

    }

    @Test
    public void fun1() throws IOException {

        String rootPath = "file/test";
        File file = new File(rootPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        for (int i = 0; i < 3; i++) {
            String childPath = rootPath + "/" + i;
            File chileFile = new File(childPath);
            if (!chileFile.exists()) {
                chileFile.mkdirs();
            }
            String filePath = "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Ffile04.16sucai.com%2Fd%2Ffile%2F2015%2F0504%2Fcf57eb1952ac4a98f150ed8b734ad6c2.jpg&refer=http%3A%2F%2Ffile04.16sucai.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1663942426&t=dbf207d9025df8de07434f1aa0f444b9";
            URL url = new URL(filePath);
            URLConnection con = url.openConnection();
            InputStream fis = con.getInputStream();
            int len;
            byte[] buffer = new byte[1024];
            FileOutputStream fos = new FileOutputStream(childPath + "/" + "123.jpg");
            while ((len = fis.read(buffer)) != -1) {
                fos.write(buffer, 0, len);
            }
            fos.flush();
            fis.close();
            fos.close();
            System.out.println("success" + i);
        }


    }


}
