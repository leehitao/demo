package com.xrt.bzj.service.impl;

import com.xrt.bzj.common.util.ZipUtil;
import com.xrt.bzj.service.FileDownloadService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.zip.ZipOutputStream;

/**
 * @author Lee
 * @version 1.0
 * @project demo
 * @package com.xrt.bzj.service.file.impl
 * @date 2021/5/23 19:31
 */
@Service
public class FileDownloadServiceImpl implements FileDownloadService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    UserInfoDownHandle userInfoDownHandle;

    @Resource(name ="serviceExecutor")
    ThreadPoolTaskExecutor asyncServiceExecutor;

    @Override
    public void downloadUserInfo(HttpServletResponse response) {
        String filePath = "D:\\temp" + File.separator + UUID.randomUUID();
        File dir = new File(filePath);
        dir.mkdir();
        HSSFWorkbook wk = userInfoDownHandle.createUserWorkBook(filePath);
        try {
            response.setContentType("application/octet-stream");
            response.setHeader("Content-disposition", "attachment;filename=用户表.xls");//默认Excel名称
            response.flushBuffer();
            wk.write(response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                wk.close();
            } catch (IOException e) {
                logger.error("导出异常",e);
            }
        }
    }

    @Override
    public void downloadUserInfoMix(HttpServletResponse response) {
        String filePath = "D:\\temp" + File.separator + UUID.randomUUID();
        File dir = new File(filePath);
        dir.mkdir();
        Thread[] threads = new Thread[5];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                userInfoDownHandle.createUserWorkBook(filePath);
                System.out.println(Thread.currentThread().getName() + "输出完毕");
            }, "thread" + i);
            threads[i].start();
        }

        for (int i = 0; i < threads.length; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("===全都完成了===");
        ZipOutputStream zipOutputStream = null;
        response.setContentType("APPLICATION/OCTET-STREAM");
        try {
            String zipName = "excelfiles_" + System.currentTimeMillis() + ".zip";
            response.setHeader("Content-Disposition", "attachment; filename=" + new String(zipName.getBytes("GBK"), "ISO-8859-1"));
        } catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
        }
        try {
            zipOutputStream = new ZipOutputStream(response.getOutputStream());
            File[] listFiles = dir.listFiles();
            for (int i = 0; i < listFiles.length; i++) {
                ZipUtil.doCompress(listFiles[i], zipOutputStream);
                response.flushBuffer();
            }
        } catch (IOException e) {
            logger.error("压缩失败",e);
        }finally {
            try {
                if (null != zipOutputStream) {
                    zipOutputStream.close();
                }
            } catch (IOException e) {
                logger.error("压缩关流失败", e);
            }
        }

    }

    @Override
    public void downloadUserInfoMixByExcutor(HttpServletResponse response) {
        String filePath = "D:\\temp" + File.separator + UUID.randomUUID();
        CountDownLatch countDownLatch = new CountDownLatch(5);
        File dir = new File(filePath);
        dir.mkdir();
        for (int i = 0; i < 5; i++) {
            asyncServiceExecutor.submit(new Thread(() -> {
                userInfoDownHandle.createUserWorkBook(filePath);
                System.out.println(Thread.currentThread().getName() + "输出完毕");
                countDownLatch.countDown();
            }, "thread" + i));
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("===全都完成了===");
        ZipOutputStream zipOutputStream = null;
        response.setContentType("APPLICATION/OCTET-STREAM");
        try {
            String zipName = "excelfiles_" + System.currentTimeMillis() + ".zip";
            response.setHeader("Content-Disposition", "attachment; filename=" + new String(zipName.getBytes("GBK"), "ISO-8859-1"));
        } catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
        }
        try {
            zipOutputStream = new ZipOutputStream(response.getOutputStream());
            File[] listFiles = dir.listFiles();
            for (int i = 0; i < listFiles.length; i++) {
                ZipUtil.doCompress(listFiles[i], zipOutputStream);
                response.flushBuffer();
            }
        } catch (IOException e) {
            logger.error("压缩失败", e);
        } finally {
            try {
                if (null != zipOutputStream) {
                    zipOutputStream.close();
                }
            } catch (IOException e) {
                logger.error("压缩关流失败", e);
            }
        }

    }

}
