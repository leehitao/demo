package com.xrt.bzj.common.excel;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * @author Lee
 * @version 1.0
 * @project demo
 * @package com.xrt.bzj.common.excel
 * @date 2021/5/23 17:25
 */
public class ExcelHelper {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    public HSSFWorkbook fileExport(FileContainer fileContainer) {

        // 创建excel
        HSSFWorkbook wk = new HSSFWorkbook();

        // 创建一张工作表
        HSSFSheet sheet = wk.createSheet();

        sheet.setColumnWidth(0, 5000);
        HSSFRow row = sheet.createRow(0);
        // 创建第一行的第一个单元格
        // 想单元格写值
        List<FileHead> heads = fileContainer.getHeads();
        HSSFCell cell;
        for (int i = 0; i < heads.size(); i++) {
            cell = row.createCell(i);
            cell.setCellValue(heads.get(i).getName());
        }

        List dataList = fileContainer.getData();
        // 创建行数据
        for (short i = 0; i < dataList.size(); i++) {
            int index = 0;
            row = sheet.createRow(i + 1);
            Object o = dataList.get(i);
            Class aClass = o.getClass();
            for (FileHead head : heads) {

                try {
                    String methodName = captureName(head.getKey());
                    methodName = "get" + methodName;
                    Method method = aClass.getDeclaredMethod(methodName, null);
                    Object value = method.invoke(o, null);
                    row.createCell(index).setCellValue(value + "");
                    index++;
                } catch (Exception e) {
                    logger.error("导出发生错误", e);
                }
            }
        }

        try {
            wk.write(new FileOutputStream(new File(fileContainer.getFilePath()+File.separator+fileContainer.getFileName())));
            wk.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return wk;
    }

    public static String captureName(String name) {
        char[] cs = name.toCharArray();
        cs[0] -= 32;
        return String.valueOf(cs);

    }
}
