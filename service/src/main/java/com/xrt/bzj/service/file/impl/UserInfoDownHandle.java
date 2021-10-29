package com.xrt.bzj.service.file.impl;

import com.xrt.bzj.common.excel.ExcelHelper;
import com.xrt.bzj.common.excel.FileContainer;
import com.xrt.bzj.common.excel.FileHead;
import com.xrt.bzj.dao.po.AverageUser;
import com.xrt.bzj.service.mchuser.AverageUserService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author Lee
 * @version 1.0
 * @project demo
 * @package com.xrt.bzj.service.file.impl
 * @date 2021/5/23 23:36
 */
@Service
public class UserInfoDownHandle {

    @Autowired
    AverageUserService averageUserService;

    public HSSFWorkbook createUserWorkBook(String filePath) {
        HSSFWorkbook wk;List<AverageUser> list = averageUserService.findAllAverageUser();
        FileContainer<AverageUser> averageUserFileContainer = new FileContainer<>();
        List<FileHead> headList = new ArrayList<>();
        headList.add(new FileHead("id","id"));
        headList.add(new FileHead("账号","account"));
        headList.add(new FileHead("用户名","name"));
        headList.add(new FileHead("注册时间","createDate"));
        headList.add(new FileHead("修改时间","updateDate"));
        headList.add(new FileHead("头像","headUrl"));
        averageUserFileContainer.setData(list);
        averageUserFileContainer.setHeads(headList);
        averageUserFileContainer.setFilePath(filePath);
        averageUserFileContainer.setFileName(UUID.randomUUID()+"-用户表.xls");
        ExcelHelper excelHelpper = new ExcelHelper();
        wk = excelHelpper.fileExport(averageUserFileContainer);
        return wk;
    }
}
